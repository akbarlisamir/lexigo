package hu.elte.softech.repository;

import hu.elte.softech.entity.Tag;
import hu.elte.softech.entity.Topic;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

//	@Query("SELECT tt.topic_id FROM Topic_Tag tt WHERE tt.tags ")
//	public Set<Topic> findTopicsOfTag(Tag tag);
}
