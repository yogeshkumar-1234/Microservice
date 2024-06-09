package org.example.service;

import org.example.dto.ReviewMessage;
import org.example.model.Review;

import java.util.List;

public interface ReviewService {

    public String healthcheck();

    public Review addReview(Review review,ReviewMessage reviewMessage);

    public List<Review> getAllReviews();

    public ReviewMessage sendMessage(Review review);
}
