package org.codegym.demomvc.controller;

import org.codegym.demomvc.model.Group;
import org.codegym.demomvc.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/groups")
public class GroupController {

    private GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }
    @GetMapping("")
    public String groupList(Model model) {
        List<Group> groups = groupService.getAllGroups();
        System.out.println(groups.size());
        model.addAttribute("groups", groups);
        return "groups/list";
    }

    @GetMapping("/{id}/update")
    public String updateGroup(@PathVariable("id") int id, Model model) {
        Group groupUpdate = groupService.findById(id);
        model.addAttribute("group", groupUpdate);
        return "groups/update";
    }

    @PostMapping("/{id}/update")
    public String updateGroup(@PathVariable("id") int id, @RequestParam String name) {
        Group groupUpdate = groupService.findById(id);
        groupService.updateGroup(groupUpdate, name);
        return "redirect:/groups";
    }

    @GetMapping("/create")
    public String createGroup() {
        return "groups/create";
    }

    @PostMapping("/create")
    public String createGroup(@RequestParam String name) {
        groupService.createGroup(name);
        return "redirect:/groups";
    }
}
