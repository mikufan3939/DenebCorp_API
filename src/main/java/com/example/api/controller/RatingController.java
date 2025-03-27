package com.example.api.controller;

import com.example.api.model.Rating;
import com.example.api.service.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/rating")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @PostMapping("")
    public Rating createRating(@RequestBody Rating rating) {
        return ratingService.createRating(rating);
    }

    @GetMapping("")
    public List<Rating> getRatings() {
        return ratingService.getAll();
    }

    @GetMapping("/{id}")
    public Rating getRating(@PathVariable long id) {
        return ratingService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteRating(@PathVariable long id) {
        ratingService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Rating updateRating(@PathVariable long id, @RequestBody Rating rating) {
        return ratingService.updateRating(id, rating);
    }
}
