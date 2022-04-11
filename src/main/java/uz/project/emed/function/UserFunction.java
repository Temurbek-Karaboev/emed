package uz.project.emed.function;

import org.telegram.telegrambots.meta.api.objects.Message;
import uz.project.emed.dto.LoadBase;
import uz.project.emed.service.Language;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class UserFunction {
    private static String send = "default";
    private static String[] selectLang = {};
    private static Message message;
    private static int[] admins;

    private static final Map<String, Function<String, String>> userMethods = new HashMap<String, Function<String, String>>() {
        {
            put("welcome", (a) -> {
                send = "Assalomu Aleykum! \nЗдравствуйте! ";
                LoadBase.setStep("chooseLang");
                return null;
            });
            put("chooseLang", (a) -> {
                if (a.equals("ru \uD83C\uDDF7\uD83C\uDDFA")) {
                    send = "Ваше имя?";
                    LoadBase.setLanguage("ru");
                    LoadBase.setStep("userName");
                }
                else {
                    send = "Ismingiz?";
                    LoadBase.setStep("userName");
                }
                return null;
            });
            put("name", (a) -> {
                if (a.equals(selectLang[0]+" ↩")){
                    send = "Assalomu Aleykum! \nЗдравствуйте! \nWelcome!";
                    LoadBase.setStep("welcome");
                } else {
                    LoadBase.setName(a);
                    send = selectLang[1];
                    LoadBase.setStep("phone");
                }
                return null;
            });
            put("phone", (a) -> {
                send = selectLang[4];
                if (a.equals(selectLang[0]+" ↩")){
                    send = selectLang[3];
                    LoadBase.setStep("name");
                } else if (a.equals("default")){
                    send = selectLang[7];
                    LoadBase.setPhoneNumber(message.getContact().getPhoneNumber());

                    LoadBase.setStep("mainMenu");
                }
                else {
                    boolean onOff = false;
                    String checkNumber = a.replaceAll(" ", "");


                    try {
                        int convert = Integer.parseInt(checkNumber);
                        LoadBase.setPhoneNumber(a);
                        onOff = true;
                    } catch (Exception e) {
                    }
                    if (onOff){
                        send = selectLang[7];
                        LoadBase.setStep("mainMenu");
                        LoadBase.setPhoneNumber(a);
                    }
                }
                return null;
            });
            put("mainMenu", (a) -> {
                if (a.equals(selectLang[6] + " \uD83D\uDCDD")) {
                    LoadBase.setStep("Menu");
                    send = a;

                } else if (a.equals(selectLang[5] + " \uD83D\uDC45")) {
                    if(LoadBase.getLanguage().equals("ru")){
                        send="Добро пожаловать!";
                        LoadBase.setLanguage("ru");
                    }else {
                        LoadBase.setLanguage("uz");
                        send="Hush kelibsiz!";
                    }
                }
                return null;
            });
//            put("Menu", (a) -> {
//                if (a.equals(selectLang[0]+" ↩")){
//                    send = selectLang[7];
//                    LoadBase.setStep("mainMenu");
//                }else if (a.equals(selectLang[8] + " \uD83C\uDF10")||
//                        a.equals("IT Start" + " \uD83D\uDDA5")||
//                        a.equals("Office Pro (*New) \uD83D\uDCF1")||
//                        a.equals("DevOps (*New) \uD83E\uDDD1\u200D\uD83D\uDCBB")||
//                        a.equals("Frontend (HTML, CSS, JAVACRIPT) \uD83D\uDDBC")||
//                        a.equals("React JS ⚛")||
//                        a.equals("Vue JS ✅")||
//                        a.equals("PHP \uD83C\uDD7FH\uD83C\uDD7F")||
//                        a.equals("Java ☕")||
//                        a.equals("C# #️⃣")||
//                        a.equals("Flutter")||
//                        a.equals("AutoCAD \uD83C\uDD70")||
//                        a.equals("Cinema 4D \uD83D\uDD35")||
//                        a.equals("Unity \uD83C\uDFAE")||
//                        a.equals("Unreal Engine 4 \uD83C\uDFAE")
//                ){
//                    send = selectLang[11];
//                    LoadBase.setCourse(a);
//                    LoadBase.setStep("Time");
//
//                }
//                return null;
//            });
//            put("Time", (a) -> {
//                if (a.equals(selectLang[13])|| a.equals(selectLang[14])){
//                    LoadBase.setTime(a);
//                    send = selectLang[12];
//                    Main main = new Main();
//                    String sendMessage = "Yangi murojat"+ "\nUser Name: " +LoadBase.getUserName()+ "\nCourse: " + LoadBase.getCourse() + "\nPhone: " + LoadBase.getPhoneNumber()
//                            + "\nTime: "  + LoadBase.getTime() + "\nId: "+ LoadBase.getUserId() + "\nUser Tg Name: @" +message.getFrom().getUserName();
//                    for (int id : admins){
//                        main.sendMessageToId(sendMessage,id);
//                    }
//                    LoadBase.setStep("Menu");
//                }
//                else if (a.equals(selectLang[0]+" ↩")){
//                    LoadBase.setStep("Menu");
//                    send = selectLang[7];
//                }
//                return null;
//            });
//            put("End", (a) -> {
//                if (a.equals(selectLang[0]+" ↩")){
//                    LoadBase.setStep("Menu");
//                }
//                return null;
//            });

        }
    };

    public UserFunction(Message message,int[] admins) {
        UserFunction.message = message;
        switch (LoadBase.getLanguage()){
            case "uz":
                selectLang = Language.getUz();
                break;
            case "ru":
                selectLang = Language.getRu();
                break;
        }
        Function<String,String> result = userMethods.get(LoadBase.getStep());
        if (result != null) {
            if (message.getText() != null){
                result.apply(message.getText());
            } else {
                result.apply("default");
            }
        }
    }


    public static String getSend() {
        return send;
    }
}
