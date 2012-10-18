/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.cs.util;

import fi.helsinki.cs.dao.OfferDao;
import fi.helsinki.cs.model.Job;
import fi.helsinki.cs.model.Offer;
import fi.helsinki.cs.model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 * Service class which could be used to send out emails. Class is just a demonstration of how it works. 
 * Currently mail sending is switched off because sample users email addresses are bogus ones. 
 * 
 * @author tesuomin
 */
@Service
public class MailService {
    
        @Autowired
        private MailSender mailSender;
        
        @Autowired
        private OfferDao offerDao;
 
        private static final String lostOfferSubject = "Tarjouskilpailu päättynyt";
        private static final String lostOfferText = "Kiitos tarjouksesta, mutta valitettavasti tilaaja ei päätynyt tarjoukseesi. Onnea jatkossa!";
        
        private static final String winOfferSubject = "Onnittelut, voitit tarjouskilpailun!";
        private static final String winOfferText = "Onneksi olkoon! Tarjouksesi vakuutti minut ja olet voittanut tarjouskilpailuni.";
        
        /**
         * When offer is accepted, this method constructs mail messages to all participants. Winner gets different email than losers. 
         * 
         * @param user User who accepted offer
         * @param job Job in question
         * @param winningOfferId Winning offer ID
         */
        public void acceptOfferMails(User user, Job job, Long winningOfferId) {
            //TODO: Send proper emails to all participants
            List<Offer> offers = offerDao.getOffersByJob(job);
            for (Offer offer : offers) {
                if (offer.getId().equals(winningOfferId)) {
                   //Voittajan viesti
                   System.out.println("Winning email: "+user.getEmail()+", "+offer.getUser().getEmail()+", "+winOfferSubject+", "+winOfferText);
                   //sendMail(user.getEmail(), offer.getUser().getEmail(), winOfferSubject, winOfferText);
                } else {
                    //Häviäjän viesti
                    System.out.println("Losing email: no-reply@tyolletekija.fi, "+offer.getUser().getEmail()+", "+lostOfferSubject+", "+lostOfferText);
                    //sendMail("no-reply@tyolletekija.fi", offer.getUser().getEmail(), lostOfferSubject, lostOfferText);
                }
                
            }
        }    
        
        //Private method for mail sending
	private void sendMail(String from, String to, String subject, String msg) {
 
		SimpleMailMessage message = new SimpleMailMessage();
 
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		mailSender.send(message);	
	}
}
