package com.example.demo.email.service;

//@Service
//@AllArgsConstructor
public class EmailAComfimacaoService {
//
//    @Autowired
//    private EmailRepository emailRepository;
//    @Autowired
//    private JavaMailSender emailSenders;
//    @Autowired
//    private Configuration config;
//
//    @Async
//    public Email enviaEmail(Email sendEmail) {
//        sendEmail.setSendDateEmail(LocalDateTime.now());
//        MimeMessage message = emailSenders.createMimeMessage();
//        try{
//            /** Adicionando MediaType*/
//            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
//                    StandardCharsets.UTF_8.name());
//            /** Adicionando anexos*/
//            helper.addAttachment("logo.png", new ClassPathResource("static/logo.png"));
//            helper.addAttachment("anexo.pdf", new ClassPathResource("static/anexo.pdf"));
//
//            Template t = config.getTemplate("confirmacao-email.ftl");
//            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, sendEmail);
//
//            helper.setFrom(sendEmail.getEmailFrom());
//            helper.setTo(sendEmail.getUsuario().getEmail());
//            helper.setSubject(sendEmail.getSubject());
//            helper.setText(html, true);
//            emailSenders.send(message);
//
//            sendEmail.setStatusEmail(StatusEmail.SENT);
//        } catch (MailException e){
//            sendEmail.setStatusEmail(StatusEmail.ERROR);
//        } finally {
//            return emailRepository.save(sendEmail);
//        }
//    }
//
}
