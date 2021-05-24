package com.api.apiTry.repos;

import com.api.apiTry.models.Post;
import org.springframework.data.repository.CrudRepository;

//userRepository
public interface PostRepos extends CrudRepository<Post, Long> {
}


