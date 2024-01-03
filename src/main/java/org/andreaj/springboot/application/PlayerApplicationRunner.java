package org.andreaj.springboot.application;

import org.andreaj.springboot.dao.PlayerRepository;
import org.andreaj.springboot.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class PlayerApplicationRunner implements ApplicationRunner {

	@Autowired
	private PlayerRepository playerRepository;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		Player cronaldo = new Player();
		cronaldo.setName("Christiano Ronaldo");
		cronaldo.setPosition("ATTAQUANT");
		this.playerRepository.save(cronaldo);

		Player kmbappe = new Player();
		kmbappe.setName("Kylian Mbappe");
		kmbappe.setPosition("ATTAQUANT");
		this.playerRepository.save(kmbappe);

		Player lmessi = new Player();
		lmessi.setName("Lionel Messi");
		lmessi.setPosition("ATTAQUANT");
		this.playerRepository.save(lmessi);

	}
}
