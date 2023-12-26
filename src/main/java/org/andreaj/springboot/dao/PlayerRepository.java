package org.andreaj.springboot.dao;

import org.andreaj.springboot.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}
