package ar.com.lps.prototipoLPS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import ar.com.lps.prototipoLPS.model.Employee;

@Component
public class DatabaseLoader implements CommandLineRunner {

	private final EmployeeRepository employees;
	private final ManagerRepository managers;

	@Autowired
	public DatabaseLoader(EmployeeRepository employeeRepository, ManagerRepository managerRepository) {

		this.employees = employeeRepository;
		this.managers = managerRepository;
	}

	public void run(String... strings) throws Exception {

		Manager rolando = this.managers.save(new Manager("rolando", "rolandolps", "ROLE_MANAGER"));

		Manager cristian = this.managers.save(new Manager("cristian", "crislps", "ROLE_MANAGER"));

		Manager sonia = this.managers.save(new Manager("sonia", "sonialps", "ROLE_MANAGER"));

		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("rolando",
				"doesn't matter", AuthorityUtils.createAuthorityList("ROLE_MANAGER")));

		this.employees.save(new Employee("Frodo", "Baggins", "ring bearer", rolando));
		this.employees.save(new Employee("Bilbo", "Baggins", "burglar", rolando));
		this.employees.save(new Employee("Gandalf", "the Grey", "wizard", rolando));

		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("cristian",
				"doesn't matter", AuthorityUtils.createAuthorityList("ROLE_MANAGER")));

		this.employees.save(new Employee("Samwise", "Gamgee", "gardener", cristian));
		this.employees.save(new Employee("Merry", "Brandybuck", "pony rider", cristian));
		this.employees.save(new Employee("Peregrin", "Took", "pipe smoker", cristian));

		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("sonia",
				"doesn't matter", AuthorityUtils.createAuthorityList("ROLE_MANAGER")));

		this.employees.save(new Employee("Legolas", "Gamgee", "gardener", sonia));
		this.employees.save(new Employee("Aragon", "Brandybuck", "pony rider", sonia));
		this.employees.save(new Employee("El enano", "Took", "pipe smoker", sonia));

		SecurityContextHolder.clearContext();
	}
}
