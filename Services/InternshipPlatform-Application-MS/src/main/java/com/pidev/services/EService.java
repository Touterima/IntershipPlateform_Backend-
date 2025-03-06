import jakarta.mail.MessagingException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import jakarta.mail.internet.MimeMessage;


@Service
public class EService  {

    private JavaMailSender mailSender;

    private TemplateEngine templateEngine;
    public EService(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }
    public void EnvoyerUnEmail ( String EmailDestination, String ObjetEmail, String templateNameEmail ) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");

            helper.setTo(EmailDestination);
            helper.setSubject(ObjetEmail);
            //Créer le contenu HTML avec le template html(le fichier du template) et le contexte càd les variables qui vont composer le fichier HTML
            String htmlContent = templateEngine.process(templateNameEmail, null);

            //préciser le type de contenu comme HTML
            helper.setText(htmlContent, true);
            //envoyer l'email
            mailSender.send(mimeMessage);
        } catch ( MessagingException e ) {
            e.printStackTrace();
        }

    }





}