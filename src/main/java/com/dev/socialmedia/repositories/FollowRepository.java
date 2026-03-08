package com.dev.socialmedia.repositories;

import com.dev.socialmedia.models.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
     Long countByFollowingId(Long userId);
     Long countByFollowerId(Long userId);
}
