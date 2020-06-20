package hh.gmbh.db.repo;

import hh.gmbh.db.entities.CountValueProjection;
import hh.gmbh.db.entities.GmbhStringEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GmbhStringRepository extends CrudRepository<GmbhStringEntity, Long> {

    @Query("SELECT new hh.gmbh.db.entities.CountValueProjection(COUNT(s), s.value) " +
            "FROM GmbhStringEntity s GROUP BY s.value ORDER BY COUNT(s) DESC")
    List<CountValueProjection> countStrings();

    @Query("SELECT AVG(s.length) FROM GmbhStringEntity s")
    Integer getAverageLength();
}