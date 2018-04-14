package com.lagou.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;
import com.lagou.dao.ComDao;
import com.lagou.dao.UserDao;
import com.lagou.entity.Company;
import com.lagou.entity.MenuLeft;
import com.lagou.entity.Pager;
import com.lagou.entity.Position;
import com.lagou.entity.Result;
import com.lagou.entity.Resume;
import com.lagou.entity.User;
import com.lagou.util.StringUtil;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/user/*")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ComDao cd = new ComDao();
	private UserDao userDao = new UserDao();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String T = StringUtil.getTypeStr(uri);
		if ("registerclick".equals(T)) {// 用戶注册
			registerclick(request, response);
		} else if ("regDo".equals(T)) {// 用戶注册的动作
			regDo(request, response);
		} else if ("val".equals(T)) {// 验证码
			val(request, response);
		} else if ("land".equals(T)) {// 用户登陆
			land(request, response);
		} else if ("loginDo".equals(T)) {// 用户登陆的动作
			loginDo(request, response);
		} else if ("cancel".equals(T)) {// 用户退出登陆
			cancel(request, response);
		}

		else if ("resume".equals(T)) {// 用户查看简历
			request.getRequestDispatcher("/WEB-INF/jsp/Seeyourresume.jsp").forward(request, response);
		} else if ("resumeDetail".equals(T)) {// 公司查看简历
			resumeDetail(request, response);
		} else if ("changeResume".equals(T)) {// 用户修改简历的界面
			request.getRequestDispatcher("/WEB-INF/jsp/changeResume.jsp").forward(request, response);
		} else if ("changeResumeDo".equals(T)) {// 用户修改简历的动作
			changeResumeDo(request, response);
		} else if ("sendResume".equals(T)) {// 投简历
			sendResume(request, response);
		} else if ("cancelSendResume".equals(T)) {// 取消投简历
			cancelSendResume(request, response);
		} else if ("index".equals(T)) {// 主页
			index(request, response);
		} else if ("search".equals(T)) {// 模糊搜索职位名称,公司名字,公司地址
			search(request, response);
		} else if ("positionJump".equals(T)) {// 职位分页的跳转动作
			response.sendRedirect(request.getContextPath() + "/user/search?pageNow=" + request.getParameter("pageNow")
					+ "&keyword=" + request.getParameter("keyword"));
		} else if ("companyDetail".equals(T)) {// 用户查看公司的详细信息
			companyDetail(request, response);
		} else if ("positionDetail".equals(T)) {// 用户查看职位的详细信息
			/**
			 * 公司和用户查看职位信息的准备数据不同,用户要判断是否已经投递过该公司
			 */
			positionDetail(request, response);
		} else if ("companyDetail_positions".equals(T)) {// 用户查看公司发布了哪些职位
			companyDetail_positions(request, response);
		} else if ("companyDetail_positions_jump".equals(T)) {// 用户查看公司发布了哪些职位的分页的跳转
			response.sendRedirect(request.getContextPath() + "/user/companyDetail_positions?pageNow="
					+ request.getParameter("pageNow") + "&com_id=" + request.getParameter("com_id"));
		}
	 else if ("searchCompany".equals(T)) {//主界面跳转到公司页面
		 searchCompany(request, response);
	 	}
	 else if ("seeCominfo".equals(T)) {//用户查看公司的信息
		 seeCominfo(request, response);
	 }
	 else if ("changeCity".equals(T)) {//用户切换城市
		 changeCity(request, response);
	 }
	 else if ("userSearch".equals(T)) {
		 userSearch(request, response);
	 }
	 else if ("indexAjaxCom".equals(T)) {//ajax实现公司的显示
		 indexAjaxCom(request, response);
	 }
	 else if ("user_verifyRegUsername".equals(T)) {//ajax实现登陆注册的账号密码验证
		 user_verifyRegUsername(request, response);
	 }
	 else if ("user_verifyRegPassword".equals(T)) {
		 user_verifyRegPassword(request, response);
	 }
	 else if ("user_verifyRegCheckNum".equals(T)) {
		 user_verifyRegCheckNum(request, response);
	 }
	 else if ("user_verifyLoginUsername".equals(T)) {
		 user_verifyLoginUsername(request, response);
	 }
	}
	

	private void user_verifyLoginUsername(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		String name = request.getParameter("name");

		String v = "";
		String red = "0";
		if ("".equals(name) || name == null) {
			v += "x 请输入账号！";
		} else {
			boolean flag = userDao.verify(name);
			if (flag) {
				v += "√";
				red = "1";
			} else {
				v += "没有此账号";
			}
		}
		String[] msg = { v, red };
		try {
			response.getWriter().write(new Gson().toJson(msg));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void user_verifyRegCheckNum(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		String val = request.getParameter("val");
		HttpSession session = request.getSession();
		String VAL = (String) session.getAttribute("VAL");
		String msg="0";
		
		if (VAL.equalsIgnoreCase(val)) {
			msg="1";
		}
		try {
			response.getWriter().write(new Gson().toJson(msg));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void user_verifyRegPassword(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		String name=request.getParameter("pass");

		String v = "";
		String red = "0";
		if ("".equals(name) || name == null) {
			v += "x 密码不能为空！";
		} else {
			if (checkLength(name)) {
				v += "√";
				red = "1";
			} else {
				v += "密码必须由数字和字母组成，并且要同时含有数字和字母，且长度要在8-16位之间";
			}
		}
		String[] msg = { v, red };
		try {
			response.getWriter().write(new Gson().toJson(msg));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void user_verifyRegUsername(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		String name = request.getParameter("name");
		String v = "";
		String red = "0";
		if ("".equals(name) || name == null) {
			v += "x 账号不能为空！";
		} else {
			boolean flag = userDao.verify(name);
			if (flag) {
				v += "x 已经有此账号";
			} else {
				if (checkLength(name)) {
					v += "√";
					red = "1";
				} else {
					v += "账号必须由数字和字母组成，并且要同时含有数字和字母，且长度要在8-16位之间";
				}
			}
		}
		String[] msg = { v, red };
		try {
			response.getWriter().write(new Gson().toJson(msg));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public boolean checkLength(String param) {
		String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
		return param.matches(regex);
	}
	private void indexAjaxCom(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=utf-8");
		
		String address="";
		address=request.getParameter("city");
		
		ArrayList<Company> companies = cd.getEightCompanies(address,8);
		Result<ArrayList<Company>> result=new Result<>();
		result.setStatus(1);
		result.setMsg("成功获取公司信息");
		result.setData(companies);
		Gson gson=new Gson();
		String data=gson.toJson(result);
		response.getWriter().write(data);
	}
	
	/**
	 * 普通用户搜索公司
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void userSearch(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
String keyword = request.getParameter("keyword");
		
		request.setAttribute("keyword", keyword);
		String pageNow = request.getParameter("pageNow");
		if (!StringUtil.isNotNull(pageNow)) {
			pageNow = "1";
		}

		Pager<Company> pager = new Pager<>();
		pager.setPageNow(Integer.parseInt(pageNow));
		pager.setPageSize(12);
		pager.setTotalCount(userDao.userSearchCount(keyword));
		pager.setPageCount((pager.getTotalCount() - 1) / pager.getPageSize() + 1);
		pager = userDao.userSearch(keyword, pager);
		request.setAttribute("pager", pager);

		request.getRequestDispatcher("/WEB-INF/jsp/userSearch.jsp").forward(request, response);
	}
	
	/**
	 * 主页[用户未登录的时候,默认显示前面几条数据，用户登陆之后依据用户的意向显示内容]
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 先拿到用户的求职意向
		 */
		String category = "";
		User user=(User)(request.getSession().getAttribute("CUR_USER"));
		if(user!=null) {
			category=user.getResume().getR_category();
		}
		
		
		/**
		 * 依据用户的求职意向，如果用户已经登陆那就筛选处与该意向相近的职位，否则显示最新的9条职位信息
		 * 而且显示的职位均是通过审核的
		 */
		ArrayList<Position> positions = cd.getNinePositions(category,9);
		request.setAttribute("positions", positions);
		
		
		/**
		 * 把链接拿出来
		 */
		ArrayList<MenuLeft> links=userDao.getLinks();
		request.setAttribute("links", links);
		
		request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
	}
	
	private void changeCity(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String city=request.getParameter("select");
		request.getSession().setAttribute("city", city);
		response.sendRedirect(request.getContextPath()+"/user/index");
	}

	/**
	 * 用户查看公司的信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void seeCominfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int com_id=Integer.parseInt(request.getParameter("com_id"));
		Company com=cd.getOneCompanyById(com_id);
		request.setAttribute("com", com);
		request.getRequestDispatcher("/WEB-INF/jsp/seeCominfo.jsp").forward(request, response);
	}

	/**
	 * 公司搜索
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */

	private void searchCompany(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		ArrayList<Company> companies = cd.getEightCompanies("全国",8);
		request.setAttribute("companies", companies); 
		request.getRequestDispatcher("/WEB-INF/jsp/searchCompany.jsp").forward(request, response);
	}

	/**
	 * 公司查看简历的信息
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void resumeDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int r_id = Integer.parseInt(request.getParameter("r_id"));
		Resume resume = cd.getResumeById(r_id);
		request.setAttribute("resume", resume);
		request.getRequestDispatcher("/WEB-INF/jsp/seeTheResume.jsp").forward(request, response);
	}

	/**
	 * 用户取消投递简历
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void cancelSendResume(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int p_id = Integer.parseInt(request.getParameter("p_id"));
		int r_id = ((User) (request.getSession().getAttribute("CUR_USER"))).getResume().getR_id();
		userDao.cancelSendResume(p_id, r_id);
		response.sendRedirect(request.getContextPath() + "/user/positionDetail?p_id=" + p_id);
	}

	/**
	 * 用户退出登陆
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void cancel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("CUR_USER", null);
		request.getRequestDispatcher("/WEB-INF/jsp/land.jsp").forward(request, response);

	}

	/**
	 * 用户修改简历的动作
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private void changeResumeDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("CUR_USER");
		Resume resume = new Resume();
		// 确定上传后的文件存放地址
		String path = getServletContext().getRealPath("/files");
		String content = "";
		String fileName = "";
		try {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = upload.parseRequest(request);
			// 遍历参数
			for (int i = 0; i < items.size(); i++) {
				FileItem item = items.get(i);
				if (item.isFormField()) { // 如果表单是普通表单数据
					// ===不能动结束
					String fieldName = item.getFieldName();
					content = item.getString();
					content = StringUtil.rmGarbled(content);
					if ("r_name".equals(fieldName)) {
						resume.setR_name(content);
					} else if ("r_degree".equals(fieldName)) {
						resume.setR_degree(content);
					} else if ("sex".equals(fieldName)) {
						resume.setSex(content);
					} else if ("email".equals(fieldName)) {
						resume.setEmail(content);
					} else if ("edu_exp".equals(fieldName)) {
						resume.setEdu_exp(content);
					} else if ("project_exp".equals(fieldName)) {
						resume.setProject_exp(content);
					} else if ("self_avalue".equals(fieldName)) {
						resume.setSelf_avalue(content);
					} else if ("r_category".equals(fieldName)) {
						resume.setR_category(content);
					}
				} else { // 如果是文件数据
							// =====不能动开始
					String name = item.getName();// 文件的真实名字
					if (StringUtil.isNotNull(name)) {
						InputStream is = item.getInputStream();
						// uuid 128
						String uuid = UUID.randomUUID().toString();
						// 拼接一个新的文件名
						fileName = uuid + name.substring(name.lastIndexOf("."));
						path = path + File.separator + fileName;
						OutputStream os = new FileOutputStream(path);
						byte[] bytes = new byte[1024];
						int len = 0;
						while ((len = is.read(bytes)) != -1) {
							os.write(bytes, 0, len);
						}
						// ====不能动结束
						os.close();
					} else {
						fileName = user.getResume().getR_header();
					}
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		resume.setR_header(fileName);
		userDao.updateResume(resume, user.getU_id());
		/**
		 * 简历修改了之后还要更新当前session里面的用户简历表
		 */
		user.setResume(userDao.getResumeById(user.getU_id()));
		request.getRequestDispatcher("/WEB-INF/jsp/Seeyourresume.jsp").forward(request, response);

	}

	/**
	 * 用户注册页面
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void registerclick(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/registerclick.jsp").forward(request, response);
	}

	/**
	 * 用户注册的动作
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void regDo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String u_account = request.getParameter("u_account");
		String u_password = request.getParameter("u_password");
		String val = request.getParameter("val");
		HttpSession session = request.getSession();
		String VAL = (String) session.getAttribute("VAL");
		if (!StringUtil.isNotNull(u_account)) {
			response.sendRedirect(request.getContextPath() + "/user/registerclick");
			return;
		}
		if (!StringUtil.isNotNull(u_password)) {
			response.sendRedirect(request.getContextPath() + "/user/registerclick");
			return;
		}
		if (VAL.equalsIgnoreCase(val)) {
			User user = new User(u_account, u_password);
			boolean is = userDao.reg(user);
			if (is) {
				int u_id = userDao.getIdByAcount(u_account);
				userDao.insertDefaultResume(u_id);
				Resume resume = userDao.getResumeById(u_id);
				user.setResume(resume);
				response.sendRedirect(request.getContextPath() + "/user/land");
			} else {
				response.sendRedirect(request.getContextPath() + "/user/registerclick");
			}
		}
	}

	/**
	 * 生成验证码
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void val(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("image/jpeg");
		BufferedImage image = new BufferedImage(80, 20, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = image.createGraphics();
		int r = (int) (Math.random() * 127) + 127;
		int g = (int) (Math.random() * 127) + 127;
		int b = (int) (Math.random() * 127) + 127;
		g2d.setColor(new Color(r, g, b));
		g2d.fillRect(0, 0, 80, 20);
		int r1 = (int) (Math.random() * 127);
		int g1 = (int) (Math.random() * 127);
		int b1 = (int) (Math.random() * 127);
		g2d.setColor(new Color(r1, g1, b1));
		g2d.setFont(new Font("微软雅黑", Font.ITALIC, 18));
		String content = StringUtil.getRandomStr(4);
		HttpSession session = request.getSession();
		session.setAttribute("VAL", content);
		g2d.drawString(content, 1, 18);
		g2d.dispose();
		ImageIO.write(image, "jpg", response.getOutputStream());
	}

	/**
	 * 用户登陆页面
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void land(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/land.jsp").forward(request, response);
	}

	/**
	 * 用户登陆的动作
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void loginDo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String u_account = request.getParameter("u_account");
		String u_password = request.getParameter("u_password");
		HttpSession session = request.getSession();
		if (!StringUtil.isNotNull(u_account)) {
			response.sendRedirect(request.getContextPath() + "/user/land");
			return;
		}
		if (!StringUtil.isNotNull(u_password)) {
			response.sendRedirect(request.getContextPath() + "/user/land");
			return;
		}
		User user = userDao.login(u_account, u_password);
		if (user != null) {
			session.setAttribute("CUR_USER", user);
			response.sendRedirect(request.getContextPath() + "/user/index");
		} else {
			response.sendRedirect(request.getContextPath() + "/user/land");
		}
	}

	/**
	 * 用户查看公司的详细信息
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void companyDetail(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String com_id = request.getParameter("com_id");
		Company companyClicked = cd.getOneCompanyById(Integer.parseInt(com_id));
		request.setAttribute("companyClicked", companyClicked);
		request.getRequestDispatcher("/WEB-INF/jsp/companyClicked.jsp").forward(request, response);
	}

	/**
	 * 用户查看工地发布的职位列表
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void companyDetail_positions(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		/**
		 * 准备公司信息
		 */
		String com_id = request.getParameter("com_id");
		Company companyClicked = cd.getOneCompanyById(Integer.parseInt(com_id));
		request.setAttribute("companyClicked", companyClicked);
		/**
		 * 准备职位信息，职位存在于pager中
		 */
		String pageNow = request.getParameter("pageNow");
		if (!StringUtil.isNotNull(pageNow)) {
			pageNow = "1";
		}
		Pager<Position> pager = new Pager<>();
		pager.setPageNow(Integer.parseInt(pageNow));
		pager.setPageSize(10);
		pager.setTotalCount(cd.getCountPositionByCompany(companyClicked.getCom_id()));
		pager.setPageCount((pager.getTotalCount() - 1) / pager.getPageSize() + 1);
		pager = cd.getPositionsByCompany(pager, companyClicked.getCom_id());
		request.setAttribute("pager", pager);
		request.getRequestDispatcher("/WEB-INF/jsp/companyDetail_positions.jsp").forward(request, response);
	}

	/**
	 * 用户投简历
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void sendResume(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int p_id = Integer.parseInt(request.getParameter("p_id"));
		int r_id = ((User) (request.getSession().getAttribute("CUR_USER"))).getResume().getR_id();
		boolean flag = userDao.sendResume(p_id, r_id);
		String sendOk = "no";
		if (flag) {
			sendOk = "yes";
		}
		request.setAttribute("sendOk", sendOk);
		response.sendRedirect(request.getContextPath() + "/user/positionDetail?p_id=" + p_id);
	}

	/**
	 * 用户查看职位的具体信息
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void positionDetail(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String sendOk = "no";
		int p_id = Integer.parseInt(request.getParameter("p_id"));
		Position positionClicked = cd.getOnePositionById(p_id);
		request.setAttribute("positionClicked", positionClicked);
		User user=(User) (request.getSession().getAttribute("CUR_USER"));
		if(user!=null) {
			int r_id = user.getResume().getR_id();
			/**
			 * 查询是否已经投递过该职位
			 */
			boolean hasSendResume = userDao.hasSendResume(p_id, r_id);
			
			if (hasSendResume) {
				sendOk = "yes";
			}
		}
		String pString = (String) (request.getAttribute("sendOk"));
		if (StringUtil.isNotNull(pString)) {
			sendOk = pString;
		}
		request.setAttribute("sendOk", sendOk);
		request.getRequestDispatcher("/WEB-INF/jsp/positionClicked.jsp").forward(request, response);
	}

	/**
	 * 搜索关键字
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		request.setAttribute("keyword", keyword);
		String pageNow = request.getParameter("pageNow");
		if (!StringUtil.isNotNull(pageNow)) {
			pageNow = "1";
		}
		Pager<Position> pager = new Pager<>();
		pager.setPageNow(Integer.parseInt(pageNow));
		pager.setPageSize(10);
		pager.setTotalCount(cd.getCountPosition(keyword));
		pager.setPageCount((pager.getTotalCount() - 1) / pager.getPageSize() + 1);
		pager = cd.searchByKeyword(keyword, pager);
		request.setAttribute("pager", pager);
		request.getRequestDispatcher("/WEB-INF/jsp/search.jsp").forward(request, response);
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
