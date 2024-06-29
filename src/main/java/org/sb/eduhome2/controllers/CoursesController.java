package org.sb.eduhome2.controllers;

import org.sb.eduhome2.dtos.blogs.BlogCreateDto;
import org.sb.eduhome2.dtos.blogs.BlogDto;
import org.sb.eduhome2.dtos.course.CourseDetailDto;
import org.sb.eduhome2.dtos.course.CourseDto;
import org.sb.eduhome2.dtos.course.CourseCreateDto;
import org.sb.eduhome2.dtos.course.CourseUpdateDto;
import org.sb.eduhome2.models.Course;
import org.sb.eduhome2.models.Teacher;
import org.sb.eduhome2.repositories.CourseRepository;
import org.sb.eduhome2.services.BlogService;
import org.sb.eduhome2.services.CourseService;
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
import java.util.Comparator;
import java.util.List;

@Controller
public class CoursesController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/courses")
    public String index(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "6") int size)
    {
        Page<CourseDto> coursesPage= courseService.getCourses(PageRequest.of(page,size));

        if (coursesPage.hasContent())
        {
            model.addAttribute("courses", coursesPage.getContent());

        }
        else {
            model.addAttribute("courses", Collections.emptyList());

        }
        model.addAttribute("coursesPage", coursesPage);


        return "course/courses";
    }


    @GetMapping("/courses/{id}")
    public String detail(@PathVariable int id, Model model)
    {
        List<BlogDto> blogDto=blogService.getHomeBlogs();
        CourseDetailDto courseDetailDto=courseService.courseDetail(id);
        model.addAttribute("course", courseDetailDto);
        model.addAttribute("blogs",blogDto);

        return "course/course-detail";
    }
    
    
    //Admin panel
    @GetMapping("/admin/courses")
    public String courses(Model model) {
        List<Course> courses = courseRepository.findAll().stream()
                        .sorted(Comparator.comparingLong(Course::getId).reversed())
                        .toList();
        model.addAttribute("courses", courses);
        return "dashboard/course/courses";
    }
    @GetMapping("/admin/courses/create")
    public String courseCreate() {
        return "dashboard/course/course-create";
    }


    @PostMapping("admin/courses/create")
    public String blogCreate(@ModelAttribute CourseCreateDto courseCreateDto,
                             @RequestParam(value = "imageUrl", required = false) String imageUrl,
                             @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {
        if (imageUrl != null && !imageUrl.isEmpty()) {
            // If imageUrl is provided, assume it's already in the desired format
            courseCreateDto.setImage(imageUrl);
        } else if (imageFile != null && !imageFile.isEmpty()) {
            // If imageFile is uploaded, save it and set the image path accordingly
            String fileName = saveImageFile(imageFile);
            // Convert the saved full path to the desired format
            String imagePath = convertToRelativePath(fileName);
            courseCreateDto.setImage(imagePath);
        }
        courseService.addCourse(courseCreateDto);
        return "redirect:/admin/courses";
    }

    private String saveImageFile(MultipartFile imageFile) throws IOException {
        String fileName = "C:\\Users\\User\\OneDrive - Baku Higher Oil School\\Desktop\\Eduhome2.2\\src\\main\\resources\\static\\img\\course\\" + imageFile.getOriginalFilename();
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


    @GetMapping("/admin/courses/update/{id}")
    public String courseUpdate(@PathVariable int id, Model model) {
        CourseUpdateDto courseUpdateDto=courseService.findUpdateCourse(id);
        model.addAttribute("course", courseUpdateDto);
        return "dashboard/course/update";
    }

    @PostMapping("/admin/courses/update/{id}")
    public String updatecourse(@PathVariable int id, @ModelAttribute CourseUpdateDto courseUpdateDto) {
        courseService.updateCourse(id, courseUpdateDto);
        return "redirect:/admin/courses";
    }


    @GetMapping("/admin/courses/remove/{id}")
    public String courseRemove(@ModelAttribute @PathVariable int id) {
        courseService.removeCourse(id);
        return "redirect:/admin/courses";
    }
    @GetMapping("/admin/courses/activity/{id}")
    public String activity(@PathVariable int id)
    {
        courseService.activityCourse(id);
        return "redirect:/admin/courses";
    }



    @GetMapping("/search")
    public String searchCourses(@RequestParam("search") String search, Model model) {
        List<Course> searchResults = courseService.searchCourses(search); // Implement this method in CourseService
        model.addAttribute("courses", searchResults);
        return "course/searchResults"; // Thymeleaf template to display search results
    }
}
