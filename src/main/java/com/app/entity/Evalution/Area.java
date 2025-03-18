package com.app.entity.Evalution;

import jakarta.persistence.*;

@Entity
@Table(name = "area")
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "pin_code", nullable = false)
    private String pinCode;

    @ManyToOne(optional = false)
    @JoinColumn(name = "agent_id", nullable = false)
    private Agent agent;

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}