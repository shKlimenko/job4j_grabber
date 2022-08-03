package gc.leak;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class PostStore {
    public AtomicInteger atomicInteger = new AtomicInteger(1);

    private Map<Integer, Post> posts = new HashMap<>();

    public Post add(Post post) {
        int id = atomicInteger.getAndIncrement();
        post.setId(id);
        posts.put(id, post);
        return post;
    }

    public void removeAll() {
        posts.clear();
    }

    public Collection<Post> getPosts() {
        return posts.values();
    }
}
