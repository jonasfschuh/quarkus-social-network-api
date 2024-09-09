package io.github.jonasfschuh.quarlusSocialNetwork.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "followers")
@Data
public class Followers {

    @Id
    @generatedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany
    @JoinColumn(name = "follower_id")
    private User follower;


}
