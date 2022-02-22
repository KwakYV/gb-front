package ru.gb.gbfront.dao.security;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.gbfront.entity.security.Authority;

public interface AuthorityDao extends JpaRepository<Authority, Long> {
}
