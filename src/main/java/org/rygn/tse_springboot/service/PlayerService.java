package org.rygn.tse_springboot.service;

import java.util.List;

import org.rygn.tse_springboot.domain.Player;

public interface PlayerService {

	public List<Player> findAllPlayers();

	public Player findPlayer(Long id);

	public Player createPlayer(Player Player);

	public void deletePlayer(Long id);

	public Player savePlayer(Player foundPlayer);
}
