package com.tdc.outbox.user.infraestructure;

import com.tdc.outbox.user.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}