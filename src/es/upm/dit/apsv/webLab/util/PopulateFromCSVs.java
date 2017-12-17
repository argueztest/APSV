package es.upm.dit.apsv.webLab.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import es.upm.dit.apsv.webLab.dao.PublicationDAO;
import es.upm.dit.apsv.webLab.dao.PublicationDAOImpl;
import es.upm.dit.apsv.webLab.dao.ResearcherDAO;
import es.upm.dit.apsv.webLab.dao.ResearcherDAOImpl;
import es.upm.dit.apsv.webLab.model.Publication;
import es.upm.dit.apsv.webLab.model.Researcher;

public class PopulateFromCSVs {

    /**
     * args[0] should contains authors csv path.
     * Authors csv should have the following structure "AuthorId;Name;lName;Affiliation"
     *
     * args[1] should contains publications csv path.
     * Publications csv should have the following structure "PublicationId;PublicationName;CiteCount;Auth1,Auth2,..."
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        String f1 = args.length >1 ? args[0] : "authors.csv";
        String f2 = args.length >2 ? args[1] : "publications.csv";
        final ResearcherDAO daoR = ResearcherDAOImpl.getInstance();
        Stream<String> lines = new BufferedReader(new FileReader(new File(f1))).lines();
        Map<String, Researcher> researchers= lines
                .map(s->s.split(";"))
                .collect( // id; name lname; email; password; affiliation;
                        Collectors.toMap(s->s[0], s->new Researcher(s[0], s[1]+ " "+s[2]," " , s[3], "")));

        researchers.values().forEach(daoR::create);

        final PublicationDAO daoP = PublicationDAOImpl.getInstance();
        lines = new BufferedReader(new FileReader(new File(f2))).lines();
        lines.map(s->s.split(";")).filter(s->s.length==4).map(s->{
            Publication p = new Publication(s[0],
                    s[1].length()>250?s[1].substring(0, 250):s[1],
                    Integer.valueOf(s[2]));
            p.getAuthors().addAll(Arrays.stream(s[3].split(",")).filter(r->researchers.containsKey(r)).map(r->researchers.get(r)).collect(Collectors.toList()));
            return p;
        }).forEach(daoP::update);
        

    }
}
