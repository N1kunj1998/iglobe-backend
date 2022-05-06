package com.example.IiitbHandshakeBackend.repo;

import com.example.IiitbHandshakeBackend.entity.Donate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonateRepo extends JpaRepository<Donate, Integer> {
    public List<Donate> findAllByAvailableIsTrue();
}
