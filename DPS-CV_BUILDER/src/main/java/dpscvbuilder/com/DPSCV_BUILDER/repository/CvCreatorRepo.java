package dpscvbuilder.com.DPSCV_BUILDER.repository;

import dpscvbuilder.com.DPSCV_BUILDER.model.CvCreator;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CvCreatorRepo extends MongoRepository<CvCreator, String> {
}
