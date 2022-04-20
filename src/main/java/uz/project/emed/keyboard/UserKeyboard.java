package uz.project.emed.keyboard;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import uz.project.emed.model.User;
import uz.project.emed.service.Language;
import uz.project.emed.service.UserService;

import java.util.ArrayList;

public class UserKeyboard {
    public UserKeyboard(SendMessage sendMessage, User user) {

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setOneTimeKeyboard(true);
        keyboardMarkup.setResizeKeyboard(true);
        ArrayList<KeyboardRow> rows = new ArrayList<KeyboardRow>();
        KeyboardRow row1, row2, row3, row4, row5, row6;
        KeyboardButton button1, button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12, button13, button14, button15, button16, button17, button18;

        Language language = new Language();

        String[] selectLang = {};
        switch (user.getLanguage()) {
            case "uz":
                selectLang = language.getUz();
                break;
            case "ru":
                selectLang = language.getRu();
                break;
        }





        switch (user.getStep()) {
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
            case "address":
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
                row1.add(button3);
                row2.add(button1);
                rows.add(row1);
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
            case "last":
            case "delete-account":
                row1 = new KeyboardRow();
                button1 = new KeyboardButton(selectLang[13]);
                button2 = new KeyboardButton(selectLang[14]);
                row1.add(button1);
                row1.add(button2);
                rows.add(row1);
                break;
            case "menu":
                row1 = new KeyboardRow();
                row2 = new KeyboardRow();
                button1 = new KeyboardButton(selectLang[18]);
                button2 = new KeyboardButton(selectLang[19]);
                row1.add(button1);
                row2.add(button2);
                rows.add(row1);
                rows.add(row2);
                break;
            case "my-info":
                row1 = new KeyboardRow();
                row2 = new KeyboardRow();
                row3 = new KeyboardRow();
                button1 = new KeyboardButton(selectLang[20]);
                button2 = new KeyboardButton(selectLang[21]);
                button3 = new KeyboardButton(selectLang[22]);
                button4 = new KeyboardButton(selectLang[23]);
                button5 = new KeyboardButton(selectLang[0]+" ↩");
                row1.add(button1);
                row1.add(button2);
                row2.add(button3);
                row2.add(button4);
                row3.add(button5);
                rows.add(row1);
                rows.add(row2);
                rows.add(row3);
                break;
            case "about-me":
                row1 = new KeyboardRow();
                row2 = new KeyboardRow();
                row3 = new KeyboardRow();
                row4 = new KeyboardRow();
                button1 = new KeyboardButton(selectLang[0]+" ↩");
                button2 = new KeyboardButton(selectLang[27]);
                button3 = new KeyboardButton(selectLang[28]);
                button4 = new KeyboardButton(selectLang[29]);
                row1.add(button1);
                row2.add(button2);
                row3.add(button3);
                row4.add(button4);
                rows.add(row1);
                rows.add(row2);
                rows.add(row3);
                rows.add(row4);
                break;

        }


        keyboardMarkup.setKeyboard(rows);
        sendMessage.setReplyMarkup(keyboardMarkup);

    }











}
