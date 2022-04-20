package uz.project.emed.service;

public class Language {
    private  final String[] uz = {
            // 0
            "Ortga qaytish",
            // 1
            "Telefon raqamingiz",
            // 2
            "Jinsingiz",
            // 3
            "F. I. Sh.",
            // 4
            "Raqamni qayta yuboring !",
            // 5
            "Tilni o'zgartirish",
            // 6
            "Tug'ilgan kuningiz (kun.oy.yil)",
            // 7
            "Hush kelibsiz!",
            // 8
            "Yashash manzilgiz",
            // 9
            "Erkak",
            // 10
            "Ayol",
            // 11
            "XATO ! (01.01.1990) kabi kiriting qilib kiriting ! ",
            // 12
            "Barcha kiritgan ma'lumotlaringizni tasdiqlaysizmi?\n To'g'riliga ishonchingiz komilmi?",
            //13
            "Albatta",
            // 14
            "Yo'q",
            // 15
            "Muvaffaqiyatli ro'yxatdan o'tdingiz !",
            //16
            "Barcha ma'lumotlaringizni qaytadan kiriting !",
            //17
            "Xato! Bittasini tanlang!",
            //18
            "Mening kabinetim",
            //19
            "Qidiruv",
            //20
            "Mening ma'lumotlarim",
            //21
            "Hozirgi muolaja",
            //22
            "Med. tarix",
            //23
            "Qabul qilinish kerak bo'gan dorilar",
            //24
            "Telegram ID",
            //25
            "Telegram username",
            //26
            "Tanlagan til",
            //27
            "Bosh menyuga qaytish ↩",
            //28
            "Ma'lumotlarni tahrirlash",
            //29
            "Akkauntni o'chirish",
            //30
            "Bosh Menyu",
            //31
            "Akkauntingizni o'chirishni tasdiqlaysizmi?"

    };
    private  final String[] ru = {"Назад", "Номер телефона","Ваш Пол",
            "Ваши Ф.И.О","Пожалуйста, нажмите на кнопку \" Номер телефона \" ","Поменять язык","День вашего рождение (дд.мм.гггг)",
            "Добро пожаловать!","Адрес проживания","Мужчина","Женщина","Ошибка! Введите данные правильно !(01.01.1990)",
            "Вы подтверждаете всю введённую информацию?\nВы уверены?","Конечно !","Нет","Успешно зарегистрировались !", "Повторно введите всю информацию!",
            "Неверно, Выберите один вариант!", "Мой кабинет", "Поиск", "Мои данные", "Текущее лечение", "История болезни", "Лекарства",
            "Телеграм ID", "Телеграм ссылка", "Выбранный язык", "Вернуться в Меню ↩", "Редактирование данных", "Удалить аккаунт", "Главное Меню", "Вы подтверждаете удаление своего аккаунта?"

    };


    public  String[] getUz() {
        return uz;
    }

    public  String[] getRu() {
        return ru;
    }

}