package hu.elte.softech.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import hu.elte.softech.entity.Entry;
import hu.elte.softech.entity.EntryUserTagsTopic;

public interface EntryService {
	
	ResponseEntity<Object> newEntry(Entry nE);
	
	Entry retrieveOneEntry(Long id);
	
	List<Entry> findOneUserEntrys(String username);
	
	Entry editEntry(Entry eE, Long id);
	
	ResponseEntity<Void> deleteEntry(Long id);
	
	//dtoservice
	
//	List<EntryUserTagsTopic> getEntrysFull();

}
