package org.sb.eduhome2.controllers;

import org.sb.eduhome2.dtos.comments.CommentCreateDto;
import org.sb.eduhome2.dtos.comments.CommentUpdateDto;
import org.sb.eduhome2.dtos.course.CourseCreateDto;
import org.sb.eduhome2.models.StudentComments;
import org.sb.eduhome2.repositories.CommentRepository;
import org.sb.eduhome2.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.Comment;
import java.io.IOException;
import java.util.List;

@Controller
public class CommentsController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentService commentService;
    // Admin panel
    @GetMapping("/admin/comments")
    public String comments(Model model) {
        List<StudentComments> comments = commentRepository.findAll().stream().toList();
        model.addAttribute("comments", comments);
        return "dashboard/comment/comments";
    }

    @GetMapping("/admin/comments/create")
    public String commentCreate() {
        return "dashboard/comment/comment-create";
    }

    @PostMapping("/admin/comments/create")
    public String commentsCreate(@ModelAttribute CommentCreateDto commentCreateDto,
                               @RequestParam(value = "imageUrl", required = false) String imageUrl) throws IOException
    {
        commentCreateDto.setImage(imageUrl);
        commentService.addComment(commentCreateDto);
        return "redirect:/admin/comments";
    }

    @GetMapping("/admin/comments/update/{id}")
    public String commentUpdate(@PathVariable int id, Model model) {
        CommentUpdateDto commentUpdateDto = commentService.findCommentById(id);
        model.addAttribute("comment", commentUpdateDto);
        return "dashboard/comment/update";
    }

    @PostMapping("/admin/comments/update/{id}")
    public String updateComment(@PathVariable int id, @ModelAttribute CommentUpdateDto commentUpdateDto) {
        commentService.updateComment(id, commentUpdateDto);
        return "redirect:/admin/comments";
    }

    @GetMapping("/admin/comments/remove/{id}")
    public String removeComment(@PathVariable int id) {
        commentService.removeComment(id);
        return "redirect:/admin/comments";
    }
    @GetMapping("/admin/comments/activity/{id}")
    public String activity(@PathVariable int id)
    {
        commentService.activityComment(id);
        return "redirect:/admin/comments";
    }

}