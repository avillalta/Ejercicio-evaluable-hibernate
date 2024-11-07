package org.example;

import org.example.models.Opinion;
import org.example.models.Pelicula;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class DataService {

    private SessionFactory sessionFactory;

    public DataService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    //Historia de usuario 1
    public void savePelicula(Pelicula p){
        sessionFactory.inTransaction(session -> {
            session.persist(p);
        });
    }

    //Historia de usuario 2
    public List<Opinion> getOpinionesPelicula(Pelicula pelicula) {
        List<Opinion> listaOpiniones;
        try (Session session = sessionFactory.openSession()) {
            Query<Opinion> q = session.createQuery(
                    "select o from Pelicula p join p.opiniones o where p = :pelicula", Opinion.class);
            q.setParameter("pelicula", pelicula);
            listaOpiniones = q.list();
        } catch (Exception e) {
            listaOpiniones = new ArrayList<>(0);
        }
        return listaOpiniones;
    }

    //Historia de usuario 3
    public void agregarOpinionAPelicula(Pelicula pelicula, Opinion opinion) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            pelicula.getOpiniones().add(opinion);
            opinion.setPelicula(pelicula);

            session.update(pelicula);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
