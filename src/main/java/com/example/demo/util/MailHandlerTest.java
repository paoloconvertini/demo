//package com.example.demo.util;
//
//import it.quix.framework.core.configuration.ConfigurationMail;
//import it.quix.framework.core.exception.DAOFinderException;
//import it.quix.framework.core.exception.MailException;
//import it.quix.framework.core.handler.*;
//import it.quix.framework.core.handler.MailHandlerImpl.InputStreamSourceContentType;
//import it.quix.framework.core.manager.FrameworkCoreManager;
//import it.quix.framework.core.model.Mail;
//import it.quix.framework.core.model.enumeration.SendResultEnum;
//import it.quix.framework.core.utils.MimeMessageParser;
//import it.quix.framework.core.utils.MimeObjectEntry;
//import it.quix.framework.core.utils.StringReplacer;
//import it.quix.framework.core.utils.StringReplacerCallback;
//import it.quix.framework.util.exceptions.ExceptionUtil;
//import it.quix.framework.util.exceptions.SystemException;
//
//import java.awt.*;
//import java.io.*;
//import java.math.BigInteger;
//import java.net.URI;
//import java.util.*;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import javax.annotation.Resource;
//import javax.mail.*;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
//import javax.mail.internet.MimeUtility;
//import javax.swing.text.html.HTML;
//
//import com.google.common.base.Joiner;
//import com.google.common.base.Strings;
//import com.google.common.html.HtmlEscapers;
//import freemarker.template.Template;
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.io.IOUtils;
//import org.apache.commons.lang.StringUtils;
//import org.apache.commons.lang3.StringEscapeUtils;
//import org.apache.commons.lang3.text.translate.EntityArrays;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.core.io.ByteArrayResource;
//import org.springframework.core.io.InputStreamSource;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
//
//// TODO ?????
////import com.sun.mail.util.MailSSLSocketFactory;
//
//import freemarker.template.Configuration;
//
///**
// * The Class MailHandler.
// */
//public class MailHandlerTest implements MailHandler {
//
//    /**
//     * The log.
//     */
//    private static Log log = LogFactory.getLog(MailHandlerTest.class);
//
//    private static final String HTML_WRAPPER_TEMPLATE =
//            "<!DOCTYPE html><html><head><style>body{font-size: 0.5cm;}</style><meta charset=\"%s\"><title>title</title></head><body>%s</body></html>";
//    private static final String HTML_CHARSET_TEMPLATE = "<!DOCTYPE html><html><head><meta charset=\"%s\"><title>title</title></head><body>%s</body></html>";
//    private static final Pattern IMG_CID_REGEX = Pattern.compile("cid:(.*?)\"", Pattern.DOTALL);
//    private static final Pattern IMG_CID_PLAIN_REGEX = Pattern.compile("\\[cid:(.*?)\\]", Pattern.DOTALL);
//
//    @Resource(name = "mailSender")
//    private JavaMailSenderImpl mailSender;
//
//    /**
//     * The configuration.
//     */
//    @Resource(name = "freemarkerMailConfiguration")
//    private Configuration configuration;
//
//    @Resource(name = "sysConfigHandler")
//    private SimpleSysConfigHandler sysConfigHandler;
//
//    @Resource(name = "frameworkCoreManager")
//    private FrameworkCoreManager frameworkCoreManager;
//
//    @Resource(name = "templateHandler")
//    private TemplateHandler templateHandler;
//
//    /**
//     * Questo metodo si preoccupa di ripulire una lista di indirizzi email dagli indirizzi non validi.
//     * Ad esempio, un indirizzo che contiene un &lt; o un &gt; viene rifiutato dal server di posta causando il mancato invio della mail a
//     * tutti i destinatari.
//     * Questo metodo si occupa inoltre di eliminare i duplicati.
//     *
//     * @param input lista degli indirizzi mail da controllare
//     * @return lista degli indirizzi mail validi
//     */
//    private List<String> checkMailAddresses(List<String> input) {
//        List<String> output = new ArrayList<String>();
//        if (input != null) {
//            for (String email : input) {
//                if (StringUtils.isEmpty(email)) {
//                    continue;
//                }
//                if (email.indexOf('>') >= 0 || email.indexOf('<') >= 0 || email.length() < 1) {
//                    log.warn("Mail '" + email + "' contains invalid characters.");
//                } else if (StringUtils.isBlank(email) || StringUtils.isEmpty(email)) {
//                    log.warn("Mail '" + email + "' contains invalid characters.");
//                } else {
//                    if (!output.contains(email)) {
//                        output.add(email);
//                    }
//                }
//            }
//        }
//        return output;
//    }
//
//    /**
//     * Se la system property -Dquix.nosendmail è impostata a true, sono su un ambiente di sviluppo
//     * quindi non invio nessuna mail.
//     *
//     * @return se la system property è impostata a true
//     */
//    private boolean checkIfNoSendMailSystemPropertyIsSet() {
//        String property = System.getProperty("clever.nosendmail");
//        if (property != null && "true".equals(property)) {
//            log.error("Email NOT SENT because the clever.nosendmail jvm property is set to true");
//            return true;
//        }
//        String property2 = System.getProperty("quix.nosendmail");
//        if (property2 != null && "true".equals(property2)) {
//            log.error("Email NOT SENT because the quix.nosendmail jvm property is set to true");
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * Metodo che si occupa di recuperare il nome del server SMTP dalla sysConfig FRMK019_SmtpServerName. Se questo
//     * valore viene passato all'interno del oggetto mail, viene considerato valido il valore passato come parametro.
//     * <p>
//     * Se non trovate il metodo solleva un eccezione
//     *
//     * @param smtpHost
//     * @return
//     */
//    private String getSmtpHost(String smtpHost) {
//        if (StringUtils.isEmpty(smtpHost)) {
//            try {
//                smtpHost = sysConfigHandler.getConfigAsString("Framework", "DEFAULT", "FRMK019_SmtpServerName");
//            } catch (Exception e) {
//                String msg = "Error on get sysconfig FRMK019_SmtpServerName";
//                log.error(msg, e);
//                throw new RuntimeException(msg, e);
//            }
//        }
//        return smtpHost;
//    }
//
//    /**
//     * Metodo che si occupa di recuperare la porta del server SMTP dalla sysConfig FRMK020_SmtpServerPort. Se questo
//     * valore viene passato all'interno del oggetto mail, viene considerato valido il valore passato come parametro.
//     * <p>
//     * Se non trovate il metodo NON solleva un eccezione, ma restiuisce la porta 25
//     *
//     * @param smtpPort
//     * @return
//     */
//    private int getSmtpPort(BigInteger smtpPort) {
//        if (smtpPort == null) {
//            try {
//                smtpPort = sysConfigHandler.getConfigAsInteger("Framework", "DEFAULT", "FRMK020_SmtpServerPort");
//            } catch (DAOFinderException e) {
//                // Port is optional
//            } catch (Exception e) {
//                String msg = "Error on get sysconfig FRMK020_SmtpServerPort";
//                log.error(msg, e);
//                throw new RuntimeException(msg, e);
//            }
//        }
//        if (smtpPort != null && smtpPort.intValue() != 0) {
//            return smtpPort.intValue();
//        } else {
//            return 25;
//        }
//    }
//
//    /**
//     * Metodo che si occupa di recuperare lo username del server SMTP dalla sysConfig FRMK021_SmtpServerUsername. Se questo
//     * valore viene passato all'interno del oggetto mail, viene considerato valido il valore passato come parametro.
//     * <p>
//     * Se non trovate il metodo NON solleva un eccezione, ma non imposta lo username
//     *
//     * @param username
//     * @return
//     */
//    private String getUsername(String username) {
//        if (StringUtils.isEmpty(username)) {
//            try {
//                username = sysConfigHandler.getConfigAsString("Framework", "DEFAULT", "FRMK021_SmtpServerUsername");
//            } catch (DAOFinderException e) {
//                // Username is optional
//            } catch (Exception e) {
//                String msg = "Error on get sysconfig FRMK021_SmtpServerUsername";
//                log.error(msg, e);
//                throw new RuntimeException(msg, e);
//            }
//        }
//        if (StringUtils.isNotEmpty(username)) {
//            return username;
//        } else {
//            return null;
//        }
//    }
//
//    /**
//     * Metodo che si occupa di recuperare la password del server SMTP dalla sysConfig FRMK022_SmtpServerPassword. Se questo
//     * valore viene passato all'interno del oggetto mail, viene considerato valido il valore passato come parametro.
//     * <p>
//     * Se non trovate il metodo NON solleva un eccezione, ma non imposta la password
//     *
//     * @param password
//     * @return
//     */
//    private String getPassword(String password) {
//        if (StringUtils.isEmpty(password)) {
//            try {
//                password = sysConfigHandler.getConfigAsString("Framework", "DEFAULT", "FRMK022_SmtpServerPassword");
//            } catch (DAOFinderException e) {
//                // Username is optional
//            } catch (Exception e) {
//                String msg = "Error on get sysconfig FRMK022_SmtpServerPassword";
//                log.error(msg, e);
//                throw new RuntimeException(msg, e);
//            }
//        }
//        if (StringUtils.isNotEmpty(password)) {
//            return password;
//        } else {
//            return null;
//        }
//    }
//
//    /**
//     * Metodo che si occupa di recuperare il protocollo del server SMTP dalla sysConfig FRMK023_SmtpServerProtocol. Se questo
//     * valore viene passato all'interno del oggetto mail, viene considerato valido il valore passato come parametro.
//     * <p>
//     * Se non trovate il metodo NON solleva un eccezione, ma imposta il protocollo a smtp
//     *
//     * @param protocol
//     * @return
//     */
//    private String getProtocol(String protocol) {
//        if (StringUtils.isEmpty(protocol)) {
//            try {
//                protocol = sysConfigHandler.getConfigAsString("Framework", "DEFAULT", "FRMK023_SmtpServerProtocol");
//            } catch (DAOFinderException e) {
//                // protocol is optional
//            } catch (Exception e) {
//                String msg = "Error on get sysconfig FRMK023_SmtpServerProtocol";
//                log.error(msg, e);
//                throw new RuntimeException(msg, e);
//            }
//        }
//        if (StringUtils.isNotEmpty(protocol)) {
//            return protocol;
//        } else {
//            return "smtp";
//        }
//    }
//
//    /**
//     * Metodo che si occupa di recuperare le proprietà estese del server SMTP dalla sysConfig FRMK024_SmtpServerMailProperties. Se questo
//     * valore viene passato all'interno del oggetto mail, viene considerato valido il valore passato come parametro.
//     * <p>
//     * Se non trovate il metodo NON solleva un eccezione, ma non imposta proprietà estese
//     *
//     * @param smtpProperties
//     * @return
//     */
//    private Properties getSmtpProperties(Map<String, String> smtpProperties) {
//        if (smtpProperties == null) {
//            smtpProperties = new HashMap<>();
//        }
//        try {
//            smtpProperties.put("mail.smtps.auth", "true");
//            smtpProperties.put("mail.smtps.port", "465");
//            smtpProperties.put("mail.smtp.starttls.enable", "true");
//            smtpProperties.put("mail.smtps.quitwait", "false");
//            smtpProperties.put("mail.smtp.auth", "true");
//        } catch (Exception e) {
//            String msg = "Error on get sysconfig FRMK024_SmtpServerMailProperties";
//            log.error(msg, e);
//            throw new RuntimeException(msg, e);
//        }
//        if (smtpProperties != null) {
//            Properties javaMailProperties = new Properties();
//            for (String key : smtpProperties.keySet()) {
//                String value = smtpProperties.get(key);
//                javaMailProperties.put(key, value);
//            }
//            return javaMailProperties;
//        }
//        return null;
//    }
//
//    /**
//     * Metodo che si occupa di recuperare l'encodig di default del server SMTP dalla sysConfig FRMK044_MailDefaultEncoding. Se questo
//     * valore viene passato all'interno del oggetto mail, viene considerato valido il valore passato come parametro.
//     * <p>
//     * Se non trovate il metodo NON solleva un eccezione, ma imposta l'encoding a UTF-8
//     *
//     * @param defaultEncoding
//     * @return
//     */
//    private String getMailDefaultEncoding(String defaultEncoding) {
//        if (StringUtils.isEmpty(defaultEncoding)) {
//            try {
//                defaultEncoding = "UTF-8";
//            } catch (Exception e) {
//                String msg = "Error on get sysconfig FRMK044_MailDefaultEncoding";
//                log.error(msg, e);
//                throw new RuntimeException(msg, e);
//            }
//        }
//        if (StringUtils.isNotEmpty(defaultEncoding)) {
//            return defaultEncoding;
//        } else {
//            return "UTF-8";
//        }
//    }
//
//    private String getMailFrom(String mailFrom) {
//        if (StringUtils.isEmpty(mailFrom)) {
//            try {
//                mailFrom = sysConfigHandler.getConfigAsString("Framework", "DEFAULT", "FRMK067_DefaultFromMail");
//            } catch (DAOFinderException e) {
//                // Username is optional
//            } catch (Exception e) {
//                String msg = "Error on get sysconfig FRMK067_DefaultFromMail";
//                log.error(msg, e);
//                throw new RuntimeException(msg, e);
//            }
//        }
//        if (StringUtils.isNotEmpty(mailFrom)) {
//            return mailFrom;
//        } else {
//            return null;
//        }
//    }
//
//    /**
//     * Metodo principale usato per inviare una mail. Riceve un oggetto mail che raccoglie tutte le possibili configurazioni utili per inviare una email.
//     * <p>
//     * Per prima cosa questo metodo controlla se la system property -Dquix.nosendmail è impostata a true. Se è così non viene inviata nessuna mail.
//     */
//    public synchronized void sendMail(Mail mail) throws Exception {
//        sendMailFromEml(mail, null);
//    }
//
//    public synchronized void sendMailFromEml(Mail mail, File emlFile) throws Exception {
//        try {
//            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//            if (checkIfNoSendMailSystemPropertyIsSet()) {
//                return;
//            }
//            List<String> to = checkMailAddresses(mail.getTo());
//            List<String> cc = checkMailAddresses(mail.getCc());
//            List<String> bcc = checkMailAddresses(mail.getBcc());
//            if (to.size() < 1) {
//                log.info("No valid recipient found for this mail.");
//                return;
//            }
//            mailSender.setHost(getSmtpHost(mail.getSmtpHost()));
//            mailSender.setPort(getSmtpPort(mail.getPort()));
//            mailSender.setUsername(getUsername(mail.getUsername()));
//            mailSender.setPassword(getPassword(mail.getPassword()));
//            mailSender.setProtocol(getProtocol(mail.getProtocol()));
//
//            Properties props = getSmtpProperties(mail.getProps());
//            if (props == null) {
//                props = new Properties();
//            }
////			MailSSLSocketFactory sf = new MailSSLSocketFactory();
////            sf.setTrustAllHosts(true);
////            props.put("mail.smtp.ssl.socketFactory", sf);
//            props.put("mail.imaps.ssl.trust", "*");
//            props.put("mail.smpt.ssl.trust", "*");
//            props.put("mail.smpts.ssl.trust", "*");
//            mailSender.setJavaMailProperties(props);
//
//            String defaultEncoding = getMailDefaultEncoding(mail.getBodyTextPlain());
//            mailSender.setDefaultEncoding(defaultEncoding);
//            boolean isHtml = StringUtils.isNotEmpty(mail.getTemplateName()) || StringUtils.isNotEmpty(mail.getBody());
//            boolean isText = StringUtils.isNotEmpty(mail.getTemplateNameTextPlain()) || StringUtils.isNotEmpty(mail.getBodyTextPlain());
//            MimeMessage message = mailSender.createMimeMessage();
//            Map<String, String> variables = mail.getVariables();
//            if (variables != null) {
//                Set<String> keySet = variables.keySet();
//                for (String key : keySet) {
//                    message.addHeader(key, variables.get(key));
//                }
//            }
//            // use the true flag to indicate you need a multipart message
//            MimeMessageHelper helper = new MimeMessageHelper(message, true, defaultEncoding);
//            String[] toArray = to.toArray(new String[0]);
//            helper.setTo(toArray);
//            String[] ccArray = cc.toArray(new String[0]);
//            if (ccArray != null && ccArray.length > 0) {
//                helper.setCc(ccArray);
//            }
//            String[] bccArray = bcc.toArray(new String[0]);
//            if (bccArray != null && bccArray.length > 0) {
//                helper.setBcc(bccArray);
//            }
//            helper.setFrom(getMailFrom(mail.getFrom()));
//            helper.setSubject(mail.getSubject());
//            if (StringUtils.isNotEmpty(mail.getReplyTo())) {
//                helper.setReplyTo(mail.getReplyTo());
//            }
//            StringBuffer htmlText = new StringBuffer();
//            StringBuffer plainText = new StringBuffer();
//            if (StringUtils.isNotEmpty(mail.getSubjectTemplate())) {
//                String subject = searchTemplate(mail, mail.getSubjectTemplate());
//                mail.setSubject(subject);
//                // se c'è un eml file aggiungo il suo testo e i suoi allegati
//                if (emlFile != null) {
//                    addTextAndAttachmentsEml(mail, emlFile, htmlText);
//                }
//            }
//            if (isHtml) {
//                if (StringUtils.isEmpty(mail.getTemplateName())) {
//                    htmlText.append(mail.getBody());
//                } else {
//                    String body = searchTemplate(mail, mail.getTemplateName());
//                    mail.setBody(body);
//                    htmlText.append(body);
//                }
//                // se c'è un eml file aggiungo il suo testo e i suoi allegati
//                if (emlFile != null) {
//                    addTextAndAttachmentsEml(mail, emlFile, htmlText);
//                }
//            }
//            if (isText) {
//                if (StringUtils.isEmpty(mail.getTemplateNameTextPlain())) {
//                    plainText.append(mail.getBodyTextPlain());
//                } else {
//                    String body = searchTemplate(mail, mail.getTemplateName());
//                    mail.setBody(body);
//                    plainText.append(body);
//                }
//            }
//            // use the true flag to indicate the text included is HTML
//            if (isHtml && isText) {
//                helper.setText(plainText.toString(), htmlText.toString());
//            } else if (isHtml) {
//                helper.setText(htmlText.toString(), true);
//            } else if (isText) {
//                helper.setText(plainText.toString(), false);
//            } else {
//                if (emlFile == null) {
//                    throw new RuntimeException("Error on send mail: no html text or plain text is set!");
//                }
//            }
//
//            // sono sempre contenute almeno le immagini di default del framework
//            Map<String, String> embeddedimg = new HashMap<String, String>();
////            addImageMailParameter(embeddedimg);
//            // se ce ne sono di specifiche, vengono aggiunte
//            if (mail.getEmbeddedimg() != null) {
//                embeddedimg.putAll(mail.getEmbeddedimg());
//            }
//            for (String sid : embeddedimg.keySet()) {
//                try {
//                    InputStream in = this.getClass().getResourceAsStream(embeddedimg.get(sid));
//                    ByteArrayOutputStream out = new ByteArrayOutputStream();
//                    IOUtils.copy(in, out);
//                    byte[] byteArray = out.toByteArray();
//                    ByteArrayResource bar = new ByteArrayResource(byteArray);
//                    helper.addInline(sid, bar, "image/jpg");
//                } catch (Exception e) {
//                    log.debug("Image not found on classpath, try with absolute path", e);
//                    InputStream in = null;
//                    try {
//                        in = new BufferedInputStream(new FileInputStream(new File(embeddedimg.get(sid))));
//                        ByteArrayOutputStream out = new ByteArrayOutputStream();
//                        IOUtils.copy(in, out);
//                        byte[] byteArray = out.toByteArray();
//                        ByteArrayResource bar = new ByteArrayResource(byteArray);
//                        helper.addInline(sid, bar, "image/jpg");
//
//                    } finally {
//                        try {
//                            in.close();
//                        } catch (Exception e2) {
//                        }
//                    }
//                }
//            }
//
//            if (mail.getAttachmentFiles() != null) {
//                for (InputStreamSourceContentType attachmentFile : mail.getAttachmentFiles()) {
//                    String fileName = attachmentFile.getFileName();
//                    InputStreamSource inputStreamSource = attachmentFile.getInputStreamSource();
//                    String contentType = attachmentFile.getContentType();
//
//                    helper.addAttachment(fileName, inputStreamSource, contentType);
//                }
//            }
//
//            if (StringUtils.isNotBlank(mail.getReadReceipt())) {
//                helper.getMimeMessage().setHeader("Return-Receipt-To", mail.getReadReceipt());
//                helper.getMimeMessage().setHeader("Disposition-Notification-To", mail.getReadReceipt());
//            }
//            log.info("Send mail to: " + arrayToString(toArray));
//            mail.setSendResult(SendResultEnum.OK.name());
//            mail.setErrorMsg(null);
//            if (mail.getWriteToEml() != null) {
//                message.writeTo(mail.getWriteToEml());
//            }
//            mailSender.send(message);
//        } catch (Exception e) {
//            mail.setSendResult(SendResultEnum.KO.name());
//            Integer retryNr = mail.getRetryNr() != null ? mail.getRetryNr() + 1 : 1;
//            if (mail.isSkipRetry()) {
//                mail.setRetryNr(999);
//            } else {
//                mail.setRetryNr(retryNr);
//            }
//            mail.setErrorMsg(ExceptionUtil.printStackTrace(e));
//            log.error("An error occurred sending a mail", e);
//            throw new SystemException("An error occurred sending a mail", e);
//        }
//        finally {
//            try {
//                mail.setMailId(String.valueOf(Math.random()));
//                mail.setSendDatetime(Calendar.getInstance().getTime());
//            } catch (Exception e) {
//                log.error("An error occurred saving a mail on database: " + mail, e);
//            }
//        }
//    }
//
//    private String searchTemplate(Mail mail, String template) throws Exception {
//        String templateName;
//        Map<String, Object> map = new HashMap<>();
//        map.put("vars", mail.getTemplateVars());
//        try {
//            templateName = templateHandler.getTemplate(mail.getApplication(), mail.getOrganization(), mail.getLanguage(), template, map);
//        } catch (Exception e) {
//            templateName = FreeMarkerTemplateUtils.processTemplateIntoString(configuration.getTemplate(template), mail.getTemplateVars());
//        }
//        return templateName;
//    }
//
//    private void addTextAndAttachmentsEml(Mail mail, File emlFile, StringBuffer htmlText) throws Exception {
//        InputStream source = new FileInputStream(emlFile);
//        MimeMessage mimeMessage = mailSender.createMimeMessage(source);
//        String textFromMessage = getTextFromMessage(mimeMessage);
//        htmlText.append("<br>");
//        htmlText.append(textFromMessage);
//        List<InputStreamSourceContentType> attachmentList = getAttachmentFromMessage(mimeMessage);
//        if (!attachmentList.isEmpty()) {
//            if (mail.getAttachmentFiles() != null) {
//                mail.getAttachmentFiles().addAll(attachmentList);
//            } else {
//                mail.setAttachmentFiles(attachmentList);
//            }
//
//        }
//        source.close();
//    }
//
//    private String getTextFromMessage(MimeMessage message) throws Exception {
//        String subject = message.getSubject();
//        String from = message.getHeader("From", null);
//        if (from == null) {
//            from = message.getHeader("Sender", null);
//        }
//        try {
//            from = MimeUtility.decodeText(MimeUtility.unfold(from));
//        } catch (Exception e) {
//            // ignore this error
//        }
//        String[] recipients = new String[0];
//        String recipientsRaw = message.getHeader("To", null);
//        if (!Strings.isNullOrEmpty(recipientsRaw)) {
//            try {
//                recipientsRaw = MimeUtility.unfold(recipientsRaw);
//                recipients = recipientsRaw.split(",");
//                for (int i = 0; i < recipients.length; i++) {
//                    recipients[i] = MimeUtility.decodeText(recipients[i]);
//                }
//            } catch (Exception e) {
//                // ignore this error
//            }
//        }
//        String[] cc = new String[0];
//        String ccRaw = message.getHeader("Cc", null);
//        if (!Strings.isNullOrEmpty(ccRaw)) {
//            try {
//                ccRaw = MimeUtility.unfold(ccRaw);
//                cc = ccRaw.split(",");
//                for (int i = 0; i < cc.length; i++) {
//                    cc[i] = MimeUtility.decodeText(cc[i]);
//                }
//            } catch (Exception e) {
//                // ignore this error
//            }
//        }
//
//        String sentDateStr = message.getHeader("date", null);
//
//        MimeObjectEntry<String> bodyEntry = MimeMessageParser.findBodyPart(message);
//        String charsetName = bodyEntry.getContentType().getParameter("charset");
//        final HashMap<String, MimeObjectEntry<String>> inlineImageMap = MimeMessageParser.getInlineImageMap(message);
//        String htmlBody = bodyEntry.getEntry();
//        if (bodyEntry.getContentType().match("text/html")) {
//            htmlBody = String.format(HTML_CHARSET_TEMPLATE, charsetName, htmlBody.replaceAll("(?i)</?(html|body)>", ""));
//            if (inlineImageMap.size() > 0) {
//                // find embedded images and embed them in html using <img src="data:image ...> syntax
//                htmlBody = StringReplacer.replace(htmlBody, IMG_CID_REGEX, new StringReplacerCallback() {
//
//                    public String replace(Matcher m) throws Exception {
//                        MimeObjectEntry<String> base64Entry = inlineImageMap.get("<" + m.group(1) + ">");
//
//                        // found no image for this cid, just return the matches string as it is
//                        if (base64Entry == null) {
//                            return m.group();
//                        }
//
//                        return "data:" + base64Entry.getContentType().getBaseType() + ";base64," + base64Entry.getEntry() + "\"";
//                    }
//                });
//            }
//
//            for (String[] rep : EntityArrays.ISO8859_1_ESCAPE()) {
//                htmlBody = htmlBody.replace(rep[0], rep[1]);
//            }
//            for (String[] rep : EntityArrays.HTML40_EXTENDED_ESCAPE()) {
//                htmlBody = htmlBody.replace(rep[0], rep[1]);
//            }
//
//
//        } else {
//            // replace \n line breaks with <br>
//            htmlBody = htmlBody.replace("\n", "<br>").replace("\r", "");
//            htmlBody = htmlBody.replace(" ", "&nbsp;");
//            htmlBody = String.format(HTML_WRAPPER_TEMPLATE, charsetName, htmlBody);
//            if (inlineImageMap.size() > 0) {
//                // find embedded images and embed them in html using <img src="data:image ...> syntax
//                htmlBody = StringReplacer.replace(htmlBody, IMG_CID_PLAIN_REGEX, new StringReplacerCallback() {
//
//                    public String replace(Matcher m) throws Exception {
//                        MimeObjectEntry<String> base64Entry = inlineImageMap.get("<" + m.group(1) + ">");
//
//                        // found no image for this cid, just return the matches string
//                        if (base64Entry == null) {
//                            return m.group();
//                        }
//
//                        return "<img src=\"data:" + base64Entry.getContentType().getBaseType() + ";base64," + base64Entry.getEntry() + "\" />";
//                    }
//                });
//            }
//        }
//
//        String headers = "";
//        headers += "<div class=\"WordSection1\">";
//        headers +=
//                "<p class=\"MsoNormal\"><b><span lang=\"EN-US\" style=\"mso-fareast-language:IT\">From:</span></b><span lang=\"EN-US\" style=\"mso-fareast-language:IT\"> "
//                        + HtmlEscapers.htmlEscaper().escape(from) + "<br>";
//        headers += "<b>Sent:</b> " + StringEscapeUtils.escapeHtml4(sentDateStr) + "<br>";
//        headers += "<b>To:</b> " + HtmlEscapers.htmlEscaper().escape(Joiner.on(", ").join(recipients)) + "<br>";
//        headers += "<b>Cc:</b> " + HtmlEscapers.htmlEscaper().escape(Joiner.on(", ").join(cc)) + "<br>";
//        headers += "<b>Subject:</b> " + HtmlEscapers.htmlEscaper().escape(subject) + "";
//        List<Part> attachmentParts = MimeMessageParser.getAttachments(message);
//        if (attachmentParts != null && !attachmentParts.isEmpty()) {
//            headers += "<br>";
//            headers += "<b>Attachments:</b> ";
//            boolean first = true;
//            for (Part part : attachmentParts) {
//                String attachmentFilename = null;
//                try {
//                    attachmentFilename = part.getFileName();
//                } catch (Exception e) {
//                    // ignore this error
//                }
//
//                headers += attachmentFilename;
//                if (first) {
//                    first = false;
//                } else {
//                    headers += ", ";
//                }
//            }
//        }
//        headers += "<o:p></o:p></span></p>";
//        headers += "</div>";
//        htmlBody =
//                htmlBody.replace("</head><body>",
//                        "<style>.header-name {color:#9E9E9E; text-align:right;}</style></head><body><table style='border:1px solid #DDD; margin: 8px'>" + headers
//                                + "</table>");
//        return htmlBody;
//    }
//
//    private List<InputStreamSourceContentType> getAttachmentFromMessage(MimeMessage message) throws Exception {
//        List<InputStreamSourceContentType> attachmentList = new ArrayList<>();
//        List<Part> attachmentParts = MimeMessageParser.getAttachments(message);
//        if (attachmentParts != null && !attachmentParts.isEmpty()) {
//            for (Part part : attachmentParts) {
//                InputStream stream = part.getInputStream();
//                String filename = part.getFileName();
//                String contentType = part.getContentType();
//                if (filename != null) {
//                    InputStreamSourceContentType attachment = new InputStreamSourceContentType(contentType, new ByteArrayResource(IOUtils.toByteArray(stream)), filename);
//                    attachmentList.add(attachment);
//                }
//                if (stream != null) {
//                    stream.close();
//                }
//            }
//        }
//        return attachmentList;
//    }
//
//    /**
//     * Metodo di utilita' per inviare una mail.
//     *
//     * @param mail         contiene i nomi delle sysconfig da leggere per parametrizzare la mail. L'unica configurazione obbligatoria e' il nome del template
//     *                     da utilizzare, le altre proprieta' possono essere impostate uguali a null.
//     * @param templateVars mappa delle variabili da passare al template freemarker.
//     * @param embeddedimg  mappa delle immagini da embeddare all'intenro della mail. La mappa contiene sid, percorso all'interno del classpath.
//     * @param application  nome dell'applicazione utilizzata per leggere le sysconfig
//     * @throws MailException
//     */
//    public void sendMailWithSysConfig(ConfigurationMail mail, Map<String, Object> templateVars, Map<String, String> embeddedimg, String application) throws MailException {
//        sendMailWithSysConfig(mail, templateVars, embeddedimg, null, application);
//    }
//
//    /**
//     * Metodo di utilita' per inviare una mail.
//     *
//     * @param mail         contiene i nomi delle sysconfig da leggere per parametrizzare la mail. L'unica configurazione obbligatoria e' il nome del template
//     *                     da utilizzare, le altre proprieta' possono essere impostate uguali a null.
//     * @param templateVars mappa delle variabili da passare al template freemarker.
//     * @param embeddedimg  mappa delle immagini da embeddare all'intenro della mail. La mappa contiene sid, percorso all'interno del classpath.
//     * @param fixedTo      elenco dei destinatari (se impostata anche la relativa sysconfig, viene fatta l'unione delle due liste).
//     * @param application  nome dell'applicazione utilizzata per leggere le sysconfig
//     */
//    public void sendMailWithSysConfig(ConfigurationMail mail, Map<String, Object> templateVars, Map<String, String> embeddedimg, String[] fixedTo, String application) throws MailException {
//        sendMailWithSysConfig(mail, templateVars, embeddedimg, fixedTo, null, application);
//    }
//
//    /**
//     * Metodo di utilita' per inviare una mail.
//     *
//     * @param conf            contiene i nomi delle sysconfig da leggere per parametrizzare la mail. L'unica configurazione obbligatoria e' il nome del template
//     *                        da utilizzare, le altre proprieta' possono essere impostate uguali a null.
//     * @param templateVars    mappa delle variabili da passare al template freemarker.
//     * @param embeddedimg     mappa delle immagini da embeddare all'intenro della mail. La mappa contiene sid, percorso all'interno del classpath.
//     * @param fixedTo         elenco dei destinatari (se impostata anche la relativa sysconfig, viene fatta l'unione delle due liste).
//     * @param attachmentFiles allegati alla mail
//     * @param application     nome dell'applicazione utilizzata per leggere le sysconfig
//     */
//    public void sendMailWithSysConfig(ConfigurationMail conf, Map<String, Object> templateVars, Map<String, String> embeddedimg, String[] fixedTo, List<MailHandlerImpl.InputStreamSourceContentType> attachmentFiles, String application) throws MailException {
//        String bccConfigurationName = conf.getBccconfigurationName();
//        String ccConfigurationName = conf.getCcconfigurationName();
//        String replyToConfigurationName = conf.getReplyToConfigurationName();
//        String subjectConfigurationName = conf.getSubjectConfigurationName();
//        String templateConfigurationName = conf.getTemplateConfigurationName();
//        String toConfigurationName = conf.getToConfigurationName();
//        String forceToConfigurationName = conf.getForceToConfigurationName();
//        try {
//            List<String> bcc = new ArrayList<String>();
//            if (bccConfigurationName != null) {
//                bcc = sysConfigHandler.getConfigAsList(application, "DEFAULT", bccConfigurationName);
//            }
//            List<String> cc = new ArrayList<String>();
//            if (ccConfigurationName != null) {
//                cc = sysConfigHandler.getConfigAsList(application, "DEFAULT", ccConfigurationName);
//            }
//            String replyTo = null;
//            if (replyToConfigurationName != null) {
//                replyTo = sysConfigHandler.getConfigAsString(application, "DEFAULT", replyToConfigurationName);
//            }
//            String subject = null;
//            if (subject == null) {
//                subject = sysConfigHandler.getConfigAsString(application, "DEFAULT", subjectConfigurationName);
//            }
//            String templateName = sysConfigHandler.getConfigAsString(application, "DEFAULT", templateConfigurationName);
//
//            List<String> to = new ArrayList<String>();
//            List<String> forceToUserList = null;
//            if (forceToConfigurationName != null) {
//                forceToUserList = sysConfigHandler.getConfigAsList(application, "DEFAULT", forceToConfigurationName);
//            }
//
//            // if force to == null (or forceTo.size() = 0) --> retrive recipients from config To
//            if (forceToConfigurationName == null || (forceToConfigurationName != null && forceToUserList.size() == 0)) {
//                // it -> reperimento destinatari della mail
//                // en -> retrieval of mail recipients
//                if (toConfigurationName != null) {
//                    List<String> configuratedTo = sysConfigHandler.getConfigAsList(application, "DEFAULT", toConfigurationName);
//                    to.addAll(configuratedTo);
//                }
//                if (fixedTo != null) {
//                    for (String email : fixedTo) {
//                        if (!to.contains(email)) {
//                            to.add(email);
//                        }
//                    }
//                }
//            } else {
//                // else --> use recipients from forceTo
//                to.addAll(forceToUserList);
//            }
//            String collectionMailFrom = conf.getCollectionMailFrom();
//            String from = sysConfigHandler.getConfigAsString(application, "DEFAULT", collectionMailFrom);
//            if (embeddedimg == null) {
//                embeddedimg = new HashMap<String, String>();
//            }
//            addImageMailParameter(embeddedimg);
//            addMailParameter(templateVars, sysConfigHandler);
//            Mail m = new Mail();
//            m.setFrom(from);
//            m.setReplyTo(replyTo);
//            m.setTo(to);
//            m.setCc(cc);
//            m.setBcc(bcc);
//            m.setSubject(subject);
//            m.setTemplateName(templateName);
//            m.setTemplateVars(templateVars);
//            m.setEmbeddedimg(embeddedimg);
//            m.setAttachmentFiles(attachmentFiles);
//            sendMail(m);
//        } catch (Exception e) {
//            throw new MailException("Error on sending mail.", e);
//        }
//    }
//
//    /**
//     * @deprecated use {@link MailHandler#sendMail(Mail)}
//     */
//    @Deprecated
//    public void sendMailTemplateAlreadyProcessed(Mail mail) throws Exception {
//        mail.setBody(mail.getTemplateName());
//        mail.setBodyTextPlain(mail.getTemplateNameTextPlain());
//        mail.setTemplateName(null);
//        mail.setTemplateNameTextPlain(null);
//        sendMail(mail);
//    }
//
//    private String arrayToString(String[] args) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("[");
//        int i = 0;
//        for (String string : args) {
//            if (i > 0) {
//                sb.append(" , ");
//            }
//            sb.append(string);
//            i++;
//        }
//        sb.append("]");
//        return sb.toString();
//    }
//
//    /**
//     * Sets the mail sender.
//     *
//     * @param mailSender the new mail sender
//     */
//    public void setMailSender(JavaMailSenderImpl mailSender) {
//        this.mailSender = mailSender;
//    }
//
//    /**
//     * Gets the mail sender.
//     *
//     * @return the mail sender
//     */
//    public JavaMailSenderImpl getMailSender() {
//        return mailSender;
//    }
//
//    /**
//     * Gets the configuration.
//     *
//     * @return the configuration
//     */
//    public Configuration getConfiguration() {
//        return configuration;
//    }
//
//    /**
//     * Sets the configuration.
//     *
//     * @param configuration the new configuration
//     */
//    public void setConfiguration(Configuration configuration) {
//        this.configuration = configuration;
//    }
//
//
//    public static void addMailParameter(Map<String, Object> params, SimpleSysConfigHandler sysConfigHandler) {
//        try {
//
//            Map<String, String> configAsMap = sysConfigHandler.getConfigAsMap("Framework", "DEFAULT", "FRMK018_MailStyleProps");
//            params.putAll(configAsMap);
//        } catch (Exception e) {
//            throw new RuntimeException("Error on get config FRMK018_MailStyleProps.", e);
//        }
//    }
//
//    public static void addImageMailParameter(Map<String, String> params) {
//        try {
//            params.put("logo", "mail/images/logo_email.jpg");
//            params.put("left", "mail/images/footer_left.jpg");
//            params.put("right", "mail/images/footer_right.jpg");
//        } catch (Exception e) {
//            throw new RuntimeException("Error on get config FRMK027_MailStyleImage.", e);
//        }
//    }
//
//
//
//
////    public static class InputStreamSourceContentType {
////        private InputStreamSource inputStreamSource;
////        private String contentType;
////        private String fileName;
////
////        public InputStreamSourceContentType(String contentType,
////                                            InputStreamSource inputStreamSource, String fileName) {
////            super();
////            this.contentType = contentType;
////            this.inputStreamSource = inputStreamSource;
////            this.fileName = fileName;
////        }
////
////        public InputStreamSource getInputStreamSource() {
////            return inputStreamSource;
////        }
////
////        public void setInputStreamSource(InputStreamSource inputStreamSource) {
////            this.inputStreamSource = inputStreamSource;
////        }
////
////        public String getContentType() {
////            return contentType;
////        }
////
////        public void setContentType(String contentType) {
////            this.contentType = contentType;
////        }
////
////        public String getFileName() {
////            return fileName;
////        }
////
////        public void setFileName(String fileName) {
////            this.fileName = fileName;
////        }
////
////        /**
////         * Constructs a <code>String</code> with all attributes
////         * in name = value format.
////         *
////         * @return a <code>String</code> representation
////         * of this object.
////         */
////        public String toString() {
////            final String TAB = "    ";
////
////            String retValue = "";
////
////            retValue = "InputStreamSourceContentType ( "
////                    + super.toString() + TAB
////                    + "inputStreamSource = " + this.inputStreamSource + TAB
////                    + "contentType = " + this.contentType + TAB
////                    + "fileName = " + this.fileName + TAB
////                    + " )";
////
////            return retValue;
////        }
////    }
//}
