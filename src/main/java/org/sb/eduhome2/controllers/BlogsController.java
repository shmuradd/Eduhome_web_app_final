package org.sb.eduhome2.controllers;

import org.sb.eduhome2.dtos.blogs.BlogCreateDto;
import org.sb.eduhome2.dtos.blogs.BlogDetailDto;
import org.sb.eduhome2.dtos.blogs.BlogDto;
import org.sb.eduhome2.dtos.blogs.BlogUpdateDto;
import org.sb.eduhome2.dtos.course.CourseDetailDto;
import org.sb.eduhome2.models.Blog;
import org.sb.eduhome2.repositories.BlogRepository;
import org.sb.eduhome2.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
public class BlogsController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogRepository blogRepository;
    @GetMapping("/blogs")
    public String index(Model model)
    {
        List<BlogDto> blogs = blogService.getBlogs();
        model.addAttribute("blogs", blogs);
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

    @PostMapping("/admin/blogs/create")
    public String blogCreate(@ModelAttribute BlogCreateDto blogCreateDto,
                             @RequestParam(value = "imageUrl", required = false) String imageUrl) throws IOException {
        blogCreateDto.setImage(imageUrl);
        blogService.addBlog(blogCreateDto);
        return "redirect:/admin/blogs";
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
