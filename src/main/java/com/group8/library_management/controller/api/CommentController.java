package com.group8.library_management.controller.api;

import com.group8.library_management.constant.HttpStatusCode;
import com.group8.library_management.dto.request.CreateCommentReq;
import com.group8.library_management.dto.response.BaseAPIRes;
import com.group8.library_management.dto.response.CommentRes;
import com.group8.library_management.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/api/${api.version}/books")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final MessageSource messageSource;
    @PostMapping("/comments")
    public ResponseEntity<BaseAPIRes<CommentRes>> createComment(
            @RequestBody CreateCommentReq request,
            Locale locale) {


        CommentRes commentRes = commentService.createComment(request);


        String message = messageSource.getMessage("comment.create.success", null, "Bình luận đã được thêm thành công.",
                locale);

        return ResponseEntity.ok(
                BaseAPIRes.success(HttpStatusCode.OK, message, commentRes));
    }
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<BaseAPIRes<Void>> deleteComment(@PathVariable Integer id, Locale locale) {

        commentService.deleteComment(id);
        String message = messageSource.getMessage("comment.delete.success", null, "Xóa bình luận thành công.", locale);
        return ResponseEntity.ok(BaseAPIRes.success(HttpStatusCode.OK, message, null));
    }

}
