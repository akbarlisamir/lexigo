package hu.elte.softech.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hu.elte.softech.entity.*;
import hu.elte.softech.repository.EntryRepository;
import hu.elte.softech.repository.TagRepository;
import hu.elte.softech.repository.TopicRepository;

@Service
public class TopicServiceImpl implements TopicService {

	@Autowired
	private TagService tagS;

	@Autowired
	private EntryService entryS;

	@Autowired
	private UserService userS;

	@Autowired
	private TopicRepository topicR;

	@Autowired
	private TagRepository tagR;

	@Autowired
	private EntryRepository entryR;

	@Override
	public List<Topic> allTopics() {
		return topicR.findAll();
	}

	@Override
	public List<TopicUserTagsEntrys> allTopicsWD() {
		List<Topic> listT = topicR.findAll();
		List<TopicUserTagsEntrys> listTUTE = new ArrayList<TopicUserTagsEntrys>();
		for(Topic t: listT) {
			listTUTE.add(getTopicWD(t.getId()));
		}
		return listTUTE;
	}

	@Override
	public Topic getTopic(Long topicId) {
		return topicR.findById(topicId).get();
	}

	@Override
	public TopicUserTagsEntrys getTopicWD(Long topicId) {
		TopicUserTagsEntrys tute = new TopicUserTagsEntrys();
		Topic topic = getTopic(topicId);

		tute.setTopic(topic);
		tute.setUser(topic.getUser());

		tute.setEntrys(getEntryListforTopicWD(topicId));
		tute.setTags(getTagListforTopicWD(topicId));

		return tute;
	}

	@Override
	public List<Topic> getOneUserTopicsByUsername(String username) {
		return getTopicsByUser(userS.findOneUserByUsername(username).getId());
	}

	@Override
	public List<Topic> getTopicsByUser(Long userId) {
		return topicR.findTopicListByUser(userS.findOneUserByUserId(userId));
	}

	@Override
	public TopicWD createTopicOfUser(Long userId, TopicWD topicRequest) {
		Topic topic = new Topic();
		topic.setUser(userS.findOneUserByUserId(userId));
		topic.setValue(topicRequest.getTopic().getValue());
		Topic resultOfCreateTopic = createTopicOnly(topic);

		TopicWD result = new TopicWD();
		result.setTags(createConnectionTopicTag(topicRequest.getTags(), resultOfCreateTopic.getId()));
		result.setTopic(resultOfCreateTopic);
		result.setEntrys(null);
		return result;
	}

	@Override
	public TopicWD editTopicOfUser(Long topicId, TopicWD topicRequest) {
		Topic temp = topicR.findById(topicId).get();
		temp.setValue(topicRequest.getTopic().getValue());
		Topic resOfSave = topicR.save(temp);
		TopicWD ans = new TopicWD();
		ans.setTopic(resOfSave);
		if(topicRequest.getTags() != null) {
			ans.setTags(createConnectionTopicTag(topicRequest.getTags(), resOfSave.getId()));
		}
		ans.setEntrys(getEntryListforTopicWD(resOfSave.getId()));
		return ans;
	}

	@Override
	public ResponseEntity<Void> deleteTopic(Long topicId) {
		Topic topic = topicR.findById(topicId).get();
		tagR.delFromTopicTagByTopic(topicId);
		topicR.delFromFollowTopicByTopic(topicId);
		List<Entry> listEntry = entryR.findAllForTopic(topic);
		for(Entry e: listEntry) {
			entryS.deleteEntry(e.getId());
		}
		topicR.deleteById(topicId);
	    return ResponseEntity.noContent().build();
	}

	@Override
	public List<Topic> followTopic(Long userId, Long topicId) {
		if(topicR.checkExistenseFollowTopic(userId, topicId) == 0)
			topicR.insIntoFollowTopicByUserTopic(userId, topicId);
		return getFollowTopicsByUser(userId);
	}

	@Override
	public List<Topic> unfollowTopic(Long userId, Long topicId) {
		if(topicR.checkExistenseFollowTopic(userId, topicId) != 0)
			topicR.delFromFollowTopicByUserTopic(userId, topicId);
		return getFollowTopicsByUser(userId);
	}

	@Override
	public List<Topic> getFollowTopicsByUser(Long userId) {
		List<Topic> temp = new ArrayList<Topic>();
		for(String s: topicR.findFollowTopicListByUser(userId)) {
			temp.add(topicR.findById(Long.parseLong(s)).get());
		}
		return temp;
	}

	@Override
	public List<Tag> getTagListforTopicWD (Long topicId){
		List<Tag> ans = new ArrayList<Tag>();
		for(String tagString: tagS.findTagListOfTopic(topicId)) {
			ans.add(tagS.findOneTag(Long.parseLong(tagString)));
		}
		return ans;
	}


	//region utils

	private Topic createTopicOnly(Topic topicRequest) {
		Topic temp = topicR.findTopicByTopicName(topicRequest.getValue());
		if(temp != null) {
			return temp;
		}
		else {
			temp = new Topic();
			temp.setUser(topicRequest.getUser());
			temp.setValue(topicRequest.getValue());
			return topicR.save(temp);
		}
	}

	private List<Tag> createConnectionTopicTag(List<Tag> tagList, Long topicId) {
		List<Tag> ans = new ArrayList<Tag> ();
		for(Tag t: tagList) {
			Tag crt = tagS.createTag(t);
			tagS.createTopicTag(topicId, crt.getId());
			ans.add(crt);
		}
		return ans;
	}

	private List<Entry> getEntryListforTopicWD (Long topicId){
		List<Entry> ans = new ArrayList<Entry>();
		for(String entryString: entryS.findEntryListOfTopic(topicId)) {
			ans.add(entryS.retrieveOneEntry(Long.parseLong(entryString)));//.fin.findOneTag(Long.parseLong(tagString)));
		}
		return ans;
	}



	//endregion utils
}


//@Override
//public ResponseEntity<Object> createTopic(TopicDTO tdto) {
////	Topic topic = new Topic();
////	topic.setUser(ur.findById(tdto.getUser_id()).get());
////	topic.setValue(tdto.getTopic_value());
//
//	Long id = (long) tr.findAll().size() + 1;
//	tr.ins(id, tdto.getTopic_value(), tdto.getUser_id());
//
//	for(String val: tdto.getTags()) {
//		if(tgr.findTagByValue(val) != null) {
//			tgr.ins(id, tgr.findTagByValue(val).getId());
//		}
//		else {
//
//		}
//	}
//
//	List<String> lt = tdto.getTags();
//	Set<Tag> tagset = new HashSet<Tag>();
//	for(String e : lt) {
//		Tag t = tgr.findTagByValue(e);
//		if(t != null) {
//			tagset.add(t);
//		}
//		else {
//			Tag tt = tgr.save(t);
//			tagset.add(tt);
//		}
//	}
//
//	topic.setTags(tagset);
//
//	Topic nT = tr.save(topic);
//
//	URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//			.buildAndExpand(nT.getId()).toUri();
//		return ResponseEntity.created(location).build();
//}

