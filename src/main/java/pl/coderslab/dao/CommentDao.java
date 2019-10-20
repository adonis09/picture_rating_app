package pl.coderslab.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.entity.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
