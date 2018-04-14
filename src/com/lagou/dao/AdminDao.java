package com.lagou.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lagou.entity.Admin;
import com.lagou.entity.Company;
import com.lagou.entity.Pager;
import com.lagou.entity.Position;
import com.lagou.util.SqlUtil;

public class AdminDao {
	
	/**
	 * 管理员登陆
	 * 
	 * @param account
	 * @param password
	 * @return
	 */
	public Admin login(String account, String password) {
		String sql = " select * from admin  where a_account=? and a_password=md5(?) ";
		Connection conn = SqlUtil.connect();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Admin admin = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, account);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if (rs.next()) {
				admin = new Admin();
				admin.setAccount(rs.getString("a_account"));
				admin.setPassword(rs.getString("a_password"));
				admin.setID(rs.getInt("a_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(conn, pst, null);
		}
		return admin;
	}

	/**
	 * 按关键字查询一页的公司
	 * 
	 * @param pager
	 * @param key
	 * @return
	 */
	public Pager<Company> serachCompany(Pager<Company> pager, String key) {
		String sql = " select * from company  where com_name like ? limit ?,? ";
		String sql2 = "select count(*) c from company where com_name like ? ";
		PreparedStatement pst2 = null;
		ResultSet rs2 = null;
		Connection conn = SqlUtil.connect();
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Company> companies = new ArrayList<Company>();
		try {
			pst2 = conn.prepareStatement(sql2);
			pst2.setString(1, "%"+key+"%");
			rs2 = pst2.executeQuery();
			if (rs2.next()) {
				pager.setTotalCount(rs2.getInt("c"));
				pager.setPageCount((pager.getTotalCount() - 1) / pager.getPageSize() + 1);
			}
			pst = conn.prepareStatement(sql);
			pst.setString(1, "%"+key+"%");
			pst.setInt(2, (pager.getPageNow() - 1) * pager.getPageSize());
			pst.setInt(3, pager.getPageSize());
			rs = pst.executeQuery();
			while (rs.next()) {
				Company company = new Company();
				company.setCom_account(rs.getString("com_account"));
				company.setCom_address(rs.getString("com_address"));
				company.setCom_desc(rs.getString("com_desc"));
				company.setCom_email(rs.getString("com_email"));
				company.setCom_id(rs.getInt("com_id"));
				company.setCom_logo(rs.getString("com_logo"));
				company.setCom_name(rs.getString("com_name"));
				company.setCom_password(rs.getString("com_password"));
				company.setCom_reg(rs.getString("com_reg"));
				company.setCom_status(rs.getInt("com_status"));
				company.setCom_type(rs.getString("com_type").split(","));
				company.setSign(rs.getString("com_sign"));
				companies.add(company);
			}
			pager.setContent((ArrayList<Company>) companies);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(conn, pst, rs);
			SqlUtil.close(conn, pst2, rs2);
		}

		return pager;
	}

	/**
	 * 按注册时间查询一页的公司
	 * 
	 * @param pager
	 * @return
	 */
	public Pager<Company> serachPagerCompany(Pager<Company> pager) {
		String sql = " select * from company  order by com_reg desc limit ?,? ";
		String sql1 = " select count(*) c from company ";
		Connection conn = SqlUtil.connect();
		PreparedStatement pst = null;
		ResultSet rs = null;
		PreparedStatement pst1 = null;
		ResultSet rs1 = null;
		List<Company> companies = new ArrayList<Company>();
		try {
			pst1 = conn.prepareStatement(sql1);
			rs1 = pst1.executeQuery();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, (pager.getPageNow() - 1) * pager.getPageSize());
			pst.setInt(2, pager.getPageSize());
			rs = pst.executeQuery();
			if (rs1.next()) {
				pager.setTotalCount(rs1.getInt("c"));
				pager.setPageCount((pager.getTotalCount() - 1) / pager.getPageSize() + 1);
			}
			while (rs.next()) {
				Company company = new Company();
				company.setCom_account(rs.getString("com_account"));
				company.setCom_address(rs.getString("com_address"));
				company.setCom_desc(rs.getString("com_desc"));
				company.setCom_email(rs.getString("com_email"));
				company.setCom_id(rs.getInt("com_id"));
				company.setCom_logo(rs.getString("com_logo"));
				company.setCom_name(rs.getString("com_name"));
				company.setCom_reg(rs.getString("com_reg"));
				company.setCom_status(rs.getInt("com_status"));
				company.setCom_type(rs.getString("com_type").split(","));
				company.setSign(rs.getString("com_sign"));
				companies.add(company);
			}

			pager.setContent((ArrayList<Company>) companies);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(conn, pst1, rs1);
			SqlUtil.close(conn, pst, rs);

		}

		return pager;
	}

