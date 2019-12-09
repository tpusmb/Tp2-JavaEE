package fr.usmb.m2isc.javaee.backlogs.ejb;

import fr.usmb.m2isc.javaee.backlogs.jpa.*;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Startup
@Singleton
public class InitBean implements Init {

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    public void init() {
        User ludo = new User("ludo","ludo");
        User trima = new User("trima","trima");
        em.persist(ludo);
        em.persist(trima);


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Backlog annecyBacklog = new Backlog();
            Backlog chamberyBacklog = new Backlog();

            ArrayList<Entry> entriesAnnecy = new ArrayList<Entry>();
            ArrayList<Entry> entriesChambe = new ArrayList<Entry>();
            Entry entry1 = new Entry(simpleDateFormat.parse("25/12/2010"),"Visualiser une carte",45,2,"Pourvoir visualiser la liste des magasins");
            Entry entry2 = new Entry(simpleDateFormat.parse("25/12/2012"),"Ajouter un item",45,2,"Ajouter une item dans le panier");
            Entry entry3 = new Entry(simpleDateFormat.parse("25/12/2019"),"Vider le panier",45,2,"Vider le panier");
            Entry entry4 = new Entry(simpleDateFormat.parse("26/08/2019"),"Manger un pandoro",45,2,"Miam");

            ArrayList<Comment> commentsEntry1 = new ArrayList<Comment>();
            ArrayList<Comment> commentsEntry2 = new ArrayList<Comment>();
            Comment comment1 = new Comment("besoin d'aide",trima);
            Comment comment2 = new Comment("tu te débrouille",ludo);
            Comment comment3 = new Comment("j'ai bientôt finit :)",ludo);
            commentsEntry1.add(comment1);
            commentsEntry1.add(comment2);
            commentsEntry2.add(comment3);
            em.persist(comment1);
            em.persist(comment2);
            em.persist(comment3);


            entry1.setComments(commentsEntry1);
            entry2.setComments(commentsEntry2);
            entriesAnnecy.add(entry1);
            entriesAnnecy.add(entry2);
            entriesAnnecy.add(entry3);
            entriesChambe.add(entry4);
            annecyBacklog.setEntries(entriesAnnecy);
            chamberyBacklog.setEntries(entriesChambe);

            em.persist(annecyBacklog);
            em.persist(chamberyBacklog);
            em.persist(entry1);
            em.persist(entry2);
            em.persist(entry3);
            em.persist(entry4);

            Agence annecy = new Agence("Agence Annecy",annecyBacklog);
            Agence chambery = new Agence("Agence Chambery",chamberyBacklog);
            em.persist(annecy);
            em.persist(chambery);



        } catch (ParseException e) {
            e.printStackTrace();
        }









    }
}
