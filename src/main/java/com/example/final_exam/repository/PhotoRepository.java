package com.example.final_exam.repository;

import com.example.final_exam.model.Photo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PhotoRepository extends CrudRepository<Photo,Integer> {
  List<Photo> findAllByPlaceId(int id);
}
