package hu.elte.softech.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.PreRemove;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Data
@Entity
public class Tst{
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String value;
    
//    @ManyToMany(fetch=FetchType.LAZY,mappedBy = "tags")
//    @JsonIgnore
//    private Set<Topic> topics;
    
//    @ManyToOne
//    @JoinColumn(name="user_id", referencedColumnName="id", nullable=false)
//    private User user;
    
    @ManyToMany(fetch=FetchType.LAZY,mappedBy = "tsts", cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
    @Fetch(value=FetchMode.SELECT)
	@JsonIgnore
    private Set<Trt> trts;
    
    public void removeTrt(Trt p) {
        this.trts.remove(p);
        p.getTsts().remove(this);
    }
    
    @PreRemove
    private void removeTstsFromTrts() {
        for (Trt u : trts) {
            u.getTsts().remove(this);
        }
    }
    
//    @PostLoad
//    private void postLoadFunction(){
//    	System.out.println("TAG JAVA CALLED!!!!!!!!!!!!!")
//        //log.info("BankBranch PostLoad method called");
//    }

}
