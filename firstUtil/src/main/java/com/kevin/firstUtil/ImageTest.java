package com.kevin.firstUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaowenjian
 * @since 2021/9/9 18:53
 */
public class ImageTest {
    public static void main(String[] args) {
        String filePath = "D:\\rongbao\\share.jpg";
        String outPath = "D:\\share2.jpg";
        drawTextInImg(filePath, outPath,260,1120,76,"GqVD6Q","DIN Condensed Bold",true);
        filePath = "D:\\share2.jpg";
        outPath = "D:\\share3.jpg";
        drawTextInImg(filePath, outPath,240,945,36,"何伟杰的亲朋好友","黑体",false);
    }

    public static void drawTextInImg(String filePath,String outPath, int x,int y,int fontSize,String textStr,String fontStr,boolean flag) {
        try {
            // 获取本地文件
            BufferedImage image = ImageIO.read(new File(filePath));
            // 获取服务器图片
            URL url = new URL("http://baidu.com");
            image = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ImageIcon imgIcon = new ImageIcon(filePath);
        Image img = imgIcon.getImage();
        // 获取图片的长宽
        int width = img.getWidth(null);
        int height = img.getHeight(null);
        //获取图片数据流
        BufferedImage bimage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        // 获取图片编辑器
        Graphics2D g = bimage.createGraphics();
        // 字体颜色
        g.setColor(getColor("#FFFFFF"));
        // 北京颜色
        g.setBackground(Color.white);
        // 编辑器加载图片数据，如果BufferedImage通过ImageIO.read方式获取则不需要此步骤
        g.drawImage(img, 0, 0, null);
        // 创建字体对象
        Font font =new Font(fontStr, Font.BOLD, fontSize);
        g.setFont(font);
        String tempStr=new String();
        if(flag){
            int i=1;
            while(textStr.length()>0)
            {
                tempStr=textStr.substring(0, 1);
                textStr=textStr.substring(1, textStr.length());
                if(i==1){
                    g.drawString(tempStr, x, y);
                }else {
                    g.drawString(tempStr,x+40*(i-1),y);
                }
                i++;
            }
        }else {
            // 向图片添加文字
            g.drawString(textStr, x, y);
        }

        g.dispose();

        try {
            FileOutputStream out = new FileOutputStream(outPath);
            ImageIO.write(bimage, "JPEG", out);
            out.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // color #2395439  获取颜色
    public static Color getColor(String color) {
        if (color.charAt(0) == '#') {
            color = color.substring(1);
        }
        if (color.length() != 6) {
            return null;
        }
        try {
            int r = Integer.parseInt(color.substring(0, 2), 16);
            int g = Integer.parseInt(color.substring(2, 4), 16);
            int b = Integer.parseInt(color.substring(4), 16);
            return new Color(r, g, b);
        } catch (NumberFormatException nfe) {
            return null;
        }
    }
}
