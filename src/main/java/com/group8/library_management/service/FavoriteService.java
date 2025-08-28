package com.group8.library_management.service;

import com.group8.library_management.dto.request.AddFavoriteReq;


public interface FavoriteService {
    boolean addFavorite(Integer userId, AddFavoriteReq request);
    boolean removeFavorite(Integer userId, Integer bookId);
}
