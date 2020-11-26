package hu.elte.softech.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class EntryRankTopic{
	
	public Entry entry;
	
	public String topicName;
	
	public int rankUp;
	
	public int rankDown;

}
