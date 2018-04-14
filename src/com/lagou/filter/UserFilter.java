package com.lagou.filter;
/**
 * 实现过滤器 
 * 实现一个接口
 * @author hchr
 *
 */

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/user/*")
public class UserFilter implements Filter {

	private List<String> list = new Vector<>();

	/**
	 * 销毁
	 */
	@Override
	public void destroy() {
		System.out.println("destory");
	}

	/**
	 * 执行过滤
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// 请求到达之前
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession();
		Object o = session.getAttribute("CUR_USER");
		String uri = request.getRequestURI();
		
		String contextPath = request.getContextPath();
		uri = uri.substring(contextPath.length());
		// 如果路径需要验证是否登录
		if (list.contains(uri)) {
			// 验证是否登录
			if (o == null) {
				response.sendRedirect(contextPath + "/user/land");
				return;
			}
		}
		response.setContentType("text/html;charset=utf-8");

		// 请求通过
		chain.doFilter(req, res);
		// 响应之前

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		list.add("/user/resume");
		list.add("/user/changeResume");
		list.add("/user/changeResumeDo");
		list.add("/user/cancelSendResume");
		list.add("/user/sendResume");
	}

}
