package hu.elte.softech.service;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;

import hu.elte.softech.entity.Tag;
import hu.elte.softech.entity.TopicTag;
import hu.elte.softech.entity.TopicUserTagsEntrys;

public interface TagService {

	//GET Tags
	List<Tag> findAllTags();

	//POST Tag
	Tag createTag(Tag tagRequest);

	//EDIT Tag
	Tag editTag(Long tagId, Tag tagRequest);

	//GET Tag
	Tag findOneTag(Long tagId);

	//GET Topics By TagId
	List<TopicUserTagsEntrys> findOneTagTopics(Long tagId);

	//DELETE Tag
	ResponseEntity<Void> deleteTag(Long tagId);

	//POST Tags
	Set<Tag> createTags(Set<Tag> tagsRequest);

	//POST Tags
	TopicTag createTopicTag(Long topicId, Long tagId);

	List<String> findTagListOfTopic(Long topicId);

}
