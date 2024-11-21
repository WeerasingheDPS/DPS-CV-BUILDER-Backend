package dpscvbuilder.com.DPSCV_BUILDER.service.impl;

import dpscvbuilder.com.DPSCV_BUILDER.exception.DpsCvBuilderException;
import dpscvbuilder.com.DPSCV_BUILDER.model.ConfirmationToken;
import dpscvbuilder.com.DPSCV_BUILDER.service.EmailService;
import dpscvbuilder.com.DPSCV_BUILDER.util.enums.ErrorEnum;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Value("${email.base.url}")
    private  String emailBaseUrl;

    @Override
    public void sendVerificationEmail(ConfirmationToken confirmationToken) {
        String templatePath = "src/main/resources/templates/confirm-email-template.html";
        String htmlContent = getHtmlContent(templatePath);
        htmlContent = htmlContent.replace("{verify_token}", confirmationToken.getToken()).replace("{email_base_url}", emailBaseUrl);
        String subject = "Verify Your Email";
        sendEmailWithHtmlTemplate(confirmationToken.getUserEmail(), htmlContent, subject);
   }

    private void sendEmailWithHtmlTemplate(String email, String htmlContent, String subject) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (Exception ex) {
            log.info("Error sending email for {} -> {}", email, ex.getMessage());
        }

    }

    private String getHtmlContent(String path) {
        try {
           String html = new String(Files.readAllBytes(Paths.get(path)));
           return html;
        }catch (Exception ex){
            log.info("Error getting email verify template :  {}", ex.getMessage());
            return "Sorry! Getting some error!, Please try again!";
        }
    }
}
