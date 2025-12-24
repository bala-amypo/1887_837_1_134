package com.example.demo.repository;

import com.example.demo.model.AppUser;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryAppUserRepository implements AppUserRepository {

    private final Map<Long, AppUser> store = new HashMap<>();
    private final AtomicLong idGen = new AtomicLong(1);

    @Override
    public Optional<AppUser> findByEmail(String email) {
        return store.values()
                .stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public boolean existsByEmail(String email) {
        return findByEmail(email).isPresent();
    }

    @Override
    public AppUser save(AppUser user) {
        if (user.getId() == null) {
            user.setId(idGen.getAndIncrement());
        }
        store.put(user.getId(), user);
        return user;
    }
}
