/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nkd.conf;


import com.nkd.pojo.Author;
import com.nkd.pojo.Category;
import com.nkd.pojo.Book;
import com.nkd.pojo.User;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author duynk
 */
public class HibernateUtil {
    private final static SessionFactory FACTORY;
    
    static {
        Configuration conf = new Configuration();
        Properties pros = new Properties();
        pros.put(Environment.DIALECT,"org.hibernate.dialect.MySQLDialect");
        pros.put(Environment.DRIVER,"com.mysql.cj.jdbc.Driver");
        pros.put(Environment.URL,"jdbc:mysql://localhost:3306/library");
        pros.put(Environment.USER,"root");
        pros.put(Environment.PASS,"123456");
        
        conf.setProperties(pros);
        conf.addAnnotatedClass(Category.class);
        conf.addAnnotatedClass(Book.class);
        conf.addAnnotatedClass(Author.class);
        
        conf.addAnnotatedClass(User.class);
        
        ServiceRegistry registry =  new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);
        
    }
    public static SessionFactory getSessionFactory() {
        return FACTORY;
    }
}
