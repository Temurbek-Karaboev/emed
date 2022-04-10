package uz.project.emed.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import uz.project.emed.dto.UserDTO;
import uz.project.emed.model.User;
import uz.project.emed.repository.UserRepository;

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

    public void saveNewUser(UserDTO userDTO){
        User user = mapper.convertValue(userDTO, User.class);

        System.out.println(user.getTgID());
        userRepository.save(user);
    }

}
