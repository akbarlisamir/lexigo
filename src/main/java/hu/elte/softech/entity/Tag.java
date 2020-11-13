package hu.elte.softech.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
public class Tag {
	
	@Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, unique = true)
    private String value;

}
