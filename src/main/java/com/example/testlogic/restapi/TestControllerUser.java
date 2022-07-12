package com.example.testlogic.restapi;

import com.example.testlogic.entity.News;
import com.example.testlogic.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestControllerUser {

    @Autowired
    NewsService newsService;

    @GetMapping("/user")
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<News>> getList(){
        return ResponseEntity.ok(newsService.findAll());
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getDetail(@PathVariable Integer id) {
        Optional<News> optionalNews = newsService.findById(id);
        if (!optionalNews.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalNews.get());
    }
}
