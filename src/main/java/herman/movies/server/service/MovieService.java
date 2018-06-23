package herman.movies.server.service;

import herman.movies.server.repository.IMovieRepository;
import herman.movies.server.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by herman.kurniawan on 6/23/2018.
 */
@Service
public class MovieService implements IMovieService {
    @Autowired
    private IMovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.getAllMovies();
    }

    public Movie createMovie(Movie movie) {
        return movieRepository.createMovie(movie);
    }

    public Movie updateMovie(String title, Movie movie) {
        return movieRepository.updateMovie(title, movie);
    }

    public boolean deleteMovie(String title) {
        return movieRepository.deleteMovie(title);
    }

    public Movie getMovieById(String title) {
        return movieRepository.getMovieByTitle(title);
    }
}
