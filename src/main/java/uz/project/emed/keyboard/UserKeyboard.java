package uz.project.emed.keyboard;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import uz.project.emed.dto.UserDTO;
import uz.project.emed.service.Language;
import java.util.ArrayList;
import java.util.List;

public class UserKeyboard {
    public UserKeyboard(SendMessage sendMessage) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setOneTimeKeyboard(false);
        keyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> rows = new ArrayList<>();
        KeyboardRow row1, row2;
        KeyboardButton button1, button2, button3;

        String[] selectLang = {};
        switch (UserDTO.getLanguage()) {
            case "uz":
                selectLang = Language.getUz();
                break;
            case "ru":
                selectLang = Language.getRu();
                break;
        }





        switch (UserDTO.getStep()) {
            case "chooseLang":
                row1 = new KeyboardRow();
                button1 = new KeyboardButton("uz \uD83C\uDDFA\uD83C\uDDFF");
                button2 = new KeyboardButton("ru \uD83C\uDDF7\uD83C\uDDFA");
                row1.add(button1);
                row1.add(button2);
                rows.add(row1);
                break;
            case "name":
            case "birthday":
                row1 = new KeyboardRow();
                button1 = new KeyboardButton(selectLang[0] + " ↩");
                row1.add(button1);
                rows.add(row1);
                break;
            case "gender":
                row1 = new KeyboardRow();
                row2 = new KeyboardRow();
                button1 = new KeyboardButton(selectLang[0] + " ↩");
                button2 = new KeyboardButton(selectLang[9]);
                button3 = new KeyboardButton(selectLang[10]);
                row1.add(button2);
                row1.add (button3);
                row2.add(button1);
                rows.add (row1);
                rows.add(row2);
                break;
            case "phone":
                row1 = new KeyboardRow();
                row2 = new KeyboardRow();
                KeyboardButton contactKeyBoard = new KeyboardButton(selectLang[1] + " \uD83D\uDCDE");
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

