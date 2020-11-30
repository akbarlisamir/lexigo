package hu.elte.softech.entity;

import java.util.List;

import lombok.Data;

@Data
public class TopicWD {

	public Topic topic;

	public List<Entry> entrys;

	public List<Tag> tags;


}
