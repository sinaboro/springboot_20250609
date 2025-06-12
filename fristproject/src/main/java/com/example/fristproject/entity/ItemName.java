package com.example.fristproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ItemName {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String nameAge;

    private String description;

}
