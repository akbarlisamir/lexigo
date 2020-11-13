package hu.elte.softech.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;

@Data
@Entity
public class Tag {
	
	@Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, unique = true)
    private String value;
    
    @ManyToMany(mappedBy = "tags")
    private Set<Topic> topictags;

}
