package com.reykel.rabbitmq;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    
    private final JavaMailSender mailSender;
    
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    
    public void sendOrderConfirmation(String to, String productName, Integer quantity, Double price) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Order Confirmation - " + productName);
        message.setText("Thank you for your order!\n\n" +
                        "Product: " + productName + "\n" +
                        "Quantity: " + quantity + "\n" +
                        "Price: $" + price + "\n\n" +
                        "We will process your order shortly.");
        
        mailSender.send(message);
    }
}
