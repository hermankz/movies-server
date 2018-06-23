package herman.movies.server.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import herman.movies.server.entity.Movie;
import herman.movies.server.exception.EntityAlreadyExistsException;
import herman.movies.server.exception.EntityNotFoundException;
import herman.movies.server.util.FileDataUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by herman.kurniawan on 6/23/2018.
 */
@Repository
public class MovieRepository implements IMovieRepository {
    @Value("${file.data.location}")
    private String fileDataLocation;
    private TypeReference<List<Movie>> typeReference = new TypeReference<List<Movie>>() {
    };

    private List<Movie> loadMovieDataFromFile() {
        return FileDataUtil.loadMovieDataFromFile(fileDataLocation, typeReference);
    }

    private boolean writeMovieDataToFile(List<Movie> list) {
        return FileDataUtil.writeMovieDataToFile(fileDataLocation, list);
    }

    private static Movie findMovieByTitle(List<Movie> list, String title) {
        for (Movie movie : list) {
            if (movie.getTitle().equals(title)) {
                return movie;
            }
        }
        return null;
    }

    public List<Movie> getAllMovies() {
        return loadMovieDataFromFile();
    }

    public Movie createMovie(Movie movie) {
        // load everything from file first
        List<Movie> list = loadMovieDataFromFile();

        // find existing movie
        Movie existingMovie = findMovieByTitle(list, movie.getTitle());
        if (existingMovie != null) {
            throw new EntityAlreadyExistsException("Movie", "title", movie.getTitle());
        }

        // add new movie
        list.add(movie);

        // save to file override
        writeMovieDataToFile(list);

        return movie;
    }

    public Movie updateMovie(String title, Movie movie) {
        // load everything from file first
        List<Movie> list = loadMovieDataFromFile();

        // find existing movie
        Movie existingMovie = findMovieByTitle(list, title);
        if (existingMovie == null) {
            throw new EntityNotFoundException("Movie", "title", title);
        }

        // update existing movie
        existingMovie.setTitle(movie.getTitle());
        existingMovie.setDirector(movie.getDirector());
        existingMovie.setReleaseDate(movie.getReleaseDate());
        existingMovie.setType(movie.getType());

        // save to file override
        writeMovieDataToFile(list);

        return movie;
    }

    public boolean deleteMovie(String title) {
        // load everything from file first
        List<Movie> list = loadMovieDataFromFile();

        // find existing movie
        Movie existingMovie = findMovieByTitle(list, title);
        if (existingMovie == null) {
            throw new EntityNotFoundException("Movie", "title", title);
        }

        // delete existing movie
        list.remove(existingMovie);

        // save to file override
        writeMovieDataToFile(list);

        return true;
    }

    public Movie getMovieByTitle(String title) {
        // load everything from file first
        List<Movie> list = loadMovieDataFromFile();

        // find existing movie
        Movie existingMovie = findMovieByTitle(list, title);
        if (existingMovie == null) {
            throw new EntityNotFoundException("Movie", "title", title);
        }
        return existingMovie;
    }
}
