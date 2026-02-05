package com.joaoguilhermmy.workshopmongo.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joaoguilhermmy.workshopmongo.domain.Post;
import com.joaoguilhermmy.workshopmongo.resources.util.URL;
import com.joaoguilhermmy.workshopmongo.service.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/posts")
@Tag(name = "Post Management", description = "Operations related to posts and searches")
public class PostResources {

    @Autowired
    private PostService service;

    @Operation(summary = "Find post by ID", description = "Retrieves detailed information about a specific post.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post found successfully"),
            @ApiResponse(responseCode = "404", description = "Post not found")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findByid(@PathVariable String id) {
        Post post = service.findById(id);
        return ResponseEntity.ok().body(post);
    }

    @Operation(summary = "Search posts by title", description = "Performs a simple search to find posts containing the given text in the title.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Search completed successfully"),
            @ApiResponse(responseCode = "200", description = "Return empty list if no post matches")
    })
    @GetMapping(value = "/titlesearch")
    public ResponseEntity<List<Post>> findTitle(@RequestParam(value = "text", defaultValue = "") String text) {
        text = URL.decodeParam(text);
        List<Post> list = service.findByTitle(text);
        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "Advanced post search", description = "Search for posts containing specific text within a defined date range (minDate and maxDate).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Advanced search completed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid date format")
    })
    @RequestMapping(value = "/fullsearch", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> fullSearch(
            @RequestParam(value = "text", defaultValue = "") String text,
            @RequestParam(value = "minDate", defaultValue = "") String minDate,
            @RequestParam(value = "maxDate", defaultValue = "") String maxDate) {
        text = URL.decodeParam(text);
        Date min = URL.convertDate(minDate, new Date(0L));
        Date max = URL.convertDate(maxDate, new Date());
        List<Post> list = service.fullSearch(text, min, max);
        return ResponseEntity.ok().body(list);
    }
}