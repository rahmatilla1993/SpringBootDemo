package com.example.springbootdemo.post;

import com.example.springbootdemo.profile.Profile;
import com.example.springbootdemo.profile.ProfileConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostRepository postRepository;
    private final ProfileConfig profileConfig;

    @Autowired
    public PostController(PostRepository postRepository, ProfileConfig profileConfig) {
        this.postRepository = postRepository;
        this.profileConfig = profileConfig;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Post getOne(@PathVariable("id") int id) {
        return postRepository.findById(id).get();
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public HttpEntity<Profile> getProfile() {
        return ResponseEntity.ok(profileConfig.getActivate());
    }

    @RequestMapping(method = RequestMethod.POST)
    public Post save(@RequestBody Post post) {
        return postRepository.save(post);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Post edit(@RequestBody Post post) {
        return postRepository.save(post);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        postRepository.deleteById(id);
    }
}
