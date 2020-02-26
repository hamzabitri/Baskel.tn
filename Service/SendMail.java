/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import connexion.DataBase;
import Service.ServiceUser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author NacimGastli
 */
public class SendMail {

    public SendMail() {
    }

    public int GenerateCode() {
        Random rand = new Random(System.currentTimeMillis());

        int red = rand.nextInt(99999);
        return red;
    }

    public void SendEmail(String email) {
        int code = GenerateCode();
        PreparedStatement pst, pst2;
        ResultSet rs, rs2;
        DataBase con;

        try {
            String req = "UPDATE test set code=? where email=? ";
            try {
                pst2 = DataBase.getInstance().getConnection().prepareStatement(req);
                pst2.setInt(1, code);
                pst2.setString(2, email);
                pst2.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("code " + code + " ");
            String host = "smtp.gmail.com";
            String user = "aziz.sayeb@esprit.tn";
            String pass = "tckkiller";
            String to = email;
            String from = "aziz.sayeb@esprit.tn";
            String messageText = "This is confirmation number for your expertprogramming account. Please insert this number to activate your account = " + code + " ";
            String subject = "Fixit Email Confirmation ";
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setText(messageText);

            javax.mail.Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("message sent successfully");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
