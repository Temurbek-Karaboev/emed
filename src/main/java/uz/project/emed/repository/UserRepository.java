package uz.project.emed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.project.emed.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByTgID(Long tgID);
    void deleteUserByTgID(Long tgID);

    User getUserByTgID(Long tgID);

    @Modifying
    @Query("UPDATE User u SET u.step=:step WHERE u.tgID=:tgID")
    void setStep(Long tgID, String step);

    @Query("select u.language from User u where u.tgID=:tgID")
    String getLang(Long tgID);


    @Modifying
    @Query("UPDATE User u SET u.language=:lang WHERE u.tgID=:tgID")
    void setLang(Long tgID, String lang);
}
