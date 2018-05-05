package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entity.User;

@Repository
public class UserDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<User> getItems() {
		String sql = "SELECT id_user, username, fullname, password, email, users.id_role, name FROM users INNER JOIN roles ON users.id_role = roles.id_role";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));
	}
	
	public int checkItem(String username) {
		// try catch
		String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { username }, Integer.class);
	}
	
	public int addItem(User objUser) {
		String sql = "INSERT INTO users(username, fullname, password, email, id_role) VALUES (?, ?, ?, ?, ?)";
		return jdbcTemplate.update(sql, new Object[] { objUser.getUsername(), objUser.getFullname(),
				objUser.getPassword(), objUser.getEmail(), objUser.getId_role() });
	}
	
	public int delItem(int id) {
		String sql = "DELETE FROM users WHERE id_user = ?";
		return jdbcTemplate.update(sql, new Object[] { id });
	}
	
	public User getItem(int id) {
		String sql = "SELECT id_user, username, fullname, password, email, users.id_role, name FROM users INNER JOIN roles ON users.id_role = roles.id_role WHERE id_user = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper<User>(User.class));
	}
	
	public User getItem(String name) {
		try {
			String sql = "SELECT * FROM users WHERE username = ?";
			return jdbcTemplate.queryForObject(sql, new Object[] { name }, new BeanPropertyRowMapper<User>(User.class));
		} catch (Exception e) {
			return null;
		}
	}
	
	public int editItem(User objUser) {
		String sql = "UPDATE users SET fullname = ?, password = ?, email = ?, id_role = ? WHERE id_user = ?";
		return jdbcTemplate.update(sql,
				new Object[] { objUser.getFullname(), objUser.getPassword(), objUser.getEmail(), objUser.getId_role(), objUser.getId_user() });
	}
	
	public int countItems() {
		String sql = "SELECT COUNT(*) AS count_user FROM users";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
}
