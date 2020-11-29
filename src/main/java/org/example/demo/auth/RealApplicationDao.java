package org.example.demo.auth;

import org.example.demo.security.ApplicationUserRole;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Repository("real")
public class RealApplicationDao implements ApplicationUserDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        ApplicationUser user = null;
        String hql = "From user as u WHERE u.username = :cuname";
        try (Session session = sessionFactory.openSession()){
            Query<ApplicationUser> query = session.createQuery(hql);
            query.setParameter("cuname", username);
            user = query.uniqueResult();
            return Optional.ofNullable(user);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public ApplicationUser addUser(ApplicationUser user) {
        Optional<ApplicationUser> dup = selectApplicationUserByUsername(user.getUsername());
        if(dup.isPresent()){
            return null;
        }
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            return user;
        } catch(Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }
}
