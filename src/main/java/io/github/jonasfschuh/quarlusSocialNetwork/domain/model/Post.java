package io.github.jonasfschuh.quarlusSocialNetwork.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "post_text")
    private String text;
    @Column(name = "dateTime")
    private LocalDateTime dataTime;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
