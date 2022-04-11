package uz.project.emed.dto;


public class LoadBase {
    private static String name= "";
    private static String gender ="";
    private static Long birthdayDate=0L;
    private static String homeAddress="";
    private static String phoneNumber="";
    private static Long tgID=0L;
    private static String tgUsername="";
    private  static String step="";
    private static String language="uz";
    private static boolean status=false;

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        LoadBase.name = name;
    }

    public static String getGender() {
        return gender;
    }

    public static void setGender(String gender) {
        LoadBase.gender = gender;
    }

    public static Long getBirthdayDate() {
        return birthdayDate;
    }

    public static void setBirthdayDate(Long birthdayDate) {
        LoadBase.birthdayDate = birthdayDate;
    }

    public static String getHomeAddress() {
        return homeAddress;
    }

    public static void setHomeAddress(String homeAddress) {
        LoadBase.homeAddress = homeAddress;
    }

    public static String getPhoneNumber() {
        return phoneNumber;
    }

    public static void setPhoneNumber(String phoneNumber) {
        LoadBase.phoneNumber = phoneNumber;
    }

    public static Long getTgID() {
        return tgID;
    }

    public static void setTgID(Long tgID) {
        LoadBase.tgID = tgID;
    }

    public static String getTgUsername() {
        return tgUsername;
    }

    public static void setTgUsername(String tgUsername) {
        LoadBase.tgUsername = tgUsername;
    }

    public static String getStep() {
        return step;
    }

    public static void setStep(String step) {
        LoadBase.step = step;
    }

    public static String getLanguage() {
        return language;
    }

    public static void setLanguage(String language) {
        LoadBase.language = language;
    }

    public static boolean isStatus() {
        return status;
    }

    public static void setStatus(boolean status) {
        LoadBase.status = status;
    }
}


