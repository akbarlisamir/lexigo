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

	@Query(value = "INSERT INTO Ranking(value, entry_id, user_id) VALUES(:value, :entryId, :userId)",nativeQuery = true)
	void ins(@Param("value") Boolean value, @Param("entryId") Long entryId, @Param("userId") Long userId);

	@Query(value = "UPDATE Ranking r SET r.value = :value WHERE r.user_id = :userId AND r.entry_id = :entryId",nativeQuery = true)
	void upd(@Param("value") Boolean value, @Param("entryId") Long entryId, @Param("userId") Long userId);

	@Query(value = "SELECT COUNT(*) FROM Ranking r WHERE r.user_id = :userId AND r.entry_id = :entryId", nativeQuery = true)
	public int checkExistenseRank(@Param("userId") Long userId, @Param("entryId") Long entryId);

	@Query(value = "SELECT COUNT(*) FROM Ranking r WHERE r.entry_id = :entryId AND r.value = :value", nativeQuery=true)
	int findRankByEntryId(@Param("entryId") Long entryId, @Param("value") Boolean value);

	@Query(value = "SELECT t.entry_id FROM Ranking t WHERE t.user_id = ?1", nativeQuery = true)
	public List<String> findRankEntryListByUser(Long userId);
}
