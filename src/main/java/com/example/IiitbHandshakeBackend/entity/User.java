package com.example.IiitbHandshakeBackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true)
    private String username;
    private String password;
    private int rollNo;
    private String email;
    private String role; // STUDENT, ALUMNI, ADMIN
    private String upiId;
    private boolean active;

    public User(String username, String password, int rollNo, String email, String role, String upiId, boolean active) {
        this.username = username;
        this.password = password;
        this.rollNo = rollNo;
        this.email = email;
        this.role = role;
        this.upiId = upiId;
        this.active = active;
    }
}
