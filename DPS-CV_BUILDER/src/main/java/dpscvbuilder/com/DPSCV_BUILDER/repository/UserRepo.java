package dpscvbuilder.com.DPSCV_BUILDER.repository;

import dpscvbuilder.com.DPSCV_BUILDER.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);

    boolean existsByEmailAndUserType(String email, String userType);

    boolean existsById(String id);
}
