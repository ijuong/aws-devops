package net.cj.edu.aws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	@GetMapping("/")
	public String home() {
		return "<h1>aws-devops (ci/cd finish)</h1>";
	}
	
	@GetMapping("/date")
	public String date() throws ClassNotFoundException {
		Class.forName("org.postgresql.Driver");

		String connurl = "jdbc:postgresql://aws-devops-db.ctskx05p3ruc.ap-northeast-2.rds.amazonaws.com:5432/postgres";
		String user = "postgres";
		String password = "12345678";
		String result = "NO_DATA";

		try (Connection connection = DriverManager.getConnection(connurl, user, password);) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT now() AS date");

			while (rs.next()) {
				result = rs.getString("date");
			}
			rs.close();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
}
