package kr.ac.hansung.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.ac.hansung.model.Users;

@Repository
public class UsersDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public boolean insert(Users user) {

		String userid = user.getUserid();
		String password = user.getPassword();
		String name = user.getName();
		int birth = user.getBirth();
		String gender = user.getGender();
		int stamp = user.getStamp();

		int fashion = user.getFashion();
		int beauty = user.getBeauty();
		int general = user.getGeneral();
		int sports = user.getSports();
		int health = user.getHealth();

		String sqlStatement = "insert into users (userid, password, name, birth, gender, stamp, fashion, beauty, general, sports, health) values(?,?,?,?,?,?,?,?,?,?,?)";

		return (jdbcTemplate.update(sqlStatement, new Object[] { userid, password, name, birth, gender, stamp, fashion,
				beauty, general, sports, health }) == 1);
	}

	public List<Users> getUsers() {
		String sqlStatement = "select * from users";

		return jdbcTemplate.query(sqlStatement, new RowMapper<Users>() {

			@Override
			public Users mapRow(ResultSet rs, int rowNum) throws SQLException {

				Users users = new Users();

				users.setUserid(rs.getString("userid"));
				users.setPassword(rs.getString("password"));
				users.setName(rs.getString("name"));
				users.setBirth(rs.getInt("birth"));
				users.setGender(rs.getString("gender"));
				users.setStamp(rs.getInt("stamp"));
				users.setFashion(rs.getInt("fashion"));
				users.setBeauty(rs.getInt("beauty"));
				users.setGeneral(rs.getInt("general"));
				users.setSports(rs.getInt("sports"));
				users.setHealth(rs.getInt("health"));

				return users;
			}

		});
	}

	// 로그인 중복확인
	public List<Users> getIds() {
		String sqlStatement = "select userid from users";

		return jdbcTemplate.query(sqlStatement, new RowMapper<Users>() {

			@Override
			public Users mapRow(ResultSet rs, int rowNum) throws SQLException {

				Users users = new Users();

				users.setUserid(rs.getString("userid"));

				return users;
			}

		});
	}

	// 로그인
	public Users getUserInfo(String id, String password) {
		String sqlStatement = "select * from users where userid=? and password=?";
		return jdbcTemplate.queryForObject(sqlStatement, new Object[] { id, password }, new RowMapper<Users>() {

			@Override
			public Users mapRow(ResultSet rs, int rowNum) throws SQLException {

				Users user = new Users();

				user.setUserid(rs.getString("userid"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setBirth(rs.getInt("birth"));
				user.setGender(rs.getString("gender"));
				user.setStamp(rs.getInt("stamp"));
				user.setFashion(rs.getInt("fashion"));
				user.setBeauty(rs.getInt("beauty"));
				user.setGeneral(rs.getInt("general"));
				user.setSports(rs.getInt("sports"));
				user.setHealth(rs.getInt("health"));

				return user;

			}

		});
	}

	public Users getUserInfo(String id) {

		String sqlStatement = "select * from users where userid=?";
		return jdbcTemplate.queryForObject(sqlStatement, new Object[] { id}, new RowMapper<Users>() {

			@Override
			public Users mapRow(ResultSet rs, int rowNum) throws SQLException {

				Users user = new Users();

				user.setUserid(rs.getString("userid"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setBirth(rs.getInt("birth"));
				user.setGender(rs.getString("gender"));
				user.setStamp(rs.getInt("stamp"));
				user.setFashion(rs.getInt("fashion"));
				user.setBeauty(rs.getInt("beauty"));
				user.setGeneral(rs.getInt("general"));
				user.setSports(rs.getInt("sports"));
				user.setHealth(rs.getInt("health"));

				return user;

			}

		});
	}

	public boolean saveStamp(String id, int stampCnt) {

		String sqlStatement = "update users set stamp = ? where userid=?";

		System.out.println("stampCnt = " + stampCnt);

		return (jdbcTemplate.update(sqlStatement, new Object[] { stampCnt, id }) == 1);

	}

}
