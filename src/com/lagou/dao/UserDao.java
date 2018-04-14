package com.lagou.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lagou.entity.Company;
import com.lagou.entity.MenuLeft;
import com.lagou.entity.MenuRight;
import com.lagou.entity.Pager;
import com.lagou.entity.Resume;
import com.lagou.entity.User;
import com.lagou.util.SqlUtil;

public class UserDao {
	/**
	 * 普通用户通过字段模糊搜索公司数量
	 * 
	 * @param keyword
	 * @return
	 */
	public int userSearchCount(String keyword) {
		Connection conn = SqlUtil.connect();
		PreparedStatement pst = null;
		ResultSet rs = null;
		int count = 0;
		String sql = "select count(*) c from company where com_name like ? or com_type like ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, "%" + keyword + "%");
			pst.setString(2, "%" + keyword + "%");
			rs = pst.executeQuery();
			if (rs.next()) {
				count = rs.getInt("c");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			SqlUtil.close(conn, pst, rs);

		}
		return count;
	}

	/**
	 * 普通用户通过字段模糊搜索公司
	 * 
	 * @param keyword
	 * @return
	 */
	public Pager<Company> userSearch(String keyword, Pager<Company> pager) {
		Connection conn = SqlUtil.connect();
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Company> list = new ArrayList<>();
		String sql = "select * from company where com_name like ? or com_type like ? limit ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, "%" + keyword + "%");
			pst.setString(2, "%" + keyword + "%");
			pst.setInt(3, (pager.getPageNow() - 1) * pager.getPageSize());
			pst.setInt(4, pager.getPageSize());
			rs = pst.executeQuery();
			while (rs.next()) {
				Company company = new Company();
				company.setCom_id(rs.getInt("com_id"));
				company.setCom_account(rs.getString("com_account"));
				company.setCom_name(rs.getString("com_name"));
				company.setCom_address(rs.getString("com_address"));
				company.setCom_logo(rs.getString("com_logo"));
				company.setCom_status(rs.getInt("com_status"));
				company.setCom_reg(rs.getString("com_reg"));
				company.setCom_type(rs.getString("com_type").split(","));
				company.setCom_desc(rs.getString("com_desc"));
				company.setCom_email(rs.getString("com_email"));
				company.setSign(rs.getString("com_sign"));
				list.add(company);
			}
			pager.setContent(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			SqlUtil.close(conn, pst, rs);

		}
		return pager;
	}

