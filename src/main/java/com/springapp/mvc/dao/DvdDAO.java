package com.springapp.mvc.dao;

import com.springapp.mvc.SessionFacade;
import com.springapp.mvc.dto.Dvd;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public class DvdDAO implements IDvdDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addDvd(Dvd dvd) {
        Session session = SessionFacade.getSession(sessionFactory);
//        Transaction transaction = session.beginTransaction();
        session.save(dvd);
//        transaction.commit();
//        session.close();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Dvd> getDvdForOwner(Integer userId) {
        Session session = SessionFacade.getSession(sessionFactory);

        javax.persistence.Query query = session.createNativeQuery("select * from T_DVD d WHERE d.OWNER = :userId", Dvd.class);
        query.setParameter("userId", userId);
        List<Dvd> dvds =   query.getResultList();
//        ArrayList<Profile> profile = query.list();
//        session.close();

        return dvds;
    }

    public List<Dvd> getTakenDvd(Integer userId){
        Session session = SessionFacade.getSession(sessionFactory);
        javax.persistence.Query query = session.createNativeQuery(
                "select * from T_DVD d " +
                "inner join T_TAKEN_ITEM t on d.ID = t.DVD_ID " +
                "where t.USER_ID = :userId", Dvd.class);
        query.setParameter("userId", userId);
        List<Dvd> dvds =   query.getResultList();
        session.close();

        return dvds;
    }

    public List<Dvd> getGivenDvd(Integer userId){
        Session session = SessionFacade.getSession(sessionFactory);
        javax.persistence.Query query = session.createNativeQuery(
                "select * from T_DVD d " +
                        "inner join T_TAKEN_ITEM t on d.ID = t.DVD_ID " +
                        "where d.OWNER = :userId", Dvd.class);
        query.setParameter("userId", userId);
        List<Dvd> dvds =   query.getResultList();
        session.close();

        return dvds;
    }

    public Dvd get(Integer id){
        Session session = SessionFacade.getSession(sessionFactory);
        javax.persistence.Query query = session.createNativeQuery(
                "select * from T_DVD d where d.ID = :id", Dvd.class);
        query.setParameter("id", id);
        List<Dvd> dvds =   query.getResultList();
//        session.close();

        return dvds.get(0);
    }

    public List<Dvd> getFree() {
        Session session = SessionFacade.getSession(sessionFactory);
        javax.persistence.Query query = session.createNativeQuery(
                "select d.* from T_DVD d LEFT JOIN T_TAKEN_ITEM t on d.ID = t.DVD_ID " +
                        "where t.DVD_ID is null", Dvd.class);

        List<Dvd> dvds = query.getResultList();
//        session.close();

        return dvds;
    }
}
