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
import uz.project.emed.keyboard.UserKeyboard;
import uz.project.emed.model.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


@Configuration
@PropertySource("classpath:application.properties")
@Data
public class TelegramBotHandler extends TelegramLongPollingBot {
    private final UserService userService;
    ObjectMapper mapper = new ObjectMapper();
    private static String[] selectLang = {};

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("start");
        Message message = update.getMessage();
        Long userId = message.getChatId();
        User user = new User();
        Language language = new Language();


        if (userService.isPresent(userId)) {
            user = new User();
            user.setTgID(userId);
            user.setStep("welcome");
            user.setLanguage("uz");
            user.setStatus(false);
            user.setRegistered(false);
            user.setTgUsername("@" + message.getFrom().getUserName());
            userService.saveNewUser(user);
        }
        try {
            user = userService.getUser(userId);
        } catch (Exception ignored) {

        }
        switch (userService.getLang(userId)) {
            case "uz":
                selectLang = language.getUz();
                break;
            case "ru":
                selectLang = language.getRu();
                break;
        }

        if (!user.isRegistered()) {
            switch (user.getStep()) {
                case "welcome":
                    settterStep(user, "chooseLang");
                    sender(user, message, "Assalomu Aleykum! \nЗдравствуйте! ");
                    break;
                case "chooseLang":
                    settterStep(user, "name");
                    if (message.getText().equals("ru \uD83C\uDDF7\uD83C\uDDFA")) {
                        setttterLang(user, "ru");
                        sender(user, message, "Ваши Ф. И. О.");

                    } else if (message.getText().equals("uz \uD83C\uDDFA\uD83C\uDDFF")){
                        settterStep(user, "name");
                        sender(user, message, "F.I.Sh");
                    }
                    else{
                    settterStep(user, "chooseLang");
                    sender(user, message, selectLang[17]);
                    userService.saveNewUser(user);
                }
                    break;


                case "name":
                    settterStep(user, "chooseLang");
                    if (message.getText().equals(selectLang[0] + " ↩")) {
                        sender(user, message, "Assalomu Aleykum! \nЗдравствуйте! ");
                    } else {
                        settterStep(user, "gender");
                        user.setName(message.getText());
                        sender(user, message, selectLang[2]);
                    }
                    userService.saveNewUser(user);
                    break;

                case "gender":
                    if (message.getText().equals(selectLang[0] + " ↩")) {
                        settterStep(user, "name");
                        sender(user, message, selectLang[3]);
                        userService.saveNewUser(user);
                        break;
                    } else if(Objects.equals(message.getText(), selectLang[9]) || Objects.equals(message.getText(), selectLang[10])){
                        settterStep(user, "birthday");
                        user.setGender(message.getText());
                        sender(user, message, selectLang[6]);
                        userService.saveNewUser(user);
                        System.err.println(user.getGender());
                        break;
                    }else{
                        settterStep(user, "gender");
                        sender(user, message, selectLang[17]);
                        userService.saveNewUser(user);
                        break;
                    }



                case "birthday":
                    if (message.getText().equals(selectLang[0] + " ↩")) {
                        settterStep(user, "gender");
                        sender(user, message, selectLang[2]);
                        userService.saveNewUser(user);
                    } else {
                        if (timeConverter(message.getText()) != null) {
                            try {
                                user.setBirthdayDate(timeConverter(message.getText()));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            settterStep(user, "address");
                            sender(user, message, selectLang[8]);
                            System.err.println(user.getBirthdayDate());
                        } else {
                            sender(user, message, selectLang[11]);
                            user.setStep("birthday");
                        }
                        userService.saveNewUser(user);
                    }
                    break;
                    case "address":
                    if (message.getText().equals(selectLang[0] + " ↩")) {
                        settterStep(user, "birthday");
                        sender(user, message, selectLang[6]);

                    } else {
                        user.setHomeAddress(message.getText());
                        settterStep(user, "phone");
                        sender(user, message, selectLang[1]);
                        System.err.println(user.getHomeAddress());
                    }
                    userService.saveNewUser(user);
                    break;
                case "phone":
                    if(message.getContact()!=null){
                        settterStep(user, "last");
                        user.setPhoneNumber(message.getContact().getPhoneNumber());
                        System.err.println(user.getPhoneNumber());
                        sender(user, message, selectLang[12]);
                    }
                    else if (message.getText().equals(selectLang[0] + " ↩"  )) {
                        settterStep(user, "address");
                        sender(user, message, selectLang[8]);
                    }  else {
                        settterStep(user, "phone");
                        sender(user, message, selectLang[4]);
                    }
                    userService.saveNewUser(user);
                    break;
                case "last":
                    if(message.getText().equals(selectLang[13])){
                        settterStep(user, "menu");
                        user.setRegistered(true);
                        sender(message,selectLang[15] );
                        userService.saveNewUser(user);
                    }
                    else if (message.getText().equals(selectLang[14])){
                        settterStep(user, "chooseLang");
                        sender(user, message, selectLang[16]);
                        userService.saveNewUser(user);
                    }else{
                        settterStep(user, "last");
                        sender(user, message, selectLang[17]);

                        userService.saveNewUser(user);
                    }
                    break;
            }
        } else {
            switch (user.getStep()) {
                case "menu":
                    sender(user, message, selectLang[30]);
                    if(message.getText().equals(selectLang[18])){
                        settterStep(user, "my-info");
                        sender(user, message, selectLang[18]);
                        userService.saveNewUser(user);
                    }
                    else if (message.getText().equals(selectLang[19])){
                        settterStep(user, "find");
                        sender(user, message, selectLang[19]);
                        userService.saveNewUser(user);
                    }
                    else{
                        settterStep(user, "menu");
                        sender(user, message, selectLang[17]);
                        userService.saveNewUser(user);
                    }
                case "my-info":
                    if(message.getText().equals(selectLang[20])){
                        settterStep(user, "about-me");
                        sender(user, message, selectLang[20]);
                        userService.saveNewUser(user);

                        sender(message, selectLang[3]+":  "+ user.getName()+"\n"
                                +selectLang[2]+":  "+ user.getGender()+"\n"
                                +selectLang[6]+":  "+ timeConverter(user.getBirthdayDate()) +"\n"
                                +selectLang[8]+":  "+ user.getHomeAddress() +"\n"
                                +selectLang[1]+":  "+ user.getPhoneNumber() +"\n"
                                +selectLang[24]+":  "+ user.getTgID() +"\n"
                                +selectLang[25]+":  "+ user.getTgUsername() +"\n"
                                +selectLang[26]+":  "+ user.getLanguage());
                    }
                    else if (message.getText().equals(selectLang[21])){
                        settterStep(user, "med-now");
                        sender(user, message, selectLang[21]);
                        userService.saveNewUser(user);
                    }
                    else if (message.getText().equals(selectLang[22])){
                        settterStep(user, "med-history");
                        sender(user, message, selectLang[22]);
                        userService.saveNewUser(user);
                    }
                    else if (message.getText().equals(selectLang[23])){
                        settterStep(user, "drug");
                        sender(user, message, selectLang[23]);
                        userService.saveNewUser(user);
                    }
                    else if (message.getText().equals(selectLang[0] + " ↩"  )) {
                        settterStep(user, "menu");
                        sender(user, message, selectLang[30]);
                        userService.saveNewUser(user);
                    }
                    else{
                        settterStep(user, "my-info");
                        sender(user, message, selectLang[17]);
                        userService.saveNewUser(user);
                    }
                    break;
                case "about-me":
                    if (message.getText().equals(selectLang[0] + " ↩"  )) {
                    settterStep(user, "my-info");
                    sender(user, message, selectLang[18]);
                    userService.saveNewUser(user);
                }
                    else if(message.getText().equals(selectLang[27])){
                        settterStep(user, "menu");
                        sender(user, message, selectLang[30]);
                        userService.saveNewUser(user);
                    }
                    else if(message.getText().equals(selectLang[28])){
                        sender(user, message, selectLang[3]);
                        settterStep(user, "name");
                        user.setRegistered(false);
                        userService.saveNewUser(user);
                    }else if(message.getText().equals(selectLang[29])){
                        sender(user, message, selectLang[31]);
                        settterStep(user, "delete-account");
                        userService.saveNewUser(user);
                    }
                    break;
                case "delete-account":
                    if (message.getText().equals(selectLang[13])){
                        userService.deleteAccount(message.getChatId());
                        settterStep(user, "welcome");
                        userService.saveNewUser(user);
                    }
                    else if(message.getText().equals(selectLang[14])){
                        settterStep(user, "about me");
                        sender(user, message, selectLang[18]);
                        userService.saveNewUser(user);
                    }
                    break;
            }
            }


        System.out.println("Global End!");

    }


    public Long timeConverter(String birthday) {
        try {
            DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            Date date = formatter.parse(birthday);
            return date.getTime();
        } catch (Exception e) {
            return null;
        }
    }

    public String timeConverter(Long date){
        Date dateInLong=new Date(date);
        SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
        return df2.format(date);
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

    public void sender(User user, Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(message.getChatId()));
        sendMessage.setText(text);
        new UserKeyboard(sendMessage, user);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void settterStep(User user, String step) {
        user.setStep(step);
        userService.setStep(user.getTgID(), step);
    }

    public void setttterLang(User user, String lang) {
        user.setLanguage(lang);
        userService.setLang(user.getTgID(), lang);
    }
}


