package com.kevin.conveter;


import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

/**
 * @author kevin
 * @date 2019-11-15 13:48
 * @description todo
 **/
public class ImageConverter implements MessageConverter {


    @Override
    public Message toMessage(Object o, MessageProperties messageProperties) throws MessageConversionException {
        return null;
    }

    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        System.out.println("自定义的图片转换器................");
        String msgContentType = message.getMessageProperties().getContentType();
        String fileSuffix = null;
        if (msgContentType != null && (msgContentType.contains("png") || msgContentType.contains("jpg"))) {
            fileSuffix = msgContentType.split("/")[1];
        } else {
            fileSuffix = "jpg";
        }
        byte[] msgBody = message.getBody();
        String filePrefixName = UUID.randomUUID().toString();
        String filePath = "d:/kevin/picture" + filePrefixName + "." + fileSuffix;
        System.out.println("文件路径：" + filePath);
        File file = new File(filePath);
        try {
            Files.copy(new ByteArrayInputStream(msgBody), file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
