package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.dao.PictureDao;
import pl.coderslab.entity.Picture;

@RequestMapping("/picture")
@Controller
public class PictureController {

    @Autowired
    private PictureDao pictureDao;

    @RequestMapping("/add")
    @ResponseBody
    public String addPicture() {
        Picture picture = new Picture();
        picture.setFileName("someFileName.jpg");
        picture.setUser_id(3);
        pictureDao.savePicture(picture);
        return "Added picture:<br>" + picture.getId() + " | " + picture.getFileName();
    }

    @RequestMapping("/find/{id}")
    @ResponseBody
    public String findPicture(@PathVariable("id") long id) {

        Picture picture = pictureDao.findById(id);

        return "Found picture:<br>" + picture.getId() + " | " + picture.getFileName();
    }

    @RequestMapping("/update/{id}")
    @ResponseBody
    public String updatePicture(@PathVariable("id") long id) {

        Picture picture = pictureDao.findById(id);
        picture.setFileName("updatedFilName.png");
        picture.setUser_id(1);

        pictureDao.update(picture);

        return "Picture got updated:<br>" + picture.getId() + " | " + picture.getFileName() + " | " + picture.getUser_id();
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public String deletePicture(@PathVariable("id") long id){

        Picture picture = pictureDao.findById(id);
        pictureDao.delete(picture);

        return "Deleted picture having id: " + picture.getId();

    }
}
