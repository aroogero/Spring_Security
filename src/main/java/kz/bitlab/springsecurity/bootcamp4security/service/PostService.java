package kz.bitlab.springsecurity.bootcamp4security.service;

import kz.bitlab.springsecurity.bootcamp4security.model.Post;
import kz.bitlab.springsecurity.bootcamp4security.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService; //сервис может подтягивать другого сервиса



    public Post createPost(Post post) { //rest practice 1
        if (!(post.getAuthor()!=null && post.getAuthor().getId()!=null)) {
            post.setAuthor(userService.getCurrentUser()); //добавили автора вытащив его из сессии
        }
        return postRepository.save(post);
    }

    public List<Post> getPosts() {
        return postRepository.findAll();
    }
    public Post getPost(Long id) {
        return postRepository.findById(id).orElseThrow();
    }

    public Post updatePost(@RequestBody Post post) { //Этот метод будет вызываться при Путмаппинге
        if (!(post.getAuthor()!=null && post.getAuthor().getId()!=null)) {
            post.setAuthor(userService.getCurrentUser()); //добавили автора вытащив его из сессии
        }
        return postRepository.save(post);
    }
}
