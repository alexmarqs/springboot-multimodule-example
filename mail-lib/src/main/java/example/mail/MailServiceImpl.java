package example.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Mail service.
 */
@Service
public class MailServiceImpl implements MailService {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    /**
     * The Mail properties.
     */
    @Autowired
    private MailProperties mailProperties;

    @Override
    public void sendEmail(String subject, String text) {
        logger.info("Start sending the email with subject {} and text {}",
                subject == null ? mailProperties.getDefaultSubject() : subject, text);
        // business logic here
    }
}
