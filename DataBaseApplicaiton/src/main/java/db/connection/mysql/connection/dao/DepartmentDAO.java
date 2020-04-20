package db.connection.mysql.connection.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import db.connection.mysql.connection.DbSQLQuery;
import db.connection.mysql.connection.model.Department;


public class DepartmentDAO {

	private static final Logger logger = Logger.getLogger(DepartmentDAO.class);

	
	public List<Department> getAll() {
		
		List<Department> departments = new ArrayList<Department>();
		
		ResultSet resultSet = DbSQLQuery.select("select * from departments");
		
		try {
			
			if(resultSet == null) {
				return departments;
			}
			while(resultSet.next()) {
				departments.add(new Department(resultSet.getString("dept_no"),resultSet.getString("dept_name")));
			}
			
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return departments;
	}
	
	
}
