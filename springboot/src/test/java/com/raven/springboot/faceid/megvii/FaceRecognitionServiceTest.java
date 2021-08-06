package com.raven.springboot.faceid.megvii;

import com.raven.springboot.SpringbootApplication;
import com.raven.springboot.common.constant.MsgResult;
import com.raven.springboot.faceid.megvii.service.FaceRecognitionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @PackageName: com.raven.springboot.faceid.megvii
 * @ClassName: FaceRecognitionServiceTest
 * @Blame: raven
 * @Date: 2021-08-03 9:24
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
@AutoConfigureMockMvc
@Slf4j
class FaceRecognitionServiceTest{

    @Autowired
    private FaceRecognitionService faceRecognitionService;

    @Test
    public void getTokenTest(){
        MsgResult result = faceRecognitionService.getToken("or00000", "610624XXXXXXX", "XXX");
        System.out.println(result);
    }

    @Test
    public void getResultTest(){
        MsgResult result = faceRecognitionService.getResult("1627972354,2536dc52-0b48-4816-9506-e98932533ddc");
        System.out.println(result);
    }
}
