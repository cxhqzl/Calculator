package calculator_main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigInteger;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 计算器
 * @author Xinhai Cao
 *
 */
@SuppressWarnings("serial")
public class Calculator extends JFrame implements ActionListener,KeyListener{
	/** 显示框  */
	private static JTextField show = new JTextField("0");
	/** 按钮名称数组 */
	private static final String btnsName[] = {"清空","退格","x²","xⁿ","sin","cos","tan","sqrt","10ⁿ","log","Exp","/","∏",
												"7","8","9","+","n!","4","5","6","-","±","1","2","3","*","Mod",
												".","0","ln","="};
	/** 创建按钮 */
	private static JButton btns[] = new JButton[btnsName.length];
	/** 存储点击按钮的名称 */
	private static String btnNameNow = null;
	/** 存储计算数据*/
	private static ArrayList<String> data = new ArrayList<String>();
	/**数据计算完成标志*/
	boolean equalSign = false;
	
	public Calculator() {
		super("计算器");
		setVisible(true);
		setBounds(200, 200, 600, 390);//窗口默认打开位置和尺寸
		setLayout(new BorderLayout(4,4));
		add(show,BorderLayout.NORTH);
		show.setEnabled(false);
		show.setHorizontalAlignment(JTextField.RIGHT);//设置内容居右显示
		show.setBackground(Color.WHITE);
		/** 容器1，放置按钮*/
		JPanel p1 = new JPanel();
		add(p1,BorderLayout.CENTER);
		p1.setBackground(Color.white);
		p1.setLayout(new BorderLayout(4,4));
		/** 容器2，放置清空和退格按钮*/
		JPanel p2 = new JPanel();
		p2.setLayout(new GridLayout(1, 2,4,4));
		p2.setBackground(Color.white);
		p1.add(p2,BorderLayout.NORTH);
		for(int i=0;i<2;i++) {
			btns[i] = new JButton(btnsName[i]);
			btns[i].setBackground(Color.RED);
			btns[i].addActionListener(this);
			p2.add(btns[i]);
		}
		/** 容器3，放置其它按钮 */
		JPanel p3 = new JPanel();
		p3.setLayout(new GridLayout(6, 5,4,4));
		p3.setBackground(Color.WHITE);
		p1.add(p3,BorderLayout.CENTER);
		for(int i=2;i<btnsName.length;i++) {
			btns[i] = new JButton(btnsName[i]);
			switch (btns[i].getText()) {
				case "0":
				case "1":
				case "2":
				case "3":
				case "4":
				case "5":
				case "6":
				case "7":
				case "8":
				case "9":
					btns[i].setBackground(Color.cyan);
					break;
				default:
					btns[i].setBackground(Color.white);
					break;
			}
			btns[i].addActionListener(this);
			p3.add(btns[i]);
		}
		addKeyListener(this);
	}
	public static void main(String[] args) {
		Calculator calculator = new Calculator();
		calculator.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		btnNameNow = ((JButton)(e.getSource())).getText();
		dealButton(btnNameNow);
		this.requestFocus();
	}
	/**按钮处理方法*/
	void dealButton(String btnNameNow) {
		switch (btnNameNow) {
		case "0":
		case "1":
		case "2":
		case "3":
		case "4":
		case "5":
		case "6":
		case "7":
		case "8":
		case "9":
			dealNumber();
			break;
		case "清空":
			dealClear();
			break;
		case "退格":
			dealBackspace();
			break;
		case ".":
			dealPoint();
			break;
		case "1/x":
			dealReciprocal();
			break;
		case "+":
		case "-":
		case "*":
		case "/":
		case "xⁿ":
		case "Mod":
			dealOperator();
			break;
		case "sin":
			dealSin();
			break;
		case "cos":
			dealCos();
			break;
		case "sqrt":
			dealSqrt();
			break;
		case "±":
			dealNegative();
			break;
		case "=":
			dealEqual();
			break;
		case "x²":
			dealSquare();
			break;
		case "10ⁿ":
			dealTen_any_square();
			break;
		case "log":
			dealLog();
			break;
		case "Exp":
			dealExp();
			break;
		case "∏":
			dealRatio();
			break;
		case "n!":
			dealFactorial();
			break;
		case "ln":
			dealLn();
			break;
		case "tan":
			dealTan();
			break;
		default:
			JOptionPane.showMessageDialog(this,"输入字符不合法！");
			break;
		}
	}
	/**cos 处理方法*/
	void dealCos() {
		int size = data.size();
		switch(size) {
		case 0://null
			//do nothing
			break;
		case 1://num1
			double num1 = Double.parseDouble(data.get(0));
			num1 = Math.cos(num1);
			data.set(0, String.valueOf(num1));
			show.setText(data.get(0));
			break;
		case 2://num1 op
			//do nothing
			break;
		case 3://num1 op num2
			double num2 = Double.parseDouble(data.get(2));
			num2 = Math.cos(num2);
			data.set(2, String.valueOf(num2));
			show.setText(data.get(0) + data.get(1) + data.get(3));
			break;
		}
	}
	/**tan处理方法*/
	void dealTan() {
		int size = data.size();
		switch(size) {
		case 0://null
			//do nothing
			break;
		case 1://num1
			double num1 = Double.parseDouble(data.get(0));
			num1 = Math.tan(num1);
			data.set(0, String.valueOf(num1));
			show.setText(data.get(0));
			break;
		case 2://num1 op
			//do nothing
			break;
		case 3://num1 op num2
			double num2 = Double.parseDouble(data.get(2));
			num2 = Math.tan(num2);
			data.set(2, String.valueOf(num2));
			show.setText(data.get(0) + data.get(1) + data.get(3));
			break;
		}
	}
	/**ln处理方法*/
	void dealLn() {
		int size = data.size();
		switch(size) {
		case 0://null
			//do nothing
			break;
		case 1://num1
			double num1 = Double.parseDouble(data.get(0));
			if(num1<0) {
				JOptionPane.showMessageDialog(this,"负数不能做Ln运算！");
			}
			else {
				num1 = Math.log(num1)/Math.log(Math.E);
				data.set(0, String.valueOf(num1));
				show.setText(data.get(0));
			}
			break;
		case 2://num1 op
			//do nothing
			break;
		case 3://num1 op num2
			double num2 = Double.parseDouble(data.get(2));
			if(num2<0) {
				JOptionPane.showMessageDialog(this,"负数不能做Ln运算！");
			}
			else {
				num2 = Math.log(num2)/Math.log(Math.E);
				data.set(2, String.valueOf(num2));
				show.setText(data.get(0) + data.get(1) + data.get(3));
			}
			break;
		}
	}
	/**n!处理方法*/
	void dealFactorial() {
		int size = data.size();
		switch(size) {
		case 0://null
			//do nothing
			break;
		case 1://num1
			double num1 = Double.parseDouble(data.get(0));
			if(num1<0) {
				JOptionPane.showMessageDialog(this,"负数不能做阶乘运算！");
			}
			else if( (num1 - (int)num1)!=0 ){
				JOptionPane.showMessageDialog(this,"小数不能做阶乘运算！");
			}
			else {
				num1 = (int)num1;
				BigInteger b = new BigInteger("1");
				for(int a=1;a<=num1;a++) {
					BigInteger x = new BigInteger(String.valueOf(a));
					b = b.multiply(x);
				}
				data.set(0, String.valueOf(b));
				show.setText(data.get(0));
			}
			break;
		case 2://num1 op
			//do nothing
			break;
		case 3://num1 op num2
			double num2 = Double.parseDouble(data.get(2));
			if(num2<0) {
				JOptionPane.showMessageDialog(this,"负数不能做阶乘运算！");
			}
			else if( (num2 - (int)num2)!=0 ){
				JOptionPane.showMessageDialog(this,"小数不能做阶乘运算！");
			}
			else {
				num1 = (int)num2;
				BigInteger b = new BigInteger("1");
				for(int a=1;a<=num1;a++) {
					BigInteger x = new BigInteger(String.valueOf(a));
					b = b.multiply(x);
				}
				data.set(2, String.valueOf(b));
				show.setText(data.get(0) + data.get(1) + data.get(2));
			}
			break;
		}
	}
	/**∏处理方法*/
	void dealRatio(){
		int size = data.size();
		switch(size) {
		case 0://null
			data.add(String.valueOf(Math.PI));
			show.setText(data.get(0));
			break;
		case 1://num1
			data.set(0, String.valueOf(Double.parseDouble(data.get(0)) * Math.PI ));
			show.setText(data.get(0));
			break;
		case 2://num1 op
			data.add(String.valueOf(Math.PI));
			show.setText(data.get(0) + data.get(1) + data.get(2));
			break;
		case 3://num1 op num2
			data.set(0, String.valueOf(Double.parseDouble(data.get(2)) * Math.PI ));
			show.setText(data.get(0) + data.get(1) + data.get(2));
			break;
		}
	}
	/**Exp处理方法*/
	void dealExp() {
		int size = data.size();
		switch(size) {
		case 0://null
			data.add(String.valueOf(Math.E));
			show.setText(data.get(0));
			break;
		case 1://num1
			data.set(0, String.valueOf(Double.parseDouble(data.get(0)) * Math.E ));
			show.setText(data.get(0));
			break;
		case 2://num1 op
			data.add(String.valueOf(Math.E));
			show.setText(data.get(0) + data.get(1) + data.get(2));
			break;
		case 3://num1 op num2
			data.set(0, String.valueOf(Double.parseDouble(data.get(2)) * Math.E ));
			show.setText(data.get(0) + data.get(1) + data.get(2));
			break;
		}
	}
	/**log处理方法*/
	void dealLog() {
		int size = data.size();
		switch(size) {
		case 0://null
			//do nothing
			break;
		case 1://num1
			double num1 = Double.parseDouble(data.get(0));
			if(num1<0) {
				JOptionPane.showMessageDialog(this,"负数不能做Log运算！");
			}
			else {
				num1 = Math.log(num1);
				data.set(0, String.valueOf(num1));
				show.setText(data.get(0));
			}
			break;
		case 2://num1 op
			//do nothing
			break;
		case 3://num1 op num2
			double num2 = Double.parseDouble(data.get(2));
			if(num2<0) {
				JOptionPane.showMessageDialog(this,"负数不能做Log运算！");
			}
			else {
				num2 = Math.log(num2);
				data.set(2, String.valueOf(num2));
				show.setText(data.get(0) + data.get(1) + data.get(3));
			}
			break;
		}
	}
	/**10ⁿ处理方法*/
	void dealTen_any_square(){
		int size = data.size();
		switch(size) {
		case 0://null
			//do nothing
			break;
		case 1://num1
			double num1 = Double.parseDouble(data.get(0));
			String str1 = String.valueOf(Math.pow(10, num1));
			data.set(0, str1);
			show.setText(data.get(0));
			break;
		case 2://num1 op
			//do nothing
			break;
		case 3://num1 op num2
			double num2 = Double.parseDouble(data.get(2));
			String str2 = String.valueOf(Math.pow(10, num2));
			data.set(2, str2);
			show.setText(data.get(0) + data.get(1) + data.get(2));
			break;
		}
	}
	/**x²处理方法*/
	void dealSquare(){
		int size = data.size();
		switch(size) {
		case 0://null
			//do nothing
			break;
		case 1://num1
			double num1 = Double.parseDouble(data.get(0));
			String str1 = String.valueOf(Math.pow(num1, 2));
			data.set(0, str1);
			show.setText(data.get(0));
			break;
		case 2://num1 op
			//do nothing
			break;
		case 3://num1 op num2
			double num2 = Double.parseDouble(data.get(2));
			String str2 = String.valueOf(Math.pow(num2, 2));
			data.set(2, str2);
			show.setText(data.get(0) + data.get(1) + data.get(2));
			break;
		}
	}
	/**=处理方法*/
	void dealEqual() {
		int size = data.size();
		switch(size) {
		case 0://null
			//do nothing
			break;
		case 1://num1
			//do nothing
			break;
		case 2://num1 op
			//do nothing
			break;
		case 3://num1 op num2
			equalSign = true;
			double num1 = Double.parseDouble(data.get(0));
			double num2 = Double.parseDouble(data.get(2));
			String str = data.get(1);
			dataOperation(num1,num2,str);
			show.setText(data.get(0));
			break;
		}
	}
	/**±处理方法*/
	void dealNegative() {
		int size = data.size();
		switch(size) {
		case 0://null
			//do nothing
			break;
		case 1://num1
			String str1 = data.get(0);
			if(str1.indexOf("-")==-1) {
				str1 = "-"+str1;
				data.set(0, str1);
				show.setText(data.get(0));
			}
			else {
				str1 = str1.substring(1,str1.length()-1);
				data.set(0, str1);
				show.setText(data.get(0));
			}
			break;
		case 2://num1 op
			//do nothing
			break;
		case 3://num1 op num2
			String str2 = data.get(2);
			if(str2.indexOf("-")==-1) {
				str2 = "-"+str2;
				data.set(2, str2);
				show.setText(data.get(0) + data.get(1) + "(" + data.get(2) +")");
			}
			break;
		}
	}
	/**sqrt处理方法*/
	void dealSqrt() {
		int size = data.size();
		switch(size) {
		case 0://null
			//do nothing
			break;
		case 1://num1
			double num1 = Double.parseDouble(data.get(0));
			if(num1<0) {
				JOptionPane.showMessageDialog(this,"负数不能做开方运算！");
			}
			else {
				num1 = Math.sqrt(num1);
				data.set(0, String.valueOf(num1));
				show.setText(data.get(0));
			}
			break;
		case 2://num1 op
			//do nothing
			break;
		case 3://num1 op num2
			double num2 = Double.parseDouble(data.get(2));
			if(num2<0) {
				JOptionPane.showMessageDialog(this,"负数不能做开方运算！");
			}
			else {
				num2 = Math.sqrt(num2);
				data.set(2, String.valueOf(num2));
				show.setText(data.get(0) + data.get(1) + data.get(3));
			}
			break;
		}
	}
	/**sin处理方法*/
	void dealSin() {
		int size = data.size();
		switch(size) {
		case 0://null
			//do nothing
			break;
		case 1://num1
			double num1 = Double.parseDouble(data.get(0));
			num1 = Math.sin(num1);
			data.set(0, String.valueOf(num1));
			show.setText(data.get(0));
			break;
		case 2://num1 op
			//do nothing
			break;
		case 3://num1 op num2
			double num2 = Double.parseDouble(data.get(2));
			num2 = Math.sin(num2);
			data.set(2, String.valueOf(num2));
			show.setText(data.get(0) + data.get(1) + data.get(3));
			break;
		}
	}

