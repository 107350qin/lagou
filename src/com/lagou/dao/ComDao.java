package com.lagou.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lagou.entity.Company;
import com.lagou.entity.Pager;
import com.lagou.entity.Position;
import com.lagou.entity.Resume;
import com.lagou.entity.User;
import com.lagou.util.SqlUtil;
import com.lagou.util.StringUtil;

public class ComDao {
	
	/**
	 * 输入账号时判断是否存在这个账号
	 * @param name
	 * @return
	 */
	public boolean verify(String name) {
		Connection conn=SqlUtil.connect();
		PreparedStatement ps=null;
		ResultSet rs=null;
		 String sql="select * from company where com_account=?";
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
	
	/**
	 * 通过公司id查找公司
	 * @param com_id
	 * @return
	 */
	public Object getComById(int com_id) {
		Connection conn = SqlUtil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql="select * from company where com_id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, com_id);
			rs=ps.executeQuery();
			if(rs.next()) {
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
				return company;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			SqlUtil.close(conn, ps, rs);
		}
		return null;
	}
	

	/**
	 * 修改公司信息
	 * @param cid
	 * @return
	 */
	public  boolean updateComInfo(Company com) {
		boolean flag = false;
		Connection conn = SqlUtil.connect();
		PreparedStatement pst = null;
		String sql = "update company set com_logo=?,com_name=?,com_address=?,com_reg=?,com_type=?,com_desc=?,com_email=?,com_sign=? where com_id=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, com.getCom_logo());
			pst.setString(2, com.getCom_name());
			pst.setString(3, com.getCom_address());
			pst.setString(4, com.getCom_reg());
			
			pst.setString(5, StringUtil.arrayToString(com.getCom_type()));
			pst.setString(6, com.getCom_desc());
			pst.setString(7, com.getCom_email());
			pst.setString(8, com.getSign());
			pst.setInt(9,com.getCom_id());
			flag = pst.executeUpdate() >0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			SqlUtil.close(conn, pst, null);
		}
		return flag;
	}
	
	
	/**
	 * 公司登陆
	 * @param com_account
	 * @param com_password
	 * @return
	 */
	public  Company comland(String com_account,String com_password) {
		Connection conn = SqlUtil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Company company = null;
		String sql="select * from company where com_account=? and com_password=md5(?)";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, com_account);
			ps.setString(2, com_password);
			rs=ps.executeQuery();
			if(rs.next()) {
				company = new Company();
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
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			SqlUtil.close(conn, ps, rs);
		}
		return company;
	}
	
	/**   
	 *公司后台数据库注册 
	 */
	public  boolean comreg(Company company) {
		Connection conn = SqlUtil.connect();
		PreparedStatement ps= null;
		String sql="insert into company(com_account,com_password) values (?,md5(?))";
		boolean flag = false;
		try {
			ps= conn.prepareStatement(sql);
			ps.setString(1, company.getCom_account());
			ps.setString(2, company.getCom_password());
			flag = ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			SqlUtil.close(conn, ps, null);
		}
		return flag;
	}
	/**
	 * -------------------------------------------------------------------------
	 */
	/**
	 * 查询简历
	 * @param rid
	 * @return
	 */
	public  Resume selectResumebyrid(int rid){
		Connection conn = SqlUtil.connect();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Resume resume = null;
		String sql = "select * from resume where r_id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, rid);
			rs = pst.executeQuery();
			if(rs.next()) {
				resume = new Resume();
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
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			SqlUtil.close(conn, pst, rs);
		}
		return resume;
	}
	/**
	 * 通过简历id删除已经收到的简历
	 * @author Administrator
	 *
	 */
	public  boolean deleteResume(int rid) {
		boolean flag = false;
		Connection conn = SqlUtil.connect();
		PreparedStatement pst = null;
		String sql = "delete from position_resume where r_id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, rid);
			flag = pst.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			SqlUtil.close(conn, pst, null);
		}
		return flag;
	}
	
	/**
	 * 发送offer
	 * @return
	 */
	
	public  boolean sendOffer(int rid) {
		boolean flag = false;
		Connection conn = SqlUtil.connect();
		PreparedStatement pst = null;
		String sql = "UPDATE resume LEFT JOIN position_resume ON position_resume.r_id = resume.r_id LEFT JOIN position ON position.p_id = position_resume.p_id SET r_status = ? where resume.r_id =?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Company.PASS);
			pst.setInt(2, rid);
			flag = pst.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			SqlUtil.close(conn, pst, null);
		}
		return flag;
	}
	
	/**
	 * 查询本公司收到的简历
	 * @param cid
	 * @return
	 */
	public  List<Resume> selectResumebycid(int com_id){
		Connection conn = SqlUtil.connect();
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Resume> list = new ArrayList<>();
		String sql = "SELECT resume.*, USER .u_id FROM resume LEFT JOIN USER ON USER .u_id = resume.u_id LEFT JOIN position_resume ON resume.r_id = position_resume.r_id LEFT JOIN position ON position.p_id = position_resume.p_id LEFT JOIN company ON company.com_id = position.com_id WHERE position.com_id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, com_id);
			rs = pst.executeQuery();
			while (rs.next()) {
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
				User user= new User();
				user.setU_id(rs.getInt("u_id"));
				resume.setUser(user);
				list.add(resume);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			SqlUtil.close(conn, pst, rs);
		}
		return list;
		
	}
