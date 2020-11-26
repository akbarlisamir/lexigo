package hu.elte.softech.repository;

import hu.elte.softech.entity.Ranking;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RankingRepository extends JpaRepository<Ranking,Long> {
	
	@Query(value = "SELECT COUNT(*) FROM Ranking r WHERE r.entry_id = :entryId AND r.value = :value", nativeQuery=true)
	int findByEntryId(@Param("entryId") Long entryId, @Param("value") Boolean value);
	
	@Transactional
	@Modifying      // to mark delete or update query
    @Query(value = "DELETE FROM Ranking r WHERE r.user_id = :userid",nativeQuery = true)
	void delFromRankingByUser(@Param("userid") Long userId);
	
	@Transactional
	@Modifying      // to mark delete or update query
    @Query(value = "DELETE FROM Ranking r WHERE r.entry_id = :entryid",nativeQuery = true)
	void delFromRankingByEntry(@Param("entryid") Long entryId);
	
	@Transactional
	@Modifying      // to mark delete or update query
    @Query(value = "DELETE FROM Ranking r WHERE r.user_id = :userid AND r.entry_id = :entryid",nativeQuery = true)
	void delFromRankingByUserEntry(@Param("userid") Long userId, @Param("entryid") Long entryId);
	
	
	
//	@Query(value = "INSERT INTO Topic VALUES( :id, :val, :uid)",nativeQuery = true)
//	void ins(@Param("id") Long id,@Param("val") String val,@Param("uid") Long uid);
}
