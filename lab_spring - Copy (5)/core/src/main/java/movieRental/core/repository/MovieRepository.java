package movieRental.core.repository;

import movieRental.core.model.Movie;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface MovieRepository extends BaseRepository<Movie, Long> {
    List<Movie> findMoviesByTitleContainingAndDescriptionContaining(String title, String description, Sort sort);

    List<Movie> findMoviesByTitleContainingAndDescriptionContainingAndRatingAndPriceEquals(String title, String description, int rating, int price, Sort sort);

}
