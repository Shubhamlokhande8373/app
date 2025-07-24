package com.app.entity.Evalution;

import jakarta.persistence.*;

@Entity
@Table(name = "car_evaluation_photo")
public class CarEvaluationPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }











}