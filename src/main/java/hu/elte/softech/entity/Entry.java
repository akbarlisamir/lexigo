package hu.elte.softech.entity;

// import java.time.LocalDateTime;
// import java.util.Date;
// import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
// import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
// import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
// import org.springframework.data.annotation.CreatedDate;
// import org.springframework.data.annotation.LastModifiedDate;
// import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

// import lombok.AllArgsConstructor;
import lombok.Data;
// import lombok.EqualsAndHashCode;
// import lombok.NoArgsConstructor;

@Data
@Entity
//@EntityListeners(AuditingEntityListener.class)
public class Entry {
	
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
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "topic_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Topic topic;
    
    @ManyToMany(fetch=FetchType.LAZY,mappedBy = "favs", cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
    @Fetch(value=FetchMode.SELECT)
	@JsonIgnore
    private Set<User> userfavs;
   
//    @OneToMany(fetch=FetchType.LAZY,mappedBy="entry",cascade=CascadeType.ALL)
//    @JsonIgnore
//    private List<Ranking> rankings;
    
//    @Column(nullable = false, updatable = false)
//    @CreatedDate
//    private LocalDateTime createdDate;
//    
//    @Column(nullable = false)
//    @LastModifiedDate
//    private LocalDateTime updatedDate;
    
    //@OldOne
//    @ManyToOne(fetch=FetchType.EAGER)
//    @JsonIgnore
//    private User user;
    
    
    
//    @ManyToOne
//    @JoinColumn(name="user_id", referencedColumnName="id", nullable=false)
//    private User user;
   
//    @ManyToOne
//    @JoinColumn(name="topic_id", referencedColumnName="id", nullable=false)
//    @JsonIgnore
//    private Topic topic;
  
    

}
