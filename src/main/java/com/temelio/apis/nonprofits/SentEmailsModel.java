package com.temelio.apis.nonprofits;

import com.temelio.apis.users.UserModel;
import jakarta.persistence.*;
import lombok.*;

@Entity(name="sentemails")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SentEmailsModel {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nonprofitEmail;
    @Column
//    @JoinColumn(name="user_id")
//    private UserModel sentBy;
    private String emailContent;
    private String nonProfitName;
}
