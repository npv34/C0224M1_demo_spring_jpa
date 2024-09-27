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
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }
    @GetMapping("")
    public String groupList(@RequestParam(value = "keyword", required = false)
                                String keyword, Model model, HttpSession httpSession) {
        try {
            List<Group> groups;
            if (keyword != null) {
                groups = groupService.findByName(keyword);
                model.addAttribute("keyword", keyword);
            } else {
                groups = groupService.getAllGroups();
            }
            model.addAttribute("groups", groups);
            String userLogin = (String) httpSession.getAttribute("userLogin");
            model.addAttribute("userLogin", userLogin);
            return "groups/list";
        }catch (Exception e) {
            return "errors/500";
        }
    }

    @GetMapping("/{id}/update")
    public String updateGroup(@PathVariable("id") int id, Model model) {
            Group groupUpdate = groupService.findById(id);
            model.addAttribute("group", groupUpdate);
            return "groups/update";
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public String handleEntityNotFoundException(EntityNotFoundException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "errors/404";
    }

    @PostMapping("/{id}/update")
    public String updateGroup(@PathVariable("id") int id, @RequestParam String name) throws Exception {
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
    public String deleteGroup(@PathVariable("id") int id) throws Exception {
        Group groupDelete = groupService.findById(id);
        groupService.deleteGroup(groupDelete);
        return "redirect:/groups";
    }

    @GetMapping("/{id}/students")
    public String showListStudentOfGroup(@PathVariable("id") int id, Model model) throws Exception {
        Group group = groupService.findById(id);
        List<Student> students = group.getStudents();
        model.addAttribute("group", group);
        model.addAttribute("students", students);
        return "groups/students/list";
    }

    @GetMapping("/search")
    @ResponseBody
    public List<Group> searchByName(@RequestParam("name") String name) throws Exception {
        List<Group> groups = groupService.findByName(name);
        return groups;
    }
}
