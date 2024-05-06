package com.temelio.apis.nonprofits.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SentEmailsDto {
    private String nonProfitEmail;
    private String emailContent;
}
