package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entity.Interest;

@Repository
public class InterestDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Interest> getItems() {
		String sql = "SELECT id_interest, name, description FROM interests";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Interest>(Interest.class));
	}
	
	public Interest getItem(int id) {
		String sql = "SELECT * FROM interests WHERE id_interest = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] {id}, new BeanPropertyRowMapper<Interest>(Interest.class));
	}
	
	public int editItem(Interest interest) {
		String sql = "UPDATE interests SET name = ?, description = ? WHERE id_interest = ?";
		return jdbcTemplate.update(sql, new Object[] {interest.getName(), interest.getDescription(),interest.getId_interest()});
	}
}
