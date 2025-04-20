package longlh.dev.base.excel.base.excel.threads;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class ThreadNew extends Thread {

    @Override
    public void run() {
        log.info("Thread started: {}", Thread.currentThread().getName());
        try {
            // Giả lập công việc mất thời gian
            Thread.sleep(100);
        } catch (InterruptedException e) {
            log.error("Thread was interrupted during sleep", e);
            // Đảm bảo rằng trạng thái gián đoạn được khôi phục
            Thread.currentThread().interrupt(); // Đặt lại trạng thái gián đoạn cho thread
        }
        log.info("Thread ended: {}", Thread.currentThread().getName());
    }
}
