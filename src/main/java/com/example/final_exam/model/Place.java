package com.example.final_exam.model;

import com.sun.istack.Nullable;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Data
@Entity
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String tag;
    @NotBlank
    private String description;
    @NotBlank
    private String filename;
    private int reviewers;
    private Double rating;
    private int photo;
}