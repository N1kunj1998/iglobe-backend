package com.example.IiitbHandshakeBackend.controller;

import com.example.IiitbHandshakeBackend.entity.Donate;
import com.example.IiitbHandshakeBackend.service.DonateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController()
//@RequestMapping("/donate")
public class DonateController {
    @Autowired
    private DonateService donateService;

    @PostMapping("/donate/addItem")
    public ResponseEntity<?> addItem(@RequestBody Donate donate){
        System.out.println(donate);
        return ResponseEntity.ok(donateService.addItem(donate));
    }

    @GetMapping("/donate/getDetails/{did}")
    public ResponseEntity<?> getDetails(@PathVariable("did") int did){
        return ResponseEntity.ok(donateService.getDetails(did));
    }

//    @GetMapping("/donate/getAllDetails")
//    public ResponseEntity<?> getAllDetails(){
//        return ResponseEntity.ok(donateService.getAllDetails());
//    }
}
