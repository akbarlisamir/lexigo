package hu.elte.softech.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false, unique = true, length = 20)
    private String username;

    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private Date createdTime;
    
    private Date birthday;
    
    @OneToMany(mappedBy="user")
    private List<Topic> topics;
    
    @OneToMany(mappedBy="user")
    private List<Entry> entries;
    
    @ManyToMany
    @JoinTable(name = "Favorite", 
    		  joinColumns = @JoinColumn(name = "user_id"), 
    		  inverseJoinColumns = @JoinColumn(name = "entry_id"))
    private Set<Entry> favs;
    
    
    
}

