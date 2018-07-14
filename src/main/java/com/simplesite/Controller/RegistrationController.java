package com.simplesite.Controller;

import com.simplesite.Repository.UserRepo;
import com.simplesite.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.simplesite.domain.User;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
        public String newUser(User user, Map<String, Object> model) {
            User userFromDB = userRepo.findByUsername(user.getUsername());

            if(userFromDB != null) {
                model.put("message", "User exists");
                return "registration";
            }

            user.setActive(true);
            user.setRoles(Collections.singleton(Role.USER));
            userRepo.save(user);
            return "redirect:/login";
        }

}
