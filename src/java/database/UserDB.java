package database;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Users;

public class UserDB {

    public void insert(Users user) throws Exception {
      
    EntityManager em=DBUtil.getEmFactory().createEntityManager();
    EntityTransaction trans= em.getTransaction();
        try {
            trans.begin();
            em.persist(user);
            trans.commit();
         
        } catch(Exception ex){
            trans.rollback();
        }
         finally {
           em.close();
        }
    }

    public void update(Users user) throws Exception {
       EntityManager em=DBUtil.getEmFactory().createEntityManager();
    EntityTransaction trans= em.getTransaction();

        try {
            trans.begin();
            em.merge(user);
            trans.commit();
           
        } catch (Exception ex) {
           trans.rollback();
        } finally {
          em.close();
        }
    }

    public List<Users> getAll() throws Exception {
      
        EntityManager em=DBUtil.getEmFactory().createEntityManager();
        try {
          List<Users> users=em.createNamedQuery("Users.findAll", Users.class).getResultList();
          return users;
        }  finally {
          em.close();
        }
    }

    /**
     * Get a single user by their username.
     *
     * @param username The unique username.
     * @return A User object if found, null otherwise.
     * @throws NotesDBException
     */
    public Users getUser(String username) throws Exception {
      
       EntityManager  em=DBUtil.getEmFactory().createEntityManager();
        try {
           Users user=em.find(Users.class, username);
           return user;
            }
          
        
        finally {
       em.close();
        }
    }

    public void delete(Users user) throws Exception {
       
        EntityManager em=DBUtil.getEmFactory().createEntityManager();
    EntityTransaction trans= em.getTransaction();

        try {
         trans.begin();
         em.remove(em.merge(user));
         trans.commit();
        } catch (Exception ex) {
           trans.rollback();
        } finally {
           em.close();
        }
    }
}
