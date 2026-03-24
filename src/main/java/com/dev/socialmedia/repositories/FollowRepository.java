package com.dev.socialmedia.repositories;

import com.dev.socialmedia.models.Follow;
import com.dev.socialmedia.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
     Long countByFollowingId(Long userId);
     Long countByFollowerId(Long userId);
        @Query("SELECT f.follower FROM Follow f WHERE f.following.id = :userId")
        Page<User> findAllFollowersByUserId(@Param("userId") Long userId, Pageable pageable);
        boolean existsByFollowerAndFollowing(Long followerId, Long followingId);
        void deleteByFollowerAndFollowing(Long followerId, Long followingId);
}
