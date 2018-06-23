package herman.movies.server.controller;

import herman.movies.server.entity.Movie;
import herman.movies.server.exception.EntityAlreadyExistsException;
import herman.movies.server.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by herman.kurniawan on 6/23/2018.
 */
@RequestMapping("/api")
@RestController
public class MovieController {
    @Autowired
    IMovieService movieService;

//    @RequestMapping(path="/movies", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @GetMapping(path="/movies")
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @PostMapping(path="/movies")
    public Movie createMovie(@Valid @RequestBody Movie movie) {
        return movieService.createMovie(movie);
    }

    @PutMapping(path="/movies/{id}")
    public Movie updateMovie(@PathVariable(value = "title") String title, @Valid @RequestBody Movie movie) {
        return movieService.updateMovie(title, movie);
    }

    @DeleteMapping(path="/movies/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable(value = "title") String title) {
        if (movieService.deleteMovie(title)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(path="/movies/{id}")
    public Movie getMovieById(@PathVariable(value = "title") String title) {
        return movieService.getMovieById(title);
    }
}
