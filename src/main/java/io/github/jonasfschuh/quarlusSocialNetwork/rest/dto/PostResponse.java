package io.github.jonasfschuh.quarlusSocialNetwork.rest.dto;

import io.github.jonasfschuh.quarlusSocialNetwork.domain.model.Post;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostResponse {
    private String text;
    private LocalDateTime dateTime;

    public static PostResponse fromEntity(Post post){
        PostResponse postResponse = new PostResponse();
        postResponse.setText(post.getText());
        postResponse.setDateTime(post.getDateTime());
        return postResponse;
    }

}
