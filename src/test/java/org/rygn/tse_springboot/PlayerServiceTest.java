package org.rygn.tse_springboot;

import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.rygn.tse_springboot.dao.PlayerRepository;
import org.rygn.tse_springboot.domain.Player;
import org.rygn.tse_springboot.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class PlayerServiceTest {

	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Test
	public void testFindAllPlayers()  throws Exception{
		
		Collection<Player> allPlayers = this.playerService.findAllPlayers();
		
		Assertions.assertEquals(3, allPlayers.size());
	}
	
	@Test
	public void testFindPlayer()  throws Exception{
		
		Player player = this.playerService.findPlayer(1L);
		
		Assertions.assertEquals("Christiano Ronaldo", player.getName());
	}
		
	@Test
	public void testCreatePlayer()  throws Exception{
		
		Player player = new Player();
		player.setName("Kylian Mbappe");
		
		player = this.playerService.createPlayer(player);
		
		Collection<Player> allPlayers = this.playerService.findAllPlayers();
		
		Assertions.assertEquals(4, allPlayers.size());
		
		this.playerRepository.delete(player);
	}
		
	@Test
	public void testDeletePlayer() throws Exception {
		
		this.playerService.deletePlayer(3L);
		
		Collection<Player> allPlayers = this.playerService.findAllPlayers();
		
		Assertions.assertEquals(2, allPlayers.size());
		
		Player player = new Player();
		player.setName("Kylian Mbappe");
		this.playerRepository.save(player);
	}
		
	@Test
	public void testUpdatePlayer() throws Exception {
		
		Player player = this.playerRepository.findById(2L).orElse(null);
		
		player.setName("Kylian Mbappe");
		
		this.playerService.savePlayer(player);
		
		player = this.playerService.findPlayer(2L);
		
		Assertions.assertEquals("Kylian Mbappe", player.getName());
		
		player.setName("Lionel Messi");
		
		this.playerRepository.save(player);
	}
}
