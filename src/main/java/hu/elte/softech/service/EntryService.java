package hu.elte.softech.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import hu.elte.softech.entity.Entry;
import hu.elte.softech.entity.EntryRankTopic;
import hu.elte.softech.entity.EntryUserTagsTopic;
import hu.elte.softech.entity.EntryUserTopicTagsFavRank;
import hu.elte.softech.entity.EntryWD;
import hu.elte.softech.entity.Ranking;
import hu.elte.softech.entity.Topic;

public interface EntryService {

	//MAP
	Map<String, Object> mapEntry();

	// GET Entrys
	List<Entry> findAllEntrys();

	// GET Entrys With Details
	List<EntryWD> findAllEntrysWD();

	// GET Entry
	Entry getEntry(Long entryId);

	// GET Entry With Details
	EntryWD getEntryWD(Long entryId);

	// GET Entrys By UserId
	List<Entry> getEntrysByUser(Long userId);

	// GET Entrys By TopicId
	List<Entry> getEntrysByTopic(Long topicId);

	// POST Entry
	Entry createEntry(Long userId, Long topicId, Entry nE);

	// EDIT Entry
	Entry editEntry(Long entryId, Entry entryRequest);

	// DELETE Entry
	ResponseEntity<Void> deleteEntry(Long entryId);

	// List of Tags of Topic
	List<String> findEntryListOfTopic(Long topicId);

	// POST Favorite Fav
	List<Entry> favEntry(Long userId, Long entryId);

	// DELETE Favorite UnFav
	List<Entry> unfavEntry(Long userId, Long entryId);

	// GET FollowTopic By User
	List<Entry> getFavEntrysByUser(Long userId);

	// POST Ranking
	List<Entry> rankEntry(Long userId, Long entryId, Boolean value);

	// GET Ranking
	List<Entry> getRankEntrysByUser(Long userId);

	// DELETE Ranking
	ResponseEntity<Void> deleteRankByUserEntry(Long userId, Long entryId);







	ResponseEntity<Object> newEntry(Entry nE);

	Entry retrieveOneEntry(Long id);

	List<Entry> findOneUserEntrys(String username);

	Page<Entry> entrysByUser(Long userId, Pageable pgb);

	Page<Entry> getOneUserEntrysByUsername(String username, Pageable pgb);

	Page<Entry> entrysByTopic(Long topicId, Pageable pgb);

	Entry updateEntryOfUser(Long userId, Long entryId, Entry entry);

	ResponseEntity<?> deleteEntryOfUser(Long userId, Long entryId);

	Entry updateEntryOfTopic(Long topicId, Long entryId, Entry entry);

	ResponseEntity<?> deleteEntryOfTopic(Long topicId, Long entryId);

	Entry createEntryOfTopic(Long topicId, Entry entry);

	Entry createEntryOfUser(Long userId, Entry entry);

	// ?
	List<EntryRankTopic> findAllEntrysInDetailedForm();

	// dtoservice

//	List<EntryUserTagsTopic> getEntrysFull();

}
