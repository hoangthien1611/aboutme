package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entity.Skill;

@Repository
public class SkillDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Skill> getItems() {
		String sql = "SELECT id_skill, name, percent FROM skills";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Skill>(Skill.class));
	}
	
	public int addItem(Skill skill) {
		String sql = "INSERT INTO skills (name, percent) VALUES (?, ?)";
		return jdbcTemplate.update(sql, new Object[] {skill.getName(), skill.getPercent()});
	}
	
	public int delItem(int id) {
		String sql = "DELETE FROM skills WHERE id_skill = ?";
		return jdbcTemplate.update(sql, new Object[] {id});
	}
	
	public Skill getItem(int id) {
		String sql = "SELECT * FROM skills WHERE id_skill = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] {id}, new BeanPropertyRowMapper<Skill>(Skill.class));
	}
	
	public int editItem(Skill skill) {
		String sql = "UPDATE skills SET name = ?, percent = ? WHERE id_skill = ?";
		return jdbcTemplate.update(sql, new Object[] {skill.getName(), skill.getPercent(), skill.getId_skill()});
	}
}
