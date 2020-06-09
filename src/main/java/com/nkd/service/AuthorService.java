/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nkd.service;

import com.nkd.conf.HibernateUtil;
import com.nkd.pojo.Author;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author duynk
 */
public class AuthorService {
    private final static SessionFactory factory = HibernateUtil.getSessionFactory();
    public List<Author> getAuthors() {
        try (Session session = factory.openSession()){
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Author> query = builder.createQuery(Author.class);
            Root<Author> root = query.from(Author.class);
            query.select(root);
            
            return session.createQuery(query).getResultList();
        }
    }
    
     public Author getAuthorById(int autId) {
        try (Session session = factory.openSession()){
            return session.get(Author.class, autId);
        }
    }
}
