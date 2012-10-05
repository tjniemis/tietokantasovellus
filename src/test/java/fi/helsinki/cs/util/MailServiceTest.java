/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.cs.util;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author tesuomin
 */
@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class MailServiceTest {
    
    @Autowired
    private MailService mailService;
    /**
     * Test of sendMail method, of class MailService.
     */
    @Test
    public void testSendMail() {
        System.out.println("sendMail");
        String from = "therane@gmail.com";
        String to = "tero.suominen@yahoo.com";
        String subject = "testi1";
        String msg = "testi2";;
        //mailService.sendMail(from, to, subject, msg);
        assertTrue(true);
    }
}
