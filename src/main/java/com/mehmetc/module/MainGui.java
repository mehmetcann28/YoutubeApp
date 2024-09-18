package com.mehmetc.module;

import com.mehmetc.controller.CommentController;
import com.mehmetc.controller.VideoController;
import com.mehmetc.dto.response.CommentResponseDTO;
import com.mehmetc.dto.response.VideoResponseDTO;

import java.util.List;
import java.util.Scanner;

public class MainGui {
	
	private static MainGui instance;
	private final Scanner scanner = new Scanner(System.in);
	private final VideoController videoController = VideoController.getInstance();
	private final CommentController commentController = CommentController.getInstance();
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
			for (int i = 0; i < trendingVideos.size(); i++) {
				VideoResponseDTO video = trendingVideos.get(i);
				System.out.println(i+1+". "+video.getTitle());
			}
		}
		System.out.println("----------------------------");
	}
	
	/*private void selectAndShowVideo(){
		if (trendingVideos == null || trendingVideos.isEmpty()) {
			System.out.println("Şu anda görüntülenecek video yok");
			return;
		}
		
		System.out.print("Bir video seçiniz: ");
		int videoSecim = scanner.nextInt();
		scanner.nextLine();
		
		if (videoSecim < 1 || videoSecim > trendingVideos.size()) {
			System.out.println("Geçersiz seçim");
			return;
		}
		
		VideoResponseDTO video = trendingVideos.get(videoSecim-1);
		showVideoDetails(video);
	}*/
	
	/*private void showVideoDetails(VideoResponseDTO video) {
		System.out.println("Video Title: "+video.getTitle());
		System.out.println("Video Description: "+video.getDescription());
		System.out.println("ViewCount"+video.getViewCount());
		System.out.println("LikeCount"+video.getLikeCount());
		System.out.println("DislikeCount"+video.getDislikeCount());
		
		
		List<CommentResponseDTO> commentsByVideoId = commentController.findCommentsByVideoId(video.getId());
		if (commentsByVideoId.isEmpty()) {
			System.out.println("No comments found");
		}
		else {
			System.out.println("Comments:");
			for (CommentResponseDTO comment : commentsByVideoId) {
				System.out.println(comment.getContent());
			}
		}
		System.out.println("----------------------------");
	}*/
	
	/*private void displayTrendingVideos() {
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
	}*/
	
	
	
	
	
	public int menuGui(){
		System.out.println("1. Kayıt Ol veya Giriş Yap");
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
	
}