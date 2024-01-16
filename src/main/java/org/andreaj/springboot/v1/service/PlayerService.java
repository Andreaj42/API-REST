package org.andreaj.springboot.v1.service;

import java.util.List;

import org.andreaj.springboot.v1.domain.Player;

public interface PlayerService {

	public List<Player> findAllPlayers();

	public Player findPlayer(Long id);

	public Player createPlayer(Player player);

	public void deletePlayer(Long id);

	public Player savePlayer(Player foundPlayer);
}
