package uz.project.emed.repository;

import org.checkerframework.checker.nullness.Opt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.project.emed.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByTgID(Long tgID);

    @Modifying
    @Query("UPDATE User u SET u.language =:language WHERE u.tgID = :tgID")
    void updateInfo(Long tgID, String language);

    User getStepByTgID(Long tgID);

    @Modifying
    @Query("UPDATE User u SET u.step =:step WHERE u.tgID = :tgID")
    void setStep(Long tgID, String step);

    List<User> getByTgID(Long tgID);


}
