package hu.elte.softech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hu.elte.softech.entity.Trt;
import hu.elte.softech.entity.Tst;
import hu.elte.softech.repository.TrtRepo;
import hu.elte.softech.repository.TstRepo;

@RestController
public class FakeController {
	
	@Autowired
	private TrtRepo trtRepo;

	@Autowired
	private TstRepo tstRepo;
	
	@RequestMapping(method=RequestMethod.GET,path="/trts")
	public List<Trt> allTrts() {
	    return trtRepo.findAll();
	}
	
	@RequestMapping(method=RequestMethod.GET,path="/tsts")
	public List<Tst> allTsts() {
	    return tstRepo.findAll();
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/trt/{id}")
	public Trt findOneTrt(@PathVariable Long id) {
		return trtRepo.findById(id).get();
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/tst/{id}")
	public Tst findOneTst(@PathVariable Long id) {
		return tstRepo.findById(id).get();
	}
	
//	@RequestMapping(method=RequestMethod.POST, path="/trt/new")
//	public ResponseEntity<Object> newTrt(@RequestBody Trt nT) {
//		
//	}
	
	
	
	
	
	
//	@RequestMapping(method=RequestMethod.PUT,path="/user/edit/{id}")
//	public User editUser(@RequestBody User eU, @PathVariable Long id) {
//	    return us.editUser(eU, id);
//	  }

	
	@RequestMapping(method=RequestMethod.DELETE,path="/tst/delete/{id}")
	public ResponseEntity<Void> deleteTst(@PathVariable Long id) {
//		Tst t = tstRepo.findById(id).get();
//		for (Trt var : t.getTrts()) 
//		{ 
//		    t.removeTrt(var);
//		}
	    //tstRepo.deleteById(id);
		//remove(id);
		tstRepo.del(id);
		tstRepo.deleteById(id);
	    return ResponseEntity.noContent().build();
	}
	
	@Transactional
	public void remove(Long tstid) {
	    Tst tst = tstRepo.findById(tstid).get();
	    tst.getTrts().forEach(t -> t.getTsts().remove(tst));
	    trtRepo.saveAll(tst.getTrts());
	    tstRepo.delete(tst);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,path="/trt/delete/{id}")
	public ResponseEntity<Void> deleteTrt(@PathVariable Long id) {
//		Trt t = trtRepo.findById(id).get();
//		for (Tst var : t.getTsts()) 
//		{ 
//		    t.removeTst(var);
//		}
//		
//	    tstRepo.deleteById(id);
	    trtRepo.deleteById(id);
	    return ResponseEntity.noContent().build();//works!!!!!
	}

}
