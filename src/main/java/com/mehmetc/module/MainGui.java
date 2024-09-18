package com.mehmetc.module;

import com.mehmetc.controller.CommentController;
import com.mehmetc.controller.VideoController;
import com.mehmetc.dto.response.CommentResponseDTO;
import com.mehmetc.dto.response.UserResponseDTO;
import com.mehmetc.dto.response.VideoResponseDTO;
import com.mehmetc.entity.User;

import java.util.List;
import java.util.Scanner;

public class MainGui {
	
	private static MainGui instance;
	private final Scanner scanner = new Scanner(System.in);
	private final VideoController videoController = VideoController.getInstance();
	private final UserGui userGui = new UserGui();
	private final VideoGui videoGui = VideoGui.getInstance();
	private final UserResponseDTO userResponseDTO = new UserResponseDTO();
	private boolean isUserLoggedIn = false; // Kullanıcı giriş yaptı mı?
	private String loggedInUsername = null;
	
	
	public MainGui() {
	}
	
	public static MainGui getInstance() {
		if (instance == null) {
			instance = new MainGui();
		}
		return instance;
	}
	
	public void mainGui() {
		while (true) {
			displayTrendingVideos();
			int secim = menuGui();
			mainMenuOption(secim);
		}
		
	}
	
	// Trend videoları listeleme
	private void displayTrendingVideos() {
		System.out.println("----------------------------------");
		System.out.println("             YOUTUBE              ");
		if (isUserLoggedIn) {
			System.out.println("Hoşgeldiniz, " + loggedInUsername);
		}
		System.out.println("----------------------------------");
		List<VideoResponseDTO> trendingVideos = videoController.findTrendingVideos(10);
		if (trendingVideos.isEmpty()) {
			System.out.println("No trending videos found");
		}
		else {
			System.out.println("Tüm Videolar");
			for (int i = 0; i < trendingVideos.size(); i++) {
				VideoResponseDTO video = trendingVideos.get(i);
				System.out.println(i + 1 + ". " + video.getTitle());
			}
		}
		System.out.println("----------------------------------");
	}
	
	public int menuGui() {
		System.out.println("1. Kayıt Ol");
		System.out.println("2. Giriş Yap");
		System.out.println("3. Kayıtlı Kullanıcıları Listele");
		System.out.println("4. Trend Videoları Listele");
		System.out.println("5. Kategoriye Göre Listele");
		System.out.println("6. Video Ara");
		if (isUserLoggedIn) {
			System.out.println("7. Profilimi Görüntüle");
		}
		System.out.println("0. Uygulamadan çıkış");
		System.out.print("Seçim yap: ");
		return scanner.nextInt();
	}
	
	public void mainMenuOption(int secim) {
		scanner.nextLine();
		switch (secim) {
			case 1: {
				userGui.register();
				break;
			}
			case 2: {
				isUserLoggedIn = userGui.userModule();
				if (isUserLoggedIn) {
					loggedInUsername = userGui.getLoggedInUserName();
				}
				break;
			}
			case 3: {
				userGui.listRegisteredUsers();
				break;
			}
			case 4: {
				displayTrendingVideos();
				videoGui.trendVideos(isUserLoggedIn);
				break;
			}
			case 5: {
				videoGui.listByCategory(isUserLoggedIn,userResponseDTO);
				break;
			}
			case 6: {
				break;
			}
			case 7: {
				if (isUserLoggedIn) {
					userGui.showUserProfile();
				}
				else {
					System.out.println("Bu işlemi yapmak için giriş yapmalısınız");
				}
				break;
			}
			case 0: {
				System.out.println("Çıkış yapılıyor....");
				System.exit(0);
				break;
			}
			default: {
				System.out.println("Lütfen geçerli bir seçim yapınız....");
				mainMenuOption(menuGui());
			}
		}
	}
	
}