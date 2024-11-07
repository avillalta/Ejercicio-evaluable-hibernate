package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            System.out.println("SessionFactory creada exitosamente.");


            Session session = sessionFactory.openSession();
            System.out.println("Sesión abierta exitosamente.");


            session.beginTransaction();
            session.getTransaction().commit();
            System.out.println("Transacción ejecutada exitosamente.");


            session.close();
            System.out.println("Sesión cerrada exitosamente.");

        } catch (Exception e) {
            System.err.println("Error al inicializar Hibernate: " + e);
            e.printStackTrace();
        }
    }
}