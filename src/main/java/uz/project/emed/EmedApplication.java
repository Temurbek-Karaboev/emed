package uz.project.emed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@SpringBootApplication
public class EmedApplication {

	public static void main(String[] args) throws TelegramApiException {
		SpringApplication.run(EmedApplication.class, args);
	}

}
