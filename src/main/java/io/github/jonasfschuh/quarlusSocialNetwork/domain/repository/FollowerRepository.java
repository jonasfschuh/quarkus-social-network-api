package io.github.jonasfschuh.quarlusSocialNetwork.domain.repository;

import io.github.jonasfschuh.quarlusSocialNetwork.domain.model.Follower;
import io.github.jonasfschuh.quarlusSocialNetwork.domain.model.User;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class FollowerRepository implements PanacheRepository<Follower> {
    public Boolean follows(User follower, User user) {
        var params = Parameters.with("follower", follower).and("user", user).map();

        PanacheQuery<Follower> query = find("follower = :follower and user = :user", params);
        Optional<Follower> result = query.firstResultOptional();

        return result.isPresent();
    }

    public List<Follower> findByUser(Long userId) {
        return list("user.id", userId);
    }

    public void deletebyFollowerAndUser(Long followerId, Long userId) {
        var params = Parameters
                .with("followerId", followerId)
                .and("userId", userId)
                .map();
        delete("follower.id = :followerId and user.id = :userId", params);
    }
}
