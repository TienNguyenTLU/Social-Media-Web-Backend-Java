package com.dev.socialmedia.services;

import com.dev.socialmedia.models.Follow;
import com.dev.socialmedia.models.User;
import com.dev.socialmedia.repositories.FollowRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FollowService {
    private FollowRepository followRepository;

    public Page<User> getFollowers(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return followRepository.findAllFollowersByUserId(userId, pageable);
    }
    public Follow ToggleFollow (Long followerId,Long followingId)
    {
        if (followRepository.existsByFollowerAndFollowing(followerId, followingId)) {
            followRepository.deleteByFollowerAndFollowing(followerId, followingId);
            return null;
        } else {
            Follow follow = new Follow();
            follow.setFollower(new User(followerId));
            follow.setFollowing(new User(followingId));
            return followRepository.save(follow);
        }
    }
}
