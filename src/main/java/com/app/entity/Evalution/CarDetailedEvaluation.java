package com.app.entity.Evalution;

import jakarta.persistence.*;

@Entity
@Table(name = "car_detailed_evaluation")
public class CarDetailedEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "kms", nullable = false)
    private Long kms;

    @Column(name = "year_of_manufacturing", nullable = false)
    private Long yearOfManufacturing;

    public Long getKms() {
        return kms;
    }

    public void setKms(Long kms) {
        this.kms = kms;
    }

    public Long getYearOfManufacturing() {
        return yearOfManufacturing;
    }

    public void setYearOfManufacturing(Long yearOfManufacturing) {
        this.yearOfManufacturing = yearOfManufacturing;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}