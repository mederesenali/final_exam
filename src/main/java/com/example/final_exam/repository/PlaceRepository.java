package com.example.final_exam.repository;

import com.example.final_exam.model.Place;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlaceRepository extends CrudRepository<Place,Integer> {
    List<Place> findByTag(String tag);
}
