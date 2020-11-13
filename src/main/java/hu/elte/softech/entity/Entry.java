package hu.elte.softech.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
public class Entry {
	
	@Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, unique = true)
    private String value;
    
    @ManyToOne(fetch=FetchType.LAZY)
    private User user;
    
    @ManyToOne
    private Topic topic;
    
    @ManyToMany(mappedBy = "favs")
    private Set<User> userfavs;

}
