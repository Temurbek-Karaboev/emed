package uz.project.emed.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.project.emed.model.User;
import uz.project.emed.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    ObjectMapper mapper = new ObjectMapper();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Boolean isPresent(Long id){
        return userRepository.findByTgID(id).isEmpty();
    }

    public void saveNewUser(User userInfo){
        User user = mapper.convertValue(userInfo, User.class);
        userRepository.save(user);
    }

    public void deleteUser(Long tgID){
        userRepository.deleteUserByTgID(tgID);
    }

    public User getUser(Long tgID){
        return userRepository.getUserByTgID(tgID);
    }

    @Transactional
    public void setStep(Long tgId, String step){
        userRepository.setStep(tgId,step);
    }

    @Transactional
    public String getLang(Long tgId){
        return userRepository.getLang(tgId);
    }

    @Transactional
    public void setLang(Long tgId, String lang){
         userRepository.setLang(tgId, lang);
    }

    public void deleteAccount(Long tgID){
        userRepository.deleteUserByTgID(tgID);
    }

}
