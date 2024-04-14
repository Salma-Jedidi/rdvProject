package com.example.RDV.repository;


import com.example.RDV.entities.MessagePatient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<MessagePatient, Integer> {


}