/**
 * 查询本公司已经发布的职位
 * @param cid
 * @return
 */
	public  List<Position> selectJobbyCid(int cid){
		Connection conn = SqlUtil.connect();
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Position> list = new ArrayList<>();
		String sql = "select * from position left join company on position.com_id = company.com_id where company.com_id =? ";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, cid);
			rs = pst.executeQuery();
			while(rs.next()) {
				Position position = new Position();
				position.setP_id(rs.getInt("p_id"));
				position.setP_name(rs.getString("p_name"));
				position.setP_desc(rs.getString("p_desc"));
				position.setP_release(rs.getString("p_release"));
				position.setP_salary(rs.getInt("p_salary"));
				position.setP_experience(rs.getString("p_experience"));
				position.setP_education(rs.getString("p_education"));
				position.setP_personNum(rs.getInt("p_personNum"));
				position.setP_state(rs.getInt("p_state"));
				position.setP_category(rs.getString("p_category").split(","));
				position.setP_status(rs.getInt("p_status"));
				Company company = new Company();
				company.setCom_id(rs.getInt("com_id"));
				position.setCompany(company);
				list.add(position);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			SqlUtil.close(conn, pst, rs);
		}
		return list;
	}
	
	/**
	 * 撤销职位之前先把该职位下面的所有与简历的关系数据删除
	 * @param cid
	 * @return
	 */
	public  boolean revocationJob(int p_id) {
		rmLinkPR(p_id);
		boolean flag = false;
		Connection conn = SqlUtil.connect();
		PreparedStatement pst = null;
		String sql = "delete from position where p_id =?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, p_id);
			flag = pst.executeUpdate() > 0 ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			SqlUtil.close(conn, pst, null);
		}
		return flag;
	}
	
	/**
	 * 把该职位下面的所有与简历的关系数据删除
	 * @param p_id
	 * @return
	 */
	private boolean rmLinkPR(int p_id) {
		boolean flag = false;
		Connection conn = SqlUtil.connect();
		PreparedStatement pst = null;
		String sql = "delete from position_resume where p_id =?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, p_id);
			flag = pst.executeUpdate() > 0 ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			SqlUtil.close(conn, pst, null);
		}
		return flag;
	}


	
	/**
	 * 发布职位
	 * @param postition
	 * @return
	 */
	public  boolean sendPosition(Position postition ) {
		boolean flag = false;
		Connection conn = SqlUtil.connect();
		PreparedStatement pst = null;
		String sql = "insert into position (p_id,p_name,p_desc,p_release,p_salary,p_experience,p_education,p_personNum,p_category,com_id) values (0,?,?,?,?,?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, postition.getP_name());
			pst.setString(2, postition.getP_desc());
			pst.setString(3, postition.getP_release());
			pst.setDouble(4, postition.getP_salary());
			pst.setString(5, postition.getP_experience());
			pst.setString(6, postition.getP_education());
			pst.setInt(7, postition.getP_personNum());
			pst.setString(8, StringUtil.arrayToString(postition.getP_category()));
			pst.setInt(9, postition.getCompany().getCom_id());
			flag = pst.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			SqlUtil.close(conn, pst, null);
		}
		return flag;
	}

	
	/**----------------------------------------------------
	 * 查询出主页面上的热门职位,如果已经登陆则按照用户的意向职位和地址进行搜索
	 * @param category 用户意向，未登录时为空
	 * @param address  用户地址，未登录时为空
	 * @return
	 */
	public ArrayList<Position> getNinePositions(String category,int x) {
		ArrayList<Position> positions=new ArrayList<>();
		Connection conn=SqlUtil.connect();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		String sql="select p.p_id,"
				+ "p.p_name,"
				+ "p.p_release,"
				+ "p.p_salary,"
				+ "p.p_experience,"
				+ "p.p_education,"
				+ "p.p_category,"
				+ "c.com_id,"
				+ "c.com_logo,"
				+ "c.com_name,"
				+ "c.com_type,"
				+ "c.com_address from position p "
				+ "left join company c "
				+ "on p.com_id=c.com_id where p.p_state=1 ";
		
		if(StringUtil.isNotNull(category)) {
			sql+=("and p.p_name like '"+"%"+category+"%");
			sql=sql.substring(0,sql.length());
			sql+="'";
		}
	
		sql+=" order by p.p_release desc limit "+x;
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				Position p=new Position();
				p.setP_id(rs.getInt("p_id"));
				p.setP_name(rs.getString("p_name"));
				p.setP_release(rs.getString("p_release"));
				p.setP_salary(rs.getDouble("p_salary"));
				p.setP_experience(rs.getString("p_experience"));
				p.setP_education(rs.getString("p_education"));
				p.setP_category(rs.getString("p_category").split(","));
				
				Company com=new Company();
				com.setCom_id(rs.getInt("com_id"));
				com.setCom_logo(rs.getString("com_logo"));
				com.setCom_name(rs.getString("com_name"));
				com.setCom_type(rs.getString("com_type").split(","));
				com.setCom_address(rs.getString("com_address"));
				
				p.setCompany(com);
				positions.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			SqlUtil.close(conn, ps, rs);
		}
	return positions;	
	}

	/**
	 * 查询出热门公司，默认查询前8条数据
	 * @return
	 */
	public ArrayList<Company> getEightCompanies(String address,int x) {
		ArrayList<Company> companies=new ArrayList<>();
		Connection conn=SqlUtil.connect();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		//还要查询这个公司正在招聘的职位数量
		String sql="select c.com_id,"
				+ "c.com_logo,"
				+ "c.com_name,"
				+ "c.com_type,"
				+ "c.com_sign "
				+ "from company c where c.com_status=1 ";
		if(!"全国".equals(address) && !"[切换城市]".equals(address) && StringUtil.isNotNull(address)) {
			sql+=" and c.com_address like '"+"%"+address+"%";
			sql=sql.substring(0,sql.length());
			sql+="'";
		}
		
		
		sql+=" limit "+x;
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				Company com=new Company();
				com.setCom_id(rs.getInt("com_id"));
				com.setCom_logo(rs.getString("com_logo"));
				com.setCom_name(rs.getString("com_name"));
				com.setCom_type(rs.getString("com_type").split(","));
				com.setSign(rs.getString("com_sign"));
				
				int num=getCountPositionByCompany(com.getCom_id());
				com.setPosistionNum(num);
				companies.add(com);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			SqlUtil.close(conn, ps, rs);
		}
	return companies;	
	}

	/**
	 * 通过公司id查询公司发布的职位有几个
	 * @param com_id 公司的id号
	 * @return
	 */
	public int getCountPositionByCompany(int com_id) {
		Connection conn=SqlUtil.connect();
		PreparedStatement ps=null;
		ResultSet rs=null;
		int x=0;
		//还要查询这个公司正在招聘的职位数量
		String sql="select count(*) num from position p left join company c on p.com_id=c.com_id where c.com_id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, com_id);
			rs=ps.executeQuery();
			if(rs.next()) {
				x=rs.getInt("num");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			SqlUtil.close(conn, ps, rs);
		}
	return x;	
	}

	/**
	 * 通过搜索框或者搜索侧边栏的关键字搜索职位
	 * @param keyword
	 * @return
	 */
	public Pager<Position> searchByKeyword(String keyword,Pager<Position> pager) {
		ArrayList<Position> positions=new ArrayList<>();
		Connection conn=SqlUtil.connect();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		String sql="select p.p_id,"
				+ "p.p_name,"
				+ "c.com_address,"
				+ "p.p_release,"
				+ "p.p_salary,"
				+ "p.p_experience,"
				+ "p.p_education,"
				+ "p.p_category,"
				+ "c.com_id,"
				+ "c.com_name,"
				+ "c.com_type,"
				+ "c.com_logo "
				+ "from position p "
				+ "left join company c "
				+ "on p.com_id=c.com_id "
				+ "where p.p_name like ? or "
				+ "c.com_name like ? or "
				+ "c.com_address like ? limit ?,?";
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, "%"+keyword+"%");
			ps.setString(2, "%"+keyword+"%");
			ps.setString(3, "%"+keyword+"%");
			ps.setInt(4, (pager.getPageNow()-1)*pager.getPageSize());
			ps.setInt(5, pager.getPageSize());
			rs=ps.executeQuery();
			while(rs.next()) {
				Position p=new Position();
				p.setP_id(rs.getInt("p_id"));
				p.setP_name(rs.getString("p_name"));
				p.setP_release(rs.getString("p_release"));
				p.setP_salary(rs.getDouble("p_salary"));
				p.setP_experience(rs.getString("p_experience"));
				p.setP_education(rs.getString("p_education"));
				p.setP_category(rs.getString("p_category").split(","));
				
				Company com=new Company();
				com.setCom_id(rs.getInt("com_id"));
				com.setCom_name(rs.getString("com_name"));
				com.setCom_type(rs.getString("com_type").split(","));
				com.setCom_logo(rs.getString("com_logo"));
				com.setCom_address(rs.getString("com_address"));
				
				p.setCompany(com);
				positions.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			SqlUtil.close(conn, ps, rs);
		}
		pager.setContent(positions);
		return pager;
	}

	/**
	 * 关键词搜索到的职位的数量
	 * @param keyword
	 * @return
	 */
	public int getCountPosition(String keyword) {
		Connection conn=SqlUtil.connect();
		PreparedStatement ps=null;
		ResultSet rs=null;
		int x=0;
		String sql="select count(*) num "
				+ "from position p "
				+ "left join company c "
				+ "on p.com_id=c.com_id "
				+ "where p.p_name like ? or "
				+ "c.com_name like ? or "
				+ "c.com_address like ?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, "%"+keyword+"%");
			ps.setString(2, "%"+keyword+"%");
			ps.setString(3, "%"+keyword+"%");
			rs=ps.executeQuery();
			if(rs.next()) {
				x=rs.getInt("num");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			SqlUtil.close(conn, ps, rs);
		}
		return x;
	}

	/**
	 * 被点击公司，通过公司的id查找到该公司
	 * @param com_id
	 * @return
	 */
	public Company getOneCompanyById(int com_id) {
		Connection conn=SqlUtil.connect();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select * from company where com_id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, com_id);
			rs=ps.executeQuery();
			if(rs.next()) {
				Company com=new Company();
				com.setCom_id(rs.getInt("com_id"));
				com.setCom_name(rs.getString("com_name"));
				com.setCom_address(rs.getString("com_address"));
				com.setCom_logo(rs.getString("com_logo"));
				com.setCom_reg(rs.getString("com_reg"));
				com.setCom_type(rs.getString("com_type").split(","));
				com.setCom_desc(rs.getString("com_desc"));
				com.setCom_email(rs.getString("com_email"));
				com.setSign(rs.getString("com_sign"));
				
				/**
				 * 该公司发布的职位有几个
				 */
				return com;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			SqlUtil.close(conn, ps, rs);
		}
		return null;
	}


	/**
	 * 通过职位id查找这个职位的信息以及相关的公司的信息
	 * @param p_id
	 * @return
	 */
	public Position getOnePositionById(int p_id) {
		Connection conn=SqlUtil.connect();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select * from position p left join company c on p.com_id=c.com_id where p_id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, p_id);
			rs=ps.executeQuery();
			if(rs.next()) {
				Position position=new Position();
				position.setP_id(rs.getInt("p_id"));
				position.setP_name(rs.getString("p_name"));
				position.setP_desc(rs.getString("p_desc"));
				position.setP_release(rs.getString("p_release"));
				position.setP_salary(rs.getDouble("p_salary"));
				position.setP_experience(rs.getString("p_experience"));
				position.setP_education(rs.getString("p_education"));
				position.setP_category(rs.getString("p_category").split(","));
				position.setP_personNum(rs.getInt("p_personNum"));
				
				Company com=new Company();
				com.setCom_id(rs.getInt("com_id"));
				com.setCom_name(rs.getString("com_name"));
				com.setCom_address(rs.getString("com_address"));
				com.setCom_logo(rs.getString("com_logo"));
				com.setCom_reg(rs.getString("com_reg"));
				com.setCom_type(rs.getString("com_type").split(","));
				com.setCom_desc(rs.getString("com_desc"));
				com.setCom_email(rs.getString("com_email"));
				com.setSign(rs.getString("com_sign"));
				position.setCompany(com);
				return position;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			SqlUtil.close(conn, ps, rs);
		}
		return null;
	}

	/**
	 * 公司发布的职位 分页
	 * @param pager
	 * @param com_id
	 * @return
	 */
	public Pager<Position> getPositionsByCompany(Pager<Position> pager,int com_id) {
		ArrayList<Position> positions=new ArrayList<>();
		Connection conn=SqlUtil.connect();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select * from position p left join company c on p.com_id=c.com_id where c.com_id=? limit ?,?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, com_id);
			ps.setInt(2, (pager.getPageNow()-1)*pager.getPageSize());
			ps.setInt(3, pager.getPageSize());
			rs=ps.executeQuery();
			while(rs.next()) {
				Position position=new Position();
				position.setP_id(rs.getInt("p_id"));
				position.setP_name(rs.getString("p_name"));
				position.setP_desc(rs.getString("p_desc"));
				position.setP_release(rs.getString("p_release"));
				position.setP_salary(rs.getDouble("p_salary"));
				position.setP_experience(rs.getString("p_experience"));
				position.setP_education(rs.getString("p_education"));
				position.setP_personNum(rs.getInt("p_personNum"));
				position.setP_category(rs.getString("p_category").split(","));
				positions.add(position);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			SqlUtil.close(conn, ps, rs);
		}
		pager.setContent(positions);
		return pager;
	}

	/**
	 * 通过简历id获得简历
	 * @param r_id
	 * @return
	 */
	public Resume getResumeById(int r_id) {
		Connection conn=SqlUtil.connect();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select * from resume where r_id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, r_id);
			rs=ps.executeQuery();
			if(rs.next()) {
				Resume resume=new Resume();
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
		}finally {
			SqlUtil.close(conn, ps, rs);
		}
		return null;
	}

	/**
	 * 更新职位信息
	 * @param position
	 * @return
	 */
	public boolean updatePositionInfo(Position position) {
		Connection conn=SqlUtil.connect();
		PreparedStatement ps=null;
		ResultSet rs=null;
		int x=0;
		String sql="update position set p_name=?,p_personNum=?,p_salary=?,p_release=?,p_experience=?,p_education=?,p_category=?,p_desc=? where p_id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, position.getP_name());
			ps.setInt(2, position.getP_personNum());
			ps.setDouble(3, position.getP_salary());
			ps.setString(4, position.getP_release());
			ps.setString(5, position.getP_experience());
			ps.setString(6, position.getP_education());
			ps.setString(7, StringUtil.arrayToString(position.getP_category()));
			ps.setString(8, position.getP_desc());
			ps.setInt(9, position.getP_id());
			x=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			SqlUtil.close(conn, ps, rs);
		}
		return x>0?true:false;
	}	
}