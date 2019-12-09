package fr.usmb.m2isc.javaee.backlogs.ejb;

import java.util.Iterator;
import java.util.List;

import javax.ejb.DependsOn;
import javax.ejb.EJBException;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.usmb.m2isc.javaee.backlogs.jpa.*;

@Stateless
@Remote
@DependsOn("InitBean")
public class OperationBean implements Operation {
	
	@PersistenceContext
	private EntityManager em;


	public OperationBean() {

	}

    @Override
    public List<Agence> getAllAgence() {
        return em.createQuery("select object(o) from Agence as o").getResultList();
    }


    @Override
    public User signIn(String username, String password) {
        Query req = em.createNamedQuery("signIn");
        req.setParameter("username", username);
        req.setParameter("password", password);
        return (User) req.getSingleResult();

    }

    @Override
    public Backlog getBacklogById(Long id) {
        return em.find(Backlog.class,id);
    }

    @Override
    public Backlog addEntryToBacklog(Entry entry, Long backlog_id) {
        Backlog backlog = this.getBacklogById(backlog_id);
        backlog.addEntry(entry);
        em.persist(entry);
        return backlog;
    }

    @Override
    public Agence getAgence(String number) {
        return em.find(Agence.class, Long.parseLong(number));
    }


    @Override
    public Backlog getBacklog(Agence agence) {
        return em.find(Backlog.class, agence.getId());
    }

    @Override
    public Entry getEntry(Long id) { return em.find(Entry.class, id); }

    @Override
    public void deleteEntry(Long id, Long backlog_id) {
	    Entry e = getEntry(id);
        Backlog backlog = this.getBacklogById(backlog_id);

        for(Iterator<Entry> iter = backlog.getEntries().listIterator(); iter.hasNext();) {
            Entry entry = iter.next();

            if(entry.getId().equals(e.getId())) {
                iter.remove();
            }
        }

	    if(e != null) {
	        em.remove(e);
        }
    }

    @Override
    public void updateEntry(Long entry_id, String name, String description, int priority, int estimation) {
        Entry e = em.find(Entry.class, entry_id);

        e.setName(name);
        e.setPriority(priority);
        e.setEstimation(estimation);
        e.setDescription(description);
    }

    @Override
    public void createAgency(String name) {
	    Backlog b = new Backlog();
        em.persist(b);
	    Agence a = new Agence(name, b);
        em.persist(a);
    }

    @Override
    public List<Comment> getCommentsByEntryId(Long entry_id) {
	    Entry entry = em.find(Entry.class,entry_id);
	    return entry.getComments();
    }

    @Override
    public Entry addCommentToEntry(Comment comment, Long entry_id) {

        Entry entry = this.getEntryById(entry_id);
        entry.addComment(comment);
        em.persist(comment);
        return entry;
    }

    @Override
    public Entry getEntryById(Long entry_id) {
        return em.find(Entry.class,entry_id);
    }
}
