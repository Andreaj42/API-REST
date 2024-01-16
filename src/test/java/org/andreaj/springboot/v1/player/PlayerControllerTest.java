package org.andreaj.springboot.v1.player;

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

import org.andreaj.springboot.v1.dao.PlayerRepository;
import org.andreaj.springboot.v1.domain.Player;
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
class PlayerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private PlayerRepository playerRepository;

	@Test
	void testAllPlayers() throws Exception {
		this.mockMvc
				.perform(get("/api/v1/players"))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.length()", is(6)));
	}

	@Test
	void testOnePlayer() throws Exception {
		this.mockMvc
				.perform(get("/api/v1/players/1"))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name", is("Kylian Mbappé")));
	}

	@Test
	void testCreatePlayer() throws Exception {
		Player player = new Player();
		player.setName("Lionel Messi");

		ObjectMapper mapper = new ObjectMapper();
		byte[] playerAsBytes = mapper.writeValueAsBytes(player);

		this.mockMvc
				.perform(
						post("/api/v1/players")
								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON).content(playerAsBytes))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name", is("Lionel Messi")));

		assertEquals(7, this.playerRepository.count());

		Collection<Player> players = playerRepository.findAll();

		boolean found = false;

		for (Player currentPlayer : players) {

			if (currentPlayer.getName().equals("Lionel Messi")) {

				found = true;

				playerRepository.delete(currentPlayer);
			}
		}

		assertTrue(found);
	}

	@Test
	void testDeletePlayer() throws Exception {

		Player player = new Player();
		player.setName("Kylian Mbappe");

		this.playerRepository.save(player);

		Collection<Player> players = this.playerRepository.findAll();

		Long id = 0L;

		for (Player currentPlayer : players) {

			if (currentPlayer.getName().equals("Kylian Mbappe")) {
				id = currentPlayer.getId();
			}
		}

		this.mockMvc.perform(
				delete("/api/v1/players/" + id)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		assertEquals(6, this.playerRepository.count());
	}

	@Test
	void testReplacePlayer() throws Exception {

		Player player = new Player();
		player.setName("Hugo Lloris");

		ObjectMapper mapper = new ObjectMapper();
		byte[] playerAsBytes = mapper.writeValueAsBytes(player);

		this.mockMvc.perform(
				put("/api/v1/players/1")
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(playerAsBytes))
				.andExpect(status().isOk());

		player = this.playerRepository.findById(1L).orElse(null);

		if (player != null) {
			assertEquals("Hugo Lloris", player.getName());
			player.setName("Kylian Mbappe");
			this.playerRepository.save(player);
		}
		else{
			fail("Player not found");
		}
	}
}
