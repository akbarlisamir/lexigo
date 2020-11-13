package hu.elte.softech.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
public class Ranking {
	
	@Id
    @GeneratedValue
    private int id;

	@ManyToOne(fetch=FetchType.LAZY)
    private User user;
 
	@ManyToOne(fetch=FetchType.LAZY)
    private Entry entry;
    
    @Column(nullable = false)
    private Boolean value; 
	
}
