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
            Backlog grenobleBacklog = new Backlog();

            ArrayList<Entry> entriesAnnecy = new ArrayList<Entry>();
            ArrayList<Entry> entriesChambe = new ArrayList<Entry>();
            ArrayList<Entry> entriesGrenob = new ArrayList<Entry>();
            Entry entry1 = new Entry(simpleDateFormat.parse("20/12/2012"),"Visualiser une carte",45,2,"Pouvoir visualiser la liste des magasins");
            Entry entry2 = new Entry(simpleDateFormat.parse("25/12/2014"),"Ajouter un item",45,2,"Ajouter un item dans le panier");
            Entry entry3 = new Entry(simpleDateFormat.parse("30/12/2017"),"Vider le panier",45,2,"Vider le panier");
            Entry entry4 = new Entry(simpleDateFormat.parse("31/12/2019"),"Reviser JavaEE",45,2,"C'est pas du Kotlin");
            Entry entry5 = new Entry(simpleDateFormat.parse("15/10/2020"),"Aller sur Mars",45,2,"Elon Musk a enfin finit de s'occuper de ses Tesla.");
            Entry entry6 = new Entry(simpleDateFormat.parse("20/10/2023"),"Finir le TP",45,2,"On en a fait du chemin");

            ArrayList<Comment> commentsEntry1 = new ArrayList<Comment>();
            ArrayList<Comment> commentsEntry2 = new ArrayList<Comment>();
            Comment comment1 = new Comment("On a un souci ici bro",bob);
            Comment comment2 = new Comment("Chacun ses moutons bro",stephane);
            Comment comment3 = new Comment("On passe a la mise en prod bro :)",stephane);
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
            entriesGrenob.add(entry5);
            entriesGrenob.add(entry6);
            annecyBacklog.setEntries(entriesAnnecy);
            chamberyBacklog.setEntries(entriesChambe);
            grenobleBacklog.setEntries(entriesGrenob);

            em.persist(annecyBacklog);
            em.persist(chamberyBacklog);
            em.persist(grenobleBacklog);
            em.persist(entry1);
            em.persist(entry2);
            em.persist(entry3);
            em.persist(entry4);
            em.persist(entry5);
            em.persist(entry6);

            Agence annecy = new Agence("Agence Annecy",annecyBacklog);
            Agence chambery = new Agence("Agence Chambery",chamberyBacklog);
            Agence grenoble = new Agence("Agence Grenoble",grenobleBacklog);
            em.persist(annecy);
            em.persist(chambery);
            em.persist(grenoble);



        } catch (ParseException e) {
            e.printStackTrace();
        }









    }
}
