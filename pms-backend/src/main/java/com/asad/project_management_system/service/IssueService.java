package com.asad.project_management_system.service;

import com.asad.project_management_system.model.Issue;
import com.asad.project_management_system.model.AppUser;
import com.asad.project_management_system.request.IssueRequest;

import java.util.List;

public interface IssueService {
    Issue getIssueById(Long issueId) throws Exception;

    List<Issue> getIssueByProjectId(Long projectId) throws Exception;

    Issue createIssue(IssueRequest issue, AppUser user) throws Exception;

    void deleteIssue(Long issueId, Long userId) throws Exception;

    Issue addUserToIssue(Long issueId, Long userId) throws Exception;

    Issue updateStatus(Long issueId, String status) throws Exception;
}
