package org.codegym.demomvc.service;

import org.codegym.demomvc.model.Group;
import org.codegym.demomvc.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    private GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group findById(long id) {
        return groupRepository.findById(id).orElse(null);
    }

    public void updateGroup(Group group, String name) {
        group.setName(name);
        groupRepository.save(group);
    }

    public void createGroup(String name) {
        Group group = new Group();
        group.setName(name);
        groupRepository.save(group);
    }
}
