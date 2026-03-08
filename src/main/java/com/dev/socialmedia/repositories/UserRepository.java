package com.dev.socialmedia.repositories;

import com.dev.socialmedia.models.User;
import graphql.com.google.common.base.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    // Kiểm tra xem 2 người có phải là bạn bè không (Follow chéo)
    @Query("SELECT COUNT(f) > 0 FROM Follow f " +
            "WHERE f.follower.id = :userA AND f.following.id = :userB " +
            "AND EXISTS (SELECT f2 FROM Follow f2 WHERE f2.follower.id = :userB AND f2.following.id = :userA)")
    boolean areFriends(@Param("userA") Long userA, @Param("userB") Long userB);

    // Lấy danh sách bạn bè (những người follow chéo)
    @Query("SELECT f.following FROM Follow f WHERE f.follower.id = :userId " +
            "AND f.following.id IN (SELECT f2.follower.id FROM Follow f2 WHERE f2.following.id = :userId)")
    List<User> findAllFriends(@Param("userId") Long userId);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
