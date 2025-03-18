package com.app.entity.Evalution;

import jakarta.persistence.*;

@Entity
@Table(name = "car_evaluation_photo")
public class CarEvaluationPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "photo_url", nullable = false)
    private Long photoUrl;


    @ManyToOne
    @JoinColumn(name = "car_detailed_evaluation_id")
    private CarDetailedEvaluation carDetailedEvaluation;

    public CarDetailedEvaluation getCarDetailedEvaluation() {
        return carDetailedEvaluation;
    }

    public void setCarDetailedEvaluation(CarDetailedEvaluation carDetailedEvaluation) {
        this.carDetailedEvaluation = carDetailedEvaluation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(Long photoUrl) {
        this.photoUrl = photoUrl;
    }

}