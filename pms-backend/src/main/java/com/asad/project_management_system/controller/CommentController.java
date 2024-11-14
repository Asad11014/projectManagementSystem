package com.asad.project_management_system.controller;

import com.asad.project_management_system.model.Comment;
import com.asad.project_management_system.model.AppUser;
import com.asad.project_management_system.repository.UserRepository;
import com.asad.project_management_system.request.CreateCommentRequest;
import com.asad.project_management_system.response.MessageResponse;
import com.asad.project_management_system.service.CommentService;
import com.asad.project_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<Comment> createComment(
            @RequestBody CreateCommentRequest req,
            //@RequestHeader("Authorisation") String jwt
            Authentication authentication
    ) throws Exception{
        String email = authentication.getName();
        AppUser user = userRepository.findByEmail(email);
        Comment createdComment = commentService.createComment(
                req.getIssueId(),
                user.getId(),
                req.getContent());
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<MessageResponse> deleteComment(
            @PathVariable Long commentId,
            //@RequestHeader("Authorisation") String jwt
            Authentication authentication
    ) throws Exception{
        String email = authentication.getName();
        AppUser user = userRepository.findByEmail(email);
        commentService.deleteComment(commentId, user.getId());
        MessageResponse res = new MessageResponse();
        res.setMessage("Comment deleted successfully");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{issueId}")
    public ResponseEntity<List<Comment>> getCommentsByIssueId(@PathVariable Long issueId){
        List<Comment> comments = commentService.findCommentByIssueId(issueId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
