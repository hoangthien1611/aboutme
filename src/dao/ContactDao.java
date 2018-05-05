package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import constant.Defines;
import entity.Contact;

@Repository
public class ContactDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Contact> getItems() {
		String sql = "SELECT id_contact, fullname, email, phone, content, date_create FROM contacts";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Contact>(Contact.class));
	}
	
	public int countContact() {
		String sql = "SELECT COUNT(*) AS CountContact FROM contacts";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	public Object getItems(int offset) {
		String sql = "SELECT id_contact, fullname, email, phone, content, date_create FROM contacts ORDER BY id_contact DESC LIMIT ?, ?";
		return jdbcTemplate.query(sql, new Object[] {offset, Defines.ROW_COUNT_ADMIN}, new BeanPropertyRowMapper<Contact>(Contact.class));
	}
	
	public int delItem(int id) {
		String sql = "DELETE FROM contacts WHERE id_contact = ?";
		return jdbcTemplate.update(sql, new Object[] { id });
	}

	public int addItem(Contact contact) {
		String sql = "INSERT INTO contacts(fullname, email, phone, content) VALUES (?, ?, ?, ?)";
		return jdbcTemplate.update(sql,
				new Object[] { contact.getFullname(), contact.getEmail(), contact.getPhone(), contact.getContent() });
	}
}
