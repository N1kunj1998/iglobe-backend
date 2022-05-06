package com.example.IiitbHandshakeBackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table()
public class Sell {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int sid;
    private String name;
    private String image;
    private String details;
    private int rollNo;
    private int price;
    private boolean available;
    private int studentId;

    public Sell(String name, String image, String details, int rollNo, int price, boolean available, int studentId) {
        this.name = name;
        this.image = image;
        this.details = details;
        this.rollNo = rollNo;
        this.price = price;
        this.available = available;
        this.studentId = studentId;
    }
}
