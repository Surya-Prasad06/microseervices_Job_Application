package com.reviews.reviewsservicesms.reviews.implementation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.reviews.reviewsservicesms.reviews.Reviews;
import com.reviews.reviewsservicesms.reviews.ReviewsRepository;
import com.reviews.reviewsservicesms.reviews.ReviewsService;

@Service
public class ReviewsServiceImplementation implements ReviewsService {

    @Autowired
    private ReviewsRepository reviewsRepository;

    @Override
    public List<Reviews> getallreviews(Long companyId) {
        List<Reviews> reviews = reviewsRepository.findByCompanyId(companyId);
        return reviews;

    }

    @Override
    public boolean addReview(Long companyId, Reviews reviews) {

        if (companyId != null && reviews != null) {
            reviews.setCompanyId(companyId);
            reviewsRepository.save(reviews);
            return true;
        } else {

            return false;
        }
    }

    @Override
    public Reviews getReview(Long reviewId) {

        return reviewsRepository.findById(reviewId).orElse(null);
    }

    @Override
    public boolean updateReview(Long reviewId, Reviews updatedReviews) {
        Reviews reviews = reviewsRepository.findById(reviewId).orElse(null);
        if (reviewId != null) {
            reviews.setTitle(updatedReviews.getTitle());
            reviews.setDescription(updatedReviews.getDescription());
            reviews.setRating(updatedReviews.getRating());
            reviews.setCompanyId(updatedReviews.getCompanyId());
            reviewsRepository.save(reviews);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteReview(Long reviewId) {
        Reviews reviews = reviewsRepository.findById(reviewId).orElse(null);
        if (reviews != null) {
            reviewsRepository.delete(reviews);
            return true;
        } else {
            return false;
        }
    }

}
