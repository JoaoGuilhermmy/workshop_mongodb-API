package com.joaoguilhermmy.workshopmongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaoguilhermmy.workshopmongo.domain.Post;
import com.joaoguilhermmy.workshopmongo.expection.ResourcesNotFoundException;
import com.joaoguilhermmy.workshopmongo.repository.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public Post findById(String id) {
        Optional<Post> post = repository.findById(id);
        return post.orElseThrow(() -> new ResourcesNotFoundException(id));
    }

    public List<Post> findByTitle(String text) {
        return repository.findByTitleContainingIgnoreCase(text);
    }
}
