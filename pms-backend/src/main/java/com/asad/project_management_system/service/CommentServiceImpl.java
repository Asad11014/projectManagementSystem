package com.asad.project_management_system.service;

import com.asad.project_management_system.model.Comment;
import com.asad.project_management_system.model.Issue;
import com.asad.project_management_system.model.AppUser;
import com.asad.project_management_system.repository.CommentRepository;
import com.asad.project_management_system.repository.IssueRepository;
import com.asad.project_management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Comment createComment(Long issueId, Long userId, String content) throws Exception {
        Optional<Issue> issueOptional = issueRepository.findById(issueId);
        Optional<AppUser> userOptional = userRepository.findById(userId);

        if(issueOptional.isEmpty()){
            throw new Exception("Issue not found with ID " + issueId);
        }
        if(userOptional.isEmpty()){
            throw new Exception("User not found with ID " + userId);
        }

        Issue issue = issueOptional.get();
        AppUser user = userOptional.get();

        Comment comment = new Comment();

        comment.setIssue(issue);
        comment.setUser(user);
        comment.setCreatedDateTime(LocalDateTime.now());
        comment.setContent(content);

        Comment savedComment = commentRepository.save(comment);
        issue.getComments().add(savedComment);

        return savedComment;
    }

    @Override
    public void deleteComment(Long commentId, Long userId) throws Exception {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        Optional<AppUser> userOptional = userRepository.findById(userId);

        if(commentOptional.isEmpty()){
            throw new Exception("Comment not found with ID " + commentId);
        }
        if(userOptional.isEmpty()){
            throw new Exception("User not found with ID " + userId);
        }

        Comment comment = commentOptional.get();
        AppUser user = userOptional.get();

        if(comment.getUser().equals(user)){
            commentRepository.delete(comment);
        } else {
            throw new Exception("User does not have permission to delete this comment.");
        }

    }

    @Override
    public List<Comment> findCommentByIssueId(Long issueId) {
        return commentRepository.findByIssueId(issueId);
    }
}
