package org.sb.eduhome2.controllers;

import org.sb.eduhome2.models.Blog;
import org.sb.eduhome2.models.Course;
import org.sb.eduhome2.models.Event;
import org.sb.eduhome2.models.Teacher;
import org.sb.eduhome2.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/keyword")
    public String search(@RequestParam("query") String query, Model model) {
        List<Teacher> teachers = searchService.searchTeachers(query);
        List<Blog> blogs = searchService.searchBlogs(query);
        List<Event> events = searchService.searchEvents(query);
        List<Course> courses = searchService.searchCourses(query);

        model.addAttribute("teachers", teachers);
        model.addAttribute("blogs", blogs);
        model.addAttribute("events", events);
        model.addAttribute("courses", courses);
        model.addAttribute("query", query);

        return "search/searchResults";
    }
}