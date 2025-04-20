package longlh.dev.base.excel.base.excel.threads;

import lombok.RequiredArgsConstructor;
import longlh.dev.base.excel.base.excel.service.SendEmail;

@RequiredArgsConstructor
public class ThreadSendEmail extends Thread {
    private final SendEmail sendEmail;

    @Override
    public void run() {
        // send email
        try {
            sendEmail.send();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
