package com.lagou.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lagou.dao.AdminDao;
import com.lagou.entity.Admin;
import com.lagou.entity.Company;
import com.lagou.entity.Pager;
import com.lagou.entity.Position;
import com.lagou.util.StringUtil;

@WebServlet("/admin/*")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AdminDao adminDao = new AdminDao();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String action = StringUtil.getTypeStr(uri);
		if ("login".equals(action)) {// 管理员登陆
			login(request, response);
		} else if ("loginDo".equals(action)) {// 管理员登陆动作
			loginDo(request, response);
		} else if ("showCom".equals(action)) {// 显示公司界面
			showCom(request, response);
		} else if ("showPosition".equals(action)) {// 显示职位界面
			showPosition(request, response);
		} else if ("reviewCompany".equals(action)) {// 审核公司动作
			reviewCompany(request, response);
		} else if ("reviewPosition".equals(action)) {// 审核职位动作
			reviewPosition(request, response);
		} else if ("searchPosition".equals(action)) {// 搜索职位
			searchedPosition(request, response);
		} else if ("searchCompany".equals(action)) {// 搜索公司
			searchedCompany(request, response);
		} else if ("".equals(action)) {
			loginDo(request, response);
		} else if ("quit".equals(action)) {// 退出管理员登陆
			quit(request, response);
		}

	}

	/**
	 * 显示职位界面
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void showPosition(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Pager<Position> pagerPosition = new Pager<>();
		pagerPosition.setPageSize(12);
		initCurrentPage(pagerPosition, "currentPositionPage", request, response);
		adminDao.serachPagerPosition(pagerPosition);
		request.setAttribute("pagerPosition", pagerPosition);
		request.getRequestDispatcher("/WEB-INF/jsp/showPosition.jsp").forward(request, response);
	}

	/**
	 * 退出管理员登陆
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void quit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("ADMIN");
		response.sendRedirect(request.getContextPath() + "/admin/login");

	}

	/**
	 * 搜索公司
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void searchedCompany(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String company = request.getParameter("searchedCompany");
		request.getSession().setAttribute("COM_KEY", company);
		Pager<Company> comPager = new Pager<>();
		initCurrentPage(comPager, "currentPage", request, response);
		adminDao.serachCompany(comPager, company);
		request.setAttribute("comPager", comPager);
		request.getRequestDispatcher("/WEB-INF/jsp/searchCompanyAdmin.jsp").forward(request, response);
	}

	/**
	 * 搜索职位
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void searchedPosition(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String position = request.getParameter("searchedPosition");
		Pager<Position> posiPager = new Pager<>();
		initCurrentPage(posiPager, "currentPage", request, response);
		adminDao.serachPosition(posiPager, position);
		request.setAttribute("posiPager", posiPager);
		request.setAttribute("KEY", position);
		request.getRequestDispatcher("/WEB-INF/jsp/searchPositionAdmin.jsp").forward(request, response);
	}

	/**
	 * 审核职位
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void reviewPosition(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int p_id = Integer.parseInt(request.getParameter("p_id"));
		int action = Integer.parseInt(request.getParameter("status"));
		adminDao.reviewPosition(p_id, action);
		showPosition(request, response);
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/adminLogin.jsp").forward(request, response);
	}

	/**
	 * 管理员登陆
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void loginDo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String account;
		String password;
		Admin admin = null;
		HttpSession session = request.getSession();
		if (session.getAttribute("ADMIN") == null) {
			account = request.getParameter("account");
			password = request.getParameter("password");
			admin = adminDao.login(account, password);
			session.setAttribute("ADMIN", admin);
		}
		if (admin != null) {
			response.sendRedirect(request.getContextPath() + "/admin/showCom");
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/login");
		}
	}

	/**
	 * 跳转到审核页面
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void showCom(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Pager<Company> pagerCompany = new Pager<>();
		pagerCompany.setPageSize(12);
		// 初始化pagerCompany
		initCurrentPage(pagerCompany, "currentCompanyPage", request, response);
		adminDao.serachPagerCompany(pagerCompany);
		request.setAttribute("pagerCompany", pagerCompany);
		request.getRequestDispatcher("/WEB-INF/jsp/showCom.jsp").forward(request, response);
	}

	/**
	 * 页码处理
	 * 
	 * @param pager
	 * @param cPage
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("rawtypes")
	private void initCurrentPage(Pager pager, String cPage, HttpServletRequest request, HttpServletResponse response) {
		// 获取“当前页”参数； (第一次访问当前页为null)
		String currPage = request.getParameter(cPage);
		if (currPage == null) {
			currPage = "1"; // 第一次访问，设置当前页为1;
		}
		// 转换
		int currentPage = Integer.parseInt(currPage);
		// 设置当前页参数
		pager.setPageNow(currentPage);
	}

	/**
	 * 审核公司能否发布职位
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void reviewCompany(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		int com_id = Integer.parseInt(request.getParameter("com_id"));
		int action = Integer.parseInt(request.getParameter("status"));
		adminDao.reviewCompany(com_id, action);
		showCom(request, response);
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
