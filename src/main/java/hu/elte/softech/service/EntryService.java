package hu.elte.softech.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import hu.elte.softech.entity.Entry;

public interface EntryService {
	
	ResponseEntity<Object> newEntry(Entry E);
	
	List<Entry> findOneUserEntrys(String username);

}
