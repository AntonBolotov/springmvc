package com.springapp.mvc.dao;

import com.springapp.mvc.SessionFacade;
import com.springapp.mvc.dto.Profile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class ProfileDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public Profile getByName(String name){
        Session session = SessionFacade.getSession(sessionFactory);
        Query query = session.createNativeQuery("select * from T_USERS p WHERE lower(p.name) = lower(:name)", Profile.class);
        query.setParameter("name", name);
        List<Profile> profile =   query.getResultList();
//        session.close();

        return profile.get(0);
    }

    public List<Profile> getAll(){
        Session session = SessionFacade.getSession(sessionFactory);
        Query query = session.createNativeQuery("select * from T_USERS p", Profile.class);
        List<Profile> profile =   query.getResultList();
//        session.close();

        return profile;
    }
    public Profile getById(Integer id){
        Session session = SessionFacade.getSession(sessionFactory);
        Query query = session.createNativeQuery("select * from T_USERS p WHERE p.id = :id", Profile.class);
        query.setParameter("id", id);
        List<Profile> profile =   query.getResultList();
//        session.close();

        return profile.get(0);
    }
}
