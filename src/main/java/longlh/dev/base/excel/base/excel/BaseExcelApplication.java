package longlh.dev.base.excel.base.excel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import longlh.dev.base.excel.base.excel.service.SendEmail;
import longlh.dev.base.excel.base.excel.service.impl.SendEmailImpl;
import longlh.dev.base.excel.base.excel.threads.ThreadNew;
import longlh.dev.base.excel.base.excel.threads.ThreadSendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class BaseExcelApplication {
	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext context = SpringApplication.run(BaseExcelApplication.class, args);
		SendEmail sendEmail = context.getBean(SendEmail.class);
		Thread threadDeamon = new ThreadSendEmail(sendEmail);
		threadDeamon.setDaemon(true);
		threadDeamon.start();
		Thread thread = new ThreadNew();
		thread.start();

		thread.join();
		context.close();




	}

}
