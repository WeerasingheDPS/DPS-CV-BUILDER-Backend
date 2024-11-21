package dpscvbuilder.com.DPSCV_BUILDER.service;

import dpscvbuilder.com.DPSCV_BUILDER.model.SystemUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface OAuth2Service {
    Optional<SystemUser> findByEmail(String email);

    void saveUser(SystemUser userEntity);
}
