package uz.project.emed.function;

import org.telegram.telegrambots.meta.api.objects.Message;
import uz.project.emed.dto.UserDTO;
import uz.project.emed.service.Language;
import uz.project.emed.service.UserService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public class UserFunction {
    private static   String send = "default";
    private static String[] selectLang = {};
    private static Message message;
    private static long[] admins;
    private final UserService userService;

    private static final Map<String, Function<String, String>> userMethods = new HashMap<String, Function<String, String>>() {
        {
            put("welcome", (a) -> {
                send = "Assalomu Aleykum! \nЗдравствуйте! ";
                UserDTO.setStep("chooseLang");
                return null;
            });
            put("chooseLang", (a) -> {
                if (a.equals("ru \uD83C\uDDF7\uD83C\uDDFA")) {
                    UserDTO.setLanguage("ru");
                    send = "Ваши Ф. И. О.";
                    UserDTO.setStep("name");
                } else {
                    send = selectLang[3];
                    UserDTO.setStep("name");
                }
                return null;
            });
            put("name", (a) -> {
                if (a.equals(selectLang[0] + " ↩")) {
                    send = "Assalomu Aleykum! \nЗдравствуйте!";
                    UserDTO.setStep("chooseLang");
                } else {
                    UserDTO.setName(a);
                    send = selectLang[2];
                    System.err.println(UserDTO.getName());
                    UserDTO.setStep("gender");
                }
                return null;
            });

            put("gender", (a) -> {
                if (a.equals(selectLang[0] + " ↩")) {
                    send = selectLang[3];
                    UserDTO.setStep("name");
                } else {
                    UserDTO.setGender(a);
                    send = selectLang[6];
                    System.err.println(UserDTO.getGender());
                    UserDTO.setStep("birthday");
                }
                return null;
            });

            put("birthday", (a) -> {
                if (a.equals(selectLang[0] + " ↩")) {
                    send = selectLang[6];
                    UserDTO.setStep("gender");
                } else {
                        if (timeConverter(a) != null) {
                            try{                        UserDTO.setBirthdayDate(timeConverter(a));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            send = selectLang[8];
                            System.err.println(UserDTO.getBirthdayDate());
                            UserDTO.setStep("address");
                        }
                        else {
                            send=selectLang[11];
                            UserDTO.setStep("birthday");

                        }
                        }

                return null;}
            );

            put("address", (a) -> {
                if (a.equals(selectLang[0] + " ↩")) {
                    send = selectLang[6];

                } else {
                    UserDTO.setHomeAddress(a);
                    send = selectLang[1];
                    System.err.println(UserDTO.getHomeAddress());
                    UserDTO.setStep("phone");
                }
                return null;
            });

            put("phone", (a) -> {
                if (a.equals(selectLang[0] + " ↩")) {
                    send = selectLang[8];
                    UserDTO.setStep("address");
                }  else {
                    UserDTO.setPhoneNumber(a);
                    System.err.println(UserDTO.getPhoneNumber());
                    UserDTO.setStep("last");
                }
                return null;
            });

            put("last", (a) -> {
                System.err.println("BBBOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOLLLLLLLLLLLLLLLLLLLLIIIII");
                UserDTO.setStep("done");
                send= "boldiku";
//                userService.saveNewUser(LoadBase);


                return null;
            });


        }
    };

    public UserFunction(Message message, UserService userService) {
        this.userService = userService;
        switch (UserDTO.getLanguage()) {
            case "uz":
                selectLang = Language.getUz();
                break;
            case "ru":
                selectLang = Language.getRu();
                break;
        }
        Function<String, String> result = userMethods.get(UserDTO.getStep());
        if (result != null) {
            if (message.getText() != null) {
                result.apply(message.getText());
            } else if (Objects.equals(UserDTO.getStep(), "phone")) {
                String number = message.getContact().getPhoneNumber();
                result.apply(number);
            } else {
                result.apply("default");
            }
        }
    }

    public static Long timeConverter(String birthday)  {
        try {
            DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            Date date = formatter.parse(birthday);

            return date.getTime();
        }catch (Exception e){
            return null;
        }

    }

    public static String getSend() {
        return send;
    }
}
