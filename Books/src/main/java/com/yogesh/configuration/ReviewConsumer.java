package com.yogesh.configuration;

import com.yogesh.dto.ReviewMessage;
import com.yogesh.model.Book;
import com.yogesh.service.BooksSevice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReviewConsumer {

    @Autowired
    BooksSevice booksSevice;

    @RabbitListener(queues = {"review-message-queue"})
    public void seeReviews(ReviewMessage reviewMessage){
        booksSevice.addReview(reviewMessage);
        System.out.println("Done");
    }
}
