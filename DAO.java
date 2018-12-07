package LicenseManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;

class DAO {
	BasicDataSource ds;
	
	public DAO() {
		ds = new BasicDataSource();
		
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
	    ds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
	    ds.setUsername("scott");
	    ds.setPassword("tiger");
	      
	    ds.setInitialSize(5);
	}
	public void createTable() {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			String sql = " ";
;
			pstmt = con.prepareStatement(sql);
			int result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public DTO select(int no){
		DTO dto = new DTO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			String sql = "select * from member where no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
										
			dto.setNo(rs.getInt("no"));
			dto.setName(rs.getString("name"));
			dto.setBirth(rs.getString("birth"));
			dto.setEnroll(rs.getString("enroll"));
					
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) { rs.close(); }
				if(pstmt != null) { pstmt.close(); }
				if(con != null)  { con.close(); }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return dto;
	}
	
	public void insert(DTO dto) {
		Connection con = null;
		String sql = "insert into member values(member_seq.nextVal, ?, ?)";
		PreparedStatement pstmt  = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getBirth());
			
			
			int result = pstmt.executeUpdate();
			
			if(result == 1) {
				System.out.println("데이터 저장 성공.");
			} else {
				System.out.println("데이터 저장 실패.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) { pstmt.close(); }
				if(con != null)  { con.close(); }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update(DTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update member set name=?, birth=?, enroll=? where no=?";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getBirth());
			pstmt.setString(3, dto.getEnroll());
			pstmt.setInt(4, dto.getNo());
			
			int result = pstmt.executeUpdate();
			
			if(result == 1) {
				System.out.println("데이터 수정 성공.");
			} else {
				System.out.println("데이터 수정 실패.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) { pstmt.close(); }
				if(con != null)  { con.close(); }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void delete(int no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "delete from customer where no = ?";
		
		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			int result = pstmt.executeUpdate();
			
			if(result == 1) {
				System.out.println("회원 삭제 성공.");
			} else {
				System.out.println("회원 삭제 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) { pstmt.close(); }
				if(con != null)  { con.close(); }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
}