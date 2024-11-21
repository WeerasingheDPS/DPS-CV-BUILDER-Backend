package dpscvbuilder.com.DPSCV_BUILDER.service.impl;

import dpscvbuilder.com.DPSCV_BUILDER.model.SystemUser;
import dpscvbuilder.com.DPSCV_BUILDER.repository.UserRepo;
import dpscvbuilder.com.DPSCV_BUILDER.service.OAuth2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OAuth2ServiceImpl implements OAuth2Service {

    private final UserRepo userRepo;
    public Optional<SystemUser> findByEmail(String email) {

        return userRepo.findByEmail(email);
    }

    public void saveUser(SystemUser userEntity) {
        userRepo.save(userEntity);
    }
}