	/**
	 * 公司授权
	 * 
	 * @param com_id
	 */
	public boolean reviewCompany(int com_id, int status) {
		String sql = " update company set com_status=? where com_id=? ";
		Connection conn = SqlUtil.connect();
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, status);
			pst.setInt(2, com_id);
			flag = pst.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(conn, pst, null);
		}
		return flag;
	}

	/**
	 * 查询公司的状态
	 * 
	 * @param com_id
	 * @return
	 */
	public int searchCompanyStatus(int com_id) {
		int status = 2;// 2不是内置状态
		String sql = " select com_status from company  where com_id=? ";
		Connection conn = SqlUtil.connect();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, com_id);
			rs = pst.executeQuery();
			if (rs.next())
				status = rs.getInt("com_status");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(conn, pst, rs);
		}
		return status;
	}

	/**
	 * 下架职位和上架职位
	 * 
	 * @param p_id
	 * @param state
	 * @return
	 */
	public boolean reviewPosition(int p_id, int state) {
		String sql = " update position set p_state=? where p_id=? ";
		Connection conn = SqlUtil.connect();
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, state);
			pst.setInt(2, p_id);
			flag = pst.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(conn, pst, null);
		}
		return flag;
	}

	/**
	 * 查询职位的状态
	 * 
	 * @param p_id
	 * @return
	 */
	public int searchPositionStatus(int p_id) {
		int status = 2;// 2不是内置状态
		String sql = " select p_state from position where p_id=? ";
		Connection conn = SqlUtil.connect();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, p_id);
			rs = pst.executeQuery();
			if (rs.next())
				status = rs.getInt("p_state");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(conn, pst, rs);
		}
		return status;
	}

	
	/**
	 * 按关键字查询一页的职位
	 * @param pager
	 * @param key
	 * @return
	 */
	public Pager<Position> serachPosition(Pager<Position> pager, String key) {
		String sql = " select * from position  where p_name like ? limit ?,? ";
		String sql2 = "select count(*) c from position  where p_name like ?  ";
		Connection conn = SqlUtil.connect();
		PreparedStatement pst = null;
		ResultSet rs = null;
		PreparedStatement pst2 = null;
		ResultSet rs2 = null;
		List<Position> positons = new ArrayList<Position>();
		try {
			pst2 = conn.prepareStatement(sql2);
			pst2.setString(1, "%"+key+"%");
			rs2 = pst2.executeQuery();
			if (rs2.next()) {
				pager.setTotalCount(rs2.getInt("c"));
				pager.setPageCount((pager.getTotalCount() - 1) / pager.getPageSize() + 1);
			}

			pst = conn.prepareStatement(sql);
			pst.setString(1, "%"+key+"%");
			pst.setInt(2, (pager.getPageNow() - 1) * pager.getPageSize());
			pst.setInt(3, pager.getPageSize());
			rs = pst.executeQuery();
			while (rs.next()) {
				Position position = new Position();
				position.setP_category(rs.getString("p_category").split(","));
				position.setP_desc(rs.getString("p_desc"));
				position.setP_education(rs.getString("p_education"));
				position.setP_experience(rs.getString("p_experience"));
				position.setP_id(rs.getInt("p_id"));
				position.setP_name(rs.getString("p_name"));
				position.setP_personNum(rs.getInt("p_personNum"));
				position.setP_release(rs.getString("p_release"));
				position.setP_salary(rs.getDouble("p_salary"));
				position.setP_state(rs.getShort("p_state"));
				position.setP_status(rs.getInt("p_status"));
				position.setCompany((new ComDao().getOneCompanyById(rs.getInt("com_id"))));
				positons.add(position);
			}
			pager.setContent(positons);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(conn, pst, rs);
		}

		return pager;
	}

	/**
	 * 查询一页的职位
	 * 
	 * @param pager
	 * @return
	 */
	public Pager<Position> serachPagerPosition(Pager<Position> pager) {
		String sql = " select * from position  order by p_release desc limit ?,? ";
		String sql1 = " select count(*) c from position  ";
		Connection conn = SqlUtil.connect();
		PreparedStatement pst1 = null;
		ResultSet rs1 = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Position> positons = new ArrayList<Position>();
		try {
			pst1 = conn.prepareStatement(sql1);
			rs1 = pst1.executeQuery();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, (pager.getPageNow() - 1) * pager.getPageSize());
			pst.setInt(2, pager.getPageSize());
			rs = pst.executeQuery();
			if(rs1.next()) {
				pager.setTotalCount(rs1.getInt("c"));
				pager.setPageCount((pager.getTotalCount()-1)/pager.getPageSize()+1);
			}
			while (rs.next()) {
				Position position = new Position();
				position.setP_category(rs.getString("p_category").split(","));
				position.setP_desc(rs.getString("p_desc"));
				position.setP_education(rs.getString("p_education"));
				position.setP_experience(rs.getString("p_experience"));
				position.setP_id(rs.getInt("p_id"));
				position.setP_name(rs.getString("p_name"));
				position.setP_personNum(rs.getInt("p_personNum"));
				position.setP_release(rs.getString("p_release"));
				position.setP_salary(rs.getDouble("p_salary"));
				position.setP_state(rs.getShort("p_state"));
				position.setP_status(rs.getInt("p_status"));
				position.setCompany((new ComDao()).getOneCompanyById(rs.getInt("com_id")));
				positons.add(position);
			}
			pager.setContent(positons);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(conn, pst1, rs1);
			SqlUtil.close(conn, pst, rs);
		}

		return pager;
	}

}
