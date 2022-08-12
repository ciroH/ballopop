package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/ManageCandidate", "/managecandidate" })
public class ManageCandidate extends ManageMenu {
	private static final long serialVersionUID = 1L;
       
    public ManageCandidate() {
        super();

    }

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String candidateOption = request.getParameter("candidateOption");
    	try {
	    	switch (candidateOption) {
			case "add":
				request.setAttribute("trigger", "add");
				break;
			case "modify":
				generateCandidateList(request, response);
				request.setAttribute("trigger", "modify");
				break;
			case "delete":
				generateCandidateList(request, response);
				request.setAttribute("trigger", "delete");
				break;
			case "action":
				String useCase = request.getParameter("useCase");
				switch (useCase) {
				case "add":
					
					break;
				case "modify":
					
					break;
				case "confirmmodify":
					
					break;
				case "delete":
		
					break;
	
				default:
					generateCandidateList(request, response);
					request.getRequestDispatcher("WEB-INF/candidatesPanel.jsp").forward(request, response);
					break;
				}
				break;
	
			default:
				generateCandidateList(request, response);
				break;
			}
	    	request.getRequestDispatcher("WEB-INF/candidatesPanel.jsp").forward(request, response);
    	} catch (/*SQL*/Exception e) {
			request.setAttribute("warning", "e");
	/*	} catch (IOException | ServletException e) {
			request.setAttribute("warning", e.getMessage());
	*/	} finally {
			request.getRequestDispatcher("WEB-INF/candidatesPanel.jsp").forward(request, response);
		}
	}
	//-for the modify candidateOption: grab the id and send it back (as candidateIdToModify) so you can recieve it again when admin triggers the confirmmodify form
    //-i still need to implement warning attributes, to be shown on candidatesPanel
}