package com.example.IiitbHandshakeBackend.service;

import com.example.IiitbHandshakeBackend.entity.Donate;
import com.example.IiitbHandshakeBackend.entity.Sell;
import com.example.IiitbHandshakeBackend.repo.SellRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellService {
    @Autowired
    private SellRepo sellRepo;

    public Sell addItem(Sell sell){
        sell.setAvailable(true);
        sell.setStudentId(-1);
        return sellRepo.save(sell);
    }

    public Sell getDetails(int id){
        return sellRepo.findById(id).orElse(null);
    }

    public List<Sell> getProducts(){
        return sellRepo.findAllByAvailableIsTrue();
    }



    public Sell buyProduct(String sellId){
        System.out.println("buy product" + sellId);
        int sId = Integer.parseInt(sellId);
        Sell sell = sellRepo.findById(sId).orElse(null);
        System.out.println(sell);

        if(sell != null){
            sell.setAvailable(false);
            return sellRepo.save(sell);
        }
        return sell;
    }
}
