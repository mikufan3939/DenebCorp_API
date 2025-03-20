package com.example.api.service;

import com.example.api.model.Rating;
import com.example.api.repository.RatingRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    public Rating createRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    public List<Rating> getAll() {
        return ratingRepository.findAll();
    }

    public Rating getById(long id) {
        return ratingRepository.findById(id).orElseThrow();
    }

    public void deleteById(long id) {
        ratingRepository.deleteById(id);
    }

    public Rating updateRating(long id, Rating rating) {
        Rating selectedRating = ratingRepository.findById(id).orElseThrow();
        selectedRating.setDescription(rating.getDescription());
        selectedRating.setScore(rating.getScore());
        return ratingRepository.save(selectedRating);
    }
}