	/**
	 * 普通用户注册
	 * 
	 * @param user
	 * @return
	 */
	public boolean reg(User user) {
		Connection conn = SqlUtil.connect();
		PreparedStatement ps = null;
		String sql = "insert into user values(0,?,md5(?))";
		boolean flag = false;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getU_account());
			ps.setString(2, user.getU_password());
			flag = ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(conn, ps, null);
		}
		return flag;
	}

	/**
	 * 普通用户登录
	 * 
	 * @param u_account
	 * @param u_account
	 *            z * @param u_password
	 * @return
	 */
	public User login(String u_account, String u_password) {
		Connection conn = SqlUtil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		String sql = "select * from user where u_account=? and u_password=md5(?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, u_account);
			ps.setString(2, u_password);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setU_id(rs.getInt("u_id"));
				user.setU_account(rs.getString("u_account"));
				user.setU_password(rs.getString("u_password"));

				Resume resume = getResumeById(user.getU_id());
				user.setResume(resume);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(conn, ps, rs);
		}
		return user;
	}

	/**
	 * 使用用户的id创建默认简历表
	 * 
	 * @param u_id
	 * @return
	 */
	public boolean insertDefaultResume(int u_id) {
		Connection conn = SqlUtil.connect();
		PreparedStatement ps = null;
		String sql = "insert resume(r_id,u_id) values(0,?)";
		int x = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, u_id);
			x = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(conn, ps, null);
		}
		return x > 0 ? true : false;
	}

	/**
	 * 通过用户的账户查找到id
	 * 
	 * @param u_account
	 * @return
	 */
	public int getIdByAcount(String u_account) {
		Connection conn = SqlUtil.connect();
		PreparedStatement ps = null;
		String sql = "select u_id from user where user.u_account=?";
		ResultSet rs = null;
		int u_id = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(u_account));
			rs = ps.executeQuery();
			if (rs.next()) {
				u_id = rs.getInt("u_id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(conn, ps, null);
		}
		return u_id;
	}

	/**
	 * 通过用户的id得到他的简历
	 * 
	 * @param u_id
	 * @return
	 */
	public Resume getResumeById(int u_id) {
		Connection conn = SqlUtil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from resume where u_id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, u_id);
			rs = ps.executeQuery();
			if (rs.next()) {
				Resume resume = new Resume();
				resume.setR_id(rs.getInt("r_id"));
				resume.setR_name(rs.getString("r_name"));
				resume.setR_header(rs.getString("r_header"));
				resume.setR_degree(rs.getString("r_degree"));
				resume.setEdu_exp(rs.getString("edu_exp"));
				resume.setProject_exp(rs.getString("project_exp"));
				resume.setSelf_avalue(rs.getString("self_avalue"));
				resume.setR_category(rs.getString("r_category"));
				resume.setSex(rs.getString("sex"));
				resume.setEmail(rs.getString("email"));
				return resume;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(conn, ps, rs);
		}
		return null;
	}

	/**
	 * 更新简历
	 * 
	 * @param resume
	 * @param u_id
	 * @return
	 */
	public boolean updateResume(Resume resume, int u_id) {
		Connection conn = SqlUtil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int x = 0;
		String sql = "update resume set r_name=?,r_header=?,r_degree=?,edu_exp=?,project_exp=?,self_avalue=?,r_category=?,sex=?,email=? where u_id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, resume.getR_name());
			ps.setString(2, resume.getR_header());
			ps.setString(3, resume.getR_degree());
			ps.setString(4, resume.getEdu_exp());
			ps.setString(5, resume.getProject_exp());
			ps.setString(6, resume.getSelf_avalue());
			ps.setString(7, resume.getR_category());
			ps.setString(8, resume.getSex());
			ps.setString(9, resume.getEmail());
			ps.setInt(10, u_id);
			x = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(conn, ps, rs);
		}
		return x > 0 ? true : false;
	}

	/**
	 * 投简历
	 * 
	 * @param p_id
	 * @param r_id
	 * @return
	 */
	public boolean sendResume(int p_id, int r_id) {
		Connection conn = SqlUtil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int x = 0;
		String sql = "insert position_resume values(0,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, p_id);
			ps.setInt(2, r_id);
			x = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(conn, ps, rs);
		}
		return x > 0 ? true : false;
	}

	/**
	 * 判断简历是否投果这个职位
	 * 
	 * @param p_id
	 * @param r_id
	 * @return
	 */
	public boolean hasSendResume(int p_id, int r_id) {
		Connection conn = SqlUtil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from position_resume where p_id=? and r_id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, p_id);
			ps.setInt(2, r_id);
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(conn, ps, rs);
		}
		return false;
	}

	/**
	 * 取消投递简历
	 * 
	 * @param p_id
	 * @param r_id
	 * @return
	 */
	public boolean cancelSendResume(int p_id, int r_id) {
		Connection conn = SqlUtil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int x = 0;
		String sql = "delete from position_resume where p_id=? and r_id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, p_id);
			ps.setInt(2, r_id);
			x = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(conn, ps, rs);
		}
		return x > 0 ? true : false;
	}

	/**
	 * 把动态的链接字段拿出来
	 * 
	 * @return
	 */
	public ArrayList<MenuLeft> getLinks() {
		ArrayList<MenuLeft> mls = new ArrayList<>();
		Connection conn = SqlUtil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from menu_left";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				MenuLeft ml = new MenuLeft();
				ml.setMl_id(rs.getInt("ml_id"));
				ml.setMl_title(rs.getString("ml_title"));
				ml.setMl_link(rs.getString("ml_link").split(","));
				ml.setList(getOneRightList(ml.getMl_id()));
				mls.add(ml);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(conn, ps, rs);
		}
		return mls;
	}

	/**
	 * 将右边的一堆dl以集合的形式拿出来
	 * 
	 * @param ml_id
	 * @return
	 */
	private ArrayList<MenuRight> getOneRightList(int ml_id) {
		ArrayList<MenuRight> mrs = new ArrayList<>();
		Connection conn = SqlUtil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from menu_right where ml_id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, ml_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				MenuRight mr = new MenuRight();
				mr.setMr_id(rs.getInt("mr_id"));
				mr.setMr_title(rs.getString("mr_title"));
				mr.setMr_link(rs.getString("mr_link").split(","));
				mrs.add(mr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(conn, ps, rs);
		}
		return mrs;
	}

	public boolean verify(String name) {
			Connection conn=SqlUtil.connect();
			PreparedStatement ps=null;
			ResultSet rs=null;
			 String sql="select * from user where u_account=?";
			 try {
				ps=conn.prepareStatement(sql);
				ps.setString(1, name);
				rs=ps.executeQuery();
				if(rs.next()) {
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				SqlUtil.close(conn, ps, rs);
			}
			return false;
		}
	
}
