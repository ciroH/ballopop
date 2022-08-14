package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.LogicCandidate;
import data.DataCandidate;
import entities.Candidate;

@WebServlet({ "/ManageCandidate", "/managecandidate" })
public class ManageCandidate extends ManageMenu {
	private static final long serialVersionUID = 1L;
	private Exception IllegalArgumentException;
			LogicCandidate logic;
			DataCandidate data;
			Candidate candidate;
	
       
    public ManageCandidate() {
        super();
        logic = new LogicCandidate();
        data = new DataCandidate();
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
					candidate = new Candidate(request.getParameter("name"));
					candidate.setDescription(request.getParameter("description"));
					candidate.setParty(request.getParameter("party"));
					candidate.setPhoto(request.getParameter("photo"));
						data.add(candidate);
					break;
				case "modify":
					int idToModify = Integer.parseInt(request.getParameter("id"));
					candidate = logic.get(idToModify);
					if (candidate!=null) {
						request.setAttribute("candidateToModify", candidate);
						request.setAttribute("trigger", "confirmmodify");
					} else {
						request.setAttribute("warning", "Unexpected error when getting candidate info from data layer");
					}
					break;
				case "confirmmodify":
					int candidateId; 
					candidate = new Candidate(request.getParameter("name"));
					candidate.setDescription(request.getParameter("description"));
					candidate.setParty(request.getParameter("party"));
					candidate.setPhoto(request.getParameter("photo"));
						candidateId = Integer.parseInt(request.getParameter("id"));
						if(candidateId < 0) throw IllegalArgumentException;
						candidate.setId(candidateId);
						data.modify(candidate);
					break;
				case "delete":
					data.delete(Integer.parseInt(request.getParameter("id")));
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
	    	generateCandidateList(request, response);
	    	request.getRequestDispatcher("WEB-INF/candidatesPanel.jsp").forward(request, response);
    	} catch (SQLException e) {
			request.setAttribute("warning", e.getSQLState() + " : " + e.getMessage());
		} catch (IOException | ServletException | IllegalArgumentException  e) {
			request.setAttribute("warning", e.getMessage());
		} catch (Exception e) {
			request.setAttribute("warning", e.getMessage());
		} finally {
			generateCandidateList(request, response);
			request.getRequestDispatcher("WEB-INF/candidatesPanel.jsp").forward(request, response);
		}
	}
	//-for the modify candidateOption: grab the id and send it back (as candidateIdToModify) so you can recieve it again when admin triggers the confirmmodify form
    //-i still need to implement warning attributes, to be shown on candidatesPanel
}