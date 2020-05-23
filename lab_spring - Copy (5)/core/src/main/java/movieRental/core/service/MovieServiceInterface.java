package movieRental.core.service;

import movieRental.core.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface MovieServiceInterface extends BaseServiceInterface<Movie, Long> {

    List<Movie> filter(String value);
    List<Movie> filterByDescription(String value);
    List<Movie> filterByPrice(int price);
    List<Movie> filterByRating(int rating);
    List<Movie> getAllSortedAscendingByFields(String... fields);
    List<Movie> getAllSortedDescendingByFields(String... fields);
    Page<Movie> getPage(PageRequest pageRequest);
    List<Movie> filter(Movie movie, Movie sortMovie);
}
