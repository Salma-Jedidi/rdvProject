package com.example.RDV.repository;
import com.example.RDV.entities.ServiceMed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceMed, Integer> {
}
