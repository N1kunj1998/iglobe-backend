package com.example.IiitbHandshakeBackend.controller;

import com.example.IiitbHandshakeBackend.service.DonateService;
import com.example.IiitbHandshakeBackend.service.SellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@CrossOrigin("*")
@RestController()
public class BuyController {
    @Autowired
    private DonateService donateService;

    @Autowired
    private SellService sellService;

    @GetMapping("/getDonateProducts")
    public ResponseEntity<?> getDonateProduct(){
        return ResponseEntity.ok(donateService.getProducts());
    }

    @GetMapping("/getSellProducts")
    public ResponseEntity<?> getSellProduct(){
        return ResponseEntity.ok(sellService.getProducts());
    }

    @PostMapping("/buyDonateProduct/{donateId}")
    public ResponseEntity<?> buyDonateProduct(@PathVariable("donateId") String donateId){
        return ResponseEntity.ok(donateService.buyProduct(donateId));
    }

    @GetMapping("/buySellProduct/{sId}")
    public ResponseEntity<?> buySellProduct(@PathVariable("sId") String sId){
        System.out.println("buySellProduct" + sId);
        return ResponseEntity.ok(sellService.buyProduct(sId));
    }
}
