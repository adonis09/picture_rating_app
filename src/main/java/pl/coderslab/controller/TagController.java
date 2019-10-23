package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.dao.TagDao;
import pl.coderslab.entity.Tag;

import java.util.List;

@RequestMapping("/tag")
@Controller
public class TagController {

    @Autowired
    private TagDao tagDao;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addTagForm(Model model) {

        model.addAttribute("tag", new Tag());
        return "form/addtagform";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addTagProcess(@ModelAttribute Tag tag) {

        tagDao.saveTag(tag);
        return "redirect:showall";
    }

    @RequestMapping(value = "/showall", method = RequestMethod.GET)
    public String display(Model model) {

        List<Tag> allTags = tagDao.findAll();
        model.addAttribute("tags", allTags);

        return "alltags";
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
