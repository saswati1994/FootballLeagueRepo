package com.football.LeagueMatch.Service;

import com.football.LeagueMatch.Dto.ResponseDTO;
import com.football.LeagueMatch.Entity.Country;
import com.football.LeagueMatch.Entity.League;
import com.football.LeagueMatch.Entity.Standing;
import com.football.LeagueMatch.Proxy.FootballLeagueProxyAPI;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FootballLeagueServiceTest {

    @InjectMocks
    FootballLeagueService footballLeagueService;
    @Mock
    FootballLeagueProxyAPI footballLeagueProxyAPI;


    @Test
    public void testGetStandingsNegative() throws Exception {

        List<Country> countryList =  Arrays.asList(new Country("6","USA"),
                (new Country("8","Thailand")));
        when(footballLeagueProxyAPI.getCountries("get_countries","12dfgh6y")).thenReturn(countryList);
        try {
            ResponseDTO response = footballLeagueService.getStandings("India", "RoyalLeague", "Royals");
        }catch (Exception ex){
            assertThat(ex.getMessage().equals("country not found"));
        }
    }

    @Test
    public void testGetCountryDetails(){
        List<Country> countryList =  Arrays.asList(new Country("1","India"),
                (new Country("2","England")));

        when(footballLeagueProxyAPI.getCountries(any(), any())).thenReturn(countryList);
        Country country = footballLeagueService.getCountryDetails("India");
        assertThat(country.getCountry_id().equals("1"));
    }

    @Test
    public void testGetLeagueDetails(){
        List<League>  leagueList = Arrays.asList(new League("1","India","11","RoyalLeague"));
        when(footballLeagueProxyAPI.getCompititions(argThat(arg-> arg.equals("get_leagues")), argThat(arg->arg.equals("1")), anyString())).thenReturn(leagueList);
        String leagueId = footballLeagueService.getLeagueDetails("1","RoyalLeague");
        assertThat(leagueId.equals("11"));
    }

    @Test
    public void testGetStandingDetails(){
        List<Standing> standingsList = Arrays.asList(new Standing("India","11","RoyalLeague","111","Royals","xyz"));
        when(footballLeagueProxyAPI.getStandings(argThat(arg-> arg.equals("get_standings")), argThat(arg->arg.equals("11")), any())).thenReturn(standingsList);
        Standing standing = footballLeagueService.getStandingDetails("11","Royals");
        System.out.println(standing);
        assertThat(standing.getTeam_name().equals("Royals"));
    }

}
