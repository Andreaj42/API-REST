package org.rygn.tse_springboot.dao;

import org.rygn.tse_springboot.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}
