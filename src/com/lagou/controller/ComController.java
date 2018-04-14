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
import com.lagou.entity.Company;
import com.lagou.entity.Position;
import com.lagou.entity.Resume;
import com.lagou.util.StringUtil;

/**
 * Servlet implementation class ComController
 */
@WebServlet("/com/*")
public class ComController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ComDao cd = new ComDao();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ComController() {
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
		String T = StringUtil.getTypeStr(uri);
		if ("postJob".equals(T)) {// 发布职位
			request.getRequestDispatcher("/WEB-INF/jsp/postJobInterface.jsp").forward(request, response);
		} else if ("postJobInterfaceDo".equals(T)) {// 发布职位的工作
			postJobInterfaceDo(request, response);
		} else if ("revocationJob".equals(T)) {// 撤销职位
			revocationJob(request, response);
		} else if ("sendOffer".equals(T)) {
			sendOffer(request, response);
		} else if ("deleteResume".equals(T)) {
			deleteResume(request, response);
		} else if ("resumeDetails".equals(T)) {// 公司查看简历的信息页面
			resumeDetails(request, response);
		} else if ("seePositionDetail".equals(T)) {// 公司查看职位的信息页面
			seePositionDetail(request, response);
		} else if ("changePositionInfo".equals(T)) {// 公司修改职位的信息页面
			changePositionInfo(request, response);
		} else if ("changePositionInfoDo".equals(T)) {// 公司修改职位的信息动作
			changePositionInfoDo(request, response);
		} else if ("cancel".equals(T)) {// 公司退出登陆
			cancel(request, response);
		}
		// ---------------------------------------------------------------
		else if ("comreg".equals(T)) {// 显示公司注册页
			comreg(request, response);
		} else if ("comland".equals(T)) {// 显示公司登录页
			comland(request, response);
		} else if ("comregDo".equals(T)) {// 公司执行注册
			comregDo(request, response);
		} else if ("comlandDo".equals(T)) {// 公司执行登陆
			comlandDo(request, response);
		} else if ("val".equals(T)) {// 验证码
			val(request, response);
		} else if ("comindex".equals(T)) {// 显示公司主页
			comindex(request, response);
		} else if ("cominfo".equals(T)) {// 显示公司信息页面
			cominfo(request, response);
		} else if ("changeComInfo".equals(T)) {// 公司修改信息的页面
			request.getRequestDispatcher("/WEB-INF/jsp/changeComInfo.jsp").forward(request, response);
		} else if ("changeComInfoDo".equals(T)) {// 显示公司信息页面
			changeComInfoDo(request, response);
		}
		 else if ("com_verifyRegUsername".equals(T)) {//ajax实现登陆注册的账号密码验证
			 com_verifyRegUsername(request, response);
		 }
		 else if ("com_verifyRegPassword".equals(T)) {
			 com_verifyRegPassword(request, response);
		 }
		 else if ("com_verifyRegCheckNum".equals(T)) {
			 com_verifyRegCheckNum(request, response);
		 }
		 else if ("com_verifyLoginUsername".equals(T)) {
			 com_verifyLoginUsername(request, response);
		 }
	}

	
	private void com_verifyLoginUsername(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		String name = request.getParameter("name");

		String v = "";
		String red = "0";
		if ("".equals(name) || name == null) {
			v += "x 请输入账号！";
		} else {
			boolean flag = cd.verify(name);
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

	private void com_verifyRegCheckNum(HttpServletRequest request, HttpServletResponse response) {
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

	private void com_verifyRegPassword(HttpServletRequest request, HttpServletResponse response) {
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

	private void com_verifyRegUsername(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		String name = request.getParameter("name");
		String v = "";
		String red = "0";
		if ("".equals(name) || name == null) {
			v += "x 账号不能为空！";
		} else {
			boolean flag = cd.verify(name);
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
	
	
	/**
	 * 公司修改职位的信息
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void changePositionInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int p_id = Integer.parseInt(request.getParameter("p_id"));
		Position position = cd.getOnePositionById(p_id);
		request.setAttribute("position", position);
		request.getRequestDispatcher("/WEB-INF/jsp/changePositionInfo.jsp").forward(request, response);
	}

	/**
	 * 公司修改职位信息的动作
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void changePositionInfoDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int p_id = Integer.parseInt(request.getParameter("p_id"));
		int p_personNum = Integer.parseInt(request.getParameter("p_personNum"));
		double p_salary = Double.parseDouble(request.getParameter("p_salary"));
		String p_name = request.getParameter("p_name");
		String p_release = request.getParameter("p_release");
		String p_experience = request.getParameter("p_experience");
		String p_education = request.getParameter("p_education");
		String p_category = request.getParameter("p_category");
		String p_desc = request.getParameter("p_desc");
		Position position = new Position();
		position.setP_id(p_id);
		position.setP_name(p_name);
		position.setP_personNum(p_personNum);
		position.setP_salary(p_salary);
		position.setP_release(p_release);
		position.setP_experience(p_experience);
		position.setP_education(p_education);
		position.setP_category(p_category.split(","));
		position.setP_desc(p_desc);
		cd.updatePositionInfo(position);
		response.sendRedirect(request.getContextPath() + "/com/seePositionDetail?p_id=" + position.getP_id());
	}

	/**
	 * 公司查看职位的具体信息[因为用户查看职位的具体信息时的准备数据和和公司不同,而且要做投简历的动作,所以分开来写]
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void seePositionDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int p_id = Integer.parseInt(request.getParameter("p_id"));
		Position positionClicked = cd.getOnePositionById(p_id);
		request.setAttribute("positionClicked", positionClicked);
		request.getRequestDispatcher("/WEB-INF/jsp/seePositionDetail.jsp").forward(request, response);
	}

	/**
	 * 公司退出登陆
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void cancel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("com", null);
		request.getRequestDispatcher("/WEB-INF/jsp/comland.jsp").forward(request, response);
	}

	/**
	 * 修改公司信息
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private void changeComInfoDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Company loginCom = (Company) session.getAttribute("com");
		Company com = new Company();
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
					if ("com_name".equals(fieldName)) {
						com.setCom_name(content);
					} else if ("com_address".equals(fieldName)) {
						com.setCom_address(content);
					} else if ("com_reg".equals(fieldName)) {
						com.setCom_reg(content);
					} else if ("com_email".equals(fieldName)) {
						com.setCom_email(content);
					} else if ("com_sign".equals(fieldName)) {
						com.setSign(content);
					} else if ("com_type".equals(fieldName)) {
						com.setCom_type(content.split(","));
					} else if ("com_desc".equals(fieldName)) {
						com.setCom_desc(content);
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
						os.close();
					} else {
						fileName = loginCom.getCom_logo();
					}
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		com.setCom_logo(fileName);
		com.setCom_id(loginCom.getCom_id());
		cd.updateComInfo(com);
		/**
		 * 修改公司信息之后还要更新当前session里面的公司信息
		 */
		session.setAttribute("com", cd.getComById(loginCom.getCom_id()));
		request.getRequestDispatcher("/WEB-INF/jsp/cominfo.jsp").forward(request, response);
	}

	/**
	 * 显示公司信息页面
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void cominfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/cominfo.jsp").forward(request, response);

	}

	/**
	 * 显示公司主界面
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void comindex(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Company companyLogin = (Company) session.getAttribute("com");
		// 查询本公司发布的职位
		List<Position> position = cd.selectJobbyCid(companyLogin.getCom_id());
		// 查询本公司收到的简历
		List<Resume> resume = cd.selectResumebycid(companyLogin.getCom_id());
		request.setAttribute("position", position);
		request.setAttribute("resume", resume);
		request.getRequestDispatcher("/WEB-INF/jsp/comindex.jsp").forward(request, response);
	}

	/**
	 * 公司注册时的验证码
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void val(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 告诉浏览器响应的内容是一张图片
		response.setContentType("image/jpeg");
		// 1创建一个图片对象
		BufferedImage image = new BufferedImage(80, 20, BufferedImage.TYPE_INT_RGB);
		// 2 获取图片的画笔
		Graphics2D g2d = image.createGraphics();
		// 设置画笔的颜色
		int r = (int) (Math.random() * 127) + 127;
		int g = (int) (Math.random() * 127) + 127;
		int b = (int) (Math.random() * 127) + 127;
		g2d.setColor(new Color(r, g, b));
		// 填充一个矩形
		g2d.fillRect(0, 0, 80, 20);
		// 重新设置画笔的颜色
		int r1 = (int) (Math.random() * 127);
		int g1 = (int) (Math.random() * 127);
		int b1 = (int) (Math.random() * 127);
		g2d.setColor(new Color(r1, g1, b1));
		// 设置字体
		g2d.setFont(new Font("微软雅黑", Font.ITALIC, 18));
		// 确定绘制的内容
		String content = StringUtil.getRandomStr(4);
		// 将文字保存在session
		HttpSession session = request.getSession();
		session.setAttribute("VAL", content);
		// 写文字
		g2d.drawString(content, 1, 18);
		// 销毁画笔
		g2d.dispose();
		ImageIO.write(image, "jpg", response.getOutputStream());
	}

	/**
	 * 后台公司网页执行登录
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void comlandDo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String com_account = request.getParameter("com_account");
		String com_password = request.getParameter("com_password");
		HttpSession session = request.getSession();
		if (!StringUtil.isNotNull(com_account)) {
			response.sendRedirect(request.getContextPath() + "/com/comland");
			return;
		}
		if (!StringUtil.isNotNull(com_password)) {
			response.sendRedirect(request.getContextPath() + "/com/comland");
			return;
		}
		Company company = cd.comland(com_account, com_password);
		if (company != null) {
			session.setAttribute("com", company);
			response.sendRedirect(request.getContextPath() + "/com/comindex");
		} else {
			response.sendRedirect(request.getContextPath() + "/com/comland");
		}
	}

	/**
	 * 后台公司网页执行注册
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void comregDo(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String com_account = request.getParameter("com_account");
		String com_password = request.getParameter("com_password");
		String val = request.getParameter("val");

		HttpSession session = request.getSession();
		String VAL = (String) session.getAttribute("VAL");
		if (!StringUtil.isNotNull(com_account)) {
			response.sendRedirect(request.getContextPath() + "/com/comreg");
			return;
		}
		if (!StringUtil.isNotNull(com_password)) {
			response.sendRedirect(request.getContextPath() + "/com/comreg");
			return;
		}
		if (VAL.equalsIgnoreCase(val)) {
			Company company = new Company(com_account, com_password);
			boolean is = cd.comreg(company);

			if (is) {

				response.sendRedirect(request.getContextPath() + "/com/comland");
			} else {
				response.sendRedirect(request.getContextPath() + "/com/comreg");
			}
		}
	}

	/**
	 * 显示公司登陆页
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void comland(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/comland.jsp").forward(request, response);

	}

	/**
	 * 显示公司注册页面
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void comreg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/comreg.jsp").forward(request, response);

	}

	// -------------------------------------------------------------------------------
	/**
	 * 转到个人简历详情页面
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void resumeDetails(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int rid = Integer.parseInt(request.getParameter("rid"));
		Resume resume = cd.selectResumebyrid(rid);
		request.setAttribute("resume", resume);
		request.getRequestDispatcher("/WEB-INF/jsp/Seeyourresume.jsp").forward(request, response);
	}

	/**
	 * 删除已经收到的简历
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void deleteResume(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int rid = Integer.parseInt(request.getParameter("rid"));
		cd.deleteResume(rid);
		comindex(request, response);
	}

	/**
	 * 录用通知
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void sendOffer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int rid = Integer.parseInt(request.getParameter("rid"));
		cd.sendOffer(rid);
		comindex(request, response);

	}

	/**
	 * 撤销当前公司发布的职位
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void revocationJob(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int p_id = Integer.parseInt(request.getParameter("p_id"));
		cd.revocationJob(p_id);
		response.sendRedirect(request.getContextPath() + "/com/comindex");
	}

	/**
	 * 发布职位
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void postJobInterfaceDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Company companyLogin = (Company) session.getAttribute("com");
		String p_name = request.getParameter("pname");
		String p_desc = request.getParameter("pdesc");
		String p_release = StringUtil.getTimeStr();
		double p_salary = Double.parseDouble(request.getParameter("psalary"));
		String p_experience = request.getParameter("pexperience");
		String p_education = request.getParameter("peducation");
		int p_personNum = Integer.parseInt(request.getParameter("ppersonNum"));
		String p_category = request.getParameter("pcategory");
		Company company = new Company();
		company.setCom_id(companyLogin.getCom_id());
		Position postition = new Position();
		postition.setP_name(p_name);
		postition.setP_desc(p_desc);
		postition.setP_salary(p_salary);
		postition.setP_experience(p_experience);
		postition.setP_education(p_education);
		postition.setP_personNum(p_personNum);
		postition.setP_category(p_category.split(","));
		postition.setP_release(p_release);
		postition.setCompany(company);

		cd.sendPosition(postition);
		response.sendRedirect(request.getContextPath() + "/com/comindex");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
