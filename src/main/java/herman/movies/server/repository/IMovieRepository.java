package herman.movies.server.repository;

import herman.movies.server.entity.Movie;

import java.util.List;

/**
 * Created by herman.kurniawan on 6/23/2018.
 */
public interface IMovieRepository {
    List<Movie> getAllMovies();

    Movie createMovie(Movie movie);

    Movie updateMovie(String title, Movie movie);

    boolean deleteMovie(String title);

    Movie getMovieByTitle(String title);
}
