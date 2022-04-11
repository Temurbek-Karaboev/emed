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
            "Ismingiz",
            // 4
            "Raqamni quyidagicha kiriting \n12 3456789",
            // 5
            "Tilni o'zgartirish",
            // 6
            "Kursga yozilish",
            // 7
            "Hush kelibsiz!",
            // 8
            "IT olamiga kirish",
            // 9
            "Kompyuter savodxonligi",
            // 10
            "Ma'lumot",
            // 11
            "Qulay vaqtni tanlang",
            // 12
            "Siz ro'yxatdan muvoffaqiyatli o'tdingiz, siz bilan telefon yoki telegram bot orqali aloqaga chiqamiz",
            // 13
            "Ertalab",
            // 14
            "Kechqurun",

    };
    private static final String[] ru = {"Назад", "Отправьте свой номер","Ваш Пол",
            "Ваше имя","Пожалуйста введите как примере \n12 3456789","Поменять язык","Подписаться на курс",
            "Добро пожаловать!","Знакомство с миром ИТ","Компьютерная грамотность","Информация","выберите удобное время",
            "Вы успешно зарегистрировались, мы с вами свяжемся через телефоном или телеграм ботом","Утром","Вечером"



    };


    public static String[] getUz() {
        return uz;
    }

    public static String[] getRu() {
        return ru;
    }

}
