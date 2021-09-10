package com.kevin.firstUtil;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaowenjian
 * @since 2021/9/6 16:29
 */
public class ImageHandle {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\sunjinyue\\Pictures\\Saved Pictures\\test12.jpg";
        String outPath = "C:\\Users\\sunjinyue\\Pictures\\Saved Pictures\\test1.jpg";
        drawTextInImg(filePath, outPath, new FontText("GqVD6Q", 5, "#FFFFFF", 40, "黑体"),
                260,1120,76,"zJPG2A","DIN Condensed Bold",true);
        filePath = "C:\\Users\\sunjinyue\\Pictures\\Saved Pictures\\test1.jpg";
        outPath = "C:\\Users\\sunjinyue\\Pictures\\Saved Pictures\\test2.jpg";
        drawTextInImg(filePath, outPath, new FontText("123456", 5, "#009AFF", 40, "黑体"),
                240,945,36,"天津融银汇金贵金属经营有限公司","黑体",false);
    }

    boolean handleAdvertisingMap( String filePath, HttpServletResponse response){
        ImageIcon imgIcon = new ImageIcon(filePath);
        Image img = imgIcon.getImage();
        int width = img.getWidth(null);
        int height = img.getHeight(null);
        BufferedImage bimage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bimage.createGraphics();
        g.setColor(getColor("#FFFFFF"));
        g.setBackground(Color.white);
        g.drawImage(img, 0, 0, null);
        String textStr = "companyDO.getCompanyCode()";
        Font font =new Font("黑体", Font.BOLD, 60);
        FontMetrics fontMetrics = g.getFontMetrics(font);
        int textWidth = fontMetrics.stringWidth(textStr);
        g.setFont(font);
        int x = (width - textWidth)/2;
        g.drawString(textStr, x, 1120);

        // 处理公司短称
        g.setColor(getColor("#009AFF"));
        textStr = "companyDO.getCompanyShortName()";
        font = new Font("",Font.BOLD,40);
        g.setFont(font);
        fontMetrics = g.getFontMetrics(font);
        textWidth = fontMetrics.stringWidth(textStr);
        x = (width - textWidth)/2;
        g.drawString(textStr,x,945);
        g.dispose();
        try {
            OutputStream out = response.getOutputStream();
            String filename="companyDO.getCompanyShortName()";
            //当文件名不是英文名的时候，最好使用url解码器去编码一下，
            filename= URLEncoder.encode(filename,"utf-8");

            //将响应的类型设置为图片,用于浏览器使用
            response.setContentType("image/jpeg");
            // Content-Disposition 用于设置文件的文件名称，设置此属性之前必须要先设置ContentType
            response.setHeader("Content-Disposition", "attachment;filename=" + filename + ".jpg");
            ImageIO.write(bimage, "JPEG", out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    // color #2395439
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
