package org.andreaj.springboot.v1.service;

import java.util.List;

import org.andreaj.springboot.v1.domain.Team;

public interface TeamService {

	public List<Team> findAllTeams();

	public Team findTeam(Long id);

	public Team createTeam(Team team);

	public void deleteTeam(Long id);

	public Team saveTeam(Team foundTeam);
}
