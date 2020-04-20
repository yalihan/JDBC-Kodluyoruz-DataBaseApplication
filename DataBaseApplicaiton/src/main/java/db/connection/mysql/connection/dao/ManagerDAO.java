package db.connection.mysql.connection.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import db.connection.mysql.connection.DbSQLQuery;
import db.connection.mysql.connection.model.Employee;
import db.connection.mysql.connection.model.Manager;

public class ManagerDAO {
	
	private static final Logger logger = Logger.getLogger(ManagerDAO.class);

	public List<Manager> loadAllActiveManagers() {
		
		List<Manager> managers = new ArrayList<Manager>();
		
		String sql=
		"SELECT e.*, dp.dept_name FROM employees e inner JOIN dept_manager dm ON e.emp_no = dm.emp_no inner JOIN departments dp ON dp.dept_no = dm.dept_no";
		ResultSet resultSet = DbSQLQuery.select(sql);
		
		try {
			if(resultSet == null) {
				return managers;
			}
			
			while(resultSet.next()) {
				managers.add(getManager(resultSet));
			}
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		
		
		return managers;
	}
	
	
	public Manager getManager(ResultSet resultSet) throws SQLException {
		Manager manager = new Manager(getEmployee(resultSet), resultSet.getString("dept_name"));
		
		return manager;
	}
	
	private Employee getEmployee(ResultSet resultSet) throws SQLException {
		
		Employee employee = new Employee();
		
		employee.setId(resultSet.getLong("emp_no"));
		employee.setName(resultSet.getString("first_name"));
		employee.setLastName(resultSet.getString("last_name"));
		employee.setGender(resultSet.getString("gender"));
		employee.setBirthDate(resultSet.getDate("birth_date"));
		employee.setHireDate(resultSet.getDate("hire_date"));
		
		return employee;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
