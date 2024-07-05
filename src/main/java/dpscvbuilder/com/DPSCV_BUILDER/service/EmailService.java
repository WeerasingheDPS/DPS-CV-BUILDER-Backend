package dpscvbuilder.com.DPSCV_BUILDER.service;

import dpscvbuilder.com.DPSCV_BUILDER.model.ConfirmationToken;
import jakarta.mail.MessagingException;

public interface EmailService {
    void sendVerificationEmail(ConfirmationToken token) throws MessagingException;
}
