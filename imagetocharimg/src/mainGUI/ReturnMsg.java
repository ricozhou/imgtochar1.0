package mainGUI;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ReturnMsg extends CImgPro {
	// 是否成功
	public boolean isSucc;

	public List<CImgPro> list3 = new ArrayList<CImgPro>();
	public List<BufferedImage> list = new ArrayList<BufferedImage>();
	public List<Integer> list4 = new ArrayList<Integer>();

	public List<Integer> getList4() {
		return list4;
	}

	public void setList4(List<Integer> list4) {
		this.list4 = list4;
	}

	public List<BufferedImage> getList() {
		return list;
	}

	public void setList(List<BufferedImage> list) {
		this.list = list;
	}

	public List<CImgPro> getList3() {
		return list3;
	}

	public void setList3(List<CImgPro> list3) {
		this.list3 = list3;
	}

	public boolean isSucc() {
		return isSucc;
	}

	public void setSucc(boolean isSucc) {
		this.isSucc = isSucc;
	}

	@Override
	public String toString() {
		return "ReturnMsg [isSucc=" + isSucc + ", list3=" + list3 + ", list=" + list + ", list4=" + list4 + "]";
	}

}
