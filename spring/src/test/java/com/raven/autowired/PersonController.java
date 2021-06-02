package com.raven.autowired;

import lombok.Data;

/**
 * @PackageName: com.raven.springboot.entity
 * @ClassName: PersonController
 * @Blame: raven
 * @Date: 2021-05-31 20:49
 * @Description:
 */
@Data
public class PersonController {
    @AutoWired
    private PersonService personService;
    private String name;
}
