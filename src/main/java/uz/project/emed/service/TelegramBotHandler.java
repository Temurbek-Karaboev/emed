package uz.project.emed.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import uz.project.emed.dto.UserDTO;
import uz.project.emed.model.User;

@Configuration
@PropertySource("classpath:application.properties")
@Data
public class TelegramBotHandler  extends TelegramLongPollingBot {
    private final UserService userService;
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("start");
        Message message = update.getMessage();
        User user = new User();
        Long userId = message.getChatId();
        System.out.println(userId);
        System.out.println(userService.getFromId(userId));
        if (userService.getFromId(userId)) {
            UserDTO userDTO = new UserDTO();
            userDTO.setTgID(userId);
            userDTO.setStep("welcome");
            userDTO.setStatus(false);
            userDTO.setTgUsername("@" + message.getFrom().getUserName());
            userService.saveNewUser(userDTO);
        }
        sender( message, "Assalomu aleykum! Здравстуйте!");





        System.out.println("Global End!");

    }


    @Value("${telegrambot.name}")
    private String USERNAME;
    @Value("${telegrambot.token}")
    private String TOKEN;

    public TelegramBotHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String getBotUsername() {
        return USERNAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }

    @Bean
    public void runBot()  {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            TelegramBotHandler telegramBotHandler =new TelegramBotHandler(userService);
            telegramBotHandler.setTOKEN(TOKEN);
            telegramBotHandler.setUSERNAME(USERNAME);
            telegramBotsApi.registerBot(telegramBotHandler);
        } catch (TelegramApiException e) {
            System.out.println(e.getMessage());
        }
    }

        public void sender(Message message, String text) {

            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(String.valueOf(message.getChatId()));
            sendMessage.setText(text);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }


