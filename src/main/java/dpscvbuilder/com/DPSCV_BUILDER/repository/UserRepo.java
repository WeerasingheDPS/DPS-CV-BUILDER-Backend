package dpscvbuilder.com.DPSCV_BUILDER.repository;

import dpscvbuilder.com.DPSCV_BUILDER.model.SystemUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//@EnableMongoRepositories
public interface UserRepo extends MongoRepository<SystemUser, String> {
    Optional<SystemUser> findByEmail(String email);

    boolean existsByEmailAndUserType(String email, String userType);

    boolean existsById(String id);

    Optional<SystemUser> findById(String id);
}
