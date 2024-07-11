package kr.co.swadpia.common.service;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import kr.co.swadpia.common.constant.EmailType;
import kr.co.swadpia.common.entity.EmailQueue;
import kr.co.swadpia.repository.jpa.EmailQueueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class MailService {

	private final JavaMailSender javaMailSender;

	private final EmailQueueRepository emailQueueRepository;

	@Value("${spring.mail.username}")
	private String from;

	public void sendQueue(String title, String to, HashMap<String, String> values, String template,
		EmailType emailType, Boolean immediate) throws MessagingException {

		for (String key : values.keySet()) {
			System.out.println("Key = " + key);

			template = template.replace("[" + key + "]", values.get(key));
		}

		EmailQueue emailQueue = new EmailQueue();

		emailQueue.setTitle(title);
		emailQueue.setSendTo(to);
		emailQueue.setBody(template);
		emailQueue.setType(emailType);

		if (immediate) {
			emailQueue.setComplete(true);
			emailQueueRepository.save(emailQueue);
			// call sendMail method asynchronously from another thread
			String finalTemplate = template;
			new Thread(() -> {
				try {
					sendMail(title, to, finalTemplate);
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}).start();



		} else {
			emailQueue.setComplete(false);
			emailQueueRepository.save(emailQueue);
		}

	}

	public void sendMail(String title, String to, String body) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setSubject(title);
		helper.setFrom("성원애드피아 <" + from + ">");
		helper.setTo(to);
		helper.setText(body, true);
		javaMailSender.send(message);
	}

}