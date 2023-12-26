package org.andreaj.springboot.service;

import java.util.List;

import org.andreaj.springboot.domain.Team;

public interface TeamService {

	public List<Team> findAllTeams();

	public Team findTeam(Long id);

	public Team createTeam(Team team);

	public void deleteTeam(Long id);

	public Team saveTeam(Team foundTeam);
}
