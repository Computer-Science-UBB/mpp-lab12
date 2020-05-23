package movieRental.core.service;

import movieRental.core.model.Movie;
import movieRental.core.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MovieService implements MovieServiceInterface {

    @Autowired
    private MovieRepository movieRepository;

    public static final Logger log = LoggerFactory.getLogger(MovieService.class);

    @Override
    public List<Movie> getAll() {
        log.trace("filter movies - method entered");
        log.trace("filter movies - method ended");
        return movieRepository.findAll();
    }

    @Override
    public Movie save(Movie entity) {
        log.trace("save movie - method entered");
        Movie result = movieRepository.save(entity);
        log.debug("save - added", result);
        log.trace("save movie - method ended");
        return result;
    }

    @Override
    public Boolean deleteById(Long id) {
        log.trace("delete movie - method entered");
        AtomicBoolean deleted = new AtomicBoolean(false);
        movieRepository.findById(id).ifPresent(movie -> {
            movieRepository.delete(movie);
            deleted.set(true);
            log.debug("delete - deleted", movie);
        });
        log.trace("delete movie - method ended");
        return deleted.get();
    }

    @Override
    @Transactional
    public Movie update(Long id, Movie entity) {
        log.trace("update movie - method entered");
        movieRepository.findById(id).ifPresent(movie -> {
            movie.setTitle(entity.getTitle());
            movie.setDescription(entity.getDescription());
            movie.setRating(entity.getRating());
            movie.setPrice(entity.getPrice());
            log.debug("update - updated ", movie);
        });
        log.trace("update movie - method ended");
        return entity;
    }

    @Override
    public List<Movie> filter(String title) {
        log.trace("filter movies by title - method entered");
        log.trace("filter movies by title - method ended");
        return movieRepository.findAll().stream()
                .filter(movie -> movie.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Movie> filterByDescription(String value) {
        log.trace("filter movies by description - method entered");
        log.trace("filter movies by description - method ended");
        return movieRepository.findAll().stream()
                .filter(movie -> movie.getDescription().toLowerCase().contains(value.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Movie> filterByPrice(int price) {
        log.trace("filter movies by price - method entered");
        log.trace("filter movies by price - method ended");
        return movieRepository.findAll().stream()
                .filter(movie -> movie.getPrice() == price)
                .collect(Collectors.toList());
    }

    @Override
    public List<Movie> filterByRating(int rating) {
        log.trace("filter movies by rating - method entered");
        log.trace("filter movies by rating - method ended");
        return movieRepository.findAll().stream()
                .filter(movie -> movie.getRating() == rating)
                .collect(Collectors.toList());
    }
    @Override
    public List<Movie> getAllSortedAscendingByFields(String... fields) {
        log.trace("getAllSortedAscendingByFields - method entered: fields={}", (Object[]) fields);
        Sort sort = new Sort(Sort.Direction.ASC, fields);
        Iterable<Movie> movies = movieRepository.findAll(sort);
        log.trace("getAllSortedAscendingByFields - method finished");
        return StreamSupport.stream(
                movies.spliterator(),
                false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Movie> getAllSortedDescendingByFields(String... fields) {
        log.trace("getAllSortedDescendingByFields - method entered: fields={}", (Object[]) fields);
        Sort sort = new Sort(Sort.Direction.DESC, fields);
        Iterable<Movie> movies = movieRepository.findAll(sort);
        log.trace("getAllSortedDescendingByFields - method finished");
        return StreamSupport.stream(
                movies.spliterator(),
                false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Movie> filter(Movie movie, Movie sortMovie) {
        log.trace("filter movies - method entered");
        log.trace("filter movies - method ended");
        Sort sort = buildSort(sortMovie);
        if (movie.getRating() == -1)
            return movieRepository.findMoviesByTitleContainingAndDescriptionContaining(movie.getTitle(), movie.getDescription(), sort);
        return movieRepository.findMoviesByTitleContainingAndDescriptionContainingAndRatingAndPriceEquals(movie.getTitle(), movie.getDescription(), movie.getRating(),movie.getPrice(), sort);
    }
    private Sort buildSort(Movie sortMovie) {
        List<String> attributes = new LinkedList<>();
        if (!sortMovie.getTitle().equals("-")) {
            attributes.add("title");
        }

        if (!sortMovie.getDescription().equals("-")) {
            attributes.add("description");
        }

        if (sortMovie.getRating() != 0) {
            attributes.add("rating");
        }

        if (sortMovie.getPrice() != 0) {
            attributes.add("price");
        }

        if (sortMovie.getId() == 1)
            return new Sort(Sort.Direction.ASC,  attributes);
        return new Sort(Sort.Direction.DESC,  attributes);

    }

    @Override
    public Page<Movie> getPage(PageRequest pageRequest) {
        log.trace("get page movies - method entered");
        log.trace("get page movies - method ended");
        return movieRepository.findAll(pageRequest);
    }

    @Override
    public List<Movie> getSorted(String params) {
        log.trace("sort movies - method entered");
        log.trace("sort movies - method ended");
        return movieRepository.findAll(Sort.by(params.split("_")));
    }
}
