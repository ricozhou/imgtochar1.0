package mainGUI;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JWindow;

public class ShowImgJF extends JFrame {
	public BufferedImage bi;
	public List<BufferedImage> list = new ArrayList<BufferedImage>();
	public List<Integer> list2 = new ArrayList<Integer>();
	public static int numImage2 = 0;

	public ShowImgJF(BufferedImage bi) {
		this.bi = bi;
		init(0);
	}

	public ShowImgJF(List<BufferedImage> list, List<Integer> list2) {
		this.list = list;
		this.list2 = list2;
		init(1);
	}

	private void init(int f) {
		int w = 0, h = 0;
		this.setLayout(new BorderLayout());
		JPanel jp1 = new JPanel();
		JLabel jlb1 = new JLabel();
		if (f == 0) {
			w = bi.getWidth();
			h = bi.getHeight() + 30;
			jlb1.setIcon(new ImageIcon(bi));
		} else if (f == 1) {
			w = list.get(0).getWidth();
			h = list.get(0).getHeight() + 30;
			int time = (list2.size() > 0 && list2 != null) ? list2.get(0) : 300;
			Timer timer = new Timer();
			timer.scheduleAtFixedRate(new TimerTask() {
				int i = 0;

				public void run() {
					if (list.get(i) != null) {
						if (numImage2 != 0) {
							// 移除之前的图片
							jlb1.setText("");
						}
						numImage2++;
						jlb1.setIcon(new ImageIcon(list.get(i)));
						i++;
						if (i > list.size() - 1) {
							i = 0;
						}
					} else {
						// JOptionPane.showMessageDialog(null, "加载图片失败！请重试！",
						// "提示消息", JOptionPane.WARNING_MESSAGE);
					}
				}
			}, time, time);
		}

		jp1.add(jlb1);
		this.add(jp1);
		this.setTitle("字符画生成器");
		this.setLocationRelativeTo(null);
		this.setSize(w, h);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ImageIcon imageIcon = new ImageIcon(getClass().getResource("img2.png"));
		this.setIconImage(imageIcon.getImage());

	}

}
