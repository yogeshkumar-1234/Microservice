package org.example.controller;

import org.example.dto.ReviewMessage;
import org.example.service.ReviewService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.example.model.Review;

import java.util.List;

@RestController
@RequestMapping("/reviews/v1")
@Validated
public class ReviewController {


    @Autowired
    private ReviewService reviewService;

    @GetMapping("/healthcheck")
    public ResponseEntity<String> healthcheck(){
        return ResponseEntity.ok().body(reviewService.healthcheck());
    }

    @PostMapping("/add")
    public ResponseEntity<Review> addReview(@RequestBody Review review){
        ReviewMessage  reviewMessage = reviewService.sendMessage(review);
        return ResponseEntity.ok().body(reviewService.addReview(review,reviewMessage));
    }

    @GetMapping("/")
    public ResponseEntity<List<Review>> getAllReviews(){
        return ResponseEntity.ok().body(reviewService.getAllReviews());
    }

}
