package com.example.RDV.repository;
import com.example.RDV.entities.Delegation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DelegRepository extends JpaRepository<Delegation, Integer> {
    Delegation findByCodeDelegation(Integer codeDelegation);

    void deleteByCodeDelegation(Integer codeDelegation);

    List<Delegation> findAllByCodeDelegation(Integer codeDelegation);
}
