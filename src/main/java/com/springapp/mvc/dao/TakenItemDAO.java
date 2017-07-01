package com.springapp.mvc.dao;

import com.springapp.mvc.SessionFacade;
import com.springapp.mvc.dto.TakenItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class TakenItemDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public void add(TakenItem takenItem) {
        Session session = SessionFacade.getSession(sessionFactory);
//        Transaction transaction = session.beginTransaction();
        session.save(takenItem);
//        transaction.commit();
//        session.close();
    }

    public void remove(TakenItem takenItem) {
        Session session = SessionFacade.getSession(sessionFactory);
//        Transaction transaction = session.beginTransaction();
        session.remove(takenItem);
//        transaction.commit();
//        session.close();
    }

    public TakenItem getByDvd(Integer id){
        Session session = SessionFacade.getSession(sessionFactory);

        Query query = session.createNativeQuery("select * from T_TAKEN_ITEM t WHERE t.DVD_ID = :id", TakenItem.class);
        query.setParameter("id", id);
        List<TakenItem> takenItems =   query.getResultList();
//        session.close();

        if(takenItems == null || takenItems.isEmpty()){
            return null;
        }

        return takenItems.get(0);
    }

    public List<TakenItem> getByUser(Integer id){
        Session session = SessionFacade.getSession(sessionFactory);

        Query query = session.createNativeQuery("select * from T_TAKEN_ITEM t WHERE t.USER_ID = :id", TakenItem.class);
        query.setParameter("id", id);
        List<TakenItem> takenItems =   query.getResultList();
//        session.close();

        return takenItems;
    }

    public List<TakenItem> getFromUser(Integer id){
        Session session = SessionFacade.getSession(sessionFactory);

        Query query = session.createNativeQuery("select * from T_TAKEN_ITEM t INNER JOIN T_DVD d ON d.ID = t.DVD_ID" +
                " where d.OWNER = :id", TakenItem.class);
        query.setParameter("id", id);
        List<TakenItem> takenItems =   query.getResultList();
//        session.close();

        return takenItems;
    }

    public TakenItem get(Integer id){
        Session session = SessionFacade.getSession(sessionFactory);

        Query query = session.createNativeQuery("select * from T_TAKEN_ITEM t  where t.ID = :id", TakenItem.class);
        query.setParameter("id", id);
        List<TakenItem> takenItems =   query.getResultList();
//        session.close();

        if(takenItems == null || takenItems.isEmpty()){
            return null;
        }

        return takenItems.get(0);
    }

}
