package fr.usmb.m2isc.javaee.backlogs.jpa;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Entry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    public List<Comment> getComments() {
        return comments;
    }

    public Date getCreation() {
        return creation;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public int getEstimation() {
        return estimation;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public Entry(Date creation, String name, int priority, int estimation, String description) {
        this.creation = creation;
        this.name = name;
        this.priority = priority;
        this.estimation = estimation;
        this.description = description;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
    private List<Comment> comments;


    @Temporal(TemporalType.TIMESTAMP)
    private Date creation;

    private String name;

    private int priority;

    private int estimation;

    private String description;

    public Entry (){

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setEstimation(int estimation) {
        this.estimation = estimation;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addComment(Comment comment){
        this.comments.add(comment);
    }
}
