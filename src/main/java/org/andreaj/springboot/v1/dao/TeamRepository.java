package org.andreaj.springboot.v1.dao;

import org.andreaj.springboot.v1.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {

}
