package com.football.LeagueMatch.Proxy;

import com.football.LeagueMatch.Entity.League;
import com.football.LeagueMatch.Entity.Country;
import com.football.LeagueMatch.Entity.Standing;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient
("apiv2.apifootball.com")
public interface FootballLeagueProxyAPI {


    @RequestMapping(method = RequestMethod.GET)
    List<Country> getCountries(@RequestParam(value = "action") String action, @RequestParam(value = "APIkey") String apiKey);

    @RequestMapping(method = RequestMethod.GET)
    List<League> getCompititions(@RequestParam(value = "action") String action, @RequestParam(value = "country_id") String country_id,
                                 @RequestParam(value = "APIkey") String apiKey);

    @RequestMapping(method = RequestMethod.GET)
    List<Standing> getStandings(@RequestParam(value = "action") String action, @RequestParam(value = "league_id") String league_id,
                                @RequestParam(value = "APIkey") String apiKey);
}
