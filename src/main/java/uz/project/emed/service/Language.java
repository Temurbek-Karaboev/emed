package uz.project.emed.service;

public class Language {
    private static final String[] uz = {
            // 0
            "Ortga qaytish",
            // 1
            "Telefon raqamingizni yuboring",
            // 2
            "Jinsingiz",
            // 3
            "F. I. Sh.",
            // 4
            "Raqamni qayta yuboring !",
            // 5
            "Tilni o'zgartirish",
            // 6
            "Tug'ilgan kuningiz kiriting  (kun.oy.yil)",
            // 7
            "Hush kelibsiz!",
            // 8
            "Yashash manzilgizni kiriting",
            // 9
            "Erkak",
            // 10
            "Ayol",
            // 11
            "XATO ! (01.01.1990) qilib kiriting ! ",
            // 12
            "Siz ro'yxatdan muvoffaqiyatli o'tdingiz, siz bilan telefon yoki telegram bot orqali aloqaga chiqamiz",
            // 13
            "Ertalab",
            // 14
            "Kechqurun",

    };
    private static final String[] ru = {"Назад", "Отправьте свой номер","Ваш Пол",
            "Ваше имя","Пожалуйста отравьте ещё раз","Поменять язык","Введите день вашего рождение (дд.мм.гггг)",
            "Добро пожаловать!","Введите адрес проживания","Мужчина","Женщина","Ошибка! Введите данные правильно (01.01.1990)",
            "Вы успешно зарегистрировались, мы с вами свяжемся через телефоном или телеграм ботом","Утром","Вечером"
    };


    public static String[] getUz() {
        return uz;
    }

    public static String[] getRu() {
        return ru;
    }

}
