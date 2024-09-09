package io.github.jonasfschuh.quarlusSocialNetwork.domain.repository;

import io.github.jonasfschuh.quarlusSocialNetwork.domain.model.Follower;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FollowerRepository implements PanacheRepository<Follower> {
}
