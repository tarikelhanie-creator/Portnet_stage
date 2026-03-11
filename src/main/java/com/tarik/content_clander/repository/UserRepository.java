package com.tarik.content_clander.repository;
import com.tarik.content_clander.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long> {}