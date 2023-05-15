package com.springsecuritypractice.controller;

import com.springsecuritypractice.model.Accessories;
import com.springsecuritypractice.model.User;
import com.springsecuritypractice.service.AccessoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accessories")
@Service
@RequiredArgsConstructor
public class AccessoriesController {

    private final AccessoriesService accessoriesService;

    @GetMapping("/onboard")
    public String display() {
        return "This user is Practicing and getting to know spring security!!";
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        return accessoriesService.adduser(user);
    }

    @GetMapping("/accessAll")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Accessories> bringAllAccessories() {
        return accessoriesService.getAllAccessories();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Accessories getAccessoriesById(@PathVariable int id) {
       return accessoriesService.getAccessoriesById(id);
    }


}
