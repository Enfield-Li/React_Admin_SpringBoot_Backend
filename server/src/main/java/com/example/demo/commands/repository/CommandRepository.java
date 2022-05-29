package com.example.demo.commands.repository;

import com.example.demo.commands.entity.Command;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandRepository extends JpaRepository<Command, Long> {}
