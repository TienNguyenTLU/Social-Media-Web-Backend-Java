package com.dev.socialmedia.repositories;

import com.dev.socialmedia.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostIdAndParentIsNull(Long postId);
    List<Comment> findAllByParentId(Long parentId);
}