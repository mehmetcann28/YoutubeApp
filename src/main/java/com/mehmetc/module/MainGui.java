package com.mehmetc.module;

import com.mehmetc.controller.VideoController;
import com.mehmetc.dto.response.VideoResponseDTO;

import java.util.List;
import java.util.Scanner;

public class MainGui {
	
	private static MainGui instance;
	private final Scanner scanner = new Scanner(System.in);
	private final VideoController videoController = VideoController.getInstance();
	private final UserGui userGui = UserGui.getInstance();
	
	
	public MainGui() {
	}
	
	public static MainGui getInstance() {
		if (instance == null) {
			instance = new MainGui();
		}
		return instance;
	}
	
	public void mainGui(){
		displayTrendingVideos();
		mainMenuOption(menuGui());
	}
	
	
	
	public int menuGui(){
		System.out.println("1. Kayıt Ol");
		System.out.println("2. Giriş Yap");
		System.out.println("0. Uygulamadan Çıkış");
		System.out.print("Seçim yap: ");
		return scanner.nextInt();
	}
	
	public void mainMenuOption(int secim){
		scanner.nextLine();
		switch (secim){
			case 1:{
				userGui.register();
				mainMenuOption(menuGui());
				break;
			}
			case 2:{
				userGui.userModule();
				mainMenuOption(menuGui());
				break;
			}
			case 0:{
				System.out.println("Çıkış yapılıyor...");
				System.exit(0);
				break;
			}
			default:{
				System.out.println("Lütfen geçerli bir seçim yapınız...");
				mainMenuOption(menuGui());
			}
		}
	}
	
	private void displayTrendingVideos() {
		System.out.println("----------------------------------");
		System.out.println("              YOUTUBE             ");
		System.out.println("----------------------------------");
		List<VideoResponseDTO> trendingVideos = videoController.findTrendingVideos(10);
		if (trendingVideos.isEmpty()) {
			System.out.println("No trending videos found");
		}
		else {
			System.out.println("Trending Videos:");
			for (VideoResponseDTO video : trendingVideos) {
				System.out.println(video);
			}
		}
		System.out.println("----------------------------");
	}
	
}