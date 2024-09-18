package com.mehmetc.module;

import com.mehmetc.controller.CommentController;
import com.mehmetc.controller.LikeController;
import com.mehmetc.controller.VideoController;
import com.mehmetc.dto.request.CommentSaveRequestDTO;
import com.mehmetc.dto.request.LikeSaveRequestDTO;
import com.mehmetc.dto.response.CommentResponseDTO;
import com.mehmetc.dto.response.UserResponseDTO;
import com.mehmetc.dto.response.VideoResponseDTO;
import com.mehmetc.entity.Like;
import com.mehmetc.entity.enums.ECategory;
import com.mehmetc.entity.enums.ELikeStatus;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class VideoGui {
	
	private static VideoGui instance;
	private final VideoController videoController = VideoController.getInstance();
	private final CommentController commentController = CommentController.getInstance();
	private final LikeController likeController = LikeController.getInstance();
	private final UserGui userGui = UserGui.getInstance();
	private final Scanner scanner = new Scanner(System.in);
	
	public VideoGui() {
	}
	
	public static VideoGui getInstance() {
		if (instance == null) {
			instance = new VideoGui();
		}
		return instance;
	}
	
	// Trend videoları sayısal olaraka seçme ve işlemleri
	public void trendVideos(boolean isUserLoggedIn) {
		List<VideoResponseDTO> trendingVideos = videoController.findTrendingVideos(10);
		System.out.print("Bir video seçin (0: Geri Dön): ");
		int secim = scanner.nextInt();
		if (secim > 0 && secim <= trendingVideos.size()) {
			VideoResponseDTO trendingVideo = trendingVideos.get(secim - 1);
			
		}
	}
	
	/*public void showVideoDetails(VideoResponseDTO video, boolean isUserLoggedIn) {
		System.out.println("Video Title: " + video.getTitle());
		System.out.println("Video Description: " + video.getDescription());
		System.out.println("ViewCount" + video.getViewCount());
		System.out.println("LikeCount" + video.getLikeCount());
		System.out.println("DislikeCount" + video.getDislikeCount());
		
		videoController.increaseViewCount(video.getId());
		
		if (isUserLoggedIn) {
			System.out.println("1. Yorum Yap");
			System.out.println("2. Tepki Ver (Like/Dislike)");
		}
		else {
			System.out.println("1. Giriş Yap");
			System.out.println("2. Kayıt Ol");
		}
		
		System.out.print("Seçim Yap");
		int secim = scanner.nextInt();
		if (isUserLoggedIn) {
			switch (secim) {
				case 1: {
				
				}
				case 2: {
				
				}
			}
		}
		else {
			System.out.println("Yorum yapmak ve tepki vermek için giriş yapmanız veya kaydolmanız gerekmektedir.");
		}
	}*/
	
	private void makeComment(Long videoId, Long userId) {
		if (userId == null) {
			System.out.println("Yorum yapabilmek için giriş yapmanız gerekmektedir");
			System.out.println("1. Giriş Yap");
			System.out.println("2. Kayıt Ol");
			int secim = scanner.nextInt();
			scanner.nextLine();
			
			if (secim == 1) {
				userGui.userModule();
				userId = userGui.getLoggedInUserId();
			}
			else {
				return;
			}
		}
		
		System.out.println("Yorumunuzu giriniz:");
		String content = scanner.nextLine();
		
		CommentSaveRequestDTO requestDTO = new CommentSaveRequestDTO(content, videoId, userId);
		
		Optional<CommentResponseDTO> savedComment = commentController.createComment(requestDTO);
		
		if (savedComment.isPresent()) {
			System.out.println("Yorum başarıyla yapıldı");
		}
		else {
			System.out.println("Yorum kaydedilirken hata oluştu");
		}
	}
	
	// Like veya Dislike verme
	private void giveLikeorDislike(Long videoId,Long userId){
		System.out.println("1. Like");
		System.out.println("2. Dislike");
		int secim = scanner.nextInt();
		if (secim == 1) {
			likeController.createLike(new LikeSaveRequestDTO(userId,videoId,ELikeStatus.LIKE));
			System.out.println("Like verildi");
		}
		else if (secim == 2) {
			likeController.createLike(new LikeSaveRequestDTO(userId,videoId,ELikeStatus.DISLIKE));
			System.out.println("Dislike verildi");
		}
	}
	
	public void listByCategory(boolean isUserLoggedIn, UserResponseDTO user) {
		System.out.println("Kategoriler:");
		System.out.println("1. Spor");
		System.out.println("2. Müzik");
		System.out.println("3. Teknoloji");
		System.out.println("4. Oyun");
		System.out.println("5. Eğlence");
		System.out.print("Seçim yap: ");
		int secim = scanner.nextInt();
		scanner.nextLine();
		
		ECategory secimCategory = switch (secim){
			case 1 -> ECategory.SPORTS;
			case 2 -> ECategory.MUSIC;
			case 3 -> ECategory.ENTERTAINMENT;
			case 4 -> ECategory.TECHNOLOGY;
			case 5 -> ECategory.GAMES;
			default -> null;
		};
		
		if (secimCategory == null) {
			System.out.println("Geçersiz kategori seçimi");
			return;
		}
		
		List<VideoResponseDTO> videosByCategory = videoController.findVideosByCategory(secimCategory);
		if (videosByCategory.isEmpty()) {
			System.out.println("Bu kategoride video bulunamadı");
		}else {
			for (int i = 0; i < videosByCategory.size(); i++) {
				VideoResponseDTO video = videosByCategory.get(i);
				System.out.println((i+1)+". "+video.getTitle());
			}
			
			System.out.println("Bir video seçin veya geri dönmek için 0'ı seçin: ");
			int videoSecim = scanner.nextInt();
			scanner.nextLine();
			
			if (videoSecim > 0 && videoSecim <= videosByCategory.size()) {
				VideoResponseDTO video = videosByCategory.get(videoSecim - 1);
				displayVideoDetails(video, isUserLoggedIn,user);
			}
		}
	}
	
	private void displayVideoDetails(VideoResponseDTO video, boolean isUserLoggedIn,UserResponseDTO user) {
		System.out.println("Video Title: " + video.getTitle());
		System.out.println("Video Description: " + video.getDescription());
		System.out.println("ViewCount" + video.getViewCount());
		System.out.println("LikeCount" + video.getLikeCount());
		System.out.println("DislikeCount" + video.getDislikeCount());
		
		videoController.increaseViewCount(video.getId());
		
		System.out.println("1. Yorum Yap");
		System.out.println("2. Like/Dislike Ver");
		System.out.println("0. Geri Dön");
		
		int secim = scanner.nextInt();
		scanner.nextLine();
		
		if (secim == 1 || secim == 2) {
			if (!isUserLoggedIn) {
				System.out.println("Yorum yapmak ve tepki vermek için giriş yapmalısınız");
				System.out.println("1. Giriş Yap");
				System.out.println("2. Kayıt Ol");
				System.out.println("0. Geri Dön");
				int loginSelection = scanner.nextInt();
				scanner.nextLine();
				if (loginSelection == 1) {
					userGui.userModule();
				}
				else if (loginSelection == 2) {
					userGui.register();
				}
			}else {
				if (secim == 1) {
					System.out.println("Yorumunuz: ");
					String content = scanner.nextLine();
					CommentSaveRequestDTO requestDTO = new CommentSaveRequestDTO(content, video.getId(), user.getId());
					commentController.createComment(requestDTO);
				}
				else if (secim == 2) {
					System.out.println("1. Like");
					System.out.println("2. Dislike");
					int likeSelection = scanner.nextInt();
					scanner.nextLine();
					ELikeStatus likeStatus = likeSelection == 1 ? ELikeStatus.LIKE : ELikeStatus.DISLIKE;
					LikeSaveRequestDTO requestDTO = new LikeSaveRequestDTO(video.getId(), user.getId(), likeStatus);
					videoController.incrementLikeCount(video.getId());
				}
			}
		}
	}
	
}