package com.zd.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ImgUtils {
	public static int FONT_SIZE=30;
	public static int WIDTH=80;
	public static int HEIGHT=30;
	
	public static Color getRandomColor(int fc, int bc) {// 给定范围获得随机颜色
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	public static Map<String, BufferedImage> getImg() {
		int width = 60, height = 20;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics graphics = image.getGraphics();
		// 设定背景色
		graphics.setColor(getRandomColor(200, 250));
		graphics.fillRect(0, 0, width, height);
		// 画边框
		graphics.setColor(new Color(0, 0, 0));
		graphics.drawRect(0, 0, width - 1, height - 1);
		// 设定字体
		graphics.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		// 随机产生干扰线，使图象中的认证码不易被其它程序探测到
		graphics.setColor(getRandomColor(150, 200));
		// 生成随机类
		Random random = new Random();
		for (int i = 0; i < 55; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			graphics.drawLine(x, y, x + xl, y + yl);
		}
		// 取随机产生的认证码(4位数字)
		graphics.setColor(getRandomColor(100, 150));
		graphics.setFont(new Font("Atlantic Inline", Font.PLAIN, 16));
		// String s = "0123456789"; // 设置备选验证码:数字"0-9"
		String s = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; // 设置备选验证码:包括"a-z"和数字"0-9"
		// String s="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; //
		// 设置备选验证码:包括"a-z"和数字"0-9"
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 4; i++) {
			String ch = String.valueOf(s.charAt(random.nextInt(s.length())));
			graphics.drawString(ch, i * 13 + 6, (random.nextInt(1) - 1) + 16);
			// valid_code += ch;
			sb.append(ch);
		}
		/*
		 * for (int i=0; i<4; i++){ code = String.valueOf(random.nextInt(10));
		 * //将认证码显示到图象中 graphics.drawString(code,i*13+6,16); valid_code += code;
		 * }
		 */
		// 将认证码存入SESSION
		// 图象生效
		graphics.dispose();

		Map<String, BufferedImage> map = new HashMap<String, BufferedImage>();
		map.put(sb.toString(), image);
		return map;
	}
}
