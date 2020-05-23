package controller;

import converter.BaseConverter;
import dto.ClientsDto;
import dto.MovieDto;
import dto.MoviesDto;
import movieRental.core.model.Movie;
import movieRental.core.service.MovieServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MovieController {
    @Autowired
    private MovieServiceInterface movieService;

    @Autowired
    private BaseConverter<Movie, MovieDto> movieConverter;

    public static final Logger log = LoggerFactory.getLogger(MovieController.class);

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    List<MovieDto> getAll(){
        log.trace("getAll --- method entered");
        log.trace("getAll --- method ended");
        return movieConverter
                .convertModelsToDtos(movieService.getAll());
    }

    @RequestMapping(value = "/movies", method = RequestMethod.POST)
    MovieDto save(@RequestBody MovieDto movieDto){
        log.trace("save --- method entered");
        log.trace("save --- method ended");
        return movieConverter.convertModelToDto(movieService.save(
                movieConverter.convertDtoToModel(movieDto)));
    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.PUT)
    MovieDto update(@PathVariable Long id, @RequestBody MovieDto movieDto){
        log.trace("update --- method entered");
        log.trace("update --- method ended");
        return movieConverter.convertModelToDto(movieService.update(id,
                movieConverter.convertDtoToModel(movieDto)));
    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> delete(@PathVariable Long id){
        log.trace("delete --- method entered");
        log.trace("delete --- method ended");
        if (movieService.deleteById(id))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/movies/{title}/{description}/{rating}/{price}", method = RequestMethod.POST)
    List<MovieDto> filter(@PathVariable String title, @PathVariable String description, @PathVariable int rating, @PathVariable int price, @RequestBody MovieDto sortMovie) {
        Movie auxMovie = Movie.builder()
                .title(title)
                .description(description)
                .rating(rating)
                .price(price)
                .build();
        return movieConverter.
                convertModelsToDtos(movieService.filter(auxMovie,
                        movieConverter.convertDtoToModel(sortMovie)));
    }

    @RequestMapping(value = "/movies/page", method = RequestMethod.GET)
    Page<MovieDto> getPage(@RequestParam(defaultValue = "0") int page) {
        PageRequest pageRequest = new PageRequest(page, 3);
        Page<Movie> result = movieService.getPage(pageRequest);
        return new PageImpl<MovieDto>(
                result.getContent().stream()
                        .map(movie -> movieConverter.convertModelToDto(movie))
                        .collect(Collectors.toList()),
                pageRequest,
                result.getTotalPages());
    }

}
