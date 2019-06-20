package vconnecting;

import java.util.ArrayList;
import java.util.Arrays;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import vconnecting.model.Category;
import vconnecting.model.Language;
import vconnecting.model.Role;
import vconnecting.model.User;
import vconnecting.repository.CategoryRepository;
import vconnecting.service.UserService;

@SpringBootApplication
public class JwtAuthServiceApp implements CommandLineRunner {

	@Autowired
	UserService userService;

	@Autowired
	CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(JwtAuthServiceApp.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... params) throws Exception {

		Category category = new Category();
		category.setName("Doctors");
		category = categoryRepository.save(category);
		Category subCategory1 = new Category();
		subCategory1.setParentCategoryId(category.getId());
		subCategory1.setName("Ortho");
		categoryRepository.save(subCategory1);

		User admin = new User();
		admin.setName("admin");
		admin.setPassword("admin");
		admin.setEmail("admin@email.com");
		admin.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_ADMIN)));

		userService.signup(admin);

		User client = new User();
		client.setName("user");
		client.setPassword("user");
		client.setEmail("user@email.com");
		client.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_USER)));

		userService.signup(client);

		User expert = new User();
		expert.setName("expert");
		expert.setPassword("expert");
		expert.setEmail("expert@email.com");
		expert.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_EXPERT)));
		expert.setCategoryId(subCategory1.getId());
		
		// Create languages
		Language lang1 = new Language("English");
		Language lang2 = new Language("Hindi");
		Language lang3 = new Language("Gujarati");
		expert.getLanguages().add(lang1);
		expert.getLanguages().add(lang2);
		expert.getLanguages().add(lang3);

		lang1.getUsers().add(expert);
		lang2.getUsers().add(expert);
		lang3.getUsers().add(expert);

		userService.signup(expert);

	}

}
