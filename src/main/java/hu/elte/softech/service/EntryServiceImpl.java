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
import hu.elte.softech.entity.EntryUserTopicTagsFavRank;
import hu.elte.softech.entity.EntryWD;
import hu.elte.softech.entity.Ranking;
import hu.elte.softech.entity.Topic;
import hu.elte.softech.entity.TopicUserTagsEntrys;
import hu.elte.softech.entity.User;
import hu.elte.softech.repository.EntryRepository;
import hu.elte.softech.repository.TopicRepository;
import hu.elte.softech.repository.UserRepository;
import hu.elte.softech.repository.RankingRepository;

@Service
public class EntryServiceImpl implements EntryService {

	@Autowired
	private UserService userS;

	@Autowired
	private TopicService topicS;

	@Autowired
	private EntryRepository entryR;

	@Autowired
	private UserRepository userR;

	@Autowired
	private TopicRepository topicR;

	@Autowired
	private RankingRepository rankR;

	@Override
	public List<Entry> findAllEntrys() {
		return entryR.findAll();
	}

	@Override
	public List<EntryWD> findAllEntrysWD() {
		List<Entry> listE = findAllEntrys();
		List<EntryWD> listEWD = new ArrayList<EntryWD>();
		for(Entry e: listE) {
			listEWD.add(getEntryWD(e.getId()));
		}
		return listEWD;
	}

	@Override
	public Entry getEntry(Long entryId) {
		return entryR.findById(entryId).get();
	}

	@Override
	public EntryWD getEntryWD(Long entryId) {
		EntryWD entryWD = new EntryWD();
		Entry entry = getEntry(entryId);

		entryWD.setEntry(entry);
		Topic topic = topicS.getTopic(Long.parseLong(entryR.findTopicByEntryId(entryId)));
		entryWD.setTopic(topic);
		entryWD.setTags(topicS.getTagListforTopicWD(topic.getId()));
		entryWD.setUser(entry.getUser());
		entryWD.setRankDown(rankR.findRankByEntryId(entryId, false));
		entryWD.setRankUp(rankR.findRankByEntryId(entryId, true));
		entryWD.setFav(entryR.findNumberOfFavByEntryId(entryId));

		return entryWD;
	}

	@Override
	public List<Entry> getEntrysByUser(Long userId) {
		return entryR.findEntryListByUser(userS.findOneUserByUserId(userId));
	}

	@Override
	public List<Entry> getEntrysByTopic(Long topicId) {
		return entryR.findEntryListByTopic(topicS.getTopic(topicId));
	}

	@Override
	public Entry createEntry(Long userId, Long topicId, Entry nE) {
		if(entryR.findEntryByValue(nE.getValue()) == null)
		{
			nE.setTopic(topicR.findById(topicId).get());
			nE.setUser(userR.findById(userId).get());
			return entryR.save(nE);
		}
		else
			return entryR.findEntryByValue(nE.getValue());

	}

	@Override
	public ResponseEntity<Void> deleteRankByUserEntry(Long userId, Long entryId) {
		rankR.delFromRankingByUserEntry(userId, entryId);
		return ResponseEntity.noContent().build();
	}

	@Override
	public Entry editEntry(Long entryId, Entry entryRequest) {
		return entryR.findById(entryId).map(entry -> {
	    	entry.setValue(entryRequest.getValue());
	    	return entryR.save(entry);
	      })
	      .orElseGet(() -> {
	    	  entryRequest.setId(entryId);
	    	  return entryR.save(entryRequest);
	      });
	}

	@Override
	public List<String> findEntryListOfTopic(Long topicId) {
		return entryR.findEntrysOfTopic(topicId);
	}







	@Override
	public ResponseEntity<Void> deleteEntry(Long entryId) {
		rankR.delFromRankingByEntry(entryId);
		entryR.delFromFavoriteByEntry(entryId);
		entryR.deleteById(entryId);
	    return ResponseEntity.noContent().build();
	}

	@Override
	public Page<Entry> getOneUserEntrysByUsername(String username, Pageable pgb) {
		return entryR.findByUserId(userS.findOneUserByUsername(username).getId(), pgb);
	}

	@Override
	public Page<Entry> entrysByUser(Long userId, Pageable pgb) {
		return entryR.findByUserId(userId, pgb);
	}








