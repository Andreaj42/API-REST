package org.andreaj.springboot.v1.controller;

import java.util.List;

import org.andreaj.springboot.v1.domain.Player;
import org.andreaj.springboot.v1.domain.Team;
import org.andreaj.springboot.v1.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {

	@Autowired
	private TeamService teamService;

	@GetMapping("/api/v1/teams")
	public List<Team> allTeams() {

		return this.teamService.findAllTeams();
	}

	@GetMapping("/api/v1/teams/{id}")
	public Team oneTeam(@PathVariable Long id) {

		return this.teamService.findTeam(id);
	}

	@PostMapping("/api/v1/teams")
	public Team createTeam(@RequestBody Team team) {

		return this.teamService.createTeam(team);
	}

	@DeleteMapping("/api/v1/teams/{id}")
	public void deleteTeam(@PathVariable Long id) {

		this.teamService.deleteTeam(id);
	}

	@PutMapping("/api/v1/teams/{id}")
	Team replaceTeam(@RequestBody Team team, @PathVariable Long id) {

		Team foundTeam = this.teamService.findTeam(id);

		if (team != null && foundTeam != null) {

			if (team.getName() != null) {
				foundTeam.setName(team.getName());
			}
			if (team.getColor() != null) {
				foundTeam.setColor(team.getColor());
			}
			if (team.getStadium() != null) {
				foundTeam.setStadium(team.getStadium());
			}
			if (team.getTrainer() != null) {
				foundTeam.setTrainer(team.getTrainer());
			}
			foundTeam = this.teamService.saveTeam(foundTeam);
		}
		return foundTeam;
	}

}
