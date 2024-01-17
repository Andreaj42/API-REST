package org.andreaj.springboot.v1.team;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collection;

import org.andreaj.springboot.v1.dao.TeamRepository;
import org.andreaj.springboot.v1.domain.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class TeamControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private TeamRepository teamRepository;

	@Test
	void testAllTeams() throws Exception {
		this.mockMvc
				.perform(get("/api/v1/teams"))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.length()", is(2)));
	}

	@Test
	void testOneTeam() throws Exception {
		this.mockMvc
				.perform(get("/api/v1/teams/1"))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name", is("France")));
	}

	@Test
	void testCreateTeam() throws Exception {
		Team team = new Team();
		team.setName("Allemagne");

		ObjectMapper mapper = new ObjectMapper();
		byte[] teamAsBytes = mapper.writeValueAsBytes(team);

		this.mockMvc
				.perform(
						post("/api/v1/teams")
								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON).content(teamAsBytes))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name", is("Allemagne")));

		assertEquals(3, this.teamRepository.count());

		Collection<Team> teams = teamRepository.findAll();
		boolean found = false;
		for (Team currentTeam : teams) {
			if (currentTeam.getName().equals("Allemagne")) {
				found = true;
				teamRepository.delete(currentTeam);
			}
		}
		assertTrue(found);
	}

	@Test
	void testDeleteTeam() throws Exception {
		Team team = new Team();
		team.setName("Allemagne");
		this.teamRepository.save(team);

		Collection<Team> teams = this.teamRepository.findAll();
		for (Team currentTeam : teams) {
			if (currentTeam.getName().equals("Allemagne")) {
				this.mockMvc.perform(
					delete("/api/v1/teams/" + currentTeam.getId())
							.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
			}
		}
		assertEquals(2, this.teamRepository.count());
	}

	@Test
	void testReplaceTeam() throws Exception {
		Team team = new Team();
		team.setStadium("Stade d'Espagne");

		ObjectMapper mapper = new ObjectMapper();
		byte[] teamAsBytes = mapper.writeValueAsBytes(team);

		this.mockMvc.perform(
				put("/api/v1/teams/2")
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(teamAsBytes))
				.andExpect(status().isOk());

		team = this.teamRepository.findById(2L).orElse(null);

		if (team != null) {
			assertEquals("Stade d'Espagne", team.getStadium());
			team.setStadium("Stade Santiago Bernabéu");
			this.teamRepository.save(team);
		}
		else{
			fail("Team not found");
		}
	}
}
