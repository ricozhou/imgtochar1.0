package mainGUI;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class CharImgParams {
	// 参数实体类
	// 缩放图片大小
	public String sfImgSizeW;
	public String sfImgSizeH;

	// 生成图片大小
	public String createImgSizeW;
	public String createImgSizeH;
	// 字符集
	public String charArray;
	// 每个字符大小
	public String charSize;
	// 图片地址
	public String filePath;
	//字符密集度
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

	public String getSfImgSizeW() {
		return sfImgSizeW;
	}

	public void setSfImgSizeW(String sfImgSizeW) {
		this.sfImgSizeW = sfImgSizeW;
	}

	public String getSfImgSizeH() {
		return sfImgSizeH;
	}

	public void setSfImgSizeH(String sfImgSizeH) {
		this.sfImgSizeH = sfImgSizeH;
	}

	public String getCreateImgSizeW() {
		return createImgSizeW;
	}

	public void setCreateImgSizeW(String createImgSizeW) {
		this.createImgSizeW = createImgSizeW;
	}

	public String getCreateImgSizeH() {
		return createImgSizeH;
	}

	public void setCreateImgSizeH(String createImgSizeH) {
		this.createImgSizeH = createImgSizeH;
	}

	public String getCharArray() {
		return charArray;
	}

	public void setCharArray(String charArray) {
		this.charArray = charArray;
	}

	public String getCharSize() {
		return charSize;
	}

	public void setCharSize(String charSize) {
		this.charSize = charSize;
	}

	@Override
	public String toString() {
		return "CharImgParams [sfImgSizeW=" + sfImgSizeW + ", sfImgSizeH=" + sfImgSizeH + ", createImgSizeW="
				+ createImgSizeW + ", createImgSizeH=" + createImgSizeH + ", charArray=" + charArray + ", charSize="
				+ charSize + ", filePath=" + filePath + ", imgIntensity=" + imgIntensity + ", isGif=" + isGif
				+ ", list=" + list + ", list3=" + list3 + "]";
	}

}
