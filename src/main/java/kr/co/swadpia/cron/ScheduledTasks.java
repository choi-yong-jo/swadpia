package kr.co.swadpia.cron;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.MessagingException;
import kr.co.swadpia.common.service.MailService;
import kr.co.swadpia.entity.EmailQueue;
import kr.co.swadpia.repository.jpa.EmailQueueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnExpression(value = "'${swadpia.admin.profile}' == 'prod' || '${swadpia.admin.profile}' == 'develop'")
public class ScheduledTasks {

    private final MailService mailService;
    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper mapper;
    private final EmailQueueRepository emailQueueRepository;


    @SchedulerLock(name = "oneMinuteJob", lockAtLeastFor = "59S", lockAtMostFor = "59S")
    @Scheduled(cron = "0 */1 * * * *")
    public void oneMinuteJob() throws MessagingException, IOException {
        mailSendFromQue();

    }


    /***
     * 메일 큐에서 메일 전송
     *
     */
    public void mailSendFromQue() throws MessagingException {

        log.info("mailSendFromQue");
        List<EmailQueue> emailQueueList = emailQueueRepository.findAllByCompleteOrderByEmailQueueIdAsc(false);

        for (EmailQueue emailQueue : emailQueueList) {
            mailService.sendMail(emailQueue.getTitle(), emailQueue.getSendTo(), emailQueue.getBody());
            emailQueue.setComplete(true);
            emailQueueRepository.save(emailQueue);
        }
    }

}
