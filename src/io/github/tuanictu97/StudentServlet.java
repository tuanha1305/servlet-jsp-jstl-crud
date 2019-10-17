package io.github.tuanictu97;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAO dao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init() {
    	dao = new DAO("jdbc:mysql://localhost/crud_servlet_jsp", "root", "");
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("action") != null) {
			String action = request.getParameter("action");
			switch (action) {
			case "add":
				System.out.println("show add");
				showAdd(request, response);
				break;
			case "doAdd":
				System.out.println("do add");
				dao.insertStdudent(new Student(request.getParameter("name"), request.getParameter("address")));
				response.sendRedirect(request.getContextPath()+"/StudentServlet");
				break;
			case "edit":
				System.out.println("show edit");
				Student student = dao.getStudent(Integer.parseInt(request.getParameter("id")));
				request.setAttribute("st", student);
				showEdit(request, response);
				break;
			case "doEdit":
				System.out.println("do edit");
				dao.editStudent(new Student(Integer.parseInt(request.getParameter("idst")), request.getParameter("name"), request.getParameter("address")));
				response.sendRedirect(request.getContextPath()+"/StudentServlet");
				break;
			case "doDelete":
				System.out.println("do delete");
				try {
					dao.delete(Integer.parseInt(request.getParameter("id")));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				response.sendRedirect(request.getContextPath()+"/StudentServlet");
				break;
			default:
				showList(request, response);
				break;
			}
			
		}else {
			showList(request, response);
		}
	}

	private void showEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
		dispatcher.forward(request, response);
	}
	private void showAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("add.jsp");
		dispatcher.forward(request, response);
	}
	private void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("students", dao.listAllStudents());
		RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
		dispatcher.forward(request, response);
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
