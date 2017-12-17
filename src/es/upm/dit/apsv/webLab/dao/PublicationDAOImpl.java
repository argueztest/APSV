package es.upm.dit.apsv.webLab.dao;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.Session;

import es.upm.dit.apsv.webLab.model.Publication;

public class PublicationDAOImpl implements PublicationDAO {
    private static PublicationDAOImpl instance;

    private PublicationDAOImpl() {
    }

    public static PublicationDAOImpl getInstance() {
        if (instance == null)
            instance = new PublicationDAOImpl();
        return instance;
    }

    @Override
    public Publication create(Publication p) {
        Session session = SessionFactoryService.get().openSession();
        try {
            session.beginTransaction();
            session.save(p);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw e;
        } finally {
            session.close();
        }
        return p;

    }

    @Override
    public Publication read(Publication p) {
        Session session = SessionFactoryService.get().openSession();

        Publication pub = null;
        try {
            pub = (Publication) session
                    .createQuery("select	p from Publication	p where	p.id= :id")
                    .setParameter("id", p.getId())
                    .uniqueResult();
        } catch (Exception e) {
            throw e;
        } finally {
            session.close();
        }
        return pub;
    }

    @Override
    public Collection<Publication> readAll() {

        Session session = SessionFactoryService.get().openSession();
        Collection<Publication> res = new ArrayList<>();
        try {
            res.addAll(session.createQuery("select	p	from	Publication	p")
                    .getResultList());
        } catch (Exception e) {
            throw e;
        } finally {
            session.close();
        }
        return res;

    }

    @Override
    public Publication update(Publication p) {

        Session session = SessionFactoryService.get().openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(p);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw e;
        } finally {
            session.close();
        }
        return p;
    }

    @Override
    public void delete(Publication p) {
        Session session = SessionFactoryService.get().openSession();
        try {
            session.beginTransaction();
            session.delete(p);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw e;
        } finally {
            session.close();
        }

    }

    @Override
	public Publication read(String pId) {
		Session session = SessionFactoryService.get().openSession();
		Publication res = null;
		try {
			res = (Publication) session
			.createQuery("select p from Publication p where p.id=:id")
			.setParameter("id", pId)
			.getSingleResult();
		} finally {
			session.close();
		}
		return res;
	}
}
