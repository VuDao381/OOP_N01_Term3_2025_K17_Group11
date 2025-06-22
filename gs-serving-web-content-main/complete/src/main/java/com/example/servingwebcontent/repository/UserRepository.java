package com.example.servingwebcontent.repository;

import com.example.servingwebcontent.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByUseremail(String useremail);
}
