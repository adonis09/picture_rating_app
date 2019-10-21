package pl.coderslab.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.entity.Tag;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class TagDao {

    @PersistenceContext
    EntityManager entityManager;
    public void saveTag(Tag entity) {
        entityManager.persist(entity);
    }

    public Tag findById(Long id) {
        return entityManager.find(Tag.class, id);
    }

    public void update(Tag entity) {
        entityManager.merge(entity);
    }

    public void delete(Tag entity) {
        entityManager.remove(entityManager.contains(entity) ?
                entity : entityManager.merge(entity));
    }

    public List<Tag> findAll(){
        Query query = entityManager.createQuery("SELECT t FROM Tag t");
        List<Tag> tags = query.getResultList();
        return tags;
    }

}
