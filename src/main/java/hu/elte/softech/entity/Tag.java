package hu.elte.softech.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PostLoad;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Tag implements Serializable{
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String value;
    
    @ManyToMany(fetch=FetchType.LAZY,mappedBy = "tags", cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
    @Fetch(value=FetchMode.SELECT)
	@JsonIgnore
    private Set<Topic> topics;
    
//    @PostLoad
//    private void postLoadFunction(){
//    	System.out.println("TAG JAVA CALLED!!!!!!!!!!!!!")
//        //log.info("BankBranch PostLoad method called");
//    }

}
