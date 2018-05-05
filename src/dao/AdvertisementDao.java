package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entity.Advertisement;

@Repository
public class AdvertisementDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Advertisement> getItems() {
		String sql = "SELECT id_ad, name, link, picture FROM advertisements";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Advertisement>(Advertisement.class));
	}

	public int addItem(Advertisement objAd) {
		String sql = "INSERT INTO advertisements (name, link, picture) VALUES (?, ?, ?)";
		return jdbcTemplate.update(sql, new Object[] {objAd.getName(), objAd.getLink(), objAd.getPicture()});
	}

	public Advertisement getItem(int id) {
		String sql = "SELECT * FROM advertisements WHERE id_ad = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] {id}, new BeanPropertyRowMapper<Advertisement>(Advertisement.class));
	}

	public int delItem(int id) {
		String sql = "DELETE FROM advertisements WHERE id_ad = ?";
		return jdbcTemplate.update(sql, new Object[] {id});
	}

	public int editItem(Advertisement objAd) {
		String sql = "UPDATE advertisements SET name = ?, link = ?, picture = ? WHERE id_ad = ?";
		return jdbcTemplate.update(sql, new Object[] {objAd.getName(), objAd.getLink(), objAd.getPicture(), objAd.getId_ad()});
	}
	
}