	@Override
	public ResponseEntity<Object> newEntry(Entry nE) {
		Entry savedEntry = entryR.save(nE);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			.buildAndExpand(savedEntry.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@Override
	public List<Entry> findOneUserEntrys(String username) {
		User user = userR.findUserByUsername(username);
		//System.out.println(user.getEmail());
		if(user == null) {
			//throw new UserNotFoundException("id-" + id);
		}
		return entryR.findAllForUser(user);
	}

	@Override
	public Entry retrieveOneEntry(Long id) {
		return entryR.findById(id).get();
	}





	@Override
	public Page<Entry> entrysByTopic(Long topicId, Pageable pgb) {
		return entryR.findByTopicId(topicId, pgb);
	}



	@Override
	public Entry createEntryOfUser(Long userId, Entry entry) {
		entry.setUser(userR.findById(userId).get());
		return entryR.save(entry);
	}

	@Override
	public Entry updateEntryOfUser(Long userId, Long entryId, Entry entry) {
		if(!userR.existsById(userId)) {

        }
		Entry ue = entryR.findById(entryId).get();
		ue.setValue(entry.getValue());
		return entryR.save(ue);
	}

	@Override
	public ResponseEntity<?> deleteEntryOfUser(Long userId, Long entryId) {
		Entry de = entryR.findByIdAndUserId(entryId, userId).get();
		entryR.delete(de);
		return ResponseEntity.ok().build();
	}

	@Override
	public Entry createEntryOfTopic(Long topicId, Entry entry) {
		entry.setTopic(topicR.findById(topicId).get());
		return entryR.save(entry);
	}

	@Override
	public Entry updateEntryOfTopic(Long topicId, Long entryId, Entry entry) {
		if(!topicR.existsById(topicId)) {

        }
		Entry ue = entryR.findById(entryId).get();
		ue.setValue(entry.getValue());
		return entryR.save(ue);
	}

	@Override
	public ResponseEntity<?> deleteEntryOfTopic(Long topicId, Long entryId) {
		Entry de = entryR.findByIdAndTopicId(entryId, topicId).get();
		entryR.delete(de);
		return ResponseEntity.ok().build();
	}

	@Override
	public List<EntryRankTopic> findAllEntrysInDetailedForm() {
		List<Entry> listE = entryR.findAll();
		List<EntryRankTopic> listERT = new ArrayList<EntryRankTopic>();
		for(Entry e: listE) {
			EntryRankTopic ert = new EntryRankTopic();
			ert.setEntry(e);
			ert.setTopicName(e.getTopic().getValue());
//			ert.setRankUp(rankR.findByEntryId(e.getId(), true));
//			ert.setRankDown(rankR.findByEntryId(e.getId(), false));
			ert.setUser(e.getUser());
			listERT.add(ert);
		}
//		listERT.sort(rank<?>);

		return listERT;
	}



	@Override
	public List<Entry> favEntry(Long userId, Long entryId) {
		if(entryR.checkExistenseFavEntry(userId, entryId) == 0)
			entryR.insIntoFavoriteByUserEntry(userId, entryId);
		return getFavEntrysByUser(userId);
	}

	@Override
	public List<Entry> unfavEntry(Long userId, Long topicId) {
		if(entryR.checkExistenseFavEntry(userId, topicId) != 0)
			entryR.delFromFavoriteByUserEntry(userId, topicId);
		return getFavEntrysByUser(userId);
	}

	@Override
	public List<Entry> getFavEntrysByUser(Long userId) {
		List<Entry> temp = new ArrayList<Entry>();
		for(String s: entryR.findFavEntryListByUser(userId)) {
			temp.add(entryR.findById(Long.parseLong(s)).get());
		}
		return temp;
	}


	@Override
	public List<Entry> rankEntry(Long userId, Long entryId, Boolean value) {
		if(rankR.checkExistenseRank(userId, entryId) == 0)
			rankR.ins(value, entryId, userId);
		else
			rankR.upd(value, entryId, userId);
		return getRankEntrysByUser(userId);
	}

	@Override
	public List<Entry> getRankEntrysByUser(Long userId) {
		List<Entry> temp = new ArrayList<Entry>();
		for(String s: rankR.findRankEntryListByUser(userId)) {
			temp.add(entryR.findById(Long.parseLong(s)).get());
		}
		return temp;
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



//	@Override
//	public Entry createRanking(Ranking rankingRequest) {
//		Long rankingId = (long) (rankR.findAll().size() + 1);
//		String value = ((rankingRequest.getValue()) ? "true" : "false");
//		rankR.ins(value, rankingRequest.getEntry().getId(),
//				rankingRequest.getUser().getId());
//		return rankingRequest.getEntry();
//	}

}
