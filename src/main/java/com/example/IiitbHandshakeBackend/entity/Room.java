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
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int roomIdx;
    private int alumniId;
    private String address;
    private int cost;
    private String image;
    private boolean available;

    public Room(int alumniId, String address, int cost, String image, boolean available) {
        this.alumniId = alumniId;
        this.address = address;
        this.cost = cost;
        this.image = image;
        this.available = available;
    }
}
