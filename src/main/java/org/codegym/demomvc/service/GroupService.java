package org.codegym.demomvc.service;

import org.codegym.demomvc.model.Group;
import org.codegym.demomvc.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group findById(long id) {
        return groupRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Could not find"));
    }

    public void updateGroup(Group group, String name) {
        group.setName(name);
        groupRepository.save(group);
    }

    public void createGroup(Group group) {
        groupRepository.save(group);
    }

    public void deleteGroup(Group group) {
        groupRepository.delete(group);
    }

    public List<Group> findByName(String keyword) {
        return groupRepository.findByNameContaining(keyword);
    }
}
