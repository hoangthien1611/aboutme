package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entity.Role;

@Repository
public class RoleDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Role> getItems() {
		String sql = "SELECT id_role, name FROM roles";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Role>(Role.class));
	}
}
