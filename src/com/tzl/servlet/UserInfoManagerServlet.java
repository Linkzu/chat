package com.tzl.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tzl.dao.UsersDao;
import com.tzl.entity.Users;
import com.tzl.util.GeneralUtils;

/**
 * Servlet implementation class UserInfoManagerServlet
 */
public class UserInfoManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInfoManagerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
	public void init(ServletConfig config) {
		GeneralUtils.BEGINTIME = config.getInitParameter("beginTime");
		GeneralUtils.ENDTIME = config.getInitParameter("endTime");
		GeneralUtils.BEGINHOUR = GeneralUtils.BEGINTIME.substring(0,2);
		GeneralUtils.ENDHOUR = GeneralUtils.ENDTIME.substring(0,2);
	}




	UsersDao userdao = new UsersDao();
    /**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String type = request.getParameter("type");
		if ("register".equals(type)) {
			register(request,response);
		}else if ("login".equals(type)) {
			login(request,response);
		}else if ("online".equals(type)) {
			online(request,response);
		}
		
		
	}

	/**
	 * @param request
	 * @param response
	 * @param 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void online(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Users> list = userdao.queryonline();
		request.setAttribute("list", list);
		HttpSession session = request.getSession();
		String nickname = (String) session.getAttribute("nickname");
		request.getRequestDispatcher("online.jsp").forward(request, response);
		
	}
/**
	 * @param request
 *
	 * @param response
	 * @param 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String account = request.getParameter("account");
		Users users = userdao.queryuser(account,null);
		if (users !=null) {
			request.setAttribute("msg", "此账号已经被注册");
			request.setAttribute("account", account);
			request.setAttribute("sex", request.getParameter("sex"));
			request.setAttribute("nickName", request.getParameter("nickName"));
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}else {
			String pwd = request.getParameter("pwd");
			String sex = request.getParameter("sex");
			String nickName = request.getParameter("nickName");
			Users users2 = new Users();
			users2.setAccount(account);
			users2.setPwd(pwd);
			users2.setNickName(nickName);
			users2.setSex(sex);
			users2.setOnline(0);
			users2.setType(0);
			userdao.add(users2);
			request.setAttribute("msg", "注册成功");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
	}
	
	public static Map<String, HttpSession> sesssionMap = new ConcurrentHashMap<String, HttpSession>();
	
	/**
	 * @param request
	 * @param response
	 * @param 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String account = request.getParameter("account");
		String pwd = request.getParameter("pwd");
		
		Users users = userdao.queryuser(account,pwd);
		if (users!=null) {
			if (users.getType()!=1) {
				boolean bflag = GeneralUtils.comparatoTime(Integer.parseInt(GeneralUtils.BEGINTIME), false);
				boolean eflag = GeneralUtils.comparatoTime(Integer.parseInt(GeneralUtils.ENDTIME), true);
				if (bflag) {
					request.setAttribute("msg", "请在"+GeneralUtils.BEGINTIME+"点后登陆");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}else if (eflag) {
					request.setAttribute("msg", "请在"+GeneralUtils.ENDTIME+"点前登陆");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}else {
					//用户登陆成功后用户在线状态修改为1，表示在线
					userdao.updateuser(1,account);
					//获取session对象
					HttpSession session = request.getSession();
					//将用户保存在session中
					session.setAttribute("account", account);
					session.setAttribute("nickName", users.getNickName());
					session.setAttribute("type", users.getType());
					session.setAttribute("online", users.getOnline());
					session.setAttribute("sex", users.getSex());
					sesssionMap.put(account, session);
					request.getRequestDispatcher("chat.jsp").forward(request, response); 
				}
			}else{
				//用户登陆成功后用户在线状态修改为1，表示在线
				userdao.updateuser(1,account);
				HttpSession session = request.getSession();
				//将用户保存在session中
				session.setAttribute("account", account);
				session.setAttribute("nickName", users.getNickName());
				session.setAttribute("type", users.getType());
				session.setAttribute("online", users.getOnline());
				session.setAttribute("sex", users.getSex());
				sesssionMap.put(account, session);
				request.getRequestDispatcher("chat.jsp").forward(request, response); 
			}
		}else {
			request.setAttribute("account", account);
			request.setAttribute("mag2", "账号或密码不正确");
			request.getRequestDispatcher("login.jsp").forward(request, response); 
		}
	}




}
