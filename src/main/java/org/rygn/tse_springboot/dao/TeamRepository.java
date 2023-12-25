package org.rygn.tse_springboot.dao;

import org.rygn.tse_springboot.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {

}
