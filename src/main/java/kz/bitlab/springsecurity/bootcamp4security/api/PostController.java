package kz.bitlab.springsecurity.bootcamp4security.api;

import kz.bitlab.springsecurity.bootcamp4security.model.Post;
import kz.bitlab.springsecurity.bootcamp4security.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //то же, что и контроллер, но возвращаемый тип данных не будет HTML-ом
//Для передачи данных мобильной разработке, мы должны отправлять данные в чистом виде, без фронта
//Поэтому передаем в виде JSON
@RequestMapping(value = "/api/post") //это его префикс
public class PostController {

    @Autowired
    private PostService postService;
    @GetMapping//у него нет никакой ссылки - это означает что ссылка по умолчанию будет вытаскивать все данные - /api/post
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    @GetMapping(value = "{id}")//если хотим посмотреть только один пост
    public Post getPost(@PathVariable(name = "id") Long id) {
        return postService.getPost(id);
    }
}
