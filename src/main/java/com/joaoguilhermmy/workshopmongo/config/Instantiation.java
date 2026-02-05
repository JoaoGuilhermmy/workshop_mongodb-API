package com.joaoguilhermmy.workshopmongo.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.joaoguilhermmy.workshopmongo.domain.Post;
import com.joaoguilhermmy.workshopmongo.domain.User;
import com.joaoguilhermmy.workshopmongo.dto.AuthorDTO;
import com.joaoguilhermmy.workshopmongo.dto.CommentDTO;
import com.joaoguilhermmy.workshopmongo.repository.PostRepository;
import com.joaoguilhermmy.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post1 = new Post(null, Instant.now(), "Partiu Viagem", "Vou viajar para São Paulo, abraços!",
                new AuthorDTO(maria));
        Post post2 = new Post(null, Instant.now(), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));

        CommentDTO comment1 = new CommentDTO(null, Instant.now(), "Boa viagem mano!", new AuthorDTO(alex));
        CommentDTO comment2 = new CommentDTO(null, Instant.now(), "Aproveite", new AuthorDTO(bob));
        CommentDTO comment3 = new CommentDTO(null, Instant.now(), "Tenha um ótimo dia!", new AuthorDTO(alex));

        post1.getComments().addAll(Arrays.asList(comment1, comment2));
        post2.getComments().addAll(Arrays.asList(comment3));
        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().addAll(Arrays.asList(post1, post2));

        userRepository.save(maria);
    }

}
