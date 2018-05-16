package mainGUI;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class CImgPro {
	// 生成的图片
	public BufferedImage bi;
	// 文本形式
	public String textImg;

	public BufferedImage getBi() {
		return bi;
	}

	public void setBi(BufferedImage bi) {
		this.bi = bi;
	}

	public String getTextImg() {
		return textImg;
	}

	public void setTextImg(String textImg) {
		this.textImg = textImg;
	}

	@Override
	public String toString() {
		return "CImgPro [bi=" + bi + ", textImg=" + textImg + "]";
	}

}
