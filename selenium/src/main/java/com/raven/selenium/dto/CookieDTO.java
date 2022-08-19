package com.raven.selenium.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 * date: 2022/8/19 10:47
 *
 * @author raven
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CookieDTO {
    private String name;
    private String value;
}