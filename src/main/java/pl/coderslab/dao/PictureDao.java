package pl.coderslab.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.entity.Picture;
import pl.coderslab.entity.Tag;

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

    public List<Picture> findAll() {
        Query query = entityManager.createQuery("SELECT p FROM Picture p");
        List<Picture> pictures = query.getResultList();
        return pictures;
    }

    public List<Picture> findAllUnapprovedPictures() {
        Query query = entityManager.createQuery("SELECT p FROM Picture p WHERE p.valid = 0");
        List<Picture> unapprovedPictures = query.getResultList();
        return unapprovedPictures;
    }

    public List<Picture> findAllApprovedPictures() {
        Query query = entityManager.createQuery("SELECT p FROM Picture p WHERE p.valid = 1");
        List<Picture> unapprovedPictures = query.getResultList();
        return unapprovedPictures;
    }

    public List<Picture> findAllApprovedPicturesOfUser(Long userId) {
        Query query = entityManager.createQuery("SELECT p FROM Picture p WHERE p.valid = 1 AND p.user.id = :userId");
        query.setParameter("userId", userId);
        List<Picture> unapprovedPictures = query.getResultList();
        return unapprovedPictures;
    }

    public List<Picture> findAllUnmarkedPicturesByUser(Long userId) {
        Query query = entityManager.createQuery("SELECT p FROM Picture p JOIN p.marks m WHERE m.user.id <> :userId AND p.valid = 1");
        query.setParameter("userId", userId);
        List<Picture> unapprovedPictures = query.getResultList();
        return unapprovedPictures;
    }

    public List<Picture> findAllPicturesHavingTag(Long tagId) {
        Query query = entityManager.createQuery("SELECT p FROM Picture p JOIN p.tags t WHERE t.id = :tagId");
        query.setParameter("tagId", tagId);
        List<Picture> picturesWithTag = query.getResultList();
        return picturesWithTag;
    }

    public List<Tag> findAllTagsOn(Long pictureId) {
        Query query = entityManager.createQuery("SELECT t FROM Tag t JOIN t.pictures p WHERE p.id = :pictureId");
        query.setParameter("pictureId", pictureId);
        List<Tag> allTagsOnPicture = query.getResultList();
        return allTagsOnPicture;
    }

    public Double calculateAvgScoreOf(Long pictureId) {
        Query query = entityManager.createQuery("SELECT AVG (m.score) FROM Mark m WHERE m.picture.id = :pictureId");
        query.setParameter("pictureId", pictureId);
        Double result = Double.parseDouble(query.getSingleResult().toString());
        return result;
    }

    public List<Picture> findAllOrderByAvgScore(){
        Query query = entityManager.createQuery("SELECT p FROM Picture p LEFT JOIN p.marks m GROUP BY p ORDER BY AVG(m.score) DESC");
        List<Picture> allPicturesOrderByAvgScore = query.getResultList();
        return allPicturesOrderByAvgScore;
    }

}
