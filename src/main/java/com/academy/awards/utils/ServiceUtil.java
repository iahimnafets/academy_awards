package com.academy.awards.utils;

import com.academy.awards.dto.MovieDTO;
import com.academy.awards.entitys.Movie;
import com.academy.awards.exception.ApiRequestException;
import com.auth0.jwt.algorithms.Algorithm;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ServiceUtil {


    public static Algorithm algorithmUtilMethod(Environment env) throws NullPointerException{
        return Algorithm.HMAC256(env.getProperty("team1.app.jwtSecret").getBytes());
    }

    public static List<MovieDTO> readFileMovies(String pathFileName ) throws FileNotFoundException {
        List<MovieDTO> listMovies = new ArrayList<>();
        boolean considerOnlyRealValues = false;
        String baseDirectory = Paths.get("").toAbsolutePath().toString();

        try (CSVReader reader = new CSVReader(new FileReader(baseDirectory + pathFileName))) {
            String[] lineInArray;
            while ((lineInArray = reader.readNext()) != null) {

             // the first line is heading, I don't have to consider it
             if(considerOnlyRealValues) {
                 String[] yearAndRate = lineInArray[0].split(" ");
                 String rate = yearAndRate[1].replaceAll("[^0-9]", "");

                 MovieDTO movieDTO = MovieDTO.builder()
                         .year(yearAndRate[0].trim())
                         .rate(Integer.parseInt(rate))
                         .category(lineInArray[1].trim())
                         .nominee(lineInArray[2].trim())
                         .additionalInfo(lineInArray[3].trim())
                         .won(lineInArray[4].trim())
                         .build();
                 listMovies.add(movieDTO);
             }else {
                 considerOnlyRealValues = true;
             }
            }
        } catch (IOException e) {
            log.error("IOException in readFileMovies ", e);
            throw new ApiRequestException("Failed load file, file not exist");
        } catch (CsvValidationException e) {
            log.error("CsvValidationException in readFileMovies ", e);
            throw new ApiRequestException("Failed Validation CSV");
        }
        return listMovies;
    }

}
