package ru.sweater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sweater.model.Message;

import java.util.List;

@Repository
public interface MessageRepos extends JpaRepository<Message, Long> {
    List<Message> findByTag(String filter);
}
