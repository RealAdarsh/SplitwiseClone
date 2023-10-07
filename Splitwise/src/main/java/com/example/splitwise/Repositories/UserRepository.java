package com.example.splitwise.Repositories;

import com.example.splitwise.Models.User;
import com.example.splitwise.Services.UserService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    User save(User entity);

    @Override
    Optional<User> findById(Long aLong);

}
