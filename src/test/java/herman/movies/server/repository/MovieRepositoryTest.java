package herman.movies.server.repository;

import herman.movies.server.entity.Movie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by herman.kurniawan on 6/23/2018.
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {MovieRepository.class})
public class MovieRepositoryTest {
    @Autowired
    private IMovieRepository movieRepository;

    private String getTestFilePath() {
        return new ClassPathResource("/src/test/resources/movies-test.json").getPath();
    }

    @Test
    public void testGetAllMovies() {
        ReflectionTestUtils.setField(movieRepository, "fileDataLocation", getTestFilePath());

        List<Movie> movies = movieRepository.getAllMovies();

        assertEquals(9, movies.size());
    }
}