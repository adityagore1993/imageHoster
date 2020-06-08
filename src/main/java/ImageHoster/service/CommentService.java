package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getAllComments(Integer imageId) {
        return commentRepository.getAllComments(imageId);
    }

    public void uploadComment(Comment comment) {
        commentRepository.addNewComment(comment);
    }

    public Comment getCommentByTitle(String title) {
        return commentRepository.getCommentByTitle(title);
    }

    public Comment getComment(Integer commentId) {
        return commentRepository.getComment(commentId);
    }

    public void updateComment(Comment updatedComment) {
         commentRepository.updateComment(updatedComment);
    }

    public void deleteComment(Integer commentId) {
        commentRepository.deleteComment(commentId);
    }


}
