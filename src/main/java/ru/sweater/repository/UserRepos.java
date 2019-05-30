package ru.sweater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sweater.model.User;

@Repository
public interface UserRepos extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
