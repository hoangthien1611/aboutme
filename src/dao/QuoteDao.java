package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entity.Quote;

@Repository
public class QuoteDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Quote> getItems() {
		String sql = "SELECT id_quote, author, content, picture FROM quotes";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Quote>(Quote.class));
	}

	public int addItem(Quote objQ) {
		String sql = "INSERT INTO quotes (author, picture, content) VALUES (?, ?, ?)";
		return jdbcTemplate.update(sql, new Object[] {objQ.getAuthor(), objQ.getPicture(), objQ.getContent()});
	}

	public Quote getItem(int id) {
		String sql = "SELECT * FROM quotes WHERE id_quote = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] {id}, new BeanPropertyRowMapper<Quote>(Quote.class));
	}

	public int delItem(int id) {
		String sql = "DELETE FROM quotes WHERE id_quote = ?";
		return jdbcTemplate.update(sql, new Object[] {id});
	}

	public int editItem(Quote objQ) {
		String sql = "UPDATE quotes SET author = ?, picture = ?, content = ? WHERE id_quote = ?";
		return jdbcTemplate.update(sql, new Object[] {objQ.getAuthor(), objQ.getPicture(), objQ.getContent(), objQ.getId_quote()});
	}
	
	public List<Quote> getItemsRandom() {
		String sql = "SELECT id_quote, author, content, picture FROM quotes ORDER BY RAND() LIMIT 3";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Quote>(Quote.class));
	}
	
}
