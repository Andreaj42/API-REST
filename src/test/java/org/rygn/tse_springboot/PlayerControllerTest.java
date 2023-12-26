package org.rygn.tse_springboot;

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

import org.junit.jupiter.api.Test;
import org.rygn.tse_springboot.dao.PlayerRepository;
import org.rygn.tse_springboot.domain.Player;
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
public class PlayerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private PlayerRepository playerRepository;

	@Test
	public void testAllPlayers() throws Exception {

		this.mockMvc
				.perform(get("/players"))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.length()", is(2)));
	}

	@Test
	public void testOnePlayer() throws Exception {

		this.mockMvc
				.perform(get("/players/1"))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name", is("Christiano Ronaldo")));
	}

	@Test
	public void testCreatePlayer() throws Exception {

		Player player = new Player();
		player.setName("Lionel Messi");

		ObjectMapper mapper = new ObjectMapper();
		byte[] playerAsBytes = mapper.writeValueAsBytes(player);

		this.mockMvc
				.perform(
						post("/players")
								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON).content(playerAsBytes))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name", is("Lionel Messi")));

		assertEquals(4, this.playerRepository.count());

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
	public void testDeletePlayer() throws Exception {

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
				delete("/players/" + id)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		assertEquals(2, this.playerRepository.count());
	}

	@Test
	public void testReplacePlayer() throws Exception {

		Player player = new Player();
		player.setName("Hugo Lloris");

		ObjectMapper mapper = new ObjectMapper();
		byte[] playerAsBytes = mapper.writeValueAsBytes(player);

		this.mockMvc.perform(
				put("/players/1")
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
