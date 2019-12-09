package fr.usmb.m2isc.javaee.backlogs.jpa;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Agence implements Serializable {
    public Agence(){

    }

    public Agence(String name, Backlog backlog) {
        this.name = name;
        this.backlog = backlog;
    }

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Backlog getBacklog() {
        return backlog;
    }

    public void setBacklog(Backlog backlog) {
        this.backlog = backlog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToOne(cascade = CascadeType.ALL)
    private Backlog backlog;

    private String name;

    public Long getId() {
        return id;
    }
}
