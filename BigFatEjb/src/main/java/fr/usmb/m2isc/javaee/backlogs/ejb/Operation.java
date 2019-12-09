package fr.usmb.m2isc.javaee.backlogs.ejb;

import java.util.List;

import fr.usmb.m2isc.javaee.backlogs.jpa.*;

public interface Operation {

    List<Agence> getAllAgence();

    Agence getAgence(String number);

    Backlog getBacklog(Agence agence);

    User signIn(String username, String password);

    Backlog getBacklogById(Long id);

    Backlog addEntryToBacklog(Entry entry, Long backlog_id);

    Entry getEntry(Long id);

    void deleteEntry(Long id, Long backlog_id);

    void updateEntry(Long entry_id, String name, String description, int priority, int estimation);

    void createAgency(String name);

    List<Comment> getCommentsByEntryId(Long entry_id);

    Entry addCommentToEntry(Comment comment, Long entry_id);

    Entry getEntryById(Long entry_id);
}