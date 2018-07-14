package com.simplesite.Controller;

import com.simplesite.Repository.MessageRepo;
import com.simplesite.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    MessageRepo repository;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {

        Iterable<Message> messages = repository.findAll();

        model.put("messages", messages);
        return "main";
    }

    @PostMapping("/main")
    public String asd(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
        Message message = new Message(text, tag);

        repository.save(message);

        Iterable<Message> messages = repository.findAll();

        model.put("messages", messages);
        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Message> messages;

        if (filter != null && !filter.isEmpty()) {
            messages = repository.findByTag(filter);
        } else {
            messages = repository.findAll();
        }


        model.put("messages", messages);

        return "main";
    }


}
