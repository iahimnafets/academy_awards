package com.academy.awards.repository;

import com.academy.awards.entitys.Movie;
import com.academy.awards.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
