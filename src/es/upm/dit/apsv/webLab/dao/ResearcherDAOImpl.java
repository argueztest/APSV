package es.upm.dit.apsv.webLab.dao;


import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.Session;

import es.upm.dit.apsv.webLab.model.Researcher;

public class ResearcherDAOImpl implements ResearcherDAO {
    private static ResearcherDAOImpl instance;

    private ResearcherDAOImpl() {
    }

    public static ResearcherDAOImpl getInstance() {
        if (instance == null)
            instance = new ResearcherDAOImpl();
        return instance;
    }

    @Override
    public Researcher create(Researcher r) {
        Session session = SessionFactoryService.get().openSession();
        try {
            session.beginTransaction();
            session.save(r);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw e;
        } finally {
            session.close();
        }
        return r;
    }

    @Override
    public Researcher read(Researcher r) {
        Session session = SessionFactoryService.get().openSession();

        Researcher res = null;
        try {
            res = (Researcher) session
                    .createQuery("select	r from	Researcher	r where	r.id= :id")
                    .setParameter("id", r.getId())
                    .uniqueResult();
        } catch (Exception e) {
            throw e;
        } finally {
            session.close();
        }
        return res;
    }

    @Override
    public Researcher update(Researcher r) {
        Session session = SessionFactoryService.get().openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(r);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw e;
        } finally {
            session.close();
        }
        return r;
    }

    @Override
    public void delete(Researcher r) {
        Session session = SessionFactoryService.get().openSession();
        try {
            session.beginTransaction();
            session.delete(r);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public Collection<Researcher> readAll() {
        Session session = SessionFactoryService.get().openSession();
        Collection<Researcher> res = new ArrayList<>();
        try {
            res.addAll(session.createQuery("select	r	from	Researcher	r")
                    .getResultList());
        } catch (Exception e) {
            throw e;
        } finally {
            session.close();
        }
        return res;
    }
    
    public Researcher searchUser( String email, String password) {
        Session session = SessionFactoryService.get().openSession();

        Researcher res = null;
        try {
            res = (Researcher) session
					.createQuery("select r from Researcher r where r.email= :email and r.password = :password")
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .uniqueResult();
        } catch (Exception e) {
            throw e;
        } finally {
            session.close();
        }
        return res;
    }

	@Override
	public Researcher read(String rId) {
		Session session = SessionFactoryService.get().openSession();
		Researcher res = null;
		try {
			res = (Researcher) session
					.createQuery("select r from Researcher r where r.id= :id")
					.setParameter("id", rId)
					.uniqueResult();
		} finally {
			session.close();
		}
		return res;
	}

	@Override
	public Researcher readEmail(String email) {
		Session session = SessionFactoryService.get().openSession();
		Researcher res = null;
		try {
			res = (Researcher) session
					.createQuery("select r from Researcher r where r.eamil= :email")
					.setParameter("email", email)
					.uniqueResult();
		}  finally {
			session.close();
		}
		return res;
	}

	@Override
	public Researcher readUser(String email, String pwd) {
		// TODO Auto-generated method stub
		return searchUser(email, pwd);
	}

    
    
}
