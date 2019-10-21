package pl.coderslab.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class UserDao {

    @PersistenceContext
    EntityManager entityManager;

    public void saveUser(User entity) {
        entityManager.persist(entity);
    }

    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    public void update(User entity) {
        entityManager.merge(entity);
    }

    public void delete(User entity) {
        entityManager.remove(entityManager.contains(entity) ?
                entity : entityManager.merge(entity));
    }

    public List<User> findAll(){
        Query query = entityManager.createQuery("SELECT u FROM User u");
        List<User> users = query.getResultList();
        return users;
    }

}
