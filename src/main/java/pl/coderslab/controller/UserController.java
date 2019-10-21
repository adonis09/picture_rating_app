package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.dao.UserDao;
import pl.coderslab.entity.User;

import java.util.List;

@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("/add")
    @ResponseBody
    public String addUser() {
        User user = new User();
        user.setName("newerUserName");
        user.setEmail("newerUser@ema.il");
        user.setPassword("newerPass");
        user.setAdmin(0);
        user.setActive(1);
        userDao.saveUser(user);
        return "Added user:<br>" + user.getId() + " | " + user.getName() + " | " + user.getEmail();
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
