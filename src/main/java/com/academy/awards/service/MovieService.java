package com.academy.awards.service;

import com.academy.awards.dto.MovieDTO;
import com.academy.awards.entitys.CategoryMovie;
import com.academy.awards.entitys.Movie;
import com.academy.awards.exception.ApiRequestException;
import com.academy.awards.repository.CategoryMovieRepository;
import com.academy.awards.repository.MovieRepository;
import com.academy.awards.utils.ServiceUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.*;


@Service
@Slf4j
@RequiredArgsConstructor
@Data
public class MovieService {


    private static final Integer SCORE_LIMIT = 99;
    private static final Integer SEARCH_TOP_RATE_LIMIT = 100;

    private final MovieRepository movieRepository;
    private final CategoryMovieRepository categoryMovieRepository;
    private final ServiceUtil serviceUtil;


    public void addAllMoviesByFile(String nameFile){
       log.info( "addMoviesByFile-RUN" );
        try {
            List<MovieDTO> listMovies = ServiceUtil.readFileMovies("/input/"+nameFile);

            HashMap<String, CategoryMovie> mapCategory = new HashMap<>();
            for(MovieDTO movieDTO : listMovies){

                if(!mapCategory.containsKey(movieDTO.getCategory())){
                    // need to add new category in the system
                    CategoryMovie category = CategoryMovie.builder()
                            .description(movieDTO.getCategory())
                            .build();
                    category = categoryMovieRepository.save(category);
                    //I saved category and reuse when I save the movie
                    mapCategory.put(category.getDescription(),category);
                }
                // Add new movie in dataBase
                Movie movie = Movie.builder()
                        .year(movieDTO.getYear() )
                        .rate(movieDTO.getRate())
                        .nominee( movieDTO.getNominee())
                        .additionalInfo( movieDTO.getAdditionalInfo())
                        .categoryId ( mapCategory.get(movieDTO.getCategory()).getCategoryId() )
                        .dateInsert( new Date() )
                        .won( movieDTO.getWon() )
                        .build();
                movieRepository.save(movie);
                log.info(" Movie -> id: {}", movie.getMovieId() );
            }

        } catch (FileNotFoundException e) {
            log.error("FileNotFoundException in addMoviesByFile ", e);
            throw new ApiRequestException("Failed load file, File not exist");
        }
        log.info( "addMoviesByFile-End" );
    }



    public void giveScoreToMovies(Long idMovie, Integer rate) {
        log.info( "giveRateToMovies-RUN" );

        List<Movie> listMovie = movieRepository.findAllById(Collections.singleton(idMovie));
        if(listMovie.size() == 0){
            throw new ApiRequestException("Movie not present with id: "+ idMovie);
        }
        if(rate > SCORE_LIMIT ){
            throw new ApiRequestException("The maximum score is "+ SCORE_LIMIT  );
        }
        Movie movie = listMovie.get(0);
        movie.setRate(rate);
        movie.setDateUpdate( new Date() );
        movieRepository.save(movie);
        log.info( "giveRateToMovies-End" );
    }


    public List<Movie> findByTopRate(Integer limit) {
        log.info( "findByTopRate-RUN" );
        if(limit > SEARCH_TOP_RATE_LIMIT ){
            throw new ApiRequestException("The maximum limit is "+ SEARCH_TOP_RATE_LIMIT );
        }
        List<Movie> movies = movieRepository.findByTopRate(limit);
        log.info( "findByTopRate-End" );
        return movies;
    }

    public List<Movie> filmGotAnOscar(String nominee) {
        log.info( "filmGotAnOscar-RUN" );
        List<Movie> listMovie = movieRepository.findByNominee(nominee);
        if(listMovie.size() == 0){
            throw new ApiRequestException("I don't find nothing with nominee: "+ nominee);
        }
        log.info( "filmGotAnOscar-End" );
        return listMovie;
    }


}