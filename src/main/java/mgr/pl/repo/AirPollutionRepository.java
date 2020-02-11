package mgr.pl.repo;

import mgr.pl.model.AirPollution;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AirPollutionRepository extends CrudRepository<AirPollution,Long> {

    List<AirPollution> findAllByMeasureDateGreaterThanOrderByMeasureDateDesc(Date date);

}
