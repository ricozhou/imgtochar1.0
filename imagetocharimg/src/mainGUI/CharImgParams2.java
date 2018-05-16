package mainGUI;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class CharImgParams2 {
	// 参数实体类
	// 缩放图片大小
	public int sfImgSizeW;
	public int sfImgSizeH;

	// 生成图片大小
	public int createImgSizeW;
	public int createImgSizeH;
	// 字符集
	public String charArray;
	// 每个字符大小
	public int charSize;
	// 图片地址
	public String filePath;
	// 字符密集度
	public int imgIntensity;
	// gif
	public boolean isGif;
	public List<BufferedImage> list = new ArrayList<BufferedImage>();
	public List<Integer> list3 = new ArrayList<Integer>();

	public int getImgIntensity() {
		return imgIntensity;
	}

	public void setImgIntensity(int imgIntensity) {
		this.imgIntensity = imgIntensity;
	}

	public List<Integer> getList3() {
		return list3;
	}

	public void setList3(List<Integer> list3) {
		this.list3 = list3;
	}

	public boolean isGif() {
		return isGif;
	}

	public void setGif(boolean isGif) {
		this.isGif = isGif;
	}

	public List<BufferedImage> getList() {
		return list;
	}

	public void setList(List<BufferedImage> list) {
		this.list = list;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getSfImgSizeW() {
		return sfImgSizeW;
	}

	public void setSfImgSizeW(int sfImgSizeW) {
		this.sfImgSizeW = sfImgSizeW;
	}

	public int getSfImgSizeH() {
		return sfImgSizeH;
	}

	public void setSfImgSizeH(int sfImgSizeH) {
		this.sfImgSizeH = sfImgSizeH;
	}

	public int getCreateImgSizeW() {
		return createImgSizeW;
	}

	public void setCreateImgSizeW(int createImgSizeW) {
		this.createImgSizeW = createImgSizeW;
	}

	public int getCreateImgSizeH() {
		return createImgSizeH;
	}

	public void setCreateImgSizeH(int createImgSizeH) {
		this.createImgSizeH = createImgSizeH;
	}

	public String getCharArray() {
		return charArray;
	}

	public void setCharArray(String charArray) {
		this.charArray = charArray;
	}

	public int getCharSize() {
		return charSize;
	}

	public void setCharSize(int charSize) {
		this.charSize = charSize;
	}

	@Override
	public String toString() {
		return "CharImgParams2 [sfImgSizeW=" + sfImgSizeW + ", sfImgSizeH=" + sfImgSizeH + ", createImgSizeW="
				+ createImgSizeW + ", createImgSizeH=" + createImgSizeH + ", charArray=" + charArray + ", charSize="
				+ charSize + ", filePath=" + filePath + ", imgIntensity=" + imgIntensity + ", isGif=" + isGif
				+ ", list=" + list + ", list3=" + list3 + "]";
	}

}
