package com.lagou.filter;

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

@WebFilter("/com/*")
public class ComFilter implements Filter {

	private List<String> list = new Vector<>();

	/**
	 * 销毁
	 */
	@Override
	public void destroy() {
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
		Object o = session.getAttribute("com");
		String uri = request.getRequestURI();

		String contextPath = request.getContextPath();
		uri = uri.substring(contextPath.length());
		// 如果路径需要验证是否登录
		if (list.contains(uri)) {
			// 验证是否登录
			if (o == null) {
				response.sendRedirect(contextPath + "/com/comland");
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
		list.add("/com/postJob");
		list.add("/com/postJobInterfaceDo");
		list.add("/com/revocationJob");
		list.add("/com/resumeDetails");
		list.add("/com/seePositionDetail");
		list.add("/com/changePositionInfo");
		list.add("/com/changePositionInfoDo");
		list.add("/com/cancel");
		list.add("/com/comindex");
		list.add("/com/changeComInfo");
		list.add("/com/changeComInfoDo");
	}

}
