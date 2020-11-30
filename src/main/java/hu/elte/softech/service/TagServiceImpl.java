package hu.elte.softech.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hu.elte.softech.entity.Tag;
import hu.elte.softech.entity.TopicTag;
import hu.elte.softech.entity.TopicUserTagsEntrys;
import hu.elte.softech.repository.TagRepository;
import hu.elte.softech.repository.TopicRepository;

@Service
public class TagServiceImpl implements TagService{

	@Autowired
	private TagRepository tagR;

	@Autowired
	private TopicService topicS;

	@Autowired
	private TopicRepository topicR;

	@Override
	public List<Tag> findAllTags() {
		return tagR.findAll();
	}

	@Override
	public Tag createTag(Tag tagRequest) {
		//return tagR.save(tagRequest);
		Tag temp = tagR.findTagByValue(tagRequest.getValue());
		if(temp != null) {
			return temp;
		}
		else {
			return tagR.save(tagRequest);
		}

	}

	@Override
	public Tag editTag(Long tagId, Tag tagRequest) {
		return tagR.findById(tagId)
			      .map(tag -> {
			    	  if(tagR.findTagByValue(tagRequest.getValue()) == null) {
			    		  tag.setValue(tagRequest.getValue());
			    		  return tagR.save(tag);
			    	  }
			    	  else {
			    		  return tagR.findTagByValue(tagRequest.getValue());
			    	  }
			      })
			      .orElseGet(() -> {
			    	  return createTag(tagRequest);
			    	  //return tagR.save(tagRequest);
			      });
	}

	@Override
	public Tag findOneTag(Long tagId) {
		Optional<Tag> tag = tagR.findById(tagId);
		if(!tag.isPresent()) {
			//throw new UserNotFoundException("id-" + id);
		}
		return tag.get();
	}

	@Override
	public ResponseEntity<Void> deleteTag(Long tagId) {
		tagR.delFromTopicTagByTag(tagId);
		tagR.deleteById(tagId);
	    return ResponseEntity.noContent().build();
	}

	@Override
	public List<TopicUserTagsEntrys> findOneTagTopics(Long tagId) {
		return topicS.allTopicsWD().stream()
				.filter(topic -> topic.getTags().contains(tagR.findById(tagId).get()))
				.collect(Collectors.toList());
		//numberList.stream().filter(i -> i%2 == 0).collect(Collectors.toList());

	}

	@Override
	public Set<Tag> createTags(Set<Tag> tagsRequest) {
		Set<Tag> result = new HashSet<Tag>();
		for(Tag tag: tagsRequest) {
			result.add(createTag(tag));
		}
		return result;
	}

	@Override
	public TopicTag createTopicTag(Long topicId, Long tagId) {
		TopicTag tt = new TopicTag();
		if(tagR.check(topicId, tagId) == 0){
			tagR.ins(topicId, tagId);
			tt.setTag(tagR.findById(tagId).get());
			tt.setTopic(topicR.findById(topicId).get());
			return tt;
		}
		else {
			return null;
		}

	}

	@Override
	public List<String> findTagListOfTopic(Long topicId) {
		return tagR.findTopicsOfTag(topicId);
	}







//	@Override
//	public Set<Topic> findOneTagTopics(Long id) {
//		Set<Topic> ts = new HashSet<Topic>();
//		Optional<Tag> tag = tagR.findById(id);
//		if(tag.isPresent()) {
//			return tag.get().getTopics();
//		}
//		return null;
//	}

}
