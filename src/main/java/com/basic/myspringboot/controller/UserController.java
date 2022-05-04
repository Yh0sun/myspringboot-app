package com.basic.myspringboot.controller;

import com.basic.myspringboot.controller.form.UserForm;
import com.basic.myspringboot.entity.User;
import com.basic.myspringboot.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j   //로그 로기지 폴더 생성할 필요x
public class UserController {
    private final UserService userService;

    @GetMapping("/leaf")
    public String leaf(Model model) {
        model.addAttribute("name", "SprigBoot");
        return "leaf";
    }

    @GetMapping("/index")
    public String userList(Model model) {
        List<User> userList = userService.selectAllUser();
        model.addAttribute("users", userList);
        return "index";
    }

    @GetMapping("/signup")
    public String showSignUpForm(UserForm myUser) {
        return "add-user";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid UserForm userForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }

        User user = new User();
        BeanUtils.copyProperties(userForm, user);

        userService.insertUser(user);
        model.addAttribute("users", userService.selectAllUser());
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userService.selectUser(id);
        log.debug("user {}", user);
        UserForm userForm = new UserForm();
        BeanUtils.copyProperties(user, userForm);
        log.info("userForm {}", userForm);
        model.addAttribute("userForm", userForm);
        return "update-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid UserForm userForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            userForm.setId(id);
            return "update-user";
        }

        log.debug("userForm {}", userForm);
        User user = new User();
        BeanUtils.copyProperties(userForm, user);

        userService.insertUser(user);
        model.addAttribute("users", userService.selectAllUser());
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        User user = userService.selectUser(id);
        userService.deleteUser(user.getId());
        model.addAttribute("users", userService.selectAllUser());
        return "index";
    }

}

