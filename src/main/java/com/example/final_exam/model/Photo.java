package com.example.final_exam.model;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;

@Data
@Entity
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String  filename;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "place_id")
    private Place place;
}
