package com.csu.rules.utils;

import net.sf.cglib.core.Local;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;

/**
 * Created by ltaoj on 17-6-6.
 */
public class HibernateUtil {
    @Autowired
    private static SessionFactory sessionFactory;
//    @Bean
//    public static LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
//        LocalSessionFactoryBean sfb = new LocalSessionFactoryBean();
//        sfb.setDataSource(dataSource);
//        sfb.setConfigLocation(new ClassPathResource("classpath:hibernate.cfg.xml"));
//        return sfb;
//    }

    //    private static SessionFactory sessionFactory;
//
//    static {
//        try {
//            sessionFactory = new Configuration().configure().buildSessionFactory();
//        } catch (Throwable ex) {
//            throw new ExceptionInInitializerError(ex);
//        }
//    }
//
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
