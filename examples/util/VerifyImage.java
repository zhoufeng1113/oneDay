package com.homevip.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 简单的验证码生成
 * @author
 *
 */
public class VerifyImage {
	public String sRand = "";

	public Color getRandColor(int fc, int bc) {
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

	public BufferedImage creatImage() {
		int width = 60;
		int height = 36;
		BufferedImage image = new BufferedImage(width, height, 1);

		Graphics g = image.getGraphics();

		Random random = new Random();

		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);

		g.setFont(new Font("Arial", 0, 18));

		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 60; ++i) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(30);
			int yl = random.nextInt(30);
			g.drawLine(x, y, x + xl, y + yl);
		}

		for (int i = 0; i < 4; ++i) {
			String rand = String.valueOf(random.nextInt(10));
			this.sRand += rand;

			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(rand, 10 * i + 10, 26);
		}

		g.dispose();
		return image;
	}
}