package com.football.LeagueMatch.LeagueExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FootballLeagueControllerAdvice {
    /**
     * This handles NotFound Exception
     * @param ex
     * @return
     */
    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<String> handleException(NotFoundException ex) {

        return new ResponseEntity<String>(ex.msg, HttpStatus.NOT_FOUND);

    }


    @ExceptionHandler(Exception.class)
    public final ResponseEntity<String> handleException(Exception ex) {

        return new ResponseEntity<String>("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
