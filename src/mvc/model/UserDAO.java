package mvc.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
	private Connection connection = null;

	public UserDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/meus_dados", "root", "Leonardo@123");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void add(User user) {
		String sql = "INSERT INTO User (username, senha, nickname) values(?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getSenha());
			stmt.setString(3, user.getNickname());
			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void addNote(User user, Note note) {
		String sql = "INSERT INTO Notes (user_id, content) VALUES (?, ?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, user.getId());
			stmt.setString(2, note.getContent());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void edit(Note note) {
		String sql = "UPDATE Notes  SET content = ? WHERE id = ? ";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, note.getContent());
			stmt.setInt(2, note.getNoteId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean check(User user) {
		boolean check = false;
		String sql = "SELECT COUNT(*) FROM User WHERE username=? AND senha=? LIMIT 1";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getSenha());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				if (rs.getInt(1) != 0) {
					check = true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
	}

	public int getId(User user) {
		int id = 0;
		String sql = "SELECT * FROM User WHERE username=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, user.getUsername());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				id = Integer.parseInt(rs.getString("id"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	public void remove(int noteId) {
		String sql = "DELETE FROM Notes WHERE id =?;";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, noteId);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;
	}

	public String getNote(int noteId) {

		String note = "";
		String sql = "SELECT * FROM Notes WHERE id=?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, noteId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				note = rs.getString("content");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return note;
	}

	public User getNotes(User user) {
		User user1 = new User();
		String sql = "SELECT User.username, Notes.id, Notes.content FROM User JOIN Notes "
				+ "ON Notes.user_id = User.id WHERE Notes.user_id= ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, user.getId());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				if (rs.isFirst()) {
					user1.setUsername(rs.getString("username"));
				}
				Note note = new Note();
				note.setContent(rs.getString("content"));
				note.setNoteId(rs.getInt("id"));

				user1.addContent(note);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user1;

	}

	public User getNotesAlphabetical(User user) {

		User user1 = new User();
		try {
			PreparedStatement stmt = connection.prepareStatement(
					"SELECT User.username, Notes.id, Notes.content FROM User JOIN Notes ON Notes.user_id = User.id  "
					+ "WHERE Notes.user_id = ? ORDER BY Notes.content ASC");

			
			stmt.setInt(1, user.getId());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Note note = new Note();
				note.setContent(rs.getString("content"));
				note.setNoteId(rs.getInt("id"));
				user1.addContent(note);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user1;
	}

	public User getNotesChronological(User user) {

		User user1 = new User();
		try {
			PreparedStatement stmt = connection.prepareStatement(
					"SELECT User.username, Notes.id, Notes.content FROM User JOIN Notes ON Notes.user_id = User.id  "
							+ "WHERE Notes.user_id = ? ORDER BY Notes.id ASC");

			stmt.setInt(1, user.getId());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Note note = new Note();
				note.setContent(rs.getString("content"));
				note.setNoteId(rs.getInt("id"));
				user1.addContent(note);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user1;
	}
}
