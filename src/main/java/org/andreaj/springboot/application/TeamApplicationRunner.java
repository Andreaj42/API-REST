package org.andreaj.springboot.application;

import org.andreaj.springboot.dao.TeamRepository;
import org.andreaj.springboot.domain.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class TeamApplicationRunner implements ApplicationRunner {

	@Autowired
	private TeamRepository teamRepository;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		Team france = new Team();
		france.setName("France");		
		this.teamRepository.save(france);
		
	}
}
