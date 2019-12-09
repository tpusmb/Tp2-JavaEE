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
        User stephane = new User("stephane","talbot");
        User bob = new User("bob","eponge");
        em.persist(stephane);
        em.persist(bob);


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Backlog annecyBacklog = new Backlog();
            Backlog chamberyBacklog = new Backlog();

            ArrayList<Entry> entriesAnnecy = new ArrayList<Entry>();
            ArrayList<Entry> entriesChambe = new ArrayList<Entry>();
            Entry entry1 = new Entry(simpleDateFormat.parse("20/12/2012"),"Visualiser une carte",45,2,"Pouvoir visualiser la liste des magasins");
            Entry entry2 = new Entry(simpleDateFormat.parse("25/12/2014"),"Ajouter un item",45,2,"Ajouter un item dans le panier");
            Entry entry3 = new Entry(simpleDateFormat.parse("30/12/2017"),"Vider le panier",45,2,"Vider le panier");
            Entry entry4 = new Entry(simpleDateFormat.parse("31/12/2019"),"Reviser JavaEE",45,2,"C'est pas du Kotlin");

            ArrayList<Comment> commentsEntry1 = new ArrayList<Comment>();
            ArrayList<Comment> commentsEntry2 = new ArrayList<Comment>();
            Comment comment1 = new Comment("besoin d'aide",bob);
            Comment comment2 = new Comment("tu te débrouille",stephane);
            Comment comment3 = new Comment("j'ai bientôt finit :)",stephane);
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
