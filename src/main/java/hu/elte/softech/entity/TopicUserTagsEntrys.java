package hu.elte.softech.entity;

import java.util.List;
import lombok.Data;

@Data
public class TopicUserTagsEntrys {

	public Topic topic;

	public User user;

	public List<Entry> entrys;

	public List<Tag> tags;

}
