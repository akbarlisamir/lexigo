package hu.elte.softech.entity;

import lombok.*;

import javax.persistence.*;

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

//    @Column(nullable = false)
//    private Date createdTime;
//    
//    private Date birthday;
    
    @OneToMany(fetch = FetchType.LAZY,mappedBy="user")
    @JsonIgnore
    private List<Topic> topics;
    
    @OneToMany(fetch = FetchType.LAZY,mappedBy="user")
    @JsonIgnore
    private List<Entry> entries;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Favorite", 
    		  joinColumns = @JoinColumn(name = "user_id"), 
    		  inverseJoinColumns = @JoinColumn(name = "entry_id"))
    @JsonIgnore
    private Set<Entry> favs;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "FollowTopic", 
    		  joinColumns = @JoinColumn(name = "user_id"), 
    		  inverseJoinColumns = @JoinColumn(name = "topic_id"))
    @JsonIgnore
    private Set<Topic> followtopics;
    
    @OneToMany(fetch = FetchType.LAZY,mappedBy="user")
    @JsonIgnore
    private List<Ranking> rankings;    
    
}

