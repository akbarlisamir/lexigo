package hu.elte.softech.entity;

import java.util.Set;

import lombok.Data;

@Data
public class TopicTags {

	private Topic topic;
	
	private User user;
		
	private Set<Tag> tagValues;

}
