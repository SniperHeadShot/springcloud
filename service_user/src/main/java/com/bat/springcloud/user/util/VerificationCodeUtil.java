package com.bat.springcloud.user.util;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * @ClassName VerificationCodeUtil
 * @Description 验证码工具类
 * @Author ZhengYu
 * @Version: 1.0
 * @Create: 2019/5/29 13:23
 **/
public class VerificationCodeUtil {

    /**
     * 验证码图片的宽度
     */
    private static final Integer IMAGE_WIDTH = 200;
    /**
     * 验证码图片的高度
     */
    private static final Integer IMAGE_HEIGHT = 69;
    /**
     * 验证码位数
     */
    private static final Integer VERIFICATION_CODE_SIZE = 4;
    /**
     * 干扰线的数量
     */
    private static final Integer DRAW_LINE_COUNT = 6;
    /**
     * 干扰线的数量
     */
    private static final Integer FILL_RECT_COUNT = 30;

    private static final String[] CHAR_ARR = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"
            , "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x"
            , "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P"
            , "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    /**
     * 生成文本的验证码
     *
     * @param
     * @return java.lang.String
     * @author ZhengYu
     * @date 2019/6/6
     */
    public static String createVerificationCodeText() {
        Random random = new Random();
        StringBuilder verificationCode = new StringBuilder();
        for (int i = 0; i < VERIFICATION_CODE_SIZE; i++) {
            verificationCode.append(CHAR_ARR[random.nextInt(CHAR_ARR.length)]);
        }
        return verificationCode.toString();
    }

    public static void drawVerificationCodeText(String verificationCodeText, HttpServletResponse response) throws IOException {
        BufferedImage verifyImg = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = (Graphics2D) verifyImg.getGraphics();
        // 设置画笔颜色-验证码背景色
        graphics.setColor(Color.WHITE);
        // 填充背景
        graphics.fillRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
        graphics.setFont(new Font("微软雅黑", Font.BOLD, 40));
        // 旋转原点的 x 坐标
        int x = 10;
        Random random = new Random();

        String[] verificationCodeArr = verificationCodeText.split("");
        for (String verificationCodeSingle : verificationCodeArr) {
            graphics.setColor(getRandomColor());
            // 设置字体旋转角度小于30度
            int degree = random.nextInt() % 30;
            // 正向旋转
            graphics.rotate(degree * Math.PI / 180, x, 45);
            graphics.drawString(verificationCodeSingle, x, 45);
            // 反向旋转
            graphics.rotate(-degree * Math.PI / 180, x, 45);
            x += 48;
        }

        // 画干扰线
        for (int i = 0; i < DRAW_LINE_COUNT; i++) {
            graphics.setColor(getRandomColor());
            // 随机画线
            graphics.drawLine(random.nextInt(IMAGE_WIDTH), random.nextInt(IMAGE_HEIGHT), random.nextInt(IMAGE_WIDTH), random.nextInt(IMAGE_HEIGHT));
        }

        // 添加噪点
        for (int i = 0; i < FILL_RECT_COUNT; i++) {
            int x1 = random.nextInt(IMAGE_WIDTH);
            int y1 = random.nextInt(IMAGE_HEIGHT);
            graphics.setColor(getRandomColor());
            graphics.fillRect(x1, y1, 2, 2);
        }

        // 必须设置响应内容类型为图片，否则前端不识别
        response.setContentType("image/png");
        // 获取文件输出流
        OutputStream os = response.getOutputStream();
        // 输出图片流
        ImageIO.write(verifyImg, "png", os);
        os.flush();
        os.close();
    }

    /**
     * 随机颜色
     *
     * @param
     * @return java.awt.Color
     * @author ZhengYu
     * @date 2019/6/6
     */
    private static Color getRandomColor() {
        Random random = new Random();
        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }
}
