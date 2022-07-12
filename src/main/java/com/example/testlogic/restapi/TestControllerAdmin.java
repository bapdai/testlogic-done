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
public class TestControllerAdmin {

    @Autowired
    NewsService newsService;

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<News>> getList(){
        return ResponseEntity.ok(newsService.findAll());
    }

    @GetMapping("/admin/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getDetail(@PathVariable Integer id){
//        News news1 = new News();
//        int views1 = news1.getViews() + 1;
//        news1.setViews(views1);

        Optional<News> optionalNews = newsService.findById(id);

//        for (int i = 1; i > 0;i++) {
//            News news1 = new News();
//            int views1 = news1.getViews() + i;
//            optionalNews.get().setViews(views1);
//        }

        News news1 = new News();
        int views1 = news1.getViews() + 3;
        optionalNews.get().setViews(views1);

            if (!optionalNews.isPresent()) {
                ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(optionalNews.get());
//        }
//        return ResponseEntity.ok(optionalNews.get());
    }

    @PostMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<News> create(@RequestBody News news){
        return ResponseEntity.ok(newsService.save(news));
    }

    @PutMapping("/admin/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<News> update(@PathVariable Integer id, @RequestBody News news){
        Optional<News> optionalNews = newsService.findById(id);
        if ((!optionalNews.isPresent())){
            ResponseEntity.badRequest().build();
        }
        News existNews = optionalNews.get();

        existNews.setTitle(news.getTitle());
        existNews.setContent(news.getContent());
        existNews.setViews(news.getViews());
        existNews.setStatus(news.getStatus());
        existNews.setAuthor(news.getAuthor());
        return ResponseEntity.ok(newsService.save(existNews));
    }

    @DeleteMapping("/admin/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        if ((!newsService.findById(id).isPresent())){
            ResponseEntity.badRequest().build();
        }
        newsService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
