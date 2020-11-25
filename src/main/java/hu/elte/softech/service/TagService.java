package hu.elte.softech.service;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;

import hu.elte.softech.entity.Tag;
import hu.elte.softech.entity.Topic;

public interface TagService {
	
	List<Tag> allTags();
	
	Tag findOneTag(Long id);
	
	Set<Topic> findOneTagTopics(Long id);
	
	ResponseEntity<Void> deleteTag(Long id);

}
