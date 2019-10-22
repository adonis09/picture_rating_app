package pl.coderslab.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.entity.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class CommentDao {

    @PersistenceContext
    EntityManager entityManager;
    public void saveComment(Comment entity) {
        entityManager.persist(entity);
    }

    public Comment findById(Long id) {
        return entityManager.find(Comment.class, id);
    }

    public void update(Comment entity) {
        entityManager.merge(entity);
    }

    public void delete(Comment entity) {
        entityManager.remove(entityManager.contains(entity) ?
                entity : entityManager.merge(entity));
    }

    public List<Comment> findAll(){
        Query query = entityManager.createQuery("SELECT c FROM Comment c");
        List<Comment> comments = query.getResultList();
        return comments;
    }

    public List<Comment> findAllRootCommentsOn(Long pictureId){
        Query query = entityManager.createQuery
                ("SELECT c FROM Comment c WHERE c.picture.id = :pictureId AND c.parentComment.id IS NULL");
        query.setParameter("pictureId", pictureId);
        List<Comment> comments = query.getResultList();
        return comments;
    }

    public List<Comment> findAllChildCommentsOf(Long parentCommentId){
        Query query = entityManager.createQuery
                ("SELECT c FROM Comment c WHERE c.parentComment.id = :parentCommentId");
        query.setParameter("parentCommentId", parentCommentId);
        List<Comment> childComments = query.getResultList();
        return childComments;
    }

    public List<Comment> findAllFlaggedComments(){
        Query query = entityManager.createQuery
                ("SELECT c FROM Comment c WHERE c.flags > 0");
        List<Comment> flaggedComments = query.getResultList();
        return flaggedComments;
    }

    public List<Comment> findAllCommentsByUser(Long userId){
        Query query = entityManager.createQuery
                ("SELECT c FROM Comment c WHERE c.user.id = :userId");
        query.setParameter("userId", userId);
        List<Comment> usersComments = query.getResultList();
        return usersComments;
    }

}
