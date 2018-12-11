package LicenseManagement;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

class JInsert extends JPanel implements ActionListener{
	JTextField name;
	JTextField birth;
	JComboBox gender; // 추가
	JComboBox marry; // 추가
	DAO dao = new DAO();
	DTO dto = new DTO();
	public JInsert() {
		JLabel label1 = new JLabel("이름");
		JLabel label2 = new JLabel("생년월일");
		JLabel label3 = new JLabel("성별");
		JComboBox<String> jcb1 = new JComboBox<String>();
		jcb1.addItem("남자");		
		jcb1.addItem("여자");
		JLabel label4 = new JLabel("결혼유무");
		JComboBox<String> jcb2 = new JComboBox<String>();
		jcb2.addItem("기혼");		
		jcb2.addItem("미혼");
		JButton jb = new JButton("등록");	
		name = new JTextField(4);
		birth = new JTextField(6);
		gender = new JComboBox();
		marry = new JComboBox();
						
		add(label1);add(name);add(label2);add(birth);
		add(label3);add(jcb1);add(label4);add(jcb2);add(jb);
				
		jb.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		
		dto.setName(name.getText());
		dto.setBirth(birth.getText());
		dto.setGender((String)gender.getSelectedItem()); // 수정 필요..?
		dto.setMarry((String)marry.getSelectedItem()); // 수정 필요..?
		dao.insert(dto);
		name.setText("");
		birth.setText("");
		gender.setToolTipText(""); // 수정 필요..?
		marry.setToolTipText(""); // 수정 필요..?
	}
}
class JSelect extends JPanel implements ActionListener{
	JTextField jtf;
	DefaultTableModel dfm;
	DAO dao;
	DTO dto;
	public JSelect() {
		JLabel jl = new JLabel("회원번호 입력 : ");
		jtf = new JTextField(4);
		JButton jb = new JButton("조회");
		
		String[] head = {"이름", "생년월일", "성별", "결혼유무", "등록일"}; // 수정
		dfm = new DefaultTableModel(null, head );
		JTable jt = new JTable(dfm);
		JScrollPane jsp = new JScrollPane(jt);	
		
		
		add(jl);add(jtf);add(jb);add(jsp);
		jb.addActionListener(this);
			}
	public void actionPerformed(ActionEvent e) {
		dao = new DAO();
		int no = Integer.parseInt(jtf.getText());
					
		dto = dao.select(no);
		dfm.setRowCount(1);
		dfm.setValueAt(dto.getName(), 0, 0);
		dfm.setValueAt(dto.getBirth(), 0, 1);
		dfm.setValueAt(dto.getGender(), 0, 2); // 추가
		dfm.setValueAt(dto.getMarry(), 0, 3); // 추가
		dfm.setValueAt(dto.getEnroll(), 0, 4); // 수정
		
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
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}	

}
