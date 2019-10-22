package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.dao.CommentDao;
import pl.coderslab.dao.PictureDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.entity.Comment;

import java.util.List;

@RequestMapping("/comment")
@Controller
public class CommentController {

    @Autowired
    private CommentDao commentDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private PictureDao pictureDao;

    @RequestMapping("/add")
    @ResponseBody
    public String addComment() {
        Comment comment = new Comment();
        comment.setContent("quite nice picture");
        comment.setUser(userDao.findById(6L));
        comment.setPicture(pictureDao.findById(1L));
        commentDao.saveComment(comment);
        return "Added comment:<br>" + comment;
    }

    @RequestMapping("/addChildTo/{parentId}")
    @ResponseBody
    public String addChildComment(@PathVariable("parentId") long parentId) {
        Comment comment = new Comment();
        comment.setParentComment(commentDao.findById(parentId));
        comment.setContent("quite nice indeed!");
        comment.setUser(userDao.findById(4L));
        comment.setPicture(pictureDao.findById(1L));
        commentDao.saveComment(comment);
        return "Added comment:<br>" + "parrent comment: " + comment.getParentComment().getId() + " | " + comment.getParentComment().getContent() + " | by: " +comment.getParentComment().getUser().getName() +
                "<br><blockquote>child comment: " + comment.getId() + " | " + comment.getContent() + " | " + comment.getUser().getName() +
                "<br>picture: " + comment.getPicture().getFileName();
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
        comment.setUser(userDao.findById(2L));
        commentDao.update(comment);

        return "Comment got updated:<br>" + comment;
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public String deleteComment(@PathVariable("id") long id){

        Comment comment = commentDao.findById(id);
        commentDao.delete(comment);

        return "Deleted comment:<br>" + comment;
    }

    @RequestMapping("/find-root-comments-on/{id}")
    @ResponseBody
    public String findRootCommentsOnPicture(@PathVariable("id") long id){

        List<Comment> comments = commentDao.findAllRootCommentsOn(id);
        String returnString = new String();

        for (Comment oneComment : comments) {
            returnString += oneComment;
        }

        return returnString;
    }

    @RequestMapping("/find-children-of/{id}")
    @ResponseBody
    public String findChildrenOf(@PathVariable("id") long id){

        List<Comment> comments =  commentDao.findAllChildCommentsOf(id);
        String returnString = new String();
        for (Comment oneComment : comments) {
            returnString += oneComment;
        }

        return returnString;
    }

    @RequestMapping("/find-flagged")
    @ResponseBody
    public String findAllFlagged(){

        List<Comment> comments =  commentDao.findAllFlaggedComments();
        String returnString = new String();
        for (Comment oneComment : comments) {
            returnString += oneComment;
        }

        return returnString;
    }
    @RequestMapping("/find-by-user/{id}")
    @ResponseBody
    public String findAllUsersComments(@PathVariable("id") long id){

        List<Comment> comments =  commentDao.findAllCommentsByUser(id);
        String returnString = new String();
        for (Comment oneComment : comments) {
            returnString += oneComment;
        }

        return returnString;
    }

}
