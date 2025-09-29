package com.reviews.reviewsservicesms.reviews;

import java.util.List;

public interface ReviewsService {
    List<Reviews> getallreviews(Long companyId);

    boolean addReview(Long companyId, Reviews reviews);

    Reviews getReview(Long reviewId);

    boolean updateReview(Long reviewId, Reviews reviews);

    boolean deleteReview(Long reviewId);
}
