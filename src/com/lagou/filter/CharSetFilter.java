package com.lagou.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import com.lagou.util.StringUtil;

/**
 * Servlet Filter implementation class CharSetFilter
 */
@WebFilter(value = { "/user/*", "/com/*", "/admin/*" })
public class CharSetFilter implements Filter {

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		if (req.getMethod().equalsIgnoreCase("get")) {
			// 设计模式 装饰模式
			req = new CharRquest(req);
		} else if (req.getMethod().equalsIgnoreCase("post")) {
			req.setCharacterEncoding("utf-8");
		}
		// pass the request along the filter chain
		chain.doFilter(req, res);

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

	class CharRquest extends HttpServletRequestWrapper {

		public CharRquest(HttpServletRequest request) {
			super(request);
		}

		@Override
		public String getParameter(String name) {
			String value = super.getParameter(name);
			if (StringUtil.isNotNull(value)) {
				try {
					value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
					
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			return value;
		}

	}

}
