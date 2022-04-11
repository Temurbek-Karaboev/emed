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
import uz.project.emed.function.UserFunction;
import uz.project.emed.keyboard.UserKeyboard;

import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource("classpath:application.properties")
@Data
public class TelegramBotHandler extends TelegramLongPollingBot {
    private final UserService userService;
    ObjectMapper mapper = new ObjectMapper();
    private static String send = "default";
    private static String[] selectLang = {};
    private static Message message;
    private static final Long[] admins = {1047067789L};


    public  final Map<Long, String> steps = new HashMap<>();

    void setLanguage(String lang) {
        String[] selectLang = {};
        switch (lang) {
            case "uz":
                selectLang = Language.getUz();
                break;
            case "ru":
                selectLang = Language.getRu();
                break;
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        SendMessage sendMessage = new SendMessage();
        boolean isAdmin = false;
        for (Long id : admins) {
            if (message.getFrom().getId() == id) {
                isAdmin = true;
            }
        }
        if (!isAdmin) {
            new UserFunction(message, userService);
            sendMessage.setChatId(String.valueOf(message.getChatId()));
            sendMessage.setText(UserFunction.getSend());
            new UserKeyboard(sendMessage);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else {
                sendMessageToId("You are admin", message.getChatId());
            }

        }

        public void sendMessageToId(String message, long userId) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(String.valueOf(userId));
            sendMessage.setText(message);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            System.err.println("Global End!");
        }




/*
*         if (userService.getFromId(userId)) {
            User user1 = new User();
            user1.setTgID(userId);
            steps.put(userId, "welcome");
            user1.setStatus(false);
            user1.setTgUsername("@" + message.getFrom().getUserName());
                switch (steps.get(userId)) {
                    case "welcome":
                        steps.put(userId, "language");
                        System.err.println("welcome");
                        sender(user1, message, "Assalomu Aleykum! Tilni tanlang \n \n Здравствуйте ! Выберите язык\n ");
                        break;
                    case "language":
                        if (Objects.equals(message.getText(), "uz") || Objects.equals(message.getText(), "ru")) {
                            steps.put(userId, "info");
                            System.err.println("info");
                            user1.setLanguage(message.getText());
                            setLanguage(message.getText());
                            break;

                        } else {
                            steps.put(userId, "welcome");
                            sender(user1, message, "Notog'ri !! \n Неправильно !!");
                            break;
                        }
                    case "info":
                        steps.put(userId, "gender");
                        System.err.println("gender");
                        sender(message, "Ismingiz");
                        user1.setName(message.getText());
                        break;
                    case "gender":
                        sender(message, selectLang[2]);
                        user1.setName(message.getText());
                        break;
                }
            user1.setStep(steps.get(userId));
            userService.saveNewUser(user1);
        }
* */
//
//        System.err.println(userService.getStep(userId));
//
//        if (user.isStatus()) {
//            sender(message, "You are admin!");
//
//        } else {
//
//            ////////////Methods for user
//
//        }






//    public void sender(User user, Message message, String text) {
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setChatId(String.valueOf(message.getChatId()));
//        sendMessage.setText(text);
//        new UserKeyboard(sendMessage, user, steps.get(user.getTgID()));
//        try {
//            execute(sendMessage);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void sender(Message message, String text) {
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setChatId(String.valueOf(message.getChatId()));
//        sendMessage.setText(text);
//        try {
//            execute(sendMessage);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }


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
    public void runBot() {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            TelegramBotHandler telegramBotHandler = new TelegramBotHandler(userService);
            telegramBotHandler.setTOKEN(TOKEN);
            telegramBotHandler.setUSERNAME(USERNAME);
            telegramBotsApi.registerBot(telegramBotHandler);
        } catch (TelegramApiException e) {
            System.out.println(e.getMessage());
        }
    }



//    String getterStep(Long tgID){
//        return userService.getStep(tgID);
//    }
//    void setterStep(Long tgID, String step){
//         userService.setStep(tgID, step);
//    }


}


