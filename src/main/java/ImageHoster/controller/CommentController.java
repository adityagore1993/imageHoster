package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.repository.ImageRepository;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private ImageService imageService;



    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String postComment(@RequestParam("comment") String comment, @ModelAttribute("imageId") Integer imageId, @PathVariable("imageTitle") String imageTitle, Model model
    , HttpSession session) throws IOException {
        User user = (User) session.getAttribute("loggeduser");
        Comment newComment = new Comment();
        newComment.setUser(user);
        newComment.setText(comment);
        newComment.setCreatedDate(new Date());
        newComment.setImage(imageService.getImage(imageId));
        commentService.uploadComment(newComment);
        Image image = imageService.getImage(imageId);
        List<Comment> comments = commentService.getAllComments(imageId);
        model.addAttribute("image", image);
        model.addAttribute("comments", comments);

        return "images/image";
    }
}
