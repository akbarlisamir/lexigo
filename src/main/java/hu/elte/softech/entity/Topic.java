package hu.elte.softech.entity;

import lombok.*;

import javax.persistence.CascadeType;
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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Topic{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String value;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @ManyToMany(fetch=FetchType.LAZY,cascade={CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name = "TopicTag",
    		  joinColumns = {@JoinColumn(name = "topic_id", referencedColumnName = "id",
              nullable = false, updatable = false)},
    		  inverseJoinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "id",
              nullable = false, updatable = false)})
    @JsonIgnore
    private Set<Tag> tags;

    @ManyToMany(fetch=FetchType.LAZY,mappedBy = "followtopics", cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
    @Fetch(value=FetchMode.SELECT)
	@JsonIgnore
	private Set<User> userfollowtopics;

//    @PostLoad
//    private void postLoadFunction(){
//        System.out.println("Topic.java called!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//    	//log.info("BankBranch PostLoad method called");
//    }



//  @Column(nullable = false)
//  private Date created;

  //@OldOne
//  @ManyToOne(fetch=FetchType.EAGER)
//  @JsonIgnore
//  private User user;

//    @ManyToMany(fetch=FetchType.LAZY,mappedBy = "followtopics",cascade=CascadeType.ALL)
//    @JsonIgnore
//    private Set<User> userfollowtopics;
}
