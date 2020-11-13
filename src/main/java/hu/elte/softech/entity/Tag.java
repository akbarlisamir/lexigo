package hu.elte.softech.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Tag {
	
	@Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, unique = true)
    private String value;

}
