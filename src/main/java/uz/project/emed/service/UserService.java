package uz.project.emed.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.project.emed.model.User;
import uz.project.emed.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    ObjectMapper mapper = new ObjectMapper();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Boolean getFromId(Long id){
        return userRepository.findByTgID(id).isEmpty();
    }

    public void saveNewUser(User userInfo){
        User user = mapper.convertValue(userInfo, User.class);

        System.out.println(user.getTgID());
        userRepository.save(user);
    }

    @Transactional
    public void lang(Long tgID, String lang){
        userRepository.updateInfo(tgID, lang);
    }

    public String getStep(Long tgID){
        User user= userRepository.getStepByTgID(tgID);
        return user.getStep();
    }

    @Transactional
    public void setStep(Long tgID, String step){
        userRepository.setStep(tgID,  step);
    }

    public List<User> getUser(Long tgID){
        return userRepository.getByTgID(tgID);
    }

}
