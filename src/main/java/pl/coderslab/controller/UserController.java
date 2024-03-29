package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.dao.UserDao;
import pl.coderslab.entity.User;

import java.util.List;

@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String addUserForm(Model model) {

        model.addAttribute("user", new User());
        return "form/adduserform";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String addUserProcess(@ModelAttribute User user) {

        userDao.saveUser(user);
        return "redirect:showall";
    }

    @RequestMapping(value = "/showall", method = RequestMethod.GET)
    public String display(Model model) {

        List<User> allusers = userDao.findAll();
        model.addAttribute("users", allusers);
        return "allusers";
    }


    @RequestMapping("/find/{id}")
    @ResponseBody
    public String findUser(@PathVariable("id") long id) {

        User user = userDao.findById(id);
        return "Found user:<br>" + user.getId() + " | " + user.getName() + " | " + user.getEmail();
    }

    @RequestMapping("/update/{id}")
    @ResponseBody
    public String updateUser(@PathVariable("id") long id) {

        User user = userDao.findById(id);
        user.setName("updatedName");
        user.setEmail("updatedEmail");

        userDao.update(user);

        return "User got updated:<br>" + user.getId() + " | " + user.getName() + " | " + user.getEmail();
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public String deleteUser(@PathVariable("id") long id){

        User user = userDao.findById(id);
        userDao.delete(user);

        return "Deleted user having id: " + user.getId();
    }

    @RequestMapping("/findAll")
    public String findAll(Model model){

        List<User> users = userDao.findAll();
        model.addAttribute("users", users);
        return "allusers";
    }

}
