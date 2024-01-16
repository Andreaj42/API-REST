package org.andreaj.springboot.v1.dao;

import org.andreaj.springboot.v1.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}
