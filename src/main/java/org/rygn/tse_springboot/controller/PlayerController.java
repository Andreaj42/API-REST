package org.rygn.tse_springboot.controller;

import java.util.List;

import org.rygn.tse_springboot.domain.Player;
import org.rygn.tse_springboot.service.PlayerService;
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
	
	@GetMapping("/players")
	public List<Player> allPlayers() {
				
		return this.playerService.findAllPlayers();
	}
	
	@GetMapping("/players/{id}")
	public Player onePlayer(@PathVariable Long id) {
				
		return this.playerService.findPlayer(id);
	}
	
	@PostMapping("/players")
	public Player createPlayer(@RequestBody Player player) {
		
		return this.playerService.createPlayer(player);
	}
	
	@DeleteMapping("/players/{id}")
	public void deletePlayer(@PathVariable Long id) {
		
		this.playerService.deletePlayer(id);
	}
	
	@PutMapping("/players/{id}")
	Player replacePlayer(@RequestBody Player player, @PathVariable Long id) {

		Player foundPlayer = this.playerService.findPlayer(id);
		
		if (player != null) {
			
			foundPlayer.setName(player.getName());
			
			foundPlayer = this.playerService.savePlayer(foundPlayer);
		}
		
		return foundPlayer;
	}
}
