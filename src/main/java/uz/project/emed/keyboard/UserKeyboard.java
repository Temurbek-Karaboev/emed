package uz.project.emed.keyboard;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import uz.project.emed.dto.LoadBase;
import uz.project.emed.model.User;
import uz.project.emed.service.Language;
import uz.project.emed.service.TelegramBotHandler;

import java.util.ArrayList;
import java.util.List;

public class UserKeyboard {
    public UserKeyboard(SendMessage sendMessage, User user, String step) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setOneTimeKeyboard(false);
        keyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> rows = new ArrayList<KeyboardRow>();
        KeyboardRow row1, row2, row3, row4, row5, row6;
        KeyboardButton button1, button2, button3, button4;

        String[] selectLang = {};
        switch (LoadBase.getLanguage()) {
            case "uz":
                selectLang = Language.getUz();
                break;
            case "ru":
                selectLang = Language.getRu();
                break;
        }





        switch (LoadBase.getStep()) {
            case "chooseLang":
                row1 = new KeyboardRow();
                button1 = new KeyboardButton("uz \uD83C\uDDFA\uD83C\uDDFF");
                button2 = new KeyboardButton("ru \uD83C\uDDF7\uD83C\uDDFA");
                row1.add(button1);
                row1.add(button2);
                rows.add(row1);
                break;
            case "name":
                row1 = new KeyboardRow();
                button1 = new KeyboardButton(selectLang[3] + " ↩");
                row1.add(button1);
                rows.add(row1);
                break;
            case "phone":
                row1 = new KeyboardRow();
                row2 = new KeyboardRow();
                KeyboardButton contactKeyBoard = new KeyboardButton(selectLang[2] + " \uD83D\uDCDE");
                contactKeyBoard.setRequestContact(true);
                button1 = contactKeyBoard;
                button2 = new KeyboardButton(selectLang[0] + " ↩");
                row1.add(button1);
                row2.add(button2);
                rows.add(row1);
                rows.add(row2);
                break;
                }

        keyboardMarkup.setKeyboard(rows);
        sendMessage.setReplyMarkup(keyboardMarkup);

        }











    }

