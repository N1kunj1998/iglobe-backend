package com.example.IiitbHandshakeBackend.repo;

import com.example.IiitbHandshakeBackend.entity.Sell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellRepo extends JpaRepository<Sell, Integer> {
    public List<Sell> findAllByAvailableIsTrue();
}
