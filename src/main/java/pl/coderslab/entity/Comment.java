package pl.coderslab.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private LocalDateTime created;

    private Integer flags;

    private Long user_id;

    private Long picture_id;

    private Long parent_comment_id;

    @PrePersist
    public void prePersist() {
        created = LocalDateTime.now();
    }

}

