package hu.elte.softech.entity;

import java.util.List;

import lombok.Data;

@Data
public class EntryWD {

	public Entry entry;

	public Topic topic;

	public List<Tag> tags;

	public int rankUp;

	public int rankDown;

	public User user;

	public int fav;

}
