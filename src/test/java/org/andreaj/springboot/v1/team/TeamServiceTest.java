package org.andreaj.springboot.v1.team;

import java.util.Collection;

import org.andreaj.springboot.v1.dao.TeamRepository;
import org.andreaj.springboot.v1.domain.Team;
import org.andreaj.springboot.v1.service.TeamService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class TeamServiceTest {

	@Autowired
	private TeamService teamService;
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Test
	void testFindAllTeams()  throws Exception{
		Collection<Team> allTeams = this.teamService.findAllTeams();
		Assertions.assertEquals(2, allTeams.size());
	}
	
	@Test
	void testFindTeam()  throws Exception{
		Team team = this.teamService.findTeam(1L);
		Assertions.assertEquals("France", team.getName());
	}
		
	@Test
	void testCreateTeam()  throws Exception{
		Team team = new Team();
		team.setName("Allemagne");
		team = this.teamService.createTeam(team);
		Collection<Team> allTeams = this.teamService.findAllTeams();
		Assertions.assertEquals(3, allTeams.size());
		this.teamService.deleteTeam(3L);
	}
		
	@Test
	void testDeleteTeam() throws Exception {
		this.teamService.deleteTeam(2L);
		Assertions.assertEquals(null, this.teamService.findTeam(2L));
		Team team = new Team();
		team.setName("Espagne");
		this.teamRepository.save(team);
	}

	@Test
	void testUpdateTeam() throws Exception {
		Team team = this.teamRepository.findById(2L).orElse(null);
		team.setName("Allemagne");
		this.teamService.saveTeam(team);
		team = this.teamService.findTeam(2L);
		Assertions.assertEquals("Allemagne", team.getName());
	}
}
