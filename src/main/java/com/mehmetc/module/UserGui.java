package com.mehmetc.module;

import com.mehmetc.controller.UserController;
import com.mehmetc.dto.request.UserSaveRequestDTO;
import com.mehmetc.dto.response.UserResponseDTO;
import com.mehmetc.entity.User;
import com.mehmetc.entity.enums.ERole;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserGui {
	
	private static UserGui instance;
	private final UserController userController = UserController.getInstance();
	private final Scanner scanner = new Scanner(System.in);
	private User loggedInUser;
	
	UserGui() {
	}
	
	public static synchronized UserGui getInstance() {
		if (instance == null) {
			instance = new UserGui();
		}
		return instance;
	}
	
	// Kullanıcı kaydı
	public void register() {
		System.out.print("Adınız: ");
		String name = scanner.nextLine();
		System.out.print("Soyadınız: ");
		String surname = scanner.nextLine();
		System.out.print("Email: ");
		String email = scanner.nextLine();
		System.out.print("Kullanıcı Adı: ");
		String username = scanner.nextLine();
		System.out.print("Şifre: ");
		String password = scanner.nextLine();
		
		ERole role = ERole.USER;
		
		UserSaveRequestDTO userSaveRequestDTO = new UserSaveRequestDTO(name, surname, email, username, password, role);
		userController.saveUser(userSaveRequestDTO);
		System.out.println("Kayıt başarılı.");
	}
	
	// Kullanıcı girişi işlemi
	public boolean userModule() {
		System.out.print("Kullanıcı adınızı giriniz: ");
		String username = scanner.nextLine();
		System.out.print("Şifrenizi giriniz: ");
		String password = scanner.nextLine();
		Optional<User> optUser = userController.findByUsernameAndPassword(username, password);
		if (optUser.isPresent()) {
			loggedInUser = optUser.get();
			System.out.println("Giriş başarılı, kullanıcı ID: " + loggedInUser.getId());
			return true;
		} else {
			System.out.println("Giriş başarısız");
			return false;
		}
	}
	
	// Kullanıcı profili görüntüleme
	public void showUserProfile() {
		if (loggedInUser != null) {
			System.out.println("Profiliniz:");
			System.out.println("Ad: " + loggedInUser.getName());
		} else {
			System.out.println("Giriş yapmadınız");
		}
	}
	
	// Kayıtlı kullanıcıları listeleme
	public void listRegisteredUsers() {
		List<UserResponseDTO> allUsers = userController.getAllUsers();
		for (UserResponseDTO user : allUsers) {
			System.out.println("Adı: " + user.getName());
		}
	}
	
	public String getLoggedInUserName() {
		return loggedInUser != null ? loggedInUser.getName() : null; //Ternary
	}
	
	// Giriş yapan kullanıcının İD sini alma
	public Long getLoggedInUserId() {
		return loggedInUser != null ? loggedInUser.getId() : null;
	}
}