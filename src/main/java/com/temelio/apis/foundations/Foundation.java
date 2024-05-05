package com.temelio.apis.foundations;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name="foundations")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Foundation {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(unique = true)
    private String email;
    private String address;
    private Date establishedDate;
}
