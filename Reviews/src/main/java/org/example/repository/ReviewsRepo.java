package org.example.repository;

import org.example.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewsRepo extends JpaRepository<Review,Integer> {
}
