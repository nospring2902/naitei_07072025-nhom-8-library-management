package com.group8.library_management.repository;

import com.group8.library_management.entity.BookComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCommentRepository extends JpaRepository<BookComment, Integer> {

}

