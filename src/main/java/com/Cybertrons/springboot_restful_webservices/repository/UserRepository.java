package com.Cybertrons.springboot_restful_webservices.repository;

import com.Cybertrons.springboot_restful_webservices.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
