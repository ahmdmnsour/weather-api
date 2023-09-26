package com.orange.weather.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "predefined_notes")
public class PredefinedNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "note")
    private String note;

    @Column(name = "min_temp")
    private int minTemp;

    @Column(name = "max_temp")
    private int maxTemp;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_edit_date", nullable = false)
    private Date lastEditDate;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    public PredefinedNote(String note, int minTemp, int maxTemp, Date lastEditDate, Admin admin) {
        this.note = note;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.lastEditDate = lastEditDate;
        this.admin = admin;
    }
}
