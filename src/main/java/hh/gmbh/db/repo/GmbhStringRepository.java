package hh.gmbh.db.repo;

import hh.gmbh.db.GmbhStringEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GmbhStringRepository extends CrudRepository<GmbhStringEntity, String> {

    @Query("SELECT s.value, COUNT(s) FROM GmbhStringEntity s GROUP BY s.value ORDER BY COUNT(s) DESC")
    List<?> countStrings();

    @Query("SELECT AVG(s.length) FROM GmbhStringEntity s")
    Integer getAverageLength();
}