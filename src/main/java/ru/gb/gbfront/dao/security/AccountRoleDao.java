package ru.gb.gbfront.dao.security;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.gbfront.entity.security.AccountRole;

public interface AccountRoleDao extends JpaRepository<AccountRole, Long> {
    AccountRole findByName(String name);
}
