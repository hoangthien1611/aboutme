package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entity.Project;

@Repository
public class ProjectDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Project> getItems() {
		String sql = "SELECT id_project, name, picture, preview_text, link FROM projects";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Project>(Project.class));
	}

	public int addItem(Project objP) {
		String sql = "INSERT INTO projects (name, link, picture, preview_text) VALUES (?, ?, ?, ?)";
		return jdbcTemplate.update(sql, new Object[] {objP.getName(), objP.getLink(), objP.getPicture(), objP.getPreview_text()});
	}
	
	public Project getItem(int id) {
		String sql = "SELECT * FROM projects WHERE id_project = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] {id}, new BeanPropertyRowMapper<Project>(Project.class));
	}
	
	public int delItem(int id) {
		String sql = "DELETE FROM projects WHERE id_project = ?";
		return jdbcTemplate.update(sql, new Object[] {id});
	}
	
	public int editItem(Project objP) {
		String sql = "UPDATE projects SET name = ?, link = ?, picture = ?, preview_text = ? WHERE id_project = ?";
		return jdbcTemplate.update(sql, new Object[] {objP.getName(), objP.getLink(), objP.getPicture(), objP.getPreview_text(), objP.getId_project()});
	}
}
