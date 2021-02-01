package com.football.LeagueMatch.controller;

import com.football.LeagueMatch.Controller.FootballLeagueController;
import com.football.LeagueMatch.Dto.ResponseDTO;
import com.football.LeagueMatch.Service.FootballLeagueService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;



@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {FootballLeagueController.class})
@WebMvcTest
public class FootBallControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    FootballLeagueService footballLeagueService;

    @InjectMocks
    FootballLeagueController footballLeagueController;


    @Test
    public void testGetStandingsSuccessful() throws Exception {
        ResponseDTO responseDTO = new ResponseDTO("1","England","10","Championship","101","Norwich","");
        when(footballLeagueService.getStandings("England","Championship","Norwich")).thenReturn(responseDTO);


        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.get("/getleagueInfo?countryName=England&leagueName=Championship&teamName=Norwich")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
         assertThat(result.getResponse().getStatus()==200);
         assertThat(result.getResponse().getContentAsString().equals("{\"countryId\":\"1\",\"countryName\":\"England\",\"leagueId\":\"10\",\"leagueName\":\"Championship\",\"teamId\":\"101\",\"teamName\":\"Norwich\",\"overAllLeaguePosition\":\"\"}\n"));
    }

   @Test
   public void testGetStandingsNegative() throws Exception {

   }

}
