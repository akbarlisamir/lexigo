package hu.elte.softech.repository;

import hu.elte.softech.entity.Tag;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

	@Query("SELECT t FROM Tag t WHERE t.value = ?1")
	public Tag findTagByValue(String value);

	@Transactional
	@Modifying      // to mark delete or update query
    @Query(value = "DELETE FROM TOPIC_TAG tt WHERE tt.tag_id = :tagid",nativeQuery = true)
	void delFromTopicTagByTag(@Param("tagid") Long tagId);

	@Transactional
	@Modifying      // to mark delete or update query
    @Query(value = "DELETE FROM TOPIC_TAG tt WHERE tt.topic_id = :topicid",nativeQuery = true)
	void delFromTopicTagByTopic(@Param("topicid") Long topicId);

	@Transactional
	@Modifying      // to mark delete or update query
    @Query(value = "DELETE FROM TOPIC_TAG tt WHERE tt.topic_id = :topicid AND tt.tag_id = :tagid",nativeQuery = true)
	void delFromTopicTagByTopicTag(@Param("topicid") Long topicId,@Param("tagid") Long tagId);

	@Transactional
	@Modifying      // to mark delete or update query
    @Query(value = "INSERT INTO TOPIC_TAG VALUES( :topicId, :tagId )",nativeQuery = true)
	void ins(@Param("topicId") Long topicId, @Param("tagId") Long tagId);

	@Query(value = "SELECT COUNT(*) FROM Topic_Tag tt WHERE tt.topic_id = :topicId AND tt.tag_id = :tagId",nativeQuery = true)
	public int check(@Param("topicId") Long topicId, @Param("tagId") Long tagId);

	@Query(value = "SELECT tt.tag_id FROM Topic_Tag tt WHERE tt.topic_id = :topicId", nativeQuery = true)
	public List<String> findTopicsOfTag(Long topicId);

	@Query(value = "SELECT COUNT(*) FROM Tag t WHERE t.id = :tagId", nativeQuery = true)
	int checkExistenseOfTagByTagId(Long tagId);
}
