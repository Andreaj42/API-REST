package org.andreaj.springboot.v1.application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.andreaj.springboot.v1.dao.TeamRepository;
import org.andreaj.springboot.v1.domain.Team;
import org.andreaj.springboot.v1.dao.PlayerRepository;
import org.andreaj.springboot.v1.domain.Player;
import org.andreaj.springboot.v1.domain.enums.PositionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SpringApplicationRunner implements ApplicationRunner {

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private TeamRepository teamRepository;

	@Override
	@Transactional
	public void run(ApplicationArguments args) throws Exception {

		/* Instanciation des équipes */
		Team team1 = new Team();
		team1.setName("France");
		team1.setColor(new String[] { "Bleu", "Blanc", "Rouge" });
		team1.setStadium("Stade de France");
		team1.setTrainer("Didier Deschamps");

		Team team2 = new Team();
		team2.setName("Espagne");
		team2.setColor(new String[] { "Rouge", "Jaune" });
		team2.setStadium("Stade Santiago Bernabéu");
		team2.setTrainer("Luis de la Fuente");

		/* Instanciation des joueurs */
		Player player1 = new Player();
		player1.setName("Kylian Mbappé");
		player1.setPosition(PositionEnum.ATTAQUANT);
		player1.setBirthdate(LocalDate.of(1998, 12, 20));
		player1.setNationality("French");
		player1.setHeight(1.78f);
		player1.setWeight(73.0f);
		this.playerRepository.save(player1);

		Player player2 = new Player();
		player2.setName("Antoine Griezmann");
		player2.setPosition(PositionEnum.ATTAQUANT);
		player2.setBirthdate(LocalDate.of(1991, 3, 21));
		player2.setNationality("French");
		player2.setHeight(1.76f);
		player2.setWeight(73.0f);
		this.playerRepository.save(player2);

		Player player3 = new Player();
		player3.setName("N'Golo Kanté");
		player3.setPosition(PositionEnum.MILIEU);
		player3.setBirthdate(LocalDate.of(1991, 3, 29));
		player3.setNationality("French");
		player3.setHeight(1.68f);
		player3.setWeight(70.0f);
		this.playerRepository.save(player3);

		Player player4 = new Player();
		player4.setName("Raphael Varane");
		player4.setPosition(PositionEnum.DEFENSEUR);
		player4.setBirthdate(LocalDate.of(1993, 4, 25));
		player4.setNationality("French");
		player4.setHeight(1.91f);
		player4.setWeight(81.0f);
		this.playerRepository.save(player4);

		Player player5 = new Player();
		player5.setName("Paul Pogba");
		player5.setPosition(PositionEnum.MILIEU);
		player5.setBirthdate(LocalDate.of(1993, 3, 15));
		player5.setNationality("French");
		player5.setHeight(1.91f);
		player5.setWeight(84.0f);
		this.playerRepository.save(player5);

		Player player6 = new Player();
		player6.setName("Sergio Ramos");
		player6.setPosition(PositionEnum.DEFENSEUR);
		player6.setBirthdate(LocalDate.of(1986, 3, 30));
		player6.setNationality("Spanish");
		player6.setHeight(1.84f);
		player6.setWeight(82.0f);
		this.playerRepository.save(player6);

		/* Ajouts aux équipes */
		List<Player> players1 = new ArrayList<>();
		players1.add(player1);
		players1.add(player2);
		players1.add(player3);
		players1.add(player4);
		players1.add(player5);
		team1.setPlayers(players1);
		this.teamRepository.save(team1);

		List<Player> players2 = new ArrayList<>();
		players2.add(player6);
		team2.setPlayers(players2);
		this.teamRepository.save(team2);
	}
}