	/**运算符按钮处理方法*/
	void dealOperator() {
		equalSign = false;
		int size = data.size();
		switch(size) {
		case 0://null
			//do nothing
			break;
		case 1://num1
			data.add(btnNameNow);
			show.setText(data.get(0) + data.get(1));
			break;
		case 2://num1 op
			//do nothing
			break;
		case 3://num1 op num2
			double num1 = Double.parseDouble(data.get(0));
			double num2 = Double.parseDouble(data.get(2));
			String str = data.get(1);
			dataOperation(num1,num2,str);
			data.add(btnNameNow);
			show.setText(data.get(0) + data.get(1));
			break;
		}
	}
	/**数字按钮处理方法*/
	void dealNumber() {
		if(equalSign) {
			data.clear();
		}
		equalSign = false;
		int size = data.size();
		switch(size) {
		case 0://null
			if(btnNameNow.equals("0")) {
				data.add(btnNameNow);
			}
			else {
				data.add(btnNameNow);
				show.setText(data.get(0));
			}
			break;
		case 1://num1
			String str1 = data.get(0);
			if(str1.equals("0")&&btnNameNow.equals("0")) {
				//do nothing
			}
			else {
				str1+=btnNameNow;
				data.set(0, str1);
				show.setText(data.get(0));
			}
			break;
		case 2://num1 op
			data.add(btnNameNow);
			show.setText(data.get(0) + data.get(1) + data.get(2));
			break;
		case 3://num1 op num2
			String str2 = data.get(2);
			str2+=btnNameNow;
			data.set(2, str2);
			show.setText(data.get(0) + data.get(1) + data.get(2));
			break;
		}
	}
	/**
	 * 数据计算处理方法
	 * @param num1 double型数据1
	 * @param num2 double型数据2
	 * @param str String型运算符
	 */
	void dataOperation(double num1,double num2,String str) {
		switch(str) {
		case "+":
			data.clear();
			data.add(String.valueOf(num1+num2));
			break;
		case "-":
			data.clear();
			data.add(String.valueOf(num1-num2));
			break;
		case "*":
			data.clear();
			data.add(String.valueOf(num1*num2));
			break;
		case "/":
			if(num2==0) {
				JOptionPane.showMessageDialog(this,"0不能作除数！");
			}
			else {
				data.clear();
				data.add(String.valueOf(num1/num2));
			}
			break;
		case "xⁿ":
			data.clear();
			data.add(String.valueOf(Math.pow(num1, num2)));
			break;
		case "Mod":
			data.clear();
			data.add(String.valueOf(num1%num2));
			break;
		}
	}
	/**倒数处理方法*/
	void dealReciprocal() {
		int size = data.size();
		switch(size) {
		case 0://null
			//do nothing
			break;
		case 1://num1
			double num1 = Double.parseDouble(data.get(0));
			if(num1==0) {
				JOptionPane.showMessageDialog(this,"0没有倒数！");
			}
			else {
				double num2 = 1/num1;
				data.set(0, String.valueOf(num2));
				show.setText(data.get(0));
			}
			break;
		case 2://num1 op
			//do nothing
			break;
		case 3://num1 op num2
			double num3 = Double.parseDouble(data.get(2));
			if(num3==0) {
				JOptionPane.showMessageDialog(this,"0没有倒数！");
			}
			else {
				double num4 = 1/num3;
				data.set(0, String.valueOf(num4));
				show.setText(data.get(0) + data.get(1) + data.get(2));
			}
			break;
		}
	}
	/**小数点处理方法*/
	void dealPoint() {
		int size = data.size();
		switch(size) {
		case 0://null
			data.add("0.");
			show.setText("0.");
			break;
		case 1://num1
			String str1 = data.get(0);
			if(str1.indexOf(".")==-1) {
				str1+=".";
				data.set(0, str1);
				show.setText(data.get(0));
			}
			else {
				//do nothing
			}
			break;
		case 2://num1 op
			data.add("0.");
			show.setText(data.get(0) + data.get(1) + data.get(2));
			break;
		case 3://num1 op num2
			String str2 = data.get(0);
			if(str2.indexOf(".")==-1) {
				str2+=".";
				data.set(2, str2);
				show.setText(data.get(0) + data.get(1) + data.get(2));
			}
			else {
				//do nothing
			}
			break;
		}
	}
	/**退格方法*/
	void dealBackspace() {
		int size = data.size();
		switch(size) {
		case 0://null
			//do nothing
			break;
		case 1://num1
			String str1 = data.get(0);
			if(str1.length()==1) {
				show.setText("0");
			}
			else {
				str1 = str1.substring(0 ,str1.length()-1);
				data.set(0, str1);
				show.setText(data.get(0));
			}
			break;
		case 2://num1 op
			data.remove(1);
			show.setText(data.get(0));
			break;
		case 3://num1 op num2
			String str2 = data.get(2);
			if(str2.length()==1) {
				data.remove(2);
				show.setText(data.get(0) + data.get(1));
			}
			else {
				str2 = str2.substring(0 ,str2.length()-1);
				data.set(2, str2);
				show.setText(data.get(0) + data.get(1) + data.get(2));
			}
			break;
		}
	}
	/**清空方法*/
	void dealClear() {
		data.clear();
		show.setText("0");
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		btnNameNow = String.valueOf(arg0.getKeyChar());
		dealButton(btnNameNow);
	}
}