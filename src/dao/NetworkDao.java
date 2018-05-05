package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entity.Network;

@Repository
public class NetworkDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Network> getItems() {
		String sql = "SELECT id_net, name, link, icon FROM social_networks";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Network>(Network.class));
	}
	
	public Network getItem(int id) {
		String sql = "SELECT * FROM social_networks WHERE id_net = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] {id}, new BeanPropertyRowMapper<Network>(Network.class));
	}
	
	public int editItem(Network network) {
		String sql = "UPDATE social_networks SET name = ?, link = ?, icon = ? WHERE id_net = ?";
		return jdbcTemplate.update(sql, new Object[] {network.getName(), network.getLink(), network.getIcon(), network.getId_net()});
	}

	public int delItem(int id) {
		String sql = "DELETE FROM social_networks WHERE id_net = ?";
		return jdbcTemplate.update(sql, new Object[] { id });
	}

	public int addItem(Network objN) {
		String sql = "INSERT INTO social_networks (name, link, icon) VALUES (?, ?, ?)";
		return jdbcTemplate.update(sql, new Object[] {objN.getName(), objN.getLink(), objN.getIcon()});
	}
}
