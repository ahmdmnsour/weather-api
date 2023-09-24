package com.orange.weather.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
@Entity
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "min_temp", nullable = false)
    private int minTemp;

    @Column(name = "max_temp", nullable = false)
    private int maxTemp;

    @Column(name = "note", nullable = false)
    private String note;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;
}
