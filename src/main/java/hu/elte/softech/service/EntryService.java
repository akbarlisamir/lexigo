package hu.elte.softech.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import hu.elte.softech.entity.Entry;
import hu.elte.softech.entity.EntryUserTagsTopic;

public interface EntryService {
	
	List<Entry> findAllEntrys();
	
	ResponseEntity<Object> newEntry(Entry nE);
	
	Entry retrieveOneEntry(Long id);
	
	List<Entry> findOneUserEntrys(String username);
	
	Entry editEntry(Entry eE, Long id);
	
	ResponseEntity<Void> deleteEntry(Long id);

	Entry createEntry(Long userId, Long topicId, Entry nE);
	
	
	
	Page<Entry> entrysByUser(Long userId, Pageable pgb);
	
	Page<Entry> entrysByTopic(Long topicId, Pageable pgb);
	
	Entry updateEntryOfUser(Long userId, Long entryId, Entry entry);
	
	ResponseEntity<?> deleteEntryOfUser(Long userId, Long entryId);
	
	Entry updateEntryOfTopic(Long topicId, Long entryId, Entry entry);
	
	ResponseEntity<?> deleteEntryOfTopic(Long topicId, Long entryId);
	
	
	
	

	
	Entry createEntryOfTopic(Long topicId, Entry entry);
	
	Entry createEntryOfUser(Long userId, Entry entry);
	
	//dtoservice
	
//	List<EntryUserTagsTopic> getEntrysFull();

}
