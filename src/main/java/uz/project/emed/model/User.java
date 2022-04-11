package uz.project.emed.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user_info")
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_sec")
    @SequenceGenerator(name = "user_sec", sequenceName = "user_sec", allocationSize = 1)
    private Long id;
    private String name;
    private String gender;
    @Column(name = "birthday_date")
    private Long birthdayDate;
    @Column(name = "home_address")
    private String homeAddress;
    @Column (name = "phone_number")
    private String phoneNumber;
    @Column(name="tg_id")
    private Long tgID;
    @Column(name="tg_username")
    private String tgUsername;
    private String step;
    private String language;
    private boolean status;
    private boolean done;




}
