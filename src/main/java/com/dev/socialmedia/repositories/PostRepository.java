package com.dev.socialmedia.repositories;

import com.dev.socialmedia.models.Post;
import graphql.com.google.common.base.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // Lấy bài viết của những người mình đang follow (Newsfeed)
    @Query("SELECT p FROM Post p WHERE p.author.id IN " +
            "(SELECT f.following.id FROM Follow f WHERE f.follower.id = :userId) ")
    Page<Post> getNewsfeed(@Param("userId") Long userId, Pageable pageable);
    List<Post> findAllByHashtagsName(String tagName);
    List<Post> findAllByCategoriesId(Long categoryId);
    @EntityGraph(attributePaths = {"author", "hashtags", "categories"})
    Optional<Post> findWithDetailsById(Long id);
}
