package com.sparta.apiproject.entities;

import javax.persistence.*;

@Entity
@Table(name = "inventory")
public class Inventory
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id", nullable = false)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}