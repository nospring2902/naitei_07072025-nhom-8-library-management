package com.group8.library_management.controller.api;

import com.group8.library_management.dto.response.BaseAPIRes;
import com.group8.library_management.service.FavoriteService;
import com.group8.library_management.service.JwtService;
import com.group8.library_management.utils.GetMessage;
import com.group8.library_management.dto.request.AddFavoriteReq;
import jakarta.validation.Valid;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/${api.version}/favorites")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private GetMessage getMessage;

    @PostMapping
    public ResponseEntity<?> addFavorite(@RequestHeader("Authorization") String authHeader,
                                         @Valid @RequestBody AddFavoriteReq request) {
        String token = authHeader.startsWith("Bearer ") ? authHeader.substring(7) : authHeader;
        Integer userId = jwtService.extractUserId(token);
        boolean success = favoriteService.addFavorite(userId, request);
        String msg = success
            ? getMessage.msg("favorite.add.success")
            : getMessage.msg("favorite.add.exists");
        return ResponseEntity.ok(BaseAPIRes.success(msg, null));
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<?> removeFavorite(@PathVariable Integer bookId, HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body(BaseAPIRes.error(getMessage.msg("auth.token.missing")));
        }
        String token = authHeader.substring(7);
        Integer userId = jwtService.extractUserId(token);
        boolean removed = favoriteService.removeFavorite(userId, bookId);
        String msg = removed
            ? getMessage.msg("favorite.remove.success")
            : getMessage.msg("favorite.remove.notfound");
        return ResponseEntity.ok(BaseAPIRes.success(msg, null));
    }
}
