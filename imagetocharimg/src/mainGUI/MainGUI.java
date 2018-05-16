package mainGUI;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.plaf.FontUIResource;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import controller.CharImgController;
import utils.ImageUtils;

public class MainGUI extends JFrame implements ActionListener {
	public JPanel jp1;
	public JLabel jlb1, jlb2, jlb3, jlb4, jlb5, jlb6, jlb7, jlb8, jlb9, jlb10;
	public JButton button1, button2, button3, button4;
	public SwingWorker<ReturnMsg, String> sw1;
	private JComboBox jcb1, jcb2, jcb3, jcb4, jcb5;
	public JRadioButton jrb1, jrb2;
	public ButtonGroup bg1;
	public JTextField tt1, tt2, tt3, tt4, tt5, tt6, tt7;
	public JFileChooser jfc1, jfc2;
	public File file;
	public BufferedImage bi1, bi2;
	public static int numImage = 0;
	public ImageUtils iu = new ImageUtils();
	private static boolean isRunning;
	public CharImgController cic = new CharImgController();
	public ReturnMsg f;
	// 是否是gif
	public boolean isGif;
	// gif图片集合
	public List<BufferedImage> list = new ArrayList<BufferedImage>();
	// 缩放后集合
	public List<BufferedImage> list2 = new ArrayList<BufferedImage>();
	public List<Integer> list3 = new ArrayList<Integer>();
	public GifMsg gm = new GifMsg();

	public MainGUI() {
		// 初始化主题
		initTheme();
		// 初始化界面
		init();
	}

