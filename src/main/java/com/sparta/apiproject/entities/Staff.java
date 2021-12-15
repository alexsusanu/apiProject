package com.sparta.apiproject.entities;

import javax.persistence.*;

@Entity
@Table(name = "staff")
public class Staff
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id", nullable = false)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}