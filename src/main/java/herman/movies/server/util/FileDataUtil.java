package herman.movies.server.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import herman.movies.server.exception.FileDataAccessException;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by herman.kurniawan on 6/23/2018.
 */
public class FileDataUtil {
    public static <T> T loadMovieDataFromFile(String fileDataLocation, TypeReference typeReference) {
        File file = new File(fileDataLocation);
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(file, typeReference);
        } catch (IOException err) {
            err.printStackTrace();
            throw new FileDataAccessException(err);
        }
    }

    public static <T> boolean writeMovieDataToFile(String fileDataLocation, List<T> list) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        try {
            writer.writeValue(new File(fileDataLocation), list);
        } catch (IOException err) {
            err.printStackTrace();
            throw new FileDataAccessException(err);
        }
        return true;
    }
}
