package hu.elte.softech.repository;

import hu.elte.softech.entity.User;
import hu.elte.softech.entity.Topic;
import hu.elte.softech.entity.Tst;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TstRepo extends JpaRepository<Tst,Long> {

	@Transactional
	@Modifying      // to mark delete or update query
    @Query(value = "DELETE FROM TRT_TST tt WHERE tt.tst_id = :id",nativeQuery = true)
	void del(@Param("id") Long name);
	
}
