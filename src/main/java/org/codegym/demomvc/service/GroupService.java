package org.codegym.demomvc.service;

import org.codegym.demomvc.model.Group;
import org.codegym.demomvc.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }
}
