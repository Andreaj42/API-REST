package org.rygn.tse_springboot.service;

import java.util.List;

import org.rygn.tse_springboot.domain.Team;

public interface TeamService {

	public List<Team> findAllTeams();

	public Team findTeam(Long id);

	public Team createTeam(Team Team);

	public void deleteTeam(Long id);

	public Team saveTeam(Team foundTeam);
}
