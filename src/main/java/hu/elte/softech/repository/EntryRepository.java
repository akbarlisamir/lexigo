package hu.elte.softech.repository;

import hu.elte.softech.entity.Entry;
import hu.elte.softech.entity.Tag;
import hu.elte.softech.entity.User;
import hu.elte.softech.entity.Topic;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EntryRepository extends JpaRepository<Entry,Long> {

	@Query("SELECT e FROM Entry e WHERE e.user = ?1")
	public List<Entry> findAllForUser(User user);

	@Query("SELECT e FROM Entry e WHERE e.topic = ?1")
	public List<Entry> findAllForTopic(Topic topic);

	@Query("SELECT e FROM Entry e WHERE e.topic = ?1 AND e.user = ?2")
	public List<Entry> findEntryForTopicUser(Topic topic, User user);
	
	@Transactional
	@Modifying      // to mark delete or update query
    @Query(value = "DELETE FROM FAVORITE fvrt WHERE fvrt.entry_id = :id",nativeQuery = true)
	void del(@Param("id") Long id);

//	@Query("SELECT e FROM Entry e WHERE e.topic IN (SELECT tt.topictags FROM TopicTag tt WHERE tt.tags = ?1)")
//	public List<Entry> findAllForTag(Tag tag);
	
}
