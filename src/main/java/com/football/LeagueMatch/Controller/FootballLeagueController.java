package com.football.LeagueMatch.Controller;

import com.football.LeagueMatch.Dto.ResponseDTO;
import com.football.LeagueMatch.LeagueExceptions.NotFoundException;
import com.football.LeagueMatch.Service.FootballLeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FootballLeagueController {

    @Autowired
    FootballLeagueService footballLeagueService;

    /**
     * This Controller deals with giving standing information based on following input
     * @param countryName
     * @param leagueName
     * @param teamName
     * @return
     * @throws NotFoundException
     */
    @GetMapping("/getleagueInfo")
    public ResponseEntity<ResponseDTO>  getStandings(@RequestParam(name = "countryName") String countryName,
                                    @RequestParam(name = "leagueName") String leagueName,
                                    @RequestParam(name = "teamName") String teamName) throws NotFoundException {

        return new ResponseEntity(footballLeagueService.getStandings(countryName,leagueName,teamName), HttpStatus.OK);

    }
}
