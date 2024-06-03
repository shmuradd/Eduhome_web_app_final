package org.sb.eduhome2.controllers;

import org.sb.eduhome2.dtos.blogs.BlogDetailDto;
import org.sb.eduhome2.dtos.blogs.BlogDto;
import org.sb.eduhome2.dtos.course.CourseDetailDto;
import org.sb.eduhome2.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class BlogsController {
    @Autowired
    private BlogService blogService;

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
        BlogDetailDto blogDetailDto=blogService.blogDetail(id);
        model.addAttribute("blog", blogDetailDto);
        return "blog/blog-details";
    }

}
