package org.andreaj.springboot.dao;

import org.andreaj.springboot.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {

}
