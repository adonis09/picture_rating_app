package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.dao.CommentDao;
import pl.coderslab.entity.Comment;

@RequestMapping("/comment")
@Controller
public class CommentController {

    @Autowired
    private CommentDao commentDao;

    @RequestMapping("/add")
    @ResponseBody
    public String addComment() {
        Comment comment = new Comment();
        comment.setContent("another new comment");
        commentDao.saveComment(comment);
        return "Added comment:<br>" + comment.getId() + " | " + comment.getContent();
    }

    @RequestMapping("/find/{id}")
    @ResponseBody
    public String findComment(@PathVariable("id") long id) {

        Comment comment = commentDao.findById(id);
        return "Found comment:<br>" + comment.getId() + " | " + comment.getContent();
    }

    @RequestMapping("/update/{id}")
    @ResponseBody
    public String updateComment(@PathVariable("id") long id) {

        Comment comment = commentDao.findById(id);
        comment.setContent("updatedCommentContent");
        comment.setUser_id(2L);
        commentDao.update(comment);

        return "Comment got updated:<br>" + comment.getId() + " | " + comment.getContent();
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public String deleteComment(@PathVariable("id") long id){

        Comment comment = commentDao.findById(id);
        commentDao.delete(comment);

        return "Deleted comment having id: " + comment.getId();

    }
}
