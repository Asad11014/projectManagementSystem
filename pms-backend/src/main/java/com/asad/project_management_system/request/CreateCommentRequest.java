package com.asad.project_management_system.request;

import lombok.Data;

@Data
public class CreateCommentRequest {

    private Long issueId;

    private String content;
}
