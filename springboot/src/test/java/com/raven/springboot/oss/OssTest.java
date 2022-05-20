package com.raven.springboot.oss;

import com.raven.springboot.BaseTest;
import com.raven.springboot.service.OSSService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;

import static org.springframework.util.FileCopyUtils.BUFFER_SIZE;


/**
 * Description:
 * date: 2022/5/20 14:09
 *
 * @author raven
 */
public class OssTest extends BaseTest {

    @Autowired
    private OSSService ossService;

    @Test
    public void test01() {
        File file = new File("/Users/raven/Downloads/picture");
        File[] files = file.listFiles();
        for (File f : files) {
            try {
                //读取图片转换为流
                FileInputStream fis = new FileInputStream(f);
                //（ps:此工具方法贴在了下方）
                byte[] data = inputStreamToBytes(fis);
                String name = f.getName();
                //上传至图片服务器（ps:此处可忽略，此文此方法是将图片上传至相应图片服务器后返回的图片url地址）
                String picUrl = ossService.upload("fix",name,data);
                //输出流写到本地
                writeUrls(picUrl,"/Users/raven/Downloads/picture/urls.txt");
            } catch ( Exception e) {
                logger.warn("读取上传异常",e);
                continue;
            }

        }


    }

    public static byte[] inputStreamToBytes(InputStream ins){
        byte[] data = null;
        ByteArrayOutputStream baos = null;
        try
        {
            baos = new ByteArrayOutputStream();
            int i = -1;
            byte[] buf = new byte[BUFFER_SIZE];
            while ((i = ins.read(buf)) != -1)
            {
                baos.write(buf, 0, i);
            }
            data = baos.toByteArray();
        }
        catch (IOException e)
        {
            //TODO: 错误处理

        }

        return data;
    }

    public void writeUrls(String url,String outPath) throws IOException {
        File txt = new File(outPath);
        url = url + "\r\n";
        byte[] bytes = new byte[512];
        bytes = url.getBytes();
        int length = bytes.length;

        FileOutputStream fos = new FileOutputStream(txt,true);
        fos.write(bytes, 0, length);
        fos.flush();
        fos.close();
    }

}