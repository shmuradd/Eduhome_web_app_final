package org.sb.eduhome2.services;

import org.sb.eduhome2.dtos.comments.CommentCreateDto;
import org.sb.eduhome2.dtos.comments.CommentUpdateDto;

public interface CommentService {
    void addComment(CommentCreateDto commentCreateDto);
    CommentUpdateDto findCommentById(int id);
    void updateComment(int id, CommentUpdateDto commentUpdateDto);
    void removeComment(int id);
    void activityComment(int commentId);
}
