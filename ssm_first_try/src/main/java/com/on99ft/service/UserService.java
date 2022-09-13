package com.on99ft.service;

import com.on99ft.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    public boolean save(User user);
    public boolean update(User user);
    public boolean delete(Long id);
    public User selectId(Long id);
    public List<User> selectAll();

    public User dologin(User user);

    public Long countUser();
}
