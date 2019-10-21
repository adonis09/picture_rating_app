package pl.coderslab.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.entity.Mark;
import pl.coderslab.entity.Picture;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

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

    public List<Mark> findAll(){
        Query query = entityManager.createQuery("SELECT m FROM Mark m");
        List<Mark> marks = query.getResultList();
        return marks;
    }

}
