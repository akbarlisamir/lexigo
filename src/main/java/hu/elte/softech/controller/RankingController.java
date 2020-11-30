package hu.elte.softech.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import hu.elte.softech.entity.*;
import hu.elte.softech.repository.*;
import hu.elte.softech.service.EntryService;
import hu.elte.softech.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
public class RankingController {

	@Autowired
	private EntryService entryS;

//	//DELETE Rank
//	@RequestMapping(method=RequestMethod.DELETE,path="/rank/{userId}/{entryId}/delete")
//	public ResponseEntity<?> deleteRank(@PathVariable Long userId, @PathVariable Long entryId) {
//	    return entryS.deleteRankByUserEntry(userId, entryId);
//	}
//
//	//POST Rank
//	@RequestMapping(method=RequestMethod.POST,path="/rank")
//	public Entry postRanking(@RequestBody Ranking rankingRequest) {
//	    return entryS.createRanking(rankingRequest);
//	}

//	@Autowired
//	private RankingRepository rr;
//	private RankingService rs;

//	@RequestMapping(method=RequestMethod.POST, path="/ranking/one")
//	public Topic newTopic(@RequestBody Topic nTp) {
//		return ts.createTopic(nTp);
//	}

//	@RequestMapping(method=RequestMethod.GET, path="/entrys")
//	public List<Entry> retrieveAllEntrys() {
//		return er.findAll();
//	}

}