	private void initTheme() {
		// 国人牛逼主题，值得学习
		// 初始化字体
		InitGlobalFont(new Font("微软雅黑", Font.PLAIN, 13));
		// 设置本属性将改变窗口边框样式定义
		// 系统默认样式 osLookAndFeelDecorated
		// 强立体半透明 translucencyAppleLike
		// 弱立体感半透明 translucencySmallShadow
		// 普通不透明 generalNoTranslucencyShadow
		BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
		// 设置主题为BeautyEye
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 隐藏“设置”按钮
		UIManager.put("RootPane.setupButtonVisible", false);
		// 开启/关闭窗口在不活动时的半透明效果
		// 设置此开关量为false即表示关闭之，BeautyEye LNF中默认是true
		BeautyEyeLNFHelper.translucencyAtFrameInactive = false;
		// 设置BeantuEye外观下JTabbedPane的左缩进
		// 改变InsetsUIResource参数的值即可实现
		UIManager.put("TabbedPane.tabAreaInsets", new javax.swing.plaf.InsetsUIResource(3, 20, 2, 20));
		//

		// 切换主题，此主题在圆形窗口有标题栏

		try {
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// font
	private static void InitGlobalFont(Font font) {
		FontUIResource fontRes = new FontUIResource(font);
		for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements();) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof FontUIResource) {
				UIManager.put(key, fontRes);
			}
		}
	}

	// 主方法
	public static void main(String[] args) {
		// 线程启动截图主程序
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainGUI();
			}
		});
	}

	private void init() {
		// 初始化面板
		jp1 = new JPanel();
		jp1.setLayout(null);

		// 基本设置
		jlb1 = new JLabel("基本设置：");
		jlb1.setBounds(20, 10, 80, 25);
		jp1.add(jlb1);

		jlb2 = new JLabel("缩放图片大小：");
		jlb2.setBounds(47, 40, 100, 25);
		jp1.add(jlb2);

		String[] sfImage = { "原图尺寸", "100x100", "200x200", "300x300", "400x400", "500x500", "自定义" };
		jcb1 = new JComboBox(sfImage);
		// 设置默认显示值
		jcb1.setSelectedIndex(0);
		jp1.add(jcb1);
		jcb1.setBounds(140, 40, 100, 25);

		tt1 = new JTextField();
		tt1.setBounds(250, 40, 60, 25);
		jp1.add(tt1);
		tt1.setVisible(false);

		jlb3 = new JLabel("x");
		jlb3.setBounds(317, 40, 10, 25);
		jp1.add(jlb3);
		jlb3.setVisible(false);

		tt2 = new JTextField();
		tt2.setBounds(330, 40, 60, 25);
		jp1.add(tt2);
		tt2.setVisible(false);

		jcb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 自定义
				if (jcb1.getSelectedIndex() == sfImage.length - 1) {
					tt1.setVisible(true);
					jlb3.setVisible(true);
					tt2.setVisible(true);
				} else {
					tt1.setVisible(false);
					jlb3.setVisible(false);
					tt2.setVisible(false);
				}
			}
		});

		jlb4 = new JLabel("生成图片大小：");
		jlb4.setBounds(47, 70, 100, 25);
		jp1.add(jlb4);

		String[] createImage = { "原图尺寸", "100x100", "200x200", "300x300", "400x400", "500x500", "自定义" };
		jcb2 = new JComboBox(createImage);
		// 设置默认显示值
		jcb2.setSelectedIndex(0);
		jp1.add(jcb2);
		jcb2.setBounds(140, 70, 100, 25);

		tt3 = new JTextField();
		tt3.setBounds(250, 70, 60, 25);
		jp1.add(tt3);
		tt3.setVisible(false);

		jlb5 = new JLabel("x");
		jlb5.setBounds(317, 70, 10, 25);
		jp1.add(jlb5);
		jlb5.setVisible(false);

		tt4 = new JTextField();
		tt4.setBounds(330, 70, 60, 25);
		jp1.add(tt4);
		tt4.setVisible(false);

		jcb2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 自定义
				if (jcb2.getSelectedIndex() == createImage.length - 1) {
					tt3.setVisible(true);
					jlb5.setVisible(true);
					tt4.setVisible(true);
				} else {
					tt3.setVisible(false);
					jlb5.setVisible(false);
					tt4.setVisible(false);
				}
			}
		});

		jlb6 = new JLabel("字符画字符集：");
		jlb6.setBounds(47, 100, 100, 25);
		jp1.add(jlb6);

		String[] customChar = { "默认字符", "8@#&$%*o!;.", "自定义" };
		jcb3 = new JComboBox(customChar);
		// 设置默认显示值
		jcb3.setSelectedIndex(0);
		jp1.add(jcb3);
		jcb3.setBounds(140, 100, 100, 25);

		tt5 = new JTextField();
		tt5.setBounds(250, 100, 60, 25);
		jp1.add(tt5);
		tt5.setVisible(false);

		jlb7 = new JLabel("（左->右 = 复杂->简单）");
		jlb7.setBounds(315, 100, 170, 25);
		jp1.add(jlb7);
		jlb7.setVisible(false);

		jcb3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 自定义
				if (jcb3.getSelectedIndex() == customChar.length - 1) {
					tt5.setVisible(true);
					jlb7.setVisible(true);
				} else {
					tt5.setVisible(false);
					jlb7.setVisible(false);
				}
			}
		});

		jlb8 = new JLabel("选择字符大小：");
		jlb8.setBounds(47, 130, 100, 25);
		jp1.add(jlb8);

		String[] charSize = { "自适应", "10", "12", "14", "16", "18", "20", "自定义" };
		jcb4 = new JComboBox(charSize);
		// 设置默认显示值
		jcb4.setSelectedIndex(0);
		jp1.add(jcb4);
		jcb4.setBounds(140, 130, 100, 25);

		tt6 = new JTextField();
		tt6.setBounds(250, 130, 60, 25);
		jp1.add(tt6);
		tt6.setVisible(false);

		jcb4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 自定义
				if (jcb4.getSelectedIndex() == charSize.length - 1) {
					tt6.setVisible(true);
				} else {
					tt6.setVisible(false);
				}
			}
		});

		// 密集度
		jlb10 = new JLabel("字符密集度：");
		jlb10.setBounds(318, 130, 100, 25);
		jp1.add(jlb10);

		String[] imgIntensity = { "1", "2", "3", "4", "5" };
		jcb5 = new JComboBox(imgIntensity);
		// 设置默认显示值
		jcb5.setSelectedIndex(0);
		jp1.add(jcb5);
		jcb5.setBounds(395, 130, 70, 25);

		button1 = new JButton("选择图片");
		button1.setBounds(30, 170, 80, 30);
		button1.addActionListener(this);
		jp1.add(button1);

		button2 = new JButton("生成图片");
		button2.setBounds(150, 170, 80, 30);
		button2.addActionListener(this);
		button2.setEnabled(false);
		jp1.add(button2);

		button3 = new JButton("另存图片");
		button3.setBounds(270, 170, 80, 30);
		button3.addActionListener(this);
		button3.setEnabled(false);
		jp1.add(button3);

		button4 = new JButton("下载文本");
		button4.setBounds(390, 170, 80, 30);
		button4.setEnabled(false);
		button4.addActionListener(this);
		jp1.add(button4);

		jlb9 = new JLabel("", SwingConstants.CENTER);
		jlb9.setBounds(30, 210, 440, 450);
		jp1.add(jlb9);

		jfc1 = new JFileChooser();
		jp1.add(jfc1);

		this.add(jp1);
		this.setTitle("字符画生成器");
		this.setSize(500, 715);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon imageIcon = new ImageIcon(getClass().getResource("img2.png"));
		this.setIconImage(imageIcon.getImage());
		// 监听界面关闭事件
		this.addWindowListener(new WindowAdapter() {
			// 当关闭时
			public void windowClosing(WindowEvent e) {

			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 监听选择文件夹按钮
		if (e.getSource().equals(button1)) {// 判断触发方法的按钮是哪个
			jfc1.setFileSelectionMode(0);// 设定选择到文件
			jfc1.setFileFilter(new FileCanChoose());
			int state = jfc1.showOpenDialog(this);// 此句是打开文件选择器界面的触发语句
			if (state == 1) {
				return;
			} else {
				file = jfc1.getSelectedFile();// f为选择到的文件
				// 判断是否是动态图
				Timer timer = new Timer();
				if (file.getName().endsWith(".gif") || file.getName().endsWith(".GIF")) {
					isGif = true;
					list.clear();
					list2.clear();
					// 读取并拆分gif
					gm = iu.splitGif(file.getAbsolutePath());
					list = gm.getList();
					// 延迟时间
					list3 = gm.getList2();
					int time = (list3.size() > 0 && list3 != null) ? list3.get(0) : 300;
					// 缩放图片组
					for (BufferedImage bi : list) {
						list2.add(iu.zoomImage(bi, 440, 450));
					}
					// thread = new CountThread();
					// thread.start();

					timer.scheduleAtFixedRate(new TimerTask() {
						int i = 0;

						public void run() {
							if (list2.size() > 0 && list2.get(i) != null) {
								if (numImage != 0) {
									// 移除之前的图片
									jlb9.setText("");
								}
								numImage++;
								jlb9.setIcon(new ImageIcon(list2.get(i)));
								i++;
								if (i > list2.size() - 1) {
									i = 0;
								}
							}
						}
					}, time, time);
					// 轮播图片组模拟gif

					// 每100ms执行一次，1000ms重新执行
					button2.setEnabled(true);
				} else {
					isGif = false;
					if (timer != null) {
						System.out.println(2);
						timer.cancel();
						jlb9.setText("");
					}
					list.clear();
					list2.clear();
					// 缩放图片
					bi1 = iu.zoomImage(file.getAbsolutePath(), 440, 450, null);
					if (bi1 != null) {
						if (numImage != 0) {
							// 移除之前的图片
							jlb9.setText("");
						}
						numImage++;
						jlb9.setIcon(new ImageIcon(bi1));
						button2.setEnabled(true);
					} else {
						JOptionPane.showMessageDialog(null, "加载图片失败！请重试！", "提示消息", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		}

		// 监听生成图片
		if (e.getSource().equals(button2)) {
			// get params
			CharImgParams cip = getCharImgPra();
			// check
			int flag = checkParams(cip);
			if (flag == 0) {
				// 转换参数
				CharImgParams2 cip2 = iu.turnPro(cip);
				button1.setEnabled(false);
				button2.setEnabled(false);
				button3.setEnabled(false);
				button4.setEnabled(false);

				// start times
				MainGUI.isRunning = true;

				sw1 = new SwingWorker<ReturnMsg, String>() {
					// 此方法是耗时任务
					@Override
					protected ReturnMsg doInBackground() throws Exception {
						f = cic.charImgController(cip2);
						return f;
					}

					// done
					protected void done() {
						ReturnMsg f = null;
						try {
							f = get();
						} catch (Exception e) {
							e.printStackTrace();
						}
						if (f != null && f.isSucc) {
							// 显示在一个窗口
							if (!isGif) {
								ShowImgJF sij = new ShowImgJF(f.getBi());
							} else {
								ShowImgJF sij = new ShowImgJF(f.getList(), f.getList4());
							}
						} else {
							JOptionPane.showMessageDialog(null, "创建字符画失败！", "提示消息", JOptionPane.WARNING_MESSAGE);
						}
						button1.setEnabled(true);
						button2.setEnabled(true);
						button3.setEnabled(true);
						button4.setEnabled(true);
					}
				};
				sw1.execute();
			} else if (flag == 1) {
				JOptionPane.showMessageDialog(null, "不能为空！请重新填写！", "提示消息", JOptionPane.WARNING_MESSAGE);
			} else if (flag == 2) {
				JOptionPane.showMessageDialog(null, "数字格式不正确！请重新填写！", "提示消息", JOptionPane.WARNING_MESSAGE);
			} else if (flag == 3) {
				JOptionPane.showMessageDialog(null, "字符集超长！请重新填写！", "提示消息", JOptionPane.WARNING_MESSAGE);
			}
		}

		// 监听另存图片
		if (e.getSource().equals(button3) || e.getSource().equals(button4)) {
			if (f != null && f.isSucc()) {
				// 打开文件夹
				// 接收文件
				JFileChooser chooser = new JFileChooser();
				// 设定只能选择到文件夹
//				chooser.setFileSelectionMode(1);
				chooser.setDialogTitle("保存文件框");
				// 默认文件名称还有放在当前目录下
				
				// 保存路径
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String fileName = sdf.format(new Date());
				if (e.getSource().equals(button3)) {
					if (isGif) {
						fileName = sdf.format(new Date()) + ".gif";
					} else {
						fileName = sdf.format(new Date()) + ".jpg";
					}
				} else if (e.getSource().equals(button4)) {
					fileName = sdf.format(new Date()) + ".txt";
				}
				File filePath = FileSystemView.getFileSystemView().getHomeDirectory();
				File defaultFile = new File(filePath + File.separator + fileName);
				chooser.setSelectedFile(defaultFile);
				int s = chooser.showSaveDialog(this);
				String saveFilePath = chooser.getSelectedFile().getPath();
				if (s == 1) {
					return;
				} else {
					try {
						if (e.getSource().equals(button3)) {
							if (isGif) {
								// 生成动态图
								iu.jpgToGif(f.getList(), f.getList4(), saveFilePath);
							} else {
								ImageIO.write(f.getBi(),
										fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()),
										new File(saveFilePath));
							}
						} else if (e.getSource().equals(button4)) {
							if (isGif) {
								// 动态图全部输出(时间戳+"_"+延迟时间+后缀123+文件格式)
								String pathName = null;
								Writer out = null;
								for (int i = 0; i < f.getList3().size(); i++) {
									pathName = saveFilePath.substring(0, saveFilePath.length() - 4) + "_"
											+ f.getList4().get(i) + "_" + i + ".txt";
									out = new FileWriter(new File(pathName));
									out.write(f.getList3().get(i).getTextImg());
									out.close();
								}

							} else {
								Writer out = new FileWriter(new File(saveFilePath));
								out.write(f.getTextImg());
								out.close();
							}
						}
						JOptionPane.showMessageDialog(null, "保存成功！", "提示消息", JOptionPane.WARNING_MESSAGE);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "保存失败！请重试！", "提示消息", JOptionPane.WARNING_MESSAGE);
						e1.printStackTrace();
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "保存失败！请重试！", "提示消息", JOptionPane.WARNING_MESSAGE);
			}

		}

	}

	// 校验参数
	private int checkParams(CharImgParams cip) {
		// 不能为空
		if ("".equals(cip.getSfImgSizeW()) || "".equals(cip.getSfImgSizeH()) || "".equals(cip.getCreateImgSizeW())
				|| "".equals(cip.getCreateImgSizeH()) || "".equals(cip.getCharArray())
				|| "".equals(cip.getCharSize())) {
			return 1;
		}
		// 数字校验
		if (!cip.getSfImgSizeW().matches("^[0-9]*$") || cip.getSfImgSizeW().length() > 8
				|| !cip.getSfImgSizeH().matches("^[0-9]*$") || cip.getSfImgSizeH().length() > 8
				|| !cip.getCreateImgSizeW().matches("^[0-9]*$") || cip.getCreateImgSizeW().length() > 8
				|| !cip.getCreateImgSizeH().matches("^[0-9]*$") || cip.getCreateImgSizeH().length() > 8
				|| !cip.getCharSize().matches("^[0-9]*$") || cip.getCharSize().length() > 8) {

			return 2;
		}
		// 字符校验
		if (cip.getCharArray().length() > 15) {
			return 3;
		}
		return 0;
	}

	// 获取参数
	private CharImgParams getCharImgPra() {
		CharImgParams cip = new CharImgParams();
		// 缩放图片大小
		if (jcb1.getSelectedIndex() == 0) {
			// 原尺寸
			cip.setSfImgSizeW("0");
			cip.setSfImgSizeH("0");
		} else if (jcb1.getSelectedIndex() == jcb1.getItemCount() - 1) {
			// 最后是自定义
			cip.setSfImgSizeW(tt1.getText().trim());
			cip.setSfImgSizeH(tt2.getText().trim());
		} else {
			String[] s = jcb1.getSelectedItem().toString().replaceAll(" ", "").split("x");
			cip.setSfImgSizeW(s[0]);
			cip.setSfImgSizeH(s[1]);
		}

		// 生成图片的大小
		if (jcb2.getSelectedIndex() == 0) {
			// 原尺寸
			cip.setCreateImgSizeW("0");
			cip.setCreateImgSizeH("0");
		} else if (jcb2.getSelectedIndex() == jcb2.getItemCount() - 1) {
			// 最后是自定义
			cip.setCreateImgSizeW(tt3.getText().trim());
			cip.setCreateImgSizeH(tt4.getText().trim());
		} else {
			String[] s = jcb2.getSelectedItem().toString().replaceAll(" ", "").split("x");
			cip.setCreateImgSizeW(s[0]);
			cip.setCreateImgSizeH(s[1]);
		}
		// 获取字符集
		if (jcb3.getSelectedIndex() == 0) {
			// 默认
			cip.setCharArray("8@#&$%*o!;.");
		} else if (jcb3.getSelectedIndex() == jcb3.getItemCount() - 1) {
			// 最后是自定义
			cip.setCharArray(tt5.getText().trim());
		} else {
			cip.setCharArray(jcb3.getSelectedItem().toString().trim());
		}

		// 获取字符大小
		if (jcb4.getSelectedIndex() == 0) {
			// 默认
			cip.setCharSize("10");
		} else if (jcb4.getSelectedIndex() == jcb4.getItemCount() - 1) {
			// 最后是自定义
			cip.setCharSize(tt6.getText().trim());
		} else {
			cip.setCharSize(jcb4.getSelectedIndex() * 2 + 8 + "");
		}
		// 获取文件地址
		cip.setGif(isGif);
		if (isGif) {
			cip.setList(list2);
			cip.setList3(list3);
		} else {
			cip.setFilePath(file.getAbsolutePath());
		}
		// 字符密集度
		cip.setImgIntensity(jcb5.getSelectedIndex() + 1);
		return cip;
	}

	// 文件过滤器
	class FileCanChoose extends FileFilter {
		public boolean accept(File file) {
			String name = file.getName();
			return (name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".bmp")
					|| name.toLowerCase().endsWith(".png") || name.toLowerCase().endsWith(".jpeg")
					|| name.toLowerCase().endsWith(".gif") || file.isDirectory());
		}

		@Override
		public String getDescription() {
			return "图片文件：*.jpg、 *.bmp、 *.png、 *.jpeg、 *.gif";
		}
	}

}
