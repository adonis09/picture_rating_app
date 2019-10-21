package pl.coderslab.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.entity.Comment;
import pl.coderslab.entity.Picture;

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

}
