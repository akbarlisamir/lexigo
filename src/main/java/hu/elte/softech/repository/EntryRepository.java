package hu.elte.softech.repository;

import hu.elte.softech.entity.Entry;
import hu.elte.softech.entity.User;
import hu.elte.softech.entity.Topic;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryRepository extends JpaRepository<Entry,Long> {

	@Query("SELECT e FROM Entry e WHERE e.user = ?1")
	public List<Entry> findAllForUser(User user);

	@Query("SELECT e FROM Entry e WHERE e.topic = ?1")
	public List<Entry> findAllForTopic(Topic topic);

	@Query("SELECT e FROM Entry e WHERE e.topic = ?1 AND e.user = ?2")
	public List<Entry> findEntryForTopicUser(Topic topic, User user);
	
}
