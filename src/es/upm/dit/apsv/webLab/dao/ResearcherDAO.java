package es.upm.dit.apsv.webLab.dao;

import java.util.Collection;

import es.upm.dit.apsv.webLab.model.Researcher;

public interface ResearcherDAO {
    public Researcher create( Researcher r);
    public Researcher read(Researcher r);
    public Collection<Researcher> readAll();
    public Researcher update( Researcher r);
    public void delete( Researcher r);
}
