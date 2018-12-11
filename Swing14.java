package gui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

class JComboBoxTest extends JFrame implements ItemListener{
	JLabel jl;
	
	JComboBoxTest(){
		ImageIcon ii = new ImageIcon("./images/banana.jpg");
		jl = new JLabel(ii);

		Container container = getContentPane(); //jframe이 container의 객체를 반환받아와서 
		container.setLayout(new FlowLayout()); //왼쪽에서 오른쪽으로 자동 가운데정렬 
		
		container.add(jl); //필드를 먼저 추가 
		
		//콤보박스 객체 생성 및 항목(Item) 추가
		JComboBox<String> jcb = new JComboBox<String>();//자료형이 generic으로 5버전부터 변경됨 
		jcb.addItem("banana");		
		jcb.addItem("apple");
		jcb.addItem("cherry");
		jcb.addItem("grape");
		jcb.addItem("pear");
		
		container.add(jcb);
		
		jcb.addItemListener(this);
		
		setTitle("콤보박스 실습"); 
		setSize(350, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setVisible(true);
		
	}
	
	@Override //ItemListener에 있는 추상메소드인 itemStateChanged를 overriding
	public void itemStateChanged(ItemEvent e) {
		// 1. 선택된 항목의 이름을 문자열 변수로 받음.
		String fruit = (String) e.getItem();
		jl.setIcon(new ImageIcon("images/"+fruit+".jpg"));
	}
	
}

public class Swing14 {
	/* JComboBox 클래스 */
	
	public static void main(String[] args) {
		new JComboBoxTest();
	}
}
