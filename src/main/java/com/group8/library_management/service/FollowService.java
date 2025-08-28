package com.group8.library_management.service;

import com.group8.library_management.dto.response.BaseAPIRes;

public interface FollowService {
    BaseAPIRes<Void> follow(String targetType, Integer targetId);
    BaseAPIRes<Void> unfollow(String targetType, Integer targetId);

}
