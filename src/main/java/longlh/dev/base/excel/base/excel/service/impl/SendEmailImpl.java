package longlh.dev.base.excel.base.excel.service.impl;

import lombok.extern.slf4j.Slf4j;
import longlh.dev.base.excel.base.excel.service.SendEmail;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class SendEmailImpl implements SendEmail {

    //@Scheduled(cron = "0,30 * * * * *")
    public void send() {
    }
}
