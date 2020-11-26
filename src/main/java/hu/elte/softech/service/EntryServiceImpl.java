package hu.elte.softech.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import hu.elte.softech.entity.Entry;
import hu.elte.softech.entity.EntryRankTopic;
import hu.elte.softech.entity.User;
import hu.elte.softech.repository.EntryRepository;
import hu.elte.softech.repository.TopicRepository;
import hu.elte.softech.repository.UserRepository;
import hu.elte.softech.repository.RankingRepository;

@Service
public class EntryServiceImpl implements EntryService {
	
	@Autowired
	private EntryRepository er;
	
	@Autowired
	private UserRepository ur;
	
	@Autowired
	private TopicRepository tpr;
	
	@Autowired
	private RankingRepository rr;
	
	//DELETE
	@Override
	public ResponseEntity<Void> deleteEntry(Long entryId) {
		rr.delFromRankingByEntry(entryId);
		er.delFromFavoriteByEntry(entryId);
		er.deleteById(entryId);
	    return ResponseEntity.noContent().build();
	}
	
	
	
	
	
	
	

	@Override
	public ResponseEntity<Object> newEntry(Entry nE) {
		Entry savedEntry = er.save(nE);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			.buildAndExpand(savedEntry.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@Override
	public List<Entry> findOneUserEntrys(String username) {
		User user = ur.findUserByUsername(username);
		//System.out.println(user.getEmail());
		if(user == null) {
			//throw new UserNotFoundException("id-" + id);
		}
		return er.findAllForUser(user);
	}

	@Override
	public Entry retrieveOneEntry(Long id) {
		return er.findById(id).get();
	}

	@Override
	public Entry editEntry(Entry eE, Long id) {
		return er.findById(id).map(entry -> {
	    	entry.setValue(eE.getValue());
	    	return er.save(entry);
	      })
	      .orElseGet(() -> {
	    	  eE.setId(id);
	    	  return er.save(eE);
	      });
	}

	@Override
	public Entry createEntry(Long userId, Long topicId, Entry nE) {
		nE.setTopic(tpr.findById(topicId).get());
		nE.setUser(ur.findById(userId).get());
		return er.save(nE);
	}

	@Override
	public Page<Entry> entrysByUser(Long userId, Pageable pgb) {
		return er.findByUserId(userId, pgb);
	}

	@Override
	public Page<Entry> entrysByTopic(Long topicId, Pageable pgb) {
		return er.findByTopicId(topicId, pgb);
	}

	@Override
	public List<Entry> findAllEntrys() {
		return er.findAll();
	}

	@Override
	public Entry createEntryOfUser(Long userId, Entry entry) {
		entry.setUser(ur.findById(userId).get());
		return er.save(entry);
	}

	@Override
	public Entry updateEntryOfUser(Long userId, Long entryId, Entry entry) {
		if(!ur.existsById(userId)) {
            
        }
		Entry ue = er.findById(entryId).get();
		ue.setValue(entry.getValue());
		return er.save(ue);
	}

	@Override
	public ResponseEntity<?> deleteEntryOfUser(Long userId, Long entryId) {
		Entry de = er.findByIdAndUserId(entryId, userId).get();
		er.delete(de);
		return ResponseEntity.ok().build();
	}
	
	@Override
	public Entry createEntryOfTopic(Long topicId, Entry entry) {
		entry.setTopic(tpr.findById(topicId).get());
		return er.save(entry);
	}

	@Override
	public Entry updateEntryOfTopic(Long topicId, Long entryId, Entry entry) {
		if(!tpr.existsById(topicId)) {
            
        }
		Entry ue = er.findById(entryId).get();
		ue.setValue(entry.getValue());
		return er.save(ue);
	}

	@Override
	public ResponseEntity<?> deleteEntryOfTopic(Long topicId, Long entryId) {
		Entry de = er.findByIdAndTopicId(entryId, topicId).get();
		er.delete(de);
		return ResponseEntity.ok().build();
	}

	@Override
	public List<EntryRankTopic> findAllEntrysInDetailedForm() {
		List<Entry> listE = er.findAll();
		List<EntryRankTopic> listERT = new ArrayList();
		for(Entry e: listE) {
			EntryRankTopic ert = new EntryRankTopic();
			ert.setEntry(e);
			ert.setTopicName(e.getTopic().getValue());
			ert.setRankUp(rr.findByEntryId(e.getId(), true));
			ert.setRankDown(rr.findByEntryId(e.getId(), false));
			listERT.add(ert);
		}
//		listERT.sort(rank<?>);
		
		return listERT;
	}

//	@Override
//	public List<EntryUserTagsTopic> getEntrysFull() {
//		return ((List<Entry>) er
//                .findAll())
//                .stream()
//                .map(this::convertToUserLocationDTO)
//				        .collect(Collectors.toList());
//	}
//	
//	 private EntryUserTagsTopic convertToEUTT(Entry e) {
//		 	EntryUserTagsTopic eutt = new EntryUserTagsTopic();
//		 	
//	        userLocationDTO.setUsername(user.getUsername());
//	        Location location = user.getLocation();
//	        userLocationDTO.setLat(location.getLat());
//	        userLocationDTO.setLng(location.getLng());
//	        userLocationDTO.setPlace(location.getPlace());
//	        return userLocationDTO;

}
