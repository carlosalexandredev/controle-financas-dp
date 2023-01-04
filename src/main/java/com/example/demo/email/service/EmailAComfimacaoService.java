package com.example.demo.email.service;

import com.example.demo.email.repository.EmailRepository;
import com.example.demo.email.entity.Email;
import com.example.demo.email.enuns.StatusEmail;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class EmailAComfimacaoService {

    @Autowired
    private EmailRepository emailRepository;
    @Autowired
    private JavaMailSender emailSenders;
    @Autowired
    private Configuration config;

    @Async
    public Email enviaEmail(Email sendEmail) {
        sendEmail.setSendDateEmail(LocalDateTime.now());
        MimeMessage message = emailSenders.createMimeMessage();
        try{
            /** Adicionando MediaType*/
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            /** Adicionando anexos*/
//            helper.addAttachment("logo.png", new ClassPathResource("static/logo.png"));
//            helper.addAttachment("anexo.pdf", new ClassPathResource("static/anexo.pdf"));

            Template t = config.getTemplate("confirmacao-email.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, sendEmail);

            helper.setFrom(sendEmail.getEmailFrom());
            helper.setTo(sendEmail.getUsuario().getEmail());
            helper.setSubject(sendEmail.getSubject());
            helper.setText(html, true);
            emailSenders.send(message);

            sendEmail.setStatusEmail(StatusEmail.SENT);
        } catch (MailException e){
            sendEmail.setStatusEmail(StatusEmail.ERROR);
        } finally {
            return emailRepository.save(sendEmail);
        }
    }

}
