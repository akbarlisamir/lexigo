package hu.elte.softech.repository;

import hu.elte.softech.entity.Entry;
import hu.elte.softech.entity.Topic;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TopicRepository extends JpaRepository<Topic,Long> {

	@Query("SELECT t FROM Topic t WHERE t.value = ?1")
	public Topic findTopicByTopicName(String topicname);
	
	@Transactional
	@Modifying      // to mark delete or update query
    @Query(value = "DELETE FROM FOLLOW_TOPIC ft WHERE ft.topic_id = :id",nativeQuery = true)
	void del(@Param("id") Long id);
	
	//List<Topic> findByValueContaining(String value);
	
//	@Query("SELECT t FROM Topic t WHERE t.value = ?1")
//	public Topic findTopicByTopicName(String topicname);
	//find topics by clicking tag
}
