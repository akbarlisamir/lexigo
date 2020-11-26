package hu.elte.softech.repository;

import hu.elte.softech.entity.Entry;
import hu.elte.softech.entity.Tag;
import hu.elte.softech.entity.Topic;
import hu.elte.softech.entity.TopicTags;
import hu.elte.softech.entity.User;

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
public interface TopicRepository extends JpaRepository<Topic,Long> {
	
	Page<Topic> findByUserId(Long userId, Pageable pageable);
	
    Optional<Topic> findByIdAndUserId(Long id, Long userId);
	
    @Query("SELECT e FROM Topic e WHERE e.user = ?1")
	public List<Topic> findAllForUser(User user);

	@Query("SELECT t FROM Topic t WHERE t.value = ?1")
	public Topic findTopicByTopicName(String topicname);
	
	@Transactional
	@Modifying      // to mark delete or update query
    @Query(value = "DELETE FROM Topic ft WHERE ft.topic_id = :topicid",nativeQuery = true)
	void delFromTopicByTopic(@Param("topicid") Long topicId);

	@Transactional
	@Modifying      // to mark delete or update query
    @Query(value = "DELETE FROM Topic ft WHERE ft.user_id = :userid",nativeQuery = true)
	void delFromTopicByUser(@Param("userid") Long userId);
	
	@Transactional
	@Modifying      // to mark delete or update query
    @Query(value = "DELETE FROM FOLLOW_TOPIC ft WHERE ft.topic_id = :topicid",nativeQuery = true)
	void delFromFollowTopicByTopic(@Param("topicid") Long topicId);

	@Transactional
	@Modifying      // to mark delete or update query
    @Query(value = "DELETE FROM FOLLOW_TOPIC ft WHERE ft.user_id = :userid",nativeQuery = true)
	void delFromFollowTopicByUser(@Param("userid") Long userId);
	
	@Transactional
	@Modifying      // to mark delete or update query
    @Query(value = "DELETE FROM FOLLOW_TOPIC ft WHERE ft.user_id = :userid AND ft.topic_id = :topicid",nativeQuery = true)
	void delFromFollowTopicByUserTopic(@Param("userid") Long userId,@Param("topicid") Long topicId);
	
	@Transactional
	@Modifying      // to mark delete or update query
    @Query(value = "INSERT INTO Topic VALUES( :id, :val, :uid)",nativeQuery = true)
	void ins(@Param("id") Long id,@Param("val") String val,@Param("uid") Long uid);
	
//	@Query(value = "SELECT tg FROM TOPIC t INNER JOIN ",nativeQuery = true)
//	List<Tag> getTagsOfTopic();
	
	//List<Topic> findByValueContaining(String value);
	
//	@Query("SELECT t FROM Topic t WHERE t.value = ?1")
//	public Topic findTopicByTopicName(String topicname);
	//find topics by clicking tag
}
