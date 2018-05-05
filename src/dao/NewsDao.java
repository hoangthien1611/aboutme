package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import constant.Defines;
import entity.News;

@Repository
public class NewsDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<News> getItems(int offset) {
		String sql = "SELECT id_news, n.name, preview_text, detail_text, n.id_cat, c.name AS nameCat, n.picture, count_views, date_create, n.id_user, username FROM news AS n INNER JOIN categories AS c ON n.id_cat = c.id_cat INNER JOIN users AS u ON n.id_user = u.id_user ORDER BY id_news DESC LIMIT ?, ?";
		return jdbcTemplate.query(sql, new Object[] {offset, Defines.ROW_COUNT_ADMIN} ,new BeanPropertyRowMapper<News>(News.class));
	}

	public int getSumNews() {
		String sql = "SELECT COUNT(*) AS CountNews FROM news AS n INNER JOIN categories AS c ON n.id_cat = c.id_cat INNER JOIN users AS u ON n.id_user = u.id_user";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public int addItem(News objN) {
		String sql = "INSERT INTO news (name, preview_text, detail_text, id_cat, picture, id_user) VALUES (?, ?, ?, ?, ?, ?)";
		return jdbcTemplate.update(sql, new Object[] {objN.getName(), objN.getPreview_text(), objN.getDetail_text(), objN.getId_cat(), objN.getPicture(), objN.getId_user()});
	}

	public News getItem(int id) {
		String sql = "SELECT id_news, n.name, preview_text, detail_text, n.id_cat, c.name AS nameCat, n.picture, count_views, date_create, n.id_user, username FROM news AS n INNER JOIN categories AS c ON n.id_cat = c.id_cat INNER JOIN users AS u ON n.id_user = u.id_user WHERE id_news = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] {id}, new BeanPropertyRowMapper<News>(News.class));
	}

	public int delItem(int id) {
		String sql = "DELETE FROM news WHERE id_news = ?";
		return jdbcTemplate.update(sql, new Object[] {id});
	}

	public int editItem(News objN) {
		String sql = "UPDATE news SET name = ?, preview_text = ?, detail_text = ?, picture = ?, id_cat = ?, id_user = ? WHERE id_news = ?";
		return jdbcTemplate.update(sql, new Object[] {objN.getName(), objN.getPreview_text(), objN.getDetail_text(), objN.getPicture(), objN.getId_cat(), objN.getId_user(), objN.getId_news()});
	}

	public List<News> getItemsNew(int i) {
		String sql = "SELECT id_news, n.name, preview_text, detail_text, n.id_cat, c.name AS nameCat, n.picture, count_views, date_create, n.id_user, username FROM news AS n INNER JOIN categories AS c ON n.id_cat = c.id_cat INNER JOIN users AS u ON n.id_user = u.id_user ORDER BY date_create DESC LIMIT ?";
		return jdbcTemplate.query(sql, new Object[] {i} ,new BeanPropertyRowMapper<News>(News.class));
	}

	public int getSumNews(Integer cid) {
		String sql = "SELECT COUNT(*) AS CountNews FROM news AS n INNER JOIN categories AS c ON n.id_cat = c.id_cat INNER JOIN users AS u ON n.id_user = u.id_user WHERE n.id_cat = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] {cid},Integer.class);
	}

	public List<News> getItemsByIDCat(int offset, Integer cid) {
		String sql = "SELECT id_news, n.name, preview_text, detail_text, n.id_cat, c.name AS nameCat, n.picture, count_views, date_create, n.id_user, username FROM news AS n INNER JOIN categories AS c ON n.id_cat = c.id_cat INNER JOIN users AS u ON n.id_user = u.id_user WHERE n.id_cat = ? ORDER BY date_create DESC LIMIT ?, ?";
		return jdbcTemplate.query(sql, new Object[] {cid, offset, Defines.ROW_COUNT_PUBLIC} ,new BeanPropertyRowMapper<News>(News.class));
	}

	public void increaseView(News objN) {
		String sql = "UPDATE news SET date_create = ?, count_views = ? WHERE id_news=?";
		jdbcTemplate.update(sql, new Object[] {objN.getDate_create(), (objN.getCount_views()+1), objN.getId_news()});
	}

	public List<News> getRelatedItems(News objN) {
		String sql = "SELECT id_news, n.name, preview_text, detail_text, n.id_cat, c.name AS nameCat, n.picture, count_views, date_create, n.id_user, username FROM news AS n INNER JOIN categories AS c ON n.id_cat = c.id_cat INNER JOIN users AS u ON n.id_user = u.id_user WHERE n.id_cat = ? AND n.id_news != ? ORDER BY date_create DESC LIMIT 3";
		return jdbcTemplate.query(sql, new Object[] {objN.getId_cat(), objN.getId_news()} ,new BeanPropertyRowMapper<News>(News.class));
	}
	
	public List<News> getItemsMostView() {
		String sql = "SELECT id_news, n.name, preview_text, detail_text, n.id_cat, c.name AS nameCat, n.picture, count_views, date_create, n.id_user, username FROM news AS n INNER JOIN categories AS c ON n.id_cat = c.id_cat INNER JOIN users AS u ON n.id_user = u.id_user ORDER BY count_views DESC LIMIT 5";
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<News>(News.class));
	}

	public int countsumNewsBySearch(String searchText) {
		String sql = "SELECT COUNT(*) AS CountNews FROM news AS n INNER JOIN categories AS c ON n.id_cat = c.id_cat INNER JOIN users AS u ON n.id_user = u.id_user WHERE n.name LIKE '%"+searchText+"%'";
		return jdbcTemplate.queryForObject(sql,Integer.class);
	}

	public List<News> getItemsBySearch(String searchText, int offset) {
		String sql = "SELECT id_news, n.name, preview_text, detail_text, n.id_cat, c.name AS nameCat, n.picture, count_views, date_create, n.id_user, username FROM news AS n INNER JOIN categories AS c ON n.id_cat = c.id_cat INNER JOIN users AS u ON n.id_user = u.id_user WHERE n.name LIKE '%"+searchText+"%' ORDER BY id_news DESC LIMIT ?, ?";
		return jdbcTemplate.query(sql, new Object[] {offset, Defines.ROW_COUNT_PUBLIC} ,new BeanPropertyRowMapper<News>(News.class));
	}

	public int countsumNewsBySearch(String searchText, int idCat) {
		String sql = "SELECT COUNT(*) AS CountNews FROM news AS n INNER JOIN categories AS c ON n.id_cat = c.id_cat INNER JOIN users AS u ON n.id_user = u.id_user WHERE n.name LIKE '%"+searchText+"%' AND n.id_cat = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] {idCat},Integer.class);
	}

	public Object getItemsByIdCatSearch(String searchText, int idCat, int offset) {
		String sql = "SELECT id_news, n.name, preview_text, detail_text, n.id_cat, c.name AS nameCat, n.picture, count_views, date_create, n.id_user, username FROM news AS n INNER JOIN categories AS c ON n.id_cat = c.id_cat INNER JOIN users AS u ON n.id_user = u.id_user WHERE n.name LIKE '%"+searchText+"%' AND n.id_cat = ? ORDER BY id_news DESC LIMIT ?, ?";
		return jdbcTemplate.query(sql, new Object[] {idCat, offset, Defines.ROW_COUNT_ADMIN} ,new BeanPropertyRowMapper<News>(News.class));
	}
}
