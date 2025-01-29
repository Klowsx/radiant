package com.example.radiant.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.radiant.Models.Review;
import com.example.radiant.Repositories.ReviewRepository;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public Review addReview(Review review) {
        review.setCreated_on(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    public Review updateReview(Long id, Review updatedReview) {
        Review existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reseña no encontrada"));

        existingReview.setRating(updatedReview.getRating());
        existingReview.setComment(updatedReview.getComment());
        existingReview.setUpdated_on(LocalDateTime.now());

        return reviewRepository.save(existingReview);
    }

    public void deleteReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reseña no encontrada"));
        reviewRepository.delete(review);
    }

    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }

}
