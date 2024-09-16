package org.codegym.demomvc.controller;

import org.codegym.demomvc.model.Group;
import org.codegym.demomvc.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/groups")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @RequestMapping("")
    public String index() {
        List<Group> groups = groupService.getAllGroups();
        System.out.println(groups.size());
        return "groups/list";
    }
}
