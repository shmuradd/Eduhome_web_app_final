package org.sb.eduhome2.controllers;

import org.sb.eduhome2.dtos.blogs.BlogCreateDto;
import org.sb.eduhome2.dtos.blogs.BlogDetailDto;
import org.sb.eduhome2.dtos.blogs.BlogDto;
import org.sb.eduhome2.dtos.blogs.BlogUpdateDto;
import org.sb.eduhome2.dtos.course.CourseDetailDto;
import org.sb.eduhome2.dtos.event.EventDto;
import org.sb.eduhome2.models.Blog;
import org.sb.eduhome2.repositories.BlogRepository;
import org.sb.eduhome2.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Controller
public class BlogsController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogRepository blogRepository;
    @GetMapping("/blogs")
    public String index(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "6") int size)
    {
        Page<BlogDto> blogsPage= blogService.getBlogs(PageRequest.of(page,size));

        if (blogsPage.hasContent())
        {
            model.addAttribute("blogs", blogsPage.getContent());

        }
        else {
            model.addAttribute("blogs", Collections.emptyList());

        }
        model.addAttribute("blogsPage", blogsPage);


        return "blog/blogs";
    }


    @GetMapping("/blogs/{id}")
    public String detail(@PathVariable int id, Model model)
    {
        List<BlogDto> blogDto=blogService.getHomeBlogs();
        model.addAttribute("blogs",blogDto);

        BlogDetailDto blogDetailDto=blogService.blogDetail(id);
        model.addAttribute("blog", blogDetailDto);
        return "blog/blog-details";
    }

    // Admin panel
    @GetMapping("/admin/blogs")
    public String blogs(Model model) {
        List<Blog> blogs = blogRepository.findAll().stream().toList();
        model.addAttribute("blogs", blogs);
        return "dashboard/blog/blogs";
    }

    @GetMapping("/admin/blogs/create")
    public String blogCreate() {
        return "dashboard/blog/blog-create";
    }

    @PostMapping("admin/blogs/create")
    public String blogCreate(@ModelAttribute BlogCreateDto blogCreateDto,
                             @RequestParam(value = "imageUrl", required = false) String imageUrl,
                             @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {
        if (imageUrl != null && !imageUrl.isEmpty()) {
            // If imageUrl is provided, assume it's already in the desired format
            blogCreateDto.setImage(imageUrl);
        } else if (imageFile != null && !imageFile.isEmpty()) {
            // If imageFile is uploaded, save it and set the image path accordingly
            String fileName = saveImageFile(imageFile);
            // Convert the saved full path to the desired format
            String imagePath = convertToRelativePath(fileName);
            blogCreateDto.setImage(imagePath);
        }
        blogService.addBlog(blogCreateDto);
        return "redirect:/admin/blogs";
    }

    private String saveImageFile(MultipartFile imageFile) throws IOException {
        String fileName = "C:\\Users\\User\\OneDrive - Baku Higher Oil School\\Desktop\\Eduhome2.2\\src\\main\\resources\\static\\img\\blog\\" + imageFile.getOriginalFilename();
        File file = new File(fileName);
        file.getParentFile().mkdirs();
        imageFile.transferTo(file);
        return fileName;
    }

    private String convertToRelativePath(String fullPath) {
        // Convert the full path to a relative path
        String relativePath = fullPath.replace("C:\\Users\\User\\OneDrive - Baku Higher Oil School\\Desktop\\Eduhome2.2\\src\\main\\resources\\static\\", "/");
        return relativePath.replace("\\", "/");
    }



    @GetMapping("/admin/blogs/update/{id}")
    public String blogUpdate(@PathVariable int id, Model model) {
        BlogUpdateDto blogUpdateDto = blogService.findUpdateBlog(id);
        model.addAttribute("blog", blogUpdateDto);
        return "dashboard/blog/update";
    }

    @PostMapping("/admin/blogs/update/{id}")
    public String updateBlog(@PathVariable int id, @ModelAttribute BlogUpdateDto blogUpdateDto) {
        blogService.updateBlog(id, blogUpdateDto);
        return "redirect:/admin/blogs";
    }

    @GetMapping("/admin/blogs/remove/{id}")
    public String blogRemove(@ModelAttribute @PathVariable int id) {
        blogService.deleteBlog(id);
        return "redirect:/admin/blogs";
    }

    @GetMapping("/admin/blogs/activity/{id}")
    public String activity(@PathVariable int id) {
        blogService.toggleBlogActivity(id);
        return "redirect:/admin/blogs";
    }

}
