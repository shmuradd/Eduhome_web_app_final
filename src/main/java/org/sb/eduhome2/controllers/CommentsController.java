package org.sb.eduhome2.controllers;

import org.sb.eduhome2.dtos.comments.CommentCreateDto;
import org.sb.eduhome2.dtos.comments.CommentUpdateDto;
import org.sb.eduhome2.dtos.course.CourseCreateDto;
import org.sb.eduhome2.models.StudentComments;
import org.sb.eduhome2.repositories.CommentRepository;
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

    @Autowired CommentService commentService;
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
        StudentComments comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid comment Id:" + id));
        CommentUpdateDto commentUpdateDto = new CommentUpdateDto();
        commentUpdateDto.setId(comment.getId());
        commentUpdateDto.setFullName(comment.getFullName());
        commentUpdateDto.setComment(comment.getComment());
        commentUpdateDto.setPosition(comment.getPosition());
        commentUpdateDto.setClas(comment.getClas());
        commentUpdateDto.setImage(comment.getImage());
        model.addAttribute("comment", commentUpdateDto);
        return "dashboard/comment/update";
    }

    @PostMapping("/admin/comments/update/{id}")
    public String updateComment(@PathVariable int id, @ModelAttribute CommentUpdateDto commentUpdateDto) {
        StudentComments comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid comment Id:" + id));
        comment.setFullName(commentUpdateDto.getFullName());
        comment.setComment(commentUpdateDto.getComment());
        comment.setPosition(commentUpdateDto.getPosition());
        comment.setClas(commentUpdateDto.getClas());
        comment.setImage(commentUpdateDto.getImage());
        commentRepository.save(comment);
        return "redirect:/admin/comments";
    }

    @GetMapping("/admin/comments/remove/{id}")
    public String removeComment(@PathVariable int id) {
        commentRepository.deleteById(id);
        return "redirect:/admin/comments";
    }
}