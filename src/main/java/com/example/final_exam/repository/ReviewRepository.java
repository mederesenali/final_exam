package com.example.final_exam.repository;

import com.example.final_exam.model.Review;
import com.example.final_exam.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review,Integer> {
    List<Review> findAllByPlaceId(int id);

}
