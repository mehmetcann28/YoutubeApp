package com.mehmetc.module;

import com.mehmetc.controller.UserController;
import com.mehmetc.dto.request.UserSaveRequestDTO;
import com.mehmetc.entity.User;
import com.mehmetc.entity.enums.ERole;
import com.mehmetc.service.UserService;

import java.util.Optional;
import java.util.Scanner;

public class UserGui {
	
	private static UserGui instance;
	private final UserController userController = UserController.getInstance();
	private final Scanner scanner = new Scanner(System.in);
	private final UserService userService = new UserService();
	private User user;
	private final MainGui mainGui = MainGui.getInstance();
	
	private UserGui() {
	}
	
	public static synchronized UserGui getInstance() {
		if (instance == null) {
			instance = new UserGui();
		}
		return instance;
	}
	
	// Kullanıcı modülü (giriş yapma)
	public void userModule() {
	
	}
	
	
	// Giriş yapma işlemleri
	public void userLoginMenuOptions(int secim) {
		scanner.nextLine(); // Boşluğu temizlemek için
		switch (secim) {
			case 1 -> {
				user = doLogin();
				if (user != null) {
					System.out.println("Başarıyla giriş yapıldı!");
				}
				mainGui.mainGui();
			}
			case 2 -> System.out.println("Ana menüye dönüyorsunuz.");
			default -> System.out.println("Lütfen geçerli bir seçim yapınız.");
		}
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
	public User doLogin() {
		System.out.print("Kullanıcı adınızı giriniz: ");
		String username = scanner.nextLine();
		System.out.print("Şifrenizi giriniz: ");
		String password = scanner.nextLine();
		Optional<User> optUser = userController.findByUsernameAndPassword(username, password);
		return optUser.orElse(null);
	}
}