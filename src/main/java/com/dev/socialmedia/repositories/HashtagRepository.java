package com.dev.socialmedia.repositories;

import com.dev.socialmedia.models.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
    List<Hashtag> findAll();
    boolean existsByName(String name);
}
