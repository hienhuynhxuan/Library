/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nkd.service;

import com.nkd.conf.HibernateUtil;
import com.nkd.pojo.Book;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
/**
 *
 * @author duynk
 */
public class BookService {
    private final static SessionFactory factory = HibernateUtil.getSessionFactory();
    
    public List<Book> getBooks(String kw) {
        try (Session session = factory.openSession()){
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Book> query = builder.createQuery(Book.class);
            Root<Book> root = query.from(Book.class);
            query.select(root);
            
//            if (kw != null && !kw.isEmpty()) {
//                String p = String.format("%%s%%", kw);
//                Predicate p1 = builder.like(root.get("name").as(String.class), p);
//                
//            }
            return session.createQuery(query).getResultList();
        }
    }
    
    public boolean addOrSaveBook(Book book) {
        try (Session session = factory.openSession()){
            try {
                session.getTransaction().begin();
                session.saveOrUpdate(book);
                session.getTransaction().commit();
            }catch(Exception ex) {
                session.getTransaction().rollback();
                return false;
            }
        } 
        return true;
    }
    
    public boolean deleteBook(Book book) {
        try (Session session = factory.openSession()){
            try {
                session.getTransaction().begin();
                session.delete(book);
                session.getTransaction().commit();
            }catch(Exception ex) {
                session.getTransaction().rollback();
                return false;
            }
        }
        return true;
    }
    public Book getBookById(int bookId) {
        try (Session session = factory.openSession()){
            return session.get(Book.class, bookId);
        }
    }
}
