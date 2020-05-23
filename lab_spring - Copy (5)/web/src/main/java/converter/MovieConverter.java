package converter;

import dto.MovieDto;
import movieRental.core.model.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieConverter extends BaseConverter<Movie, MovieDto> {
    @Override
    public Movie convertDtoToModel(MovieDto dto) {
        Movie movie = Movie.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .rating(dto.getRating())
                .price(dto.getPrice())
                .build();
        movie.setId(dto.getId());
        return movie;
    }

    @Override
    public MovieDto convertModelToDto(Movie movie) {
        MovieDto dto = MovieDto.builder()
                .title(movie.getTitle())
                .description(movie.getDescription())
                .rating(movie.getRating())
                .price(movie.getPrice())
                .build();
        dto.setId(movie.getId());
        return dto;
    }
}
