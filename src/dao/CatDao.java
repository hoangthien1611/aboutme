package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entity.Cat;

@Repository
public class CatDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Cat> getItems() {
		String sql = "SELECT id_cat, name FROM categories";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Cat>(Cat.class));
	}
	
	public int addItem(Cat cat) {
		String sql = "INSERT INTO categories (name) VALUES (?)";
		return jdbcTemplate.update(sql, new Object[] {cat.getName()});
	}
	
	public int delItem(int id) {
		String sql = "DELETE FROM categories WHERE id_cat = ?";
		return jdbcTemplate.update(sql, new Object[] {id});
	}
	
	public Cat getItem(int id) {
		String sql = "SELECT * FROM categories WHERE id_cat = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] {id}, new BeanPropertyRowMapper<Cat>(Cat.class));
	}
	
	public int editItem(Cat cat) {
		String sql = "UPDATE categories SET name = ? WHERE id_cat = ?";
		return jdbcTemplate.update(sql, new Object[] {cat.getName(), cat.getId_cat()});
	}
	
	public List<Cat> getItemsCountNews() {
		String sql = "SELECT c.id_cat, c.name, COUNT(id_news) AS count_news FROM categories AS c INNER JOIN news AS n ON c.id_cat = n.id_cat INNER JOIN users AS u ON n.id_user = u.id_user GROUP BY c.id_cat";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Cat>(Cat.class));
	}
	
	public int countItems() {
		String sql = "SELECT COUNT(*) AS count_cat FROM categories";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
}
