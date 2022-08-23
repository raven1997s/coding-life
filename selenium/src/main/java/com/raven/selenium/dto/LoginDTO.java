package com.raven.selenium.dto;

import lombok.Data;

/**
 * Description:
 * date: 2022/8/22 20:37
 *
 * @author raven
 */
@Data
public class LoginDTO {
    private String profiles;
    private String phone;
    private String passWord;
    private String domainName;
}