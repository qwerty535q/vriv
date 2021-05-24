package com.api.apiTry.controllers;

import com.api.apiTry.models.Post;
import com.api.apiTry.repos.PostRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.ArrayList;
import java.util.Optional;


//controller
//asdsadsadsa
@Controller
public class MainController {

    @Autowired
    private PostRepos postRepos;

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("title", "main page");
        return "main";
    }

    @GetMapping("/books")
    public String books(Model model) {
        Iterable<Post> posts = postRepos.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("title", "pageOfBooks");
        return "books";
    }

    @GetMapping("/books/add")
    public String booksAdd(Model model) {
        return "booksAdd";
    }

    @GetMapping("/lending books")
    public String lendingBooks(Model model) {
        model.addAttribute("title", "pageOfLending");
        return "lending books";
    }

    @PostMapping("/books/add")
    public String booksPostAdd(@RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model) {
        Post post = new Post(title, anons, full_text);
        postRepos.save(post);
        return "redirect:/books";
    }

    @GetMapping("/books/{id}")
    public String booksDetails(@PathVariable(value = "id") long id, Model model) {
        if (!postRepos.existsById(id)) {
            return "redirect:/books";
        }

            Optional<Post> post = postRepos.findById(id);
            ArrayList<Post> res = new ArrayList<>();
            post.ifPresent(res::add);
            model.addAttribute("post", res);
            return "booksDetails";
        }


    @GetMapping("/books/{id}/edit")
    public String booksEdit(@PathVariable(value = "id") long id, Model model) {
        if (!postRepos.existsById(id)) {
            return "redirect:/books";
        }

        Optional<Post> post = postRepos.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "booksEdit";
    }


    @PostMapping("/books/{id}/edit")
    public String booksPostUpdate(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model) {
        Post post = postRepos.findById(id).orElseThrow();
        post.setTitle(title);
        post.setAnons(anons);
        post.setFull_text(full_text);
        postRepos.save(post);

        return "redirect:/books";
    }


    @PostMapping("/books/{id}/remove")
    public String booksPostRemove(@PathVariable(value = "id") long id, Model model) {
        Post post = postRepos.findById(id).orElseThrow();
        postRepos.delete(post);

        return "redirect:/books";
    }


}
