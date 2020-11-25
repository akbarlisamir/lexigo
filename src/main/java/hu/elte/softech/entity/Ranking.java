package hu.elte.softech.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
public class Ranking {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

	//@OldOne
//	@ManyToOne(fetch=FetchType.EAGER)
//	@JsonIgnore
//    private User user;
	
	@ManyToOne
    @JoinColumn(name="user_id", referencedColumnName="id", nullable=false)
    private User user;
 
	@ManyToOne(fetch=FetchType.EAGER)
	@JsonIgnore
    private Entry entry;
    
    @Column(nullable = false)
    private Boolean value; 
	
}
