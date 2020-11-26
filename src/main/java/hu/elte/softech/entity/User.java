package hu.elte.softech.entity;

import lombok.*;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String username;

    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private String role;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @OneToMany(fetch = FetchType.LAZY,mappedBy="user",cascade=CascadeType.ALL)
    @JsonIgnore
    private List<Topic> topics;
    
    @OneToMany(fetch = FetchType.LAZY,mappedBy="user",cascade=CascadeType.ALL)
    @JsonIgnore
    private List<Entry> entries;
    
    @ManyToMany(fetch = FetchType.LAZY,cascade={CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name = "Favorite", 
    		joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id",
            nullable = false, updatable = false)}, 
  		  inverseJoinColumns = {@JoinColumn(name = "entry_id", referencedColumnName = "id",
            nullable = false, updatable = false)})
    @JsonIgnore
    private Set<Entry> favs;
    
    @ManyToMany(fetch=FetchType.LAZY,cascade={CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name = "FollowTopic", 
    		  joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id",
              nullable = false, updatable = false)}, 
    		  inverseJoinColumns = {@JoinColumn(name = "topic_id", referencedColumnName = "id",
              nullable = false, updatable = false)})
    @JsonIgnore
    private Set<Topic> followtopics;
    
    @OneToMany(fetch = FetchType.LAZY,mappedBy="user",cascade=CascadeType.ALL)
    private List<Ranking> rankings;
    
    //---------------------------------------
    
//    @OneToMany(mappedBy="user",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
//    private List<Tst> tsts;
    
//    public void addTst(Tst tst){
//        if(tst!=null){
//            tst.setUser(this);
//            this.tsts.add(tsts);
//        }
//    }

//    public void removeTst(Tst tst){
//        if(tst!=null){
//            tst.setUser(null);
//        }
//        this.tsts.remove(tst);
//    }
    
//  @Column(nullable = false)
//  private Date createdTime;
//  
//  private Date birthday;
    
//    @ManyToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
//    @JoinTable(name = "FollowTopic", 
//    		  joinColumns = @JoinColumn(name = "user_id"), 
//    		  inverseJoinColumns = @JoinColumn(name = "topic_id"))
//    @JsonIgnore
//    private Set<Topic> followtopics;
    
//    public void addTopic(Topic topic) {
//        this.topics.add(topic);
//        topic.setUser(this);
//    }
//    
//    public void addEntry(Entry entry) {
//    	this.entries.add(entry);
//    	entry.setUser(this);
//    }
    
    //----------------------------------------
    
}

