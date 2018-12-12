package LicenseManager;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

class JInsert extends JPanel implements ActionListener, ItemListener{
	JTextField name;
	JTextField birth;
	DAO dao = new DAO();
	DTO dto = new DTO();
	JFileChooser chs;
	JComboBox<String> jcb1;
//	JComboBox<String> jcb2 ;
	String gender;
	public JInsert() {
		
		JLabel label1 = new JLabel("이름");
		JLabel label2 = new JLabel("생년월일");
		JLabel label3 = new JLabel("성별");
		jcb1 = new JComboBox<String>();
		jcb1.addItem("");		
		jcb1.addItem("남자");		
		jcb1.addItem("여자");
//		JLabel label4 = new JLabel("결혼유무");
	/*	jcb2 = new JComboBox<String>();
		jcb2.addItem("기혼");		
		jcb2.addItem("미혼");*/
		JButton jb = new JButton("등록");	
		name = new JTextField(4);
		birth = new JTextField(6);
				
		chs= new JFileChooser();
		
		add(label1);add(name);add(label2);add(birth);
		add(label3);add(jcb1);/*add(label4);add(jcb2)*/;add(jb);
		
		jcb1.addItemListener(this);
		
		jb.addActionListener(this);
	}

	public void itemStateChanged(ItemEvent e) {
		gender = (String) e.getItem();
	}
	
	public void actionPerformed(ActionEvent e) {
//		String filepath;
//		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, GIF, PNG image", "jpg","gif","png");
//		chs.setFileFilter(filter);//파일 다이얼로그에 파일 필터 설정
//		//파일 다이얼로그 출력
//		int ret = chs.showOpenDialog(null);
//		if(ret!= JFileChooser.APPROVE_OPTION) {
//			JOptionPane.showMessageDialog(null, "파일이 선택되지 않았습니다.", "알림", JOptionPane.WARNING_MESSAGE);
//			return;
//		}else {//파일 선택후 열기버튼을 누른경우
//		filepath = chs.getSelectedFile().getPath();
//		}
//		System.out.println(filepath);
//		dto.setPhoto(filepath);
		dto.setGender(gender);
		dto.setName(name.getText());
		dto.setBirth(birth.getText());
		dao.insert(dto);
		name.setText("");
		birth.setText("");
	}

}

class JSelect extends JPanel implements ActionListener{
	JTextField jtf;
	DefaultTableModel dfm;
	DAO dao =new DAO();
	DTO dto;
	List<DTO> list = null;
	JLabel jl2;
	int list_len;
	public JSelect() {
		JLabel jl = new JLabel("회원번호 입력 : ");
		jtf = new JTextField(4);
		JButton jb = new JButton("조회");
		jl2= new JLabel("");
		String[] head = {"번호","이름"};
		dfm = new DefaultTableModel(null, head );
		JTable jt = new JTable(dfm);
		JScrollPane jsp = new JScrollPane(jt);
		
		list = dao.select();
		list_len = list.size();
		dfm.setRowCount(list_len);
		int num = 0;
		for(DTO dto: list) {
			
			dfm.setValueAt(dto.getNo(), num, 0);
			dfm.setValueAt(dto.getName(), num, 1);
			
			num++;
		}
		
		add(jl);add(jtf);add(jb);add(jl2);add(jsp);
		jb.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		
		try {
			int no = Integer.parseInt(jtf.getText());
			jtf.setText("");
			if(no <= list_len) {
				no--;
				dto = list.get(no);
				memberSelect ms	= new memberSelect(dto);
			}else {
				jl2.setText("회원번호를 확인해주세요.");
			}
			
		}catch (NumberFormatException e1) {
			jl2.setText("숫자만 입력하세요.");
			return;
		}
	}
}


class swing extends JFrame{
	public swing() {
		JTabbedPane jtp = new JTabbedPane();
		JInsert ji = new JInsert();
		JSelect js = new JSelect();
		
		jtp.addTab("회원등록", ji);
		jtp.addTab("회원조회", js);
		
		Container con = getContentPane();
		con.setLayout(new GridLayout(1,1));
		con.add(jtp);
		
		setTitle("회원관리 프로그램");
		setSize(600, 300);
		setLocation(200, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}	

}


class memberSelect extends JFrame{
	DefaultTableModel dfm;
	DTO dto;
	public memberSelect(DTO dto) {
		
		String[] head = {"항목", "정보"};
		dfm = new DefaultTableModel(null,head);
		JTable jt = new JTable(dfm);
		JScrollPane jsp = new JScrollPane(jt);
		
		dfm.setRowCount(5);
		dfm.setValueAt("번호",0, 0); dfm.setValueAt(dto.getNo(), 0, 1);
		dfm.setValueAt("이름",1, 0); dfm.setValueAt(dto.getName(), 1, 1);
		dfm.setValueAt("생년월일",2, 0); dfm.setValueAt(dto.getBirth(), 2, 1);
		dfm.setValueAt("등록일",3, 0); dfm.setValueAt(dto.getEnroll(), 3, 1);
		dfm.setValueAt("성별",4, 0); dfm.setValueAt(dto.getGender(), 4, 1);

		
		Container con = getContentPane();
		con.setLayout(new GridLayout(1,1));
		con.add(jsp);
		
		setTitle("회원정보");
		setLocation(600, 600);
		setResizable(false);
		setSize(300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}