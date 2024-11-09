package com.uams.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "software")
@Data
public class software implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "software_name", nullable = false, length = 255)
    private String softwareName;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "access_levels", nullable = false, length = 255)
    private String accessLevels;

    public software() {}

    public software(String softwareName, String description, String accessLevels) {
        this.softwareName = softwareName;
        this.description = description;
        this.accessLevels = accessLevels;
    }
    
    @Override
    public String toString() {
        return "Software{" +
                "id=" + id +
                ", softwareName='" + softwareName + '\'' +
                ", description='" + description + '\'' +
                ", accessLevels='" + accessLevels + '\'' +
                '}';
    }
}
