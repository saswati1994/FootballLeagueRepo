package com.football.LeagueMatch.Service;

import com.football.LeagueMatch.Dto.ResponseDTO;
import com.football.LeagueMatch.Entity.League;
import com.football.LeagueMatch.Entity.Country;
import com.football.LeagueMatch.Entity.Standing;
import com.football.LeagueMatch.LeagueExceptions.NotFoundException;
import com.football.LeagueMatch.Proxy.FootballLeagueProxyAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FootballLeagueService {

    @Value("${footballApi.apiKey}")
    private String apiKey;

    final String getCountryAction = "get_countries";
    final String getLeagueAction = "get_leagues";
    final String getStandingAction = "get_standings";

    @Autowired
    FootballLeagueProxyAPI footballLeagueProxyAPI;

    /**
     * getStandings method determines standing information
     * @param countryName
     * @param leagueName
     * @param teamName
     * @return
     * @throws NotFoundException
     */
    public ResponseDTO getStandings(String countryName,String leagueName,String teamName) throws NotFoundException {

        Country targetCountry = getCountryDetails(countryName);
        if(targetCountry != null){
            String targetCountryId = targetCountry.getCountry_id();
            String leagueId = getLeagueDetails(targetCountryId,leagueName);
            if(!leagueId.isEmpty()){
                Standing standing=  getStandingDetails(leagueId,teamName);
                if(standing != null){
                    ResponseDTO responseDTO = new ResponseDTO();
                    responseDTO.setCountryId(targetCountryId);
                    responseDTO.setCountryName(countryName);
                    responseDTO.setLeagueId(leagueId);
                    responseDTO.setLeagueName(leagueName);
                    responseDTO.setTeamId(standing.getTeam_id());
                    responseDTO.setTeamName(teamName);
                    responseDTO.setOverAllLeaguePosition(standing.getOverall_league_position());
                    return  responseDTO;
                }
                throw  new NotFoundException("Standing not found");

            }
            throw new NotFoundException("league not found");

        }
        throw new NotFoundException("country not found");
    }

    /**
     * getCountryDetails() deals with get country information based on country_name
     * @param countryName
     * @return
     */
    Country getCountryDetails(String countryName){
        List<Country> countryInfo =  footballLeagueProxyAPI.getCountries(getCountryAction, apiKey );

        final Optional<Country> targetCountry =  countryInfo.stream()
                .filter(country ->  country.getCountry_name().equals(countryName))
                .findFirst();
        if(targetCountry.isPresent()){
            return targetCountry.get();
        }else{
            return null;
        }
    }

    /**
     *  getLeagueDetails() provides League information
      * @param countryId
     * @param leagueName
     * @return
     */

    String getLeagueDetails(String countryId, String leagueName){
        List<League> leagues =  footballLeagueProxyAPI.getCompititions(getLeagueAction, countryId, apiKey);

        Optional<League> identifiedLeague = leagues.stream().filter(league -> league.getLeague_name().equals(leagueName)).findFirst();
        if(identifiedLeague.isPresent()){
            return identifiedLeague.get().getLeague_id();
        }else{
            return "";
        }
    }

    /**
     * getStandingDetails() deals with providing Standing information
     * @param leagueId
     * @param teamName
     * @return
     */
    Standing  getStandingDetails(String leagueId, String teamName){
        List<Standing> standingList= footballLeagueProxyAPI.getStandings(getStandingAction,leagueId,apiKey);
        Optional<Standing> identifiedStanding = standingList.stream().filter(standing -> standing.getTeam_name().equals(teamName)).findFirst();
        if(identifiedStanding.isPresent()){
            return identifiedStanding.get();
        }
       return null;
    }
}
