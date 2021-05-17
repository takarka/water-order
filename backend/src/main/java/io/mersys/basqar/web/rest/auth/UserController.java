package io.mersys.basqar.web.rest.auth;

import io.mersys.basqar.service.dto.OrderSearchDTO;
import io.mersys.basqar.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.mersys.basqar.document.auth.User;
import io.mersys.basqar.exception.ResourceNotFoundException;
import io.mersys.basqar.repository.UserRepository;
import io.mersys.basqar.security.CurrentUser;
import io.mersys.basqar.security.UserPrincipal;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }

    @PostMapping("/user/me")
    public User updateCurrentUser(@Valid @RequestBody UserDTO dto) {
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findById(principal.getId()).orElseThrow(() -> new IllegalArgumentException("User not forund"));
        user.setName(dto.getName());
        user.setPhone(dto.getPhone());
        user = userRepository.save(user);
        return user;
    }
}
