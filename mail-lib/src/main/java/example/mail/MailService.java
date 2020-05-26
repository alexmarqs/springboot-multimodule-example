package example.mail;

/**
 * The interface Mail service.
 */
public interface MailService {

    /**
     * Send email.
     *
     * @param subject the subject
     * @param text    the text
     */
    void sendEmail(String subject, String text);

}
