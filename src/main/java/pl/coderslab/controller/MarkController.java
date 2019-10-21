package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.dao.MarkDao;
import pl.coderslab.dao.PictureDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.entity.Mark;

@RequestMapping("/mark")
@Controller
public class MarkController {

    @Autowired
    private MarkDao markDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private PictureDao pictureDao;

    @RequestMapping("/add")
    @ResponseBody
    public String addMark() {
        Mark mark = new Mark();
        mark.setPicture(pictureDao.findById(4L));
        mark.setScore(8);
        mark.setUser(userDao.findById(7L));
        markDao.saveMark(mark);
        return "Added mark:<br>" + mark.getId() + " | " + mark.getUser().getName() + " | " + mark.getScore() + " | " + mark.getPicture().getFileName();
    }

    @RequestMapping("/find/{id}")
    @ResponseBody
    public String findMark(@PathVariable("id") long id) {

        Mark mark = markDao.findById(id);

        return "Found mark:<br>" + mark.getId() + " | " + mark.getScore() + " | " + mark.getUser().getName();
    }

    @RequestMapping("/update/{id}")
    @ResponseBody
    public String updateMark(@PathVariable("id") long id) {

        Mark mark = markDao.findById(id);
        mark.setScore(10);

        markDao.update(mark);

        return "Mark got updated:<br>" + mark.getId() + " | " + mark.getScore() + " | " + mark.getUser().getName();
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public String deleteMark(@PathVariable("id") long id){

        Mark mark = markDao.findById(id);
        markDao.delete(mark);

        return "Deleted mark having id: " + mark.getId();

    }
}
