package com.temelio.apis.nonprofits;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name="nonprofits")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class NonprofitModel {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(unique = true)
    private String email;
    private String address;
    private Date establishedDate;
}
