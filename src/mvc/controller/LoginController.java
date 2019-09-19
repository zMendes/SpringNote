package mvc.controller;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import mvc.model.*;

@Controller
public class LoginController {
	@RequestMapping("/")
	public String execute() {
		return "login";
	}

	@RequestMapping("/checkLogin")
	public String login(User user, HttpSession session) {
		UserDAO dao = new UserDAO();
		if (dao.check(user)) {
			session.setAttribute("Logado", dao.getId(user));
			return "redirect:myNotes";
		}
		return "login";
	}

	@RequestMapping("/register")
	public String register() {
		return "register";
	}

	@RequestMapping("/addUser")
	public String addUser(User user) {
		UserDAO dao = new UserDAO();
		dao.add(user);
		return "login";
	}

	@RequestMapping("/add")
	public String add(User user, HttpSession session) {
		session.setAttribute("id", user.getId());
		return "add";
	}

	@RequestMapping("/addNote")
	public String addNote(User user, Note note) {
		UserDAO dao = new UserDAO();
		dao.addNote(user, note);
		return "redirect:myNotes";
	}

	@RequestMapping("/chronological")
	public String crono(HttpSession session) {
		session.setAttribute("sort", "chronological");
		return "notes";
	}

	@RequestMapping("/alphabetical")
	public String alpha(HttpSession session) {
		session.setAttribute("sort", "alphabetical");
		return "notes";
	}

	@RequestMapping("/myNotes")
	public String notes(User user, HttpSession session) {
		session.setAttribute("sort", "false");
		return "notes";
	}

	@RequestMapping("/remove")
	public String remove(Note note) {
		UserDAO dao = new UserDAO();
		dao.remove(note.getNoteId());
		return "redirect:myNotes";
	}

	@RequestMapping("/alter")
	public String altera(Note note, HttpSession session) {
		UserDAO dao = new UserDAO();
		String nota = dao.getNote(note.getNoteId());
		session.setAttribute("nota", nota);
		session.setAttribute("noteId", note.getNoteId());
		return "altera";
	}

	@RequestMapping("/edit")
	public String edit(Note note) {
		UserDAO dao = new UserDAO();
		dao.edit(note);

		return "redirect:myNotes";
	}

	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:checkLogin";
	}
}
