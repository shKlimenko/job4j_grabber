package html;

import post.Post;

import java.util.List;

public interface Parse {
    List<Post> list(String link);

    Post detail(String link);
}