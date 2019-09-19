package mvc.model;

import java.util.ArrayList;
import java.util.List;


public class User {

	private int id;
	private String username;
	private String senha;
	private String nickname;
	
	private List<Note> notes;
	
	public User() {
		notes= new ArrayList<Note>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public List<Note> getContent() {
		return notes;
	}

	public void addContent(Note content) {
		this.notes.add(content);
	}



}
