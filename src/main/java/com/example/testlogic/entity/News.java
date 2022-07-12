package com.example.testlogic.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String content;

    private int views = 1;

    private Boolean status = true;

    private String author;

    public News (int id, String title, String content,
                int views, Boolean status, String author){
        this.id=id;
        this.title=title;
        this.content=content;
        this.views=views;
        this.status=status;
        this.author=author;
    }

    public void setViews(int views) {
        this.views = views++;
    }
}
