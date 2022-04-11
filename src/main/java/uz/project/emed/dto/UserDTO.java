package uz.project.emed.dto;



public class UserDTO {
    private static   String name= "";
    private static String gender ="";
    private static Long birthdayDate=0L;
    private static String homeAddress="";
    private static String phoneNumber="";
    private static Long tgID=0L;
    private static String tgUsername="";
    private static  String step="welcome";
    private static String language="uz";
    private static boolean status=false;
    private static boolean done=false;

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        UserDTO.name = name;
    }

    public static String getGender() {
        return gender;
    }

    public static void setGender(String gender) {
        UserDTO.gender = gender;
    }

    public static Long getBirthdayDate() {
        return birthdayDate;
    }

    public static void setBirthdayDate(Long birthdayDate) {
        UserDTO.birthdayDate = birthdayDate;
    }

    public static String getHomeAddress() {
        return homeAddress;
    }

    public static void setHomeAddress(String homeAddress) {
        UserDTO.homeAddress = homeAddress;
    }

    public static String getPhoneNumber() {
        return phoneNumber;
    }

    public static void setPhoneNumber(String phoneNumber) {
        UserDTO.phoneNumber = phoneNumber;
    }

    public static Long getTgID() {
        return tgID;
    }

    public static void setTgID(Long tgID) {
        UserDTO.tgID = tgID;
    }

    public static String getTgUsername() {
        return tgUsername;
    }

    public static void setTgUsername(String tgUsername) {
        UserDTO.tgUsername = tgUsername;
    }

    public static String getStep() {
        return step;
    }

    public static void setStep(String step) {
        UserDTO.step = step;
    }

    public static String getLanguage() {
        return language;
    }

    public static void setLanguage(String language) {
        UserDTO.language = language;
    }

    public static boolean isStatus() {
        return status;
    }

    public static void setStatus(boolean status) {
        UserDTO.status = status;
    }

    public static boolean isDone() {
        return done;
    }

    public static void setDone(boolean done) {
        UserDTO.done = done;
    }
}


