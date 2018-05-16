package utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import gifdecoder.AnimatedGifEncoder;
import gifdecoder.GifDecoder;
import mainGUI.CImgPro;
import mainGUI.CharImgParams;
import mainGUI.CharImgParams2;
import mainGUI.GifMsg;

public class ImageUtils {

	// 缩放gif
	public BufferedImage zoomImage(BufferedImage bufImg, int jpwidth, int jpheight) {
		return zoomImage(null, jpwidth, jpheight, bufImg);
	}

	// 缩放图片
	public BufferedImage zoomImage(String absolutePath, int jpwidth, int jpheight, BufferedImage bufImg) {
		double wr = 0, hr = 0;
		if (absolutePath != null) {
			File srcFile = new File(absolutePath);
			bufImg = null;
			try {
				bufImg = ImageIO.read(srcFile);
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}

		int w = bufImg.getWidth();
		int h = bufImg.getHeight();
		int[] wh = proImage(w, h, jpwidth, jpheight);

		Image Itemp = bufImg.getScaledInstance(wh[0], wh[1], bufImg.SCALE_SMOOTH);
		wr = wh[0] * 1.0 / bufImg.getWidth();
		hr = wh[1] * 1.0 / bufImg.getHeight();
		AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, hr), null);
		Itemp = ato.filter(bufImg, null);
		return (BufferedImage) Itemp;
	}

	private int[] proImage(int w, int h, int jpwidth, int jpheight) {
		// 判断大小
		// 先以宽为缩放准则
		if (jpwidth * h / w <= jpheight) {
			// 如果高不超过
			return new int[] { jpwidth, jpwidth * h / w };
		} else {
			// 如果高超过了
			// 则以高为准则
			if (w * jpheight / h <= jpwidth) {
				return new int[] { w * jpheight / h, jpheight };
			} else {
				return null;
			}
		}
	}

	// 转换参数
	public CharImgParams2 turnPro(CharImgParams cip) {
		CharImgParams2 cip2 = new CharImgParams2();
		if (cip != null) {
			cip2.setSfImgSizeW(Integer.valueOf(cip.getSfImgSizeW()));
			cip2.setSfImgSizeH(Integer.valueOf(cip.getSfImgSizeH()));
			cip2.setCreateImgSizeW(Integer.valueOf(cip.getCreateImgSizeW()));
			cip2.setCreateImgSizeH(Integer.valueOf(cip.getCreateImgSizeH()));
			cip2.setCharSize(Integer.valueOf(cip.getCharSize()));
			cip2.setCharArray(cip.getCharArray());
			cip2.setGif(cip.isGif());
			if (cip.isGif()) {
				cip2.setList(cip.getList());
				cip2.setList3(cip.getList3());
			} else {
				cip2.setFilePath(cip.getFilePath());
			}
			cip2.setImgIntensity(cip.getImgIntensity());
		}

		return cip2;
	}

	// 获取图片
	public BufferedImage getImage(String filePath) {
		File f = new File(filePath);
		BufferedImage image;
		if (f.exists()) {
			try {
				image = ImageIO.read(f);
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
		return image;
	}

	// 另外一种压缩
	public BufferedImage zoom(BufferedImage bimg, int w, int h) throws IOException {
		int height = bimg.getHeight();
		int width = bimg.getWidth();
		BufferedImage bi = new BufferedImage(w, height * h / width, BufferedImage.TYPE_BYTE_GRAY);

		Graphics g = bi.getGraphics();
		g.drawImage(bimg, 0, 0, w, height * h / width, null);
		g.dispose();

		return bi;
	}

	// 缩放图片
	public BufferedImage zoom2(BufferedImage bufImg, int jpwidth, int jpheight) {
		double wr = 0, hr = 0;

		Image Itemp = bufImg.getScaledInstance(jpwidth, jpheight, bufImg.SCALE_SMOOTH);
		wr = jpwidth * 1.0 / bufImg.getWidth();
		hr = jpheight * 1.0 / bufImg.getHeight();
		AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, hr), null);
		Itemp = ato.filter(bufImg, null);
		return (BufferedImage) Itemp;
	}

	// 创建一个缓冲图片
	public BufferedImage createBufferedImage(int w, int h) {
		BufferedImage image2 = new BufferedImage(w, h, BufferedImage.TYPE_INT_BGR);
		Graphics g = image2.createGraphics();
		// 背景色
		g.setColor(Color.WHITE);
		// 背景
		g.fillRect(0, 0, w, h);
		// 前景色
		g.setColor(Color.BLACK);
		return image2;
	}

	// 创建字符画
	public CImgPro createAsciiPic(BufferedImage image, BufferedImage createImg, String charText, int fontSize,
			int imgIntensity) {
		CImgPro cp = new CImgPro();
		StringBuilder sb = new StringBuilder();
		// 字符串由复杂到简单
		String base = "8@#&$%*o!;.";
		if (charText != null && !"".equals(charText)) {
			base = charText;
		}
		try {
			int w = image.getWidth();
			int h = image.getHeight();
			int ww = createImg.getWidth();
			int hh = createImg.getHeight();
			double xc = (double) (ww / w);
			double yc = (double) (hh / h);
			Graphics g2 = createGraphics(createImg);
			// 设置字体
			g2.setFont(new Font("微软雅黑", Font.PLAIN, fontSize));
			for (int y = 0; y < image.getHeight(); y += 2) {
				for (int x = 0; x < image.getWidth(); x += imgIntensity) {
					int pixel = image.getRGB(x, y);
					int r = (pixel & 0xff0000) >> 16, g = (pixel & 0xff00) >> 8, b = pixel & 0xff;
					float gray = 0.299f * r + 0.578f * g + 0.114f * b;
					int index = Math.round(gray * (base.length() + 1) / 255);
					sb.append(index >= base.length() ? " " : String.valueOf(base.charAt(index)));
					g2.drawString(index >= base.length() ? " " : String.valueOf(base.charAt(index)),
							(int) ((x + 1) * xc), (int) ((y + 1) * yc));
				}
				sb.append("\r\n");
			}
			cp.setBi(createImg);
			cp.setTextImg(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cp;
	}

	private Graphics createGraphics(BufferedImage createImg) {
		Graphics g = createImg.createGraphics();
		g.setColor(Color.WHITE); // 设置背景色
		g.fillRect(0, 0, createImg.getWidth(), createImg.getHeight());// 绘制背景
		g.setColor(Color.BLACK); // 设置前景色
		return g;
	}

	// 拆分gif
	public GifMsg splitGif(String gifName) {
		GifMsg gm = new GifMsg();
		List<BufferedImage> list = new ArrayList<BufferedImage>();
		List<Integer> list2 = new ArrayList<Integer>();
		try {
			GifDecoder decoder = new GifDecoder();
			decoder.read(gifName);
			// 个数
			int n = decoder.getFrameCount();

			for (int i = 0; i < n; i++) {
				BufferedImage frame = decoder.getFrame(i);
				list2.add(decoder.getDelay(i));
				list.add(frame);
				// ImageIO.write(frame, "jpg", new File(i + ".jpg"));
			}
			gm.setList(list);
			gm.setList2(list2);
			return gm;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gm;
	}

	// 合成gif
	public void jpgToGif(List<BufferedImage> list, List<Integer> list2, String newPicPath) {
		try {
			// 实例化处理对象
			AnimatedGifEncoder e = new AnimatedGifEncoder();
			e.setRepeat(0);
			e.start(newPicPath);
			for (int i = 0; i < list.size(); i++) {
				// 设置播放的延迟时间
				e.setDelay(list2.get(i));
				// 添加到帧中
				e.addFrame(list.get(i));
			}
			e.finish();
		} catch (Exception e) {
			System.out.println("jpgToGif Failed:");
			e.printStackTrace();
		}
	}

	// public static void main(String[] args) {
	// jpgToGif(splitGif("C:\\work\\workspace3\\swing\\imagetocharimg\\t.gif",
	// ""),"tt.gif");
	// }
}