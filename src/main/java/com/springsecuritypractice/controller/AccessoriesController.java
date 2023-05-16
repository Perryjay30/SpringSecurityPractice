package com.springsecuritypractice.controller;

import com.springsecuritypractice.config.jwt.JwtService;
import com.springsecuritypractice.config.jwt.dto.AuthRequest;
import com.springsecuritypractice.model.Accessories;
import com.springsecuritypractice.model.User;
import com.springsecuritypractice.service.AccessoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accessories")
@Service
@RequiredArgsConstructor
public class AccessoriesController {

    private final AccessoriesService accessoriesService;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

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

    @PostMapping("/authenticate")
    public String authenticationAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authenticating = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (authRequest.getUsername(), authRequest.getPassword()));
        if(authenticating.isAuthenticated())
            return jwtService.generateToken(authRequest.getUsername());
        else throw new UsernameNotFoundException("Invalid user request!!");
    }


}
