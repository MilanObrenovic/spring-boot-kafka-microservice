package com.example.kafkaconsumerdatabaseservice.repository;

import com.example.kafkaconsumerdatabaseservice.model.Wikimedia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WikimediaRepository extends JpaRepository<Wikimedia, Long> {

}
