package org.codegym.demomvc.controller;

import org.codegym.demomvc.model.Group;
import org.codegym.demomvc.model.Student;
import org.codegym.demomvc.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
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
    public String groupList(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Group> groups;
        if (keyword != null) {
            groups = groupService.findByName(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            groups = groupService.getAllGroups();
        }
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
    public String createGroup(Model model) {
        Group group = new Group();
        model.addAttribute("group", group);
        return "groups/create";
    }

    @PostMapping("/create")
    public String createGroup(@Valid @ModelAttribute("group") Group group, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "groups/create";
        }
        groupService.createGroup(group);
        return "redirect:/groups";
    }

    @GetMapping("/{id}/delete")
    public String deleteGroup(@PathVariable("id") int id) {
        Group groupDelete = groupService.findById(id);
        groupService.deleteGroup(groupDelete);
        return "redirect:/groups";
    }

    @GetMapping("/{id}/students")
    public String showListStudentOfGroup(@PathVariable("id") int id, Model model) {
        Group group = groupService.findById(id);
        List<Student> students = group.getStudents();
        model.addAttribute("group", group);
        model.addAttribute("students", students);
        return "groups/students/list";
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public String handleEntityNotFound(EntityNotFoundException e, Model model) {
        model.addAttribute("message", e.getMessage());
        return "errors/404";
    }
}
