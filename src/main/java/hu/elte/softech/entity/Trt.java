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
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Data
@Entity
public class Trt implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String value;
    
    @ManyToMany(fetch=FetchType.LAZY,cascade={CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name = "Trt_Tst", 
    		  joinColumns = {@JoinColumn(name = "trt_id", referencedColumnName = "id",
              nullable = false, updatable = false)}, 
    		  inverseJoinColumns = {@JoinColumn(name = "tst_id", referencedColumnName = "id",
              nullable = false, updatable = false)})
	@JsonIgnore
    private Set<Tst> tsts;
    
    public void removeTst(Tst p) {
        this.tsts.remove(p);
        p.getTrts().remove(this);
    }
    
}
