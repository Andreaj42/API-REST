package org.rygn.tse_springboot.service.impl;

import java.util.List;

import org.rygn.tse_springboot.dao.PlayerRepository;
import org.rygn.tse_springboot.domain.Player;
import org.rygn.tse_springboot.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	private PlayerRepository PlayerRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Player> findAllPlayers() {

		return this.PlayerRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Player findPlayer(Long id) {

		return this.PlayerRepository.findById(id).orElse(null);
	}

	@Override
	public Player createPlayer(Player Player) {

		return this.PlayerRepository.save(Player);
	}

	@Override
	public void deletePlayer(Long id) {

		this.PlayerRepository.deleteById(id);
	}

	@Override
	public Player savePlayer(Player foundPlayer) {

		return this.PlayerRepository.save(foundPlayer);
	}
}
