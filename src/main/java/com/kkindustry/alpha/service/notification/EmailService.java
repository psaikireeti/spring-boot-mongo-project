package com.kkindustry.alpha.service.notification;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  @Autowired private JavaMailSender mailSender; // Inject JavaMailSender

  /**
   * Constructs an email message dynamically.
   *
   * @param recipient The recipient's email address
   * @param name The recipient's name
   * @param templateType The type of email template (e.g., "WELCOME", "PASSWORD_RESET")
   */
  public void constructAndSendEmail(String recipient, String name, String templateType) {
    String subject;
    String text =
        switch (templateType.toUpperCase()) {
          case "WELCOME" -> {
            subject = "Welcome to Our Service, " + name + "!";
            yield "Hello "
                + name
                + ",\n\nThank you for joining us. We are excited to have you onboard!";
          }
          case "PASSWORD_RESET" -> {
            subject = "Password Reset Request";
            yield "Hi "
                + name
                + ",\n\nWe received a request to reset your password. Click the link below to reset it.";
          }
          default -> {
            subject = "Hello, " + name + "!";
            yield "Dear "
                + name
                + ",\n\nThis is a general email. Let us know if you need any assistance.";
          }
        };

    // Construct subject and text based on the template type

    // Send the email
    sendSimpleEmail(recipient, subject, text);
  }

  /**
   * Sends a simple text email.
   *
   * @param to The recipient's email
   * @param subject The email subject
   * @param text The email body
   */
  public void sendSimpleEmail(String to, String subject, String text) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(to);
    message.setSubject(subject);
    message.setText(text);
    mailSender.send(message);
  }

  /**
   * Sends an HTML email.
   *
   * @param to The recipient's email
   * @param subject The email subject
   * @param htmlContent The HTML email body
   */
  public void sendHtmlEmail(String to, String subject, String htmlContent)
      throws MessagingException {
    MimeMessage message = mailSender.createMimeMessage();
    message.setContent(htmlContent, "text/html");
    message.addRecipients(MimeMessage.RecipientType.TO, to);
    message.setSubject(subject);
    mailSender.send(message);
  }
}
