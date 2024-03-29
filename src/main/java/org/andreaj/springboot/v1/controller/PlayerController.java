package org.andreaj.springboot.v1.controller;

import java.util.List;

import org.andreaj.springboot.v1.domain.Player;
import org.andreaj.springboot.v1.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {

	@Autowired
	private PlayerService playerService;

	@GetMapping("/api/v1/players")
	public List<Player> allPlayers() {

		return this.playerService.findAllPlayers();
	}

	@GetMapping("/api/v1/players/{id}")
	public Player onePlayer(@PathVariable Long id) {

		return this.playerService.findPlayer(id);
	}

	@PostMapping("/api/v1/players")
	public Player createPlayer(@RequestBody Player player) {

		return this.playerService.createPlayer(player);
	}

	@DeleteMapping("/api/v1/players/{id}")
	public void deletePlayer(@PathVariable Long id) {

		this.playerService.deletePlayer(id);
	}

	@PutMapping("/api/v1/players/{id}")
	Player replacePlayer(@RequestBody Player player, @PathVariable Long id) {

		Player foundPlayer = this.playerService.findPlayer(id);

		if (player != null && foundPlayer != null) {

			if (player.getName() != null) {
				foundPlayer.setName(player.getName());
			}
			if (player.getPosition() != null) {
				foundPlayer.setPosition(player.getPosition());
			}
			if (player.getBirthdate() != null) {
				foundPlayer.setBirthdate(player.getBirthdate());
			}
			if (player.getNationality() != null) {
				foundPlayer.setNationality(player.getNationality());
			}
			if (player.getHeight() != null) {
				foundPlayer.setHeight(player.getHeight());
			}
			if (player.getWeight() != null) {
				foundPlayer.setWeight(player.getWeight());
			}

			foundPlayer = this.playerService.savePlayer(foundPlayer);
		}

		return foundPlayer;
	}
}
