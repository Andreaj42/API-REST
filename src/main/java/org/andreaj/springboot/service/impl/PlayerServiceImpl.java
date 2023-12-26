package org.andreaj.springboot.service.impl;

import java.util.List;

import org.andreaj.springboot.dao.PlayerRepository;
import org.andreaj.springboot.domain.Player;
import org.andreaj.springboot.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	private PlayerRepository playerRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Player> findAllPlayers() {

		return this.playerRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Player findPlayer(Long id) {

		return this.playerRepository.findById(id).orElse(null);
	}

	@Override
	public Player createPlayer(Player player) {

		return this.playerRepository.save(player);
	}

	@Override
	public void deletePlayer(Long id) {

		this.playerRepository.deleteById(id);
	}

	@Override
	public Player savePlayer(Player foundPlayer) {

		return this.playerRepository.save(foundPlayer);
	}
}