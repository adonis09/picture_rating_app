package pl.coderslab.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.entity.Mark;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class MarkDao {

    @PersistenceContext
    EntityManager entityManager;
    public void saveMark(Mark entity) {
        entityManager.persist(entity);
    }

    public Mark findById(Long id) {
        return entityManager.find(Mark.class, id);
    }

    public void update(Mark entity) {
        entityManager.merge(entity);
    }

    public void delete(Mark entity) {
        entityManager.remove(entityManager.contains(entity) ?
                entity : entityManager.merge(entity));
    }

}
