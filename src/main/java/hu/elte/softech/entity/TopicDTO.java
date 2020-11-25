package hu.elte.softech.entity;

import java.util.List;

import lombok.Data;

@Data
public class TopicDTO {
	
	private Long user_id;

	private Long topic_id;
	
	private String topic_value;
	
	private List<String> tags;

}
