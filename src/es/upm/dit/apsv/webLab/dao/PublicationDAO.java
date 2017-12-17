package es.upm.dit.apsv.webLab.dao;

import java.util.Collection;

import es.upm.dit.apsv.webLab.model.Publication;

public interface PublicationDAO {
    public Publication create(Publication p);
    public Publication read( Publication p);
    public Collection<Publication> readAll();
    public Publication update( Publication p);
    public void delete( Publication p);
    
	public Publication read(String pId);

}