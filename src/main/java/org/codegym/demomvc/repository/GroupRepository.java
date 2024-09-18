package org.codegym.demomvc.repository;

import org.codegym.demomvc.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findByNameContaining(String keyword);
}
