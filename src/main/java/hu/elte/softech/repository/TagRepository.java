package hu.elte.softech.repository;

import hu.elte.softech.entity.Tag;
import hu.elte.softech.entity.Topic;
import hu.elte.softech.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    @Query(value = "DELETE FROM TOPIC_TAG tt WHERE tt.tag_id = :id",nativeQuery = true)
	void del(@Param("id") Long id);

	@Transactional
	@Modifying      // to mark delete or update query
    @Query(value = "INSERT INTO TOPIC_TAG VALUES( :tpid, :tgid )",nativeQuery = true)
	void ins(@Param("tpid") Long tpid,@Param("tgid") Long tgid);
	
	
//	@Query("SELECT tt.topic_id FROM Topic_Tag tt WHERE tt.tags ")
//	public Set<Topic> findTopicsOfTag(Tag tag);
}
