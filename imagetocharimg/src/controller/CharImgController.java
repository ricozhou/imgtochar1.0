package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import mainGUI.CImgPro;
import mainGUI.CharImgParams;
import mainGUI.CharImgParams2;
import mainGUI.ReturnMsg;
import utils.ImageUtils;

public class CharImgController {
	public ImageUtils iu = new ImageUtils();

	// 控制类
	public ReturnMsg charImgController(CharImgParams2 cip2) {
		if (cip2.isGif()) {
			return charImgController2(cip2);
		} else {
			ReturnMsg f = new ReturnMsg();
			// 获取图片
			BufferedImage image = iu.getImage(cip2.getFilePath());
			if (image == null) {
				f.setSucc(false);
				return f;
			}
			// 对获取的图片进行缩放
			if (cip2.getSfImgSizeW() != 0) {
				try {
					image = iu.zoom2(image, cip2.getSfImgSizeW(), cip2.getSfImgSizeH());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// 创建一个缓存图片用于写入字符
			int w = image.getWidth();
			int h = image.getHeight();
			if (cip2.getCreateImgSizeW() != 0) {
				w = cip2.getCreateImgSizeW();
				h = cip2.getCreateImgSizeH();
			}
			BufferedImage createImg = iu.createBufferedImage(w, h);
			// 处理下字体
			int fontSize = 10;
			if (cip2.getCharSize() == 0) {
				// 如果自适应的话
				// 先不处理
				fontSize = 10;
			} else {
				fontSize = cip2.getCharSize();
			}

			// 生成
			CImgPro cp = iu.createAsciiPic(image, createImg, cip2.getCharArray(), fontSize, cip2.getImgIntensity());
			f.setBi(cp.getBi());
			f.setTextImg(cp.getTextImg());
			f.setSucc(true);
			return f;
		}
	}

	// 单独处理gif
	private ReturnMsg charImgController2(CharImgParams2 cip2) {
		ReturnMsg f = new ReturnMsg();
		List<BufferedImage> list = new ArrayList<BufferedImage>();
		List<BufferedImage> list2 = new ArrayList<BufferedImage>();
		// 对获取的图片进行缩放
		if (cip2.getSfImgSizeW() != 0) {
			try {
				for (BufferedImage bi : cip2.getList()) {
					list.add(iu.zoom2(bi, cip2.getSfImgSizeW(), cip2.getSfImgSizeH()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 创建一个缓存图片用于写入字符
		int w = list.get(0).getWidth();
		int h = list.get(0).getHeight();
		if (cip2.getCreateImgSizeW() != 0) {
			w = cip2.getCreateImgSizeW();
			h = cip2.getCreateImgSizeH();
		}
		for (int i = 0; i < list.size(); i++) {
			list2.add(iu.createBufferedImage(w, h));
		}
		// 处理下字体
		int fontSize = 10;
		if (cip2.getCharSize() == 0) {
			// 如果自适应的话
			// 先不处理
			fontSize = 10;
		} else {
			fontSize = cip2.getCharSize();
		}

		// 生成
		List<CImgPro> list3 = new ArrayList<CImgPro>();
		for (int i = 0; i < list2.size(); i++) {
			list3.add(iu.createAsciiPic(list.get(i), list2.get(i), cip2.getCharArray(), fontSize,
					cip2.getImgIntensity()));
		}
		// 直接返回动态图组
		f.setList(list2);
		f.setList3(list3);
		f.setList4(cip2.getList3());
		f.setSucc(true);
		return f;
	}
}
