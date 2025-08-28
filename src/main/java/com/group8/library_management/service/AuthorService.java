package com.group8.library_management.service;


import com.group8.library_management.dto.response.AuthorDetailResponseDto;

public interface AuthorService {
    AuthorDetailResponseDto getAuthorDetail(Integer id);
}
