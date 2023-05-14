package com.springsecuritypractice.service;

import com.springsecuritypractice.model.Accessories;
import com.springsecuritypractice.model.User;
import com.springsecuritypractice.repository.AccessoriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class AccessoriesService {

    private final PasswordEncoder passwordEncoder;

    private final AccessoriesRepository accessoriesRepository;
    List<Accessories> accessoriesList = null;

    public void loadAccessoriesFromDb() {
        accessoriesList = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> Accessories.builder()
                .accessoriesId(i).accessoriesName("Accessories " + i)
                .quantity(new Random().nextInt(15))
                        .price(new Random().nextInt(500))
                        .build())
                .collect(Collectors.toList());
    }

    public List<Accessories> getAllAccessories() {
        return accessoriesList;
    }

    public Accessories getAccessoriesById(int id) {
        return accessoriesList.stream()
                .filter(accessories -> accessories.getAccessoriesId() == id)
                .findAny()
                .orElseThrow(() -> new RuntimeException("accessories " + id + "not found"));
    }

    public String adduser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        accessoriesRepository.save(user);
        return "User added successfully";
    }
}
