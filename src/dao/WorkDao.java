package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entity.Work;

@Repository
public class WorkDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Work> getItems() {
		String sql = "SELECT id_work, name, description, period FROM works";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Work>(Work.class));
	}

	public int addItem(Work objW) {
		String sql = "INSERT INTO works (name, description, period) VALUES (?, ?, ?)";
		return jdbcTemplate.update(sql, new Object[] {objW.getName(), objW.getDescription(), objW.getPeriod()});
	}

	public int delItem(int id) {
		String sql = "DELETE FROM works WHERE id_work = ?";
		return jdbcTemplate.update(sql, new Object[] {id});
	}

	public Work getItem(int id) {
		String sql = "SELECT * FROM works WHERE id_work = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] {id}, new BeanPropertyRowMapper<Work>(Work.class));
	}

	public int editItem(Work objW) {
		String sql = "UPDATE works SET name = ?, description = ?, period = ? WHERE id_work = ?";
		return jdbcTemplate.update(sql, new Object[] {objW.getName(), objW.getDescription(), objW.getPeriod(), objW.getId_work()});
	}
}
