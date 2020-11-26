package hu.elte.softech.repository;

import hu.elte.softech.entity.User;
import hu.elte.softech.entity.Topic;
import hu.elte.softech.entity.Trt;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TrtRepo extends JpaRepository<Trt,Long> {

}
