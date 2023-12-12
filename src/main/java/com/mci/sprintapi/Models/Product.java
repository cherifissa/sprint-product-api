package com.mci.sprintapi.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.annotation.processing.Generated;
@Data
@Entity
@Table(name= "t_produits")
public class Product {


    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private String name;
    private float price;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
