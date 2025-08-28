package com.group8.library_management.service;

import com.group8.library_management.dto.request.CreateCommentReq;
import com.group8.library_management.dto.response.CommentRes;


public interface CommentService {
    CommentRes createComment(CreateCommentReq request);
    void deleteComment(Integer commentId);

}
