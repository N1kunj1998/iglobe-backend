package com.example.IiitbHandshakeBackend.service;

import com.example.IiitbHandshakeBackend.entity.Donate;
import com.example.IiitbHandshakeBackend.repo.DonateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonateService {
    @Autowired
    private DonateRepo donateRepo;

    public Donate addItem(Donate donate){
        donate.setAvailable(true);
        return donateRepo.save(donate);
    }

    public Donate getDetails(int did){
        return donateRepo.findById(did).orElse(null);
    }

//    public List<Donate> getAllDetails(){
//        return donateRepo.getAllByAvailableExistsOrderBy();
//    }
    public List<Donate> getProducts(){
        return donateRepo.findAllByAvailableIsTrue();
    }

    public Donate buyProduct(String donateId){
        int dId = Integer.parseInt(donateId);
        Donate donate = donateRepo.findById(dId).orElse(null);
        if(donate != null){
            donate.setAvailable(false);
            return donateRepo.save(donate);
        }
        return donate;
    }
}
