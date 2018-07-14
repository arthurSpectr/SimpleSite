package com.simplesite.Repository;

import org.springframework.data.repository.CrudRepository;

import com.simplesite.domain.Message;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface MessageRepo extends CrudRepository<Message, Long> {
    List<Message> findByTag(String tag);
}