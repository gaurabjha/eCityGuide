package com.ecity.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecity.bean.AnswersBean;
import com.ecity.bean.AutoRating;
import com.ecity.bean.GuideFilterQuestionBean;
import com.ecity.bean.GuideQuestionsBean;
import com.ecity.bean.InsertAnswerBean;
import com.ecity.bean.InsertQuestionBean;
import com.ecity.bean.JdbcConnection;
import com.ecity.bean.LoginUtil;
import com.ecity.bean.MyFeedbackBean;
import com.ecity.bean.MyQuestionsBean;
import com.ecity.bean.RateUtilBean;
import com.ecity.bean.RatingBean;
import com.ecity.bean.RegisterBean;
import com.ecity.bean.UsersAnswersBean;
import com.ecity.bean.UsersQuestionBean;

/**
 * Servlet implementation class Controller
 */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;

		// Auto Rating on Last Day of Month
		//----------------------------------------------------------------------

		Calendar cal = Calendar.getInstance();
		if ((cal.get(Calendar.DATE)) == cal.getActualMaximum(Calendar.DATE)) {
			AutoRating.rate();
		}

		//----------------------------------------------------------------------

		// redirection according to the request
		String ref = request.getParameter("ref") == null ? "" : request
				.getParameter("ref");

		HttpSession session = request.getSession();

		// login page
		if (ref.equals("Login")) {
			JdbcConnection.setCredentials(getServletContext());	
			String userName = request.getParameter("username");
			String password = request.getParameter("password");
			LoginUtil login = new LoginUtil(userName, password);
			if (login.Validate()) {
				session.setAttribute("type", login.getType());
				session.setAttribute("name", login.getName());
				session.setAttribute("uid", login.getU_id());
				session.setAttribute("count", login.getCount());
				session.setAttribute("expired", login.isExpired());
				
				// System.out.println("in controller " + login.getCount());
				// System.out.println(login.getName() + " " + login.getType());

				getServletContext().getRequestDispatcher("/Home.jsp").forward(
						request, response);

				/*
				 * 
				 * if (login.getType().equalsIgnoreCase("free")) {
				 * getServletContext().getRequestDispatcher("/Free_Home.jsp")
				 * .forward(request, response); } else if
				 * (login.getType().equalsIgnoreCase("guide")) {
				 * getServletContext().getRequestDispatcher("/Guide_Home.jsp")
				 * .forward(request, response); } else if
				 * (login.getType().equalsIgnoreCase("paid")) {
				 * getServletContext().getRequestDispatcher("/Paid_Home.jsp")
				 * .forward(request, response); }
				 */

			} else {
				request.setAttribute("err", "Invalid Username/Password");
				getServletContext().getRequestDispatcher("/login.jsp").forward(
						request, response);
			}
		}

		// register page
		else if (ref.equals("Register")) {
			String username, password, type;
			String name, phone, b_name, b_acc_no, address;
			JdbcConnection.setCredentials(getServletContext());			
			username = request.getParameter("username");
			password = request.getParameter("password");
			type = request.getParameter("type");
			name = request.getParameter("name");
			phone = request.getParameter("phone");
			b_name = request.getParameter("b_name");
			b_acc_no = request.getParameter("b_acc_no");
			address = request.getParameter("add");

			RegisterBean register = new RegisterBean(address, b_acc_no, b_name,
					name, password, phone, type, username);
			if (register.register()) {
				request.setAttribute("msg",
						"Registration Successful...Login to Continue...!!!");
				getServletContext().getRequestDispatcher("/login.jsp").forward(
						request, response);
			} else {
				request.setAttribute("err",
						"Some Error Occured...Try Again...!!!");
				getServletContext().getRequestDispatcher("/register.jsp")
						.forward(request, response);

			}

		}

		// logout option clicked
		else if (ref.equals("logout")) {
			request.getSession().invalidate();
			response.sendRedirect("./");
		}

		

		// For Guide Me Option

		else if (ref.equals("GuideMe")) {

			Connection con = JdbcConnection.getConnection();
			try {
				PreparedStatement ps = con
						.prepareStatement("select count(*) from questions where u_id = ? and status = 'UNRATED' ");
				ps.setString(1, (String) session.getAttribute("uid"));

				ResultSet rs = ps.executeQuery();
				rs.next();
				if (rs.getInt(1) != 0) {
					request
							.setAttribute("err",
									"Your Last Question Is Still Unrated...Please Rate It");
					getServletContext().getRequestDispatcher("/Home.jsp")
							.forward(request, response);
				} else {
					getServletContext().getRequestDispatcher("/GuideMe.jsp")
							.forward(request, response);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("err",
						"Some Problem Occured....Please Try Again");
				getServletContext().getRequestDispatcher("/Home.jsp").forward(
						request, response);
			} finally {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// For My Answers Option by User

		else if (ref.equals("MyAnswers")) {
			String from_date = request.getParameter("from");
			String to_date = request.getParameter("to");
			UsersAnswersBean user_ans = new UsersAnswersBean((String) session
					.getAttribute("uid"), from_date, to_date);

			session.setAttribute("Question_Array", user_ans.getMyAns());
			getServletContext().getRequestDispatcher("/User_All_Answer.jsp")
					.forward(request, response);
		}

		// Ask Question from Guide Me by users
		else if (ref.equalsIgnoreCase("Ask")) {
			String question, loc;
			question = request.getParameter("question");
			loc = request.getParameter("loc");
			String uid = (String) session.getAttribute("uid");
			// System.out.println(question + "||" + loc + "||" + uid);
			UsersQuestionBean qBean = new UsersQuestionBean(uid, question, loc);
			if (new InsertQuestionBean(qBean).insert()) {
				int count = ((Integer) session.getAttribute("count"))
						.intValue();
				session.setAttribute("count", count - 1);
				request.setAttribute("msg", "Question Posted");
				// System.out.println(request.getAttribute("msg"));
				getServletContext().getRequestDispatcher("/Home.jsp").forward(
						request, response);
			} else {
				request.setAttribute("err",
						"Some Problem Occured....Please Try Again");
				getServletContext().getRequestDispatcher("/GuideMe.jsp")
						.forward(request, response);
			}

		}

		// Guide Question
		else if (ref.equalsIgnoreCase("question")) {
			String filter, loc, to_date, from_date;
			filter = request.getParameter("filter");
			loc = request.getParameter("loc");
			to_date = request.getParameter("to");
			from_date = request.getParameter("from");

			GuideFilterQuestionBean g_question = new GuideFilterQuestionBean(
					(String) session.getAttribute("uid"), filter, loc, to_date,
					from_date);
			session.setAttribute("Question_Array", g_question.fetchQuestions());
			getServletContext().getRequestDispatcher("/All_Questions.jsp")
					.forward(request, response);
		}

		else if (ref.equalsIgnoreCase("give_ans")) {
			String q_id = request.getParameter("qid");

			GuideQuestionsBean ques = null;
			ArrayList<GuideQuestionsBean> ans_arr = (ArrayList<GuideQuestionsBean>) session
					.getAttribute("Question_Array");
			Iterator<GuideQuestionsBean> it = ans_arr.iterator();
			while (it.hasNext()) {
				ques = (GuideQuestionsBean) it.next();
				if (ques.getQ_id().equalsIgnoreCase(q_id)) {
					request.setAttribute("question", ques);
				}
			}

			request.setAttribute("answers", new AnswersBean()
					.fetchAnswers(q_id));

			/*
			 * Fetching the question from all questions whose answers are to be
			 * searched
			 */

			getServletContext().getRequestDispatcher("/Guide_Answer.jsp")
					.forward(request, response);

		}

		// answer Submition by guide
		else if (ref.equalsIgnoreCase("answer")) {
			String q_type, ans, q_id;
			q_id = request.getParameter("q_id");
			ans = request.getParameter("ans");
			q_type = request.getParameter("q_type");

			InsertAnswerBean insAns = new InsertAnswerBean((String) session
					.getAttribute("uid"), q_id, ans, q_type);
			if (insAns.insert()) {
				int count = ((Integer) session.getAttribute("count"))
						.intValue();
				session.setAttribute("count", count + 1);
				request.setAttribute("msg", "Answer Posted");
				// System.out.println(request.getAttribute("msg"));
				getServletContext().getRequestDispatcher("/Home.jsp").forward(
						request, response);
			} else {
				request.setAttribute("err",
						"Some Problem Occured....Please Try Again");
				getServletContext().getRequestDispatcher("/Home.jsp").forward(
						request, response);
			}

		}

		else if (ref.equalsIgnoreCase("MyAwards")) {
			PreparedStatement ps = null;
			Connection con = null;
			con=JdbcConnection.getConnection();
			try {
				ps = con.prepareStatement(
						"select balance from guide where u_id = ? ");

				ps.setString(1, (String) session.getAttribute("uid"));
				ResultSet rs = ps.executeQuery();
				rs.next();

				request.setAttribute("balance", rs.getString(1));
				getServletContext().getRequestDispatcher("/MyAwards.jsp")
						.forward(request, response);

			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		else if (ref.equalsIgnoreCase("MyQuestions")) {
			MyQuestionsBean myques = new MyQuestionsBean((String) session
					.getAttribute("uid"));
			session.setAttribute("Question_Array", myques.getMyQuestions());
			getServletContext().getRequestDispatcher("/All_Questions.jsp")
					.forward(request, response);

		}

		// For My Feedback Option

		else if (ref.equalsIgnoreCase("MyFeedback")) {

			MyFeedbackBean myFeed = new MyFeedbackBean((String) session
					.getAttribute("uid"));

			GuideQuestionsBean ques = myFeed.getQuestion();
			request.setAttribute("question", ques);

			request.setAttribute("answers", new AnswersBean().fetchAnswers(ques
					.getQ_id()));

			RatingBean ids = new RatingBean(ques.getQ_id());
			request.setAttribute("ids", ids.getDetails());
			getServletContext().getRequestDispatcher("/MyFeedback.jsp")
					.forward(request, response);

		}

		else if (ref.equalsIgnoreCase("ExtendMembership")) {

			Connection con = JdbcConnection.getConnection();
			try {
				PreparedStatement pst = null;
				if (((String) session.getAttribute("type"))
						.equalsIgnoreCase("paid")) {
					pst = con
							.prepareStatement("update users set exp_date=exp_date+30 , q_left = q_left +100 where u_id =?");
				} else {
					pst = con
							.prepareStatement("update guide set exp_date=exp_date+30  where u_id =?");
				}
				pst.setString(1, (String) session.getAttribute("uid"));
				
				if (pst.executeUpdate() > 0) {
					session.setAttribute("expired",false);
					request.setAttribute("msg", "Membership Extended");
					getServletContext().getRequestDispatcher("/Home.jsp")
							.forward(request, response);
				} else {
					request
							.setAttribute("err",
									"Something went wrong...Membership not Extended...!!!");
					getServletContext().getRequestDispatcher("/Home.jsp")
							.forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} 
		
		//Rating
		else if (ref.equalsIgnoreCase("Rate")) {
			String qid = request.getParameter("qid");
			String best = request.getParameter("best_rate");
			String better = request.getParameter("better_rate");
			String good = request.getParameter("good_rate");
			String ok = request.getParameter("ok_rate");
			String notok = request.getParameter("notok_rate");

			RateUtilBean rate_it = new RateUtilBean(qid, best, better, good,
					ok, notok);
			if (rate_it.rate()) {
				request.setAttribute("msg", "Rating Successful");
			} else {
				request.setAttribute("err",
						"Something went wrong...Rating Failed...!!!");
			}
			getServletContext().getRequestDispatcher("/Home.jsp").forward(
					request, response);
		}
		
		else{
			System.out.println("not implemented");
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
