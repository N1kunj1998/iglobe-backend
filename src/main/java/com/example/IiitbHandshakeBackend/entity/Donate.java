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

public class Donate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int donateId;
    private String productname;
    private String details;
    private String image;
    private int rollNo;
    private boolean available;

    public Donate(String productname, String details, String image, int rollNo, boolean available) {
        this.productname = productname;
        this.details = details;
        this.image = image;
        this.rollNo = rollNo;
        this.available = available;
    }
}
