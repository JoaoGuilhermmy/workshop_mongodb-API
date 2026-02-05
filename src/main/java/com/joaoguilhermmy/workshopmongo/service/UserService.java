package com.joaoguilhermmy.workshopmongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaoguilhermmy.workshopmongo.domain.User;
import com.joaoguilhermmy.workshopmongo.expection.ResourcesNotFoundException;
import com.joaoguilhermmy.workshopmongo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(String id) {
        Optional<User> user = repository.findById(id);

        return user.orElseThrow(() -> new ResourcesNotFoundException(id));
    }
}
