package com.uams.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "requests")
@Data
public class request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private users user;

    @ManyToOne
    @JoinColumn(name = "software_id", nullable = false)
    private software software;

    @Column(name = "access_type", nullable = false)
    private String accessType; // e.g., "Read", "Write", "ADMIN"

    @Column(name = "reason", nullable = false)
    private String reason;

    @Column(name = "status", nullable = false)
    private String status; // e.g., "Pending", "Approved", "Rejected"

    public request() {
    }

}
