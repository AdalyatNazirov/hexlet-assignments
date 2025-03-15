package exercise.dto.posts;

import exercise.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;


// BEGIN
@Getter
@AllArgsConstructor
public class PostsPage {
    private int page;
    private int size;
    private List<Post> posts;
}
// END


