package kz.bitlab.springsecurity.bootcamp4security.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_posts")
@Getter
@Setter
public class Post extends BaseEntity{
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    private User author;
}
