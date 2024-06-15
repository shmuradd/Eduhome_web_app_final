package org.sb.eduhome2.services.impls;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.sb.eduhome2.dtos.comments.CommentCreateDto;
import org.sb.eduhome2.dtos.comments.CommentUpdateDto;
import org.sb.eduhome2.models.Course;
import org.sb.eduhome2.models.StudentComments;
import org.sb.eduhome2.repositories.CommentRepository;
import org.sb.eduhome2.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ModelMapper modelMapper;



    @Override
    @Transactional
    public void addComment(CommentCreateDto commentCreateDto) {
        try {
            StudentComments comment = new StudentComments();
            comment.setFullName(commentCreateDto.getFullName());
            comment.setComment(commentCreateDto.getComment());
            comment.setIsActive(true);
            comment.setClas(commentCreateDto.getClas());
            comment.setPosition(commentCreateDto.getComment());
            comment.setImage(commentCreateDto.getImage());
            // Add any additional fields here if needed

            commentRepository.save(comment);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public CommentUpdateDto findCommentById(int id) {
        StudentComments comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found with id " + id));
        return modelMapper.map(comment, CommentUpdateDto.class);
    }

    @Override
    @Transactional
    public void updateComment(int id, CommentUpdateDto commentUpdateDto) {
        StudentComments comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found with id " + id));

        comment.setFullName(commentUpdateDto.getFullName());
        comment.setComment(commentUpdateDto.getComment());
        comment.setPosition(commentUpdateDto.getPosition());
        comment.setClas(commentUpdateDto.getClas());
        comment.setImage(commentUpdateDto.getImage());

        commentRepository.saveAndFlush(comment);
    }

    @Override
    @Transactional
    public void removeComment(int id) {
        StudentComments comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found with id " + id));
        commentRepository.delete(comment);
    }

    @Override
    @Transactional
    public void activityComment(int commentId) {
        StudentComments comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id " + commentId));
        comment.setIsActive(!comment.getIsActive()); // Toggle the deleted status
        commentRepository.flush();
    }

}
