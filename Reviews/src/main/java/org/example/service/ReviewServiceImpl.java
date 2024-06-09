package org.example.service;

import org.example.dto.ReviewMessage;
import org.example.model.Review;
import org.example.repository.ReviewsRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService{

    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewServiceImpl.class);
    @Autowired
    private ReviewsRepo reviewsRepo;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public String healthcheck(){
        return "Health status is Ok";
    }

    @Override
    public Review addReview(Review review,ReviewMessage reviewMessage) {
        rabbitTemplate.convertAndSend("","review-message-queue",reviewMessage);
        Optional<Review> existingReview = reviewsRepo.findById(review.getId());
        if(existingReview.isPresent()){
            existingReview.get().setRating(review.getRating());
            existingReview.get().setComment(review.getComment());
            existingReview.get().setBookId(review.getBookId());
            return  reviewsRepo.save(existingReview.get());
        }
        reviewsRepo.save(review);
        LOGGER.info("Added Review");
        return review;
    }

    @Override
    public List<Review> getAllReviews(){
        return reviewsRepo.findAll();
    }

    @Override
    public ReviewMessage sendMessage(Review review){
        ReviewMessage reviewMessage = new ReviewMessage();
        reviewMessage.setBookId(review.getBookId());
        reviewMessage.setId(review.getId());
        reviewMessage.setComment(review.getComment());
        reviewMessage.setRating(review.getRating());
        return reviewMessage;
    }
}
