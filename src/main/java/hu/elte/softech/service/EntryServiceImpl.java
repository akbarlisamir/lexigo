package hu.elte.softech.service;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import hu.elte.softech.entity.Entry;
import hu.elte.softech.entity.EntryUserTagsTopic;
import hu.elte.softech.entity.User;
import hu.elte.softech.repository.EntryRepository;
import hu.elte.softech.repository.UserRepository;

@Service
public class EntryServiceImpl implements EntryService {
	
	@Autowired
	private EntryRepository er;
	
	@Autowired
	private UserRepository ur;

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
	public ResponseEntity<Void> deleteEntry(Long id) {
		er.deleteById(id);
	    
	    return ResponseEntity.noContent().build();
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