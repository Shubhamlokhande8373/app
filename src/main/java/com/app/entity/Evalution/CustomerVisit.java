package com.app.entity.Evalution;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "customer_visit")
public class CustomerVisit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "mobile", nullable = false)
    private String mobile;

    @Column(name = "pin_code", nullable = false)
    private String pinCode;

    @Column(name = "date_of_visit", nullable = false)
    private LocalDate dateOfVisit;

    @Column(name = "time_Of_visit", nullable = false)
    private LocalTime timeOfVisit;

    @ManyToOne(optional = false)
    @JoinColumn(name = "agent_id", nullable = false)
    private Agent agent;

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public LocalTime getTime() {
        return timeOfVisit;
    }

    public void setTime(LocalTime time) {
        this.timeOfVisit  = time;
    }

    public LocalDate getDateOfVisit() {
        return dateOfVisit;
    }

    public void setDateOfVisit(LocalDate dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}