package pl.coderslab.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.entity.Picture;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class PictureDao {

    @PersistenceContext
    EntityManager entityManager;
    public void savePicture(Picture entity) {
        entityManager.persist(entity);
    }

    public Picture findById(Long id) {
        return entityManager.find(Picture.class, id);
    }

    public void update(Picture entity) {
        entityManager.merge(entity);
    }

    public void delete(Picture entity) {
        entityManager.remove(entityManager.contains(entity) ?
                entity : entityManager.merge(entity));
    }

    public List<Picture> findAll(){
        Query query = entityManager.createQuery("SELECT p FROM Picture p");
        List<Picture> pictures = query.getResultList();
        return pictures;
    }

}
