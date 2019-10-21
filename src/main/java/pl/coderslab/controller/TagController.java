package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.dao.TagDao;
import pl.coderslab.entity.Tag;

@RequestMapping("/tag")
@Controller
public class TagController {

    @Autowired
    private TagDao tagDao;

    @RequestMapping("/add")
    @ResponseBody
    public String addTag() {
        Tag tag = new Tag();
        tag.setTitle("newTagTitle");

        tagDao.saveTag(tag);
        return "Added tag:<br>" + tag.getId() + " | " + tag.getTitle();
    }

    @RequestMapping("/find/{id}")
    @ResponseBody
    public String findTag(@PathVariable("id") long id) {

        Tag tag = tagDao.findById(id);

        return "Found tag:<br>" + tag.getId() + " | " + tag.getTitle();
    }

    @RequestMapping("/update/{id}")
    @ResponseBody
    public String updateTag(@PathVariable("id") long id) {

        Tag tag = tagDao.findById(id);
        tag.setTitle("updatedTagTitle");
        tagDao.update(tag);

        return "Tag got updated:<br>" + tag.getId() + " | " + tag.getTitle();
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public String deleteTag(@PathVariable("id") long id){

        Tag tag = tagDao.findById(id);
        tagDao.delete(tag);

        return "Deleted tag having id: " + tag.getId();

    }
}
