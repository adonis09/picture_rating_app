package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.dao.PictureDao;
import pl.coderslab.entity.Mark;
import pl.coderslab.entity.Picture;
import pl.coderslab.entity.Tag;

import java.util.List;

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
        pictureDao.savePicture(picture);
        return "Added picture:<br>" + picture.getId() + " | " + picture.getFileName();
    }

    @RequestMapping(value = "/showall", method = RequestMethod.GET)
    public String showAll(Model model) {

        List<Picture> allpictures = pictureDao.findAll();
        model.addAttribute("pictures", allpictures);
        return "allpictures";
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
        pictureDao.update(picture);

        return "Picture got updated:<br>" + picture.getId() + " | " + picture.getFileName() + " | " + picture.getUser().getName();
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public String deletePicture(@PathVariable("id") long id){

        Picture picture = pictureDao.findById(id);
        pictureDao.delete(picture);

        return "Deleted picture having id: " + picture.getId();
    }

    @RequestMapping("/find-unapproved")
    @ResponseBody
    public String findAllUnapproved(){

        List<Picture> unapprovedPictures = pictureDao.findAllUnapprovedPictures();
        String returnString = new String();
        for (Picture onePicture : unapprovedPictures) {
            returnString += onePicture;
        }
        return returnString;
    }

    @RequestMapping("/find-approved")
    @ResponseBody
    public String findAllApproved(){

        List<Picture> unapprovedPictures = pictureDao.findAllApprovedPictures();
        String returnString = new String();
        for (Picture onePicture : unapprovedPictures) {
            returnString += onePicture;
        }
        return returnString;
    }

    @RequestMapping("/find-approved-of-user/{id}")
    @ResponseBody
    public String findAllApprovedOfUser(@PathVariable("id") long id){

        List<Picture> unapprovedPictures = pictureDao.findAllApprovedPicturesOfUser(id);
        String returnString = new String();
        for (Picture onePicture : unapprovedPictures) {
            returnString += onePicture;
        }
        return returnString;
    }

    @RequestMapping("/find-all-unmarked-by-user/{id}")
    @ResponseBody
    public String findUnmarkedByUser(@PathVariable("id") long id){

        List<Picture> unapprovedPictures = pictureDao.findAllUnmarkedPicturesByUser(id);
        String returnString = new String();
        for (Picture onePicture : unapprovedPictures) {
            returnString += onePicture;
        }
        return returnString;
    }

    @RequestMapping("/find-all-having-tag/{id}")
    @ResponseBody
    public String findAllHavingTag(@PathVariable("id") long id){

        List<Picture> unapprovedPictures = pictureDao.findAllPicturesHavingTag(id);
        String returnString = new String();
        for (Picture onePicture : unapprovedPictures) {
            returnString += onePicture;
        }
        return returnString;
    }

    @RequestMapping("/find-all-tags-on/{id}")
    @ResponseBody
    public String findAllTagsOn(@PathVariable("id") long id){

        List<Tag> unapprovedPictures = pictureDao.findAllTagsOn(id);
        String returnString = new String();
        for (Tag oneTag : unapprovedPictures) {
            returnString += oneTag;
        }
        return returnString;
    }

    @RequestMapping("/calculate-avg-score-of/{id}")
    @ResponseBody
    public String calculateAvgScore(@PathVariable("id") long id){

        Double avgScore = pictureDao.calculateAvgScoreOf(id);
        return "Average score: " + avgScore;
    }

    @RequestMapping("/find-all-order-by-score")
    @ResponseBody
    public String findAllOrderByAvgScore(){

        List<Picture> pictures = pictureDao.findAllOrderByAvgScore();
        String returnString = new String();
        for (Picture onePicture : pictures) {
            returnString += onePicture;
        }
        return returnString;
    }

    @RequestMapping("/find-all-marks-on/{id}")
    @ResponseBody
    public String findAllMarksOnPicture(@PathVariable("id") long id){

        List<Mark> marks = pictureDao.findAllMarksOn(id);
        String returnString = new String();
        for (Mark oneMark : marks) {
            returnString += oneMark;
        }
        return returnString;
    }

}
