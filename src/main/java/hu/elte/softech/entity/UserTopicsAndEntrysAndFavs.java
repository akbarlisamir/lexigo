package hu.elte.softech.entity;

import java.util.List;

import lombok.Data;

@Data
public class UserTopicsAndEntrysAndFavs {

	private User user;

	private List<Topic> topics;

	private List<Entry> entrys;

	private List<Entry> favs;

}
