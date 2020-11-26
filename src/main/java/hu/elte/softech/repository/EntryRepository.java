package hu.elte.softech.repository;

import hu.elte.softech.entity.Entry;
import hu.elte.softech.entity.Tag;
import hu.elte.softech.entity.User;
import hu.elte.softech.entity.Topic;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EntryRepository extends JpaRepository<Entry,Long> {
	
	@Transactional
	@Modifying      // to mark delete or update query
    @Query(value = "DELETE FROM Entry r WHERE r.user_id = :userid",nativeQuery = true)
	void delFromEntryByUser(@Param("userid") Long userId);
	
	@Transactional
	@Modifying      // to mark delete or update query
    @Query(value = "DELETE FROM Entry r WHERE r.entry_id = :entryid",nativeQuery = true)
	void delFromEntryByEntry(@Param("entryid") Long entryId);
	
	@Transactional
	@Modifying      // to mark delete or update query
    @Query(value = "DELETE FROM Entry r WHERE r.topic_id = :topicid",nativeQuery = true)
	void delFromEntryByTopic(@Param("topicid") Long topicId);
	
	@Transactional
	@Modifying      // to mark delete or update query
    @Query(value = "DELETE FROM FAVORITE fvrt WHERE fvrt.entry_id = :entryid",nativeQuery = true)
	void delFromFavoriteByEntry(@Param("entryid") Long entryId);
	
	@Transactional
	@Modifying      // to mark delete or update query
    @Query(value = "DELETE FROM Favorite r WHERE r.user_id = :userid",nativeQuery = true)
	void delFromFavoriteByUser(@Param("userid") Long userId);
	
	@Transactional
	@Modifying      // to mark delete or update query
    @Query(value = "DELETE FROM Favorite r WHERE r.user_id = :userid AND r.entry_id = :entryid",nativeQuery = true)
	void delFromFavoriteByUserEntry(@Param("userid") Long userId, @Param("entryid") Long entryId);
	
	
	Page<Entry> findByUserId(Long userId, Pageable pageable);
	
	Page<Entry> findByTopicId(Long topicId, Pageable pageable);
	
    Optional<Entry> findByIdAndUserId(Long id, Long userId);
    
    Optional<Entry> findByIdAndTopicId(Long id, Long topicId);

	@Query("SELECT e FROM Entry e WHERE e.user = ?1")
	public List<Entry> findAllForUser(User user);

	@Query("SELECT e FROM Entry e WHERE e.topic = ?1")
	public List<Entry> findAllForTopic(Topic topic);

	@Query("SELECT e FROM Entry e WHERE e.topic = ?1 AND e.user = ?2")
	public List<Entry> findEntryForTopicUser(Topic topic, User user);


//	@Query("SELECT e FROM Entry e WHERE e.topic IN (SELECT tt.topictags FROM TopicTag tt WHERE tt.tags = ?1)")
//	public List<Entry> findAllForTag(Tag tag);
	
}
