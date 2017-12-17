																											package es.upm.dit.apsv.webLab.model;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.*;

@Entity
public class Researcher implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    private String id;
    private String email;
    private String name;
    private String affiliation;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy="authors", cascade= {CascadeType.MERGE, CascadeType.PERSIST})
    private Collection<Publication> pubs;


    public Researcher(String id, String email, String name, String affiliation, String password) {
        super();
        this.id = id;
        this.email = email;
        this.name = name;
        this.affiliation = affiliation;
        this.password = password;
        this.pubs = new ArrayList<>();
    }

    public Researcher(){}


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Publication> getPublications() {
        return pubs;
    }

    public void setPublications(Collection<Publication> pubs) {
        this.pubs = pubs;
    }
}
