package com.dev.socialmedia.services;

import com.dev.socialmedia.models.Hashtag;
import com.dev.socialmedia.repositories.HashtagRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class HashtagService {
    private final HashtagRepository hashtagRepository;
    public HashtagService(HashtagRepository hashtagRepository) {
        this.hashtagRepository = hashtagRepository;
    }
        public void createHashtag(String request) {
            var hashtag = new com.dev.socialmedia.models.Hashtag();
            hashtag.setName(request);
            hashtagRepository.save(hashtag);
        }
        public boolean hashtagExists(String name) {
            return hashtagRepository.existsByName(name);
        }
        public Set<Hashtag> findByList(Set<String> names) {
            return Set.copyOf(hashtagRepository.findAll().stream()
                    .filter(hashtag -> names.contains(hashtag.getName()))
                    .toList());
        }

}
