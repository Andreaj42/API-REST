package org.andreaj.springboot.v1.player;

import java.util.Collection;
import java.time.LocalDate;

import org.andreaj.springboot.v1.dao.PlayerRepository;
import org.andreaj.springboot.v1.domain.Player;
import org.andreaj.springboot.v1.service.PlayerService;
import org.andreaj.springboot.v1.domain.enums.PositionEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class PlayerServiceTest {

	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Test
	void testFindAllPlayers()  throws Exception{
		Collection<Player> allPlayers = this.playerService.findAllPlayers();
		Assertions.assertEquals(6, allPlayers.size());
	}
	
	@Test
	void testFindPlayer()  throws Exception{
		Player player = this.playerService.findPlayer(1L);
		Assertions.assertEquals("Kylian Mbappé", player.getName());
	}
		
	@Test
	void testCreatePlayer()  throws Exception{
		Player player = new Player();
		player.setName("Kingsley Coman");
		player.setNationality("French");
		player = this.playerService.createPlayer(player);
		Collection<Player> allPlayers = this.playerService.findAllPlayers();
		Assertions.assertEquals(7, allPlayers.size());
		this.playerService.deletePlayer(6L);
	}
		
	@Test
	void testDeletePlayer() throws Exception {
		this.playerService.deletePlayer(3L);
		Assertions.assertEquals(null, this.playerService.findPlayer(3L));
		Player player = new Player();
		player.setName("Kylian Mbappé");
		player.setPosition(PositionEnum.ATTAQUANT);
		player.setBirthdate(LocalDate.of(1998, 12, 20));
		player.setNationality("French");
		player.setHeight(1.78f);
		player.setWeight(73.0f);
		this.playerRepository.save(player);
	}

	@Test
	void testUpdatePlayer() throws Exception {
		Player player = this.playerRepository.findById(1L).orElse(null);
		player.setWeight(80f);
		this.playerService.savePlayer(player);
		player = this.playerService.findPlayer(1L);
		Assertions.assertEquals(80f, player.getWeight());
	}
}
