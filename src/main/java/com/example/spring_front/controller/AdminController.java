package com.example.spring_front.controller;

import com.example.spring_front.entity.Role;
import com.example.spring_front.entity.User;
import com.example.spring_front.service.RoleService;
import com.example.spring_front.service.UserService;
import com.example.spring_front.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserServiceImpl userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        User user = userService.findByUsername(currentUser.getUsername());
        model.addAttribute("currentUser", user);
        model.addAttribute("users", userService.getUsers());
        return "users/index";
    }

    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/admin-panel")
    public String getUsers(@RequestParam(value = "id", required = false) Long id, Model model, @AuthenticationPrincipal UserDetails currentUser) {
        User user = userService.findByUsername(currentUser.getUsername());
        model.addAttribute("currentUser", user);
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("newUser", new User());
        model.addAttribute("listRoles", roleService.getAllRoles());
        return "users/admin-panel";
    }

    @PostMapping("admin/update_user")
    public String update_user(@ModelAttribute("user") User user, @RequestParam(value = "allRoles", required = false) String[] allRoles) {
        System.out.println("allroles"+allRoles);
        if (allRoles != null) {
            Set<Role> roleSet = new HashSet<>();
            for (String roles : allRoles) {
                roleSet.add(roleService.getRoleByName(roles));
            }
            user.setRoles(roleSet);
        }
        user.setEnabled(true);
        userService.save(user);
        return "redirect:/admin-panel";
    }
    @DeleteMapping("admin/delete")
    public String deleteUser(@RequestParam(value = "id", required = false) Long id) {
        userService.delete(id);
        return "redirect:/admin-panel";
    }

    @RequestMapping("/login_error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "/login";
    }
}
