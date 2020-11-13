package hu.elte.softech.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Topic {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, unique = true)
    private String value;

    @Column(nullable = false)
    private Date created;

    @ManyToOne(fetch=FetchType.LAZY)
    private User user;
    
    @OneToMany(mappedBy="topic")
    private List<Entry> entries;
    
    @ManyToMany
    @JoinTable(name = "TopicTag", 
    		  joinColumns = @JoinColumn(name = "tag_id"), 
    		  inverseJoinColumns = @JoinColumn(name = "topic_id"))
    private Set<Tag> tags;
    
}
