package com.academy.awards.controller;


import com.academy.awards.dto.Response;
import com.academy.awards.service.MovieService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.HeaderParam;
import java.time.LocalDateTime;
import java.util.Map;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping( "/api/movie" )
public class MovieController
{

    private final MovieService movieService;

    @ModelAttribute
    public void setResponseHeader(HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache");
    }


    @Operation( summary =  "Add file name for import all file movies, for example is present filename: academy_awards.csv" )
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping( value = "/upload-file" )
    public ResponseEntity<Response> addMoviesByFile(
             @RequestParam( name = "fileName" , required = true ) final String fileName){

        movieService.addAllMoviesByFile(fileName );

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .status(HttpStatus.OK)
                        .message("File movies uploaded correctly")
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @Operation( summary =  "Update score for a particular movie, set idMovie and score, ( the score can be between 10 and 99 )" )
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping( value = "/give-rate" )
    public ResponseEntity<Response> giveRateToMovies (@RequestParam( name = "idMovie" , required = true ) final Long idMovie,
                                                      @RequestParam( name = "rate" , required = true ) final Integer rate
    ){
        movieService.giveScoreToMovies(idMovie, rate );

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .status(HttpStatus.OK)
                        .message("Record was updated correctly")
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @Operation( summary =  "Get result if the film has an oscar" )
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping( value = "/film-oscar" )
    public ResponseEntity<Response> filmGotAnOscar(@RequestParam( name = "nominee" , required = true ) final String nominee) {

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .status(HttpStatus.OK)
                        .data(Map.of("film-oscar", movieService.filmGotAnOscar(nominee) ))
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @Operation( summary =  "Return movies by top-rate, for example first 10 or first 20, insert a limit between 1 and 100" )
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping( value = "/top-rate" )
    public ResponseEntity<Response> findByTopRate(@RequestParam( name = "limit" , required = true ) final Integer limit) {

       return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .status(HttpStatus.OK)
                        .data(Map.of("top-rate", movieService.findByTopRate(limit) ))
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }













/*
        @Operation( summary =  "TEST " )
        @SecurityRequirement(name = "Bearer Authentication")
        @PreAuthorize("hasRole('ROLE_ADMIN')")
        @GetMapping( value = "/top-rate" )
        public ResponseEntity<Response> findByTopRate(
                @RequestParam( name = "limit" , required = true ) final Integer limit
        ) {
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .status(HttpStatus.OK)
                            .data(Map.of("top-rate", movieService.findByTopRate(limit) ))
                            .statusCode(HttpStatus.OK.value())
                            .build()
            );
        }
*/


}
