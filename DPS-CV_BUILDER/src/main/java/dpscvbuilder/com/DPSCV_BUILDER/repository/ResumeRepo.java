package dpscvbuilder.com.DPSCV_BUILDER.repository;

import dpscvbuilder.com.DPSCV_BUILDER.model.Resume;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

@Repository

public interface ResumeRepo extends MongoRepository<Resume, String> {

    boolean existsByUserId(String id);

    Resume findByUserId(String id);
}
