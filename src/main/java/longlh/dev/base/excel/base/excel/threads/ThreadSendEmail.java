package longlh.dev.base.excel.base.excel.threads;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import longlh.dev.base.excel.base.excel.service.SendEmail;

@RequiredArgsConstructor
@Slf4j
public class ThreadSendEmail extends Thread {
    private final SendEmail sendEmail;

    @Override
    public void run() {
        try {
            log.info("Start sending email...");
            sendEmail.send(); // Gửi email
            Thread.sleep(10000); // Giả lập chờ sau khi gửi email
            log.info("Email sending process continued...");
        } catch (InterruptedException e) {
            log.error("Thread was interrupted during sleep", e);
            // Đảm bảo thread không tiếp tục chạy nếu bị gián đoạn
            Thread.currentThread().interrupt(); // Đặt lại trạng thái gián đoạn cho thread
        } catch (Exception e) {
            log.error("Error while sending email", e);
        }
    }
}
