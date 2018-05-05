package dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import constant.Defines;
import entity.Comment;

@Repository
public class CommentDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int countComment() {
		String sql = "SELECT COUNT(*) AS CountComment FROM comments INNER JOIN news ON comments.id_news = news.id_news";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	public List<Comment> getItems(int offset) {
		String sql = "SELECT id_comment, fullname, email, content, comments.id_news, name, comments.date_create, status, id_parent FROM comments INNER JOIN news ON comments.id_news = news.id_news ORDER BY id_comment DESC LIMIT ?, ?";
		return jdbcTemplate.query(sql, new Object[] {offset, Defines.ROW_COUNT_ADMIN}, new BeanPropertyRowMapper<Comment>(Comment.class));
	}
	
	public int delItem(int id) {
		String sql = "DELETE FROM comments WHERE id_comment = ?";
		return jdbcTemplate.update(sql, new Object[] { id });
	}

	public Comment getItem(int id) {
		String sql = "SELECT * FROM comments WHERE id_comment = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] {id}, new BeanPropertyRowMapper<Comment>(Comment.class));
	}

	public int changeStatus(int id, int gt) {
		String sql = "UPDATE comments SET status = ? WHERE id_comment = ?";
		return jdbcTemplate.update(sql, new Object[] {gt, id});
	}

	public List<Comment> getItemsByIdNews(int id) {
		String sql = "SELECT id_comment, fullname, email, content, comments.id_news, name, comments.date_create, status, id_parent FROM comments INNER JOIN news ON comments.id_news = news.id_news WHERE comments.id_news = ? AND status = 1";
		return jdbcTemplate.query(sql, new Object[] {id}, new BeanPropertyRowMapper<Comment>(Comment.class));
	}

	public int addItem(Comment objC) {
		String sql = "INSERT INTO comments(fullname, email, content, id_news, date_create, status, id_parent) VALUES (?,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql, new Object[] {objC.getFullname(), objC.getEmail(), objC.getContent(), objC.getId_news(), objC.getDate_create(),1, objC.getId_parent()});
	}
}
