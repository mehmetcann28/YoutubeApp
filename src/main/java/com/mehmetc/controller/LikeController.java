package com.mehmetc.controller;

import com.mehmetc.dto.request.LikeSaveRequestDTO;
import com.mehmetc.dto.request.LikeUpdateRequestDTO;
import com.mehmetc.entity.Like;
import com.mehmetc.service.LikeService;

import java.util.List;
import java.util.Optional;

public class LikeController {
	
	private static LikeController instance;
	private final LikeService likeService;
	
	public LikeController() {
		this.likeService = new LikeService();
	}
	
	public static synchronized LikeController getInstance(){
		if (instance == null){
			instance= new LikeController();
		}
		return instance;
	}
	
	public Optional<Like> createLike(LikeSaveRequestDTO requestDTO) {
		return likeService.saveLike(requestDTO);
	}
	
	public Optional<Like> updateLike(LikeUpdateRequestDTO requestDTO) {
		return likeService.updateLike(requestDTO);
	}
	
	public void softDeleteLike(Long likeId) {
		likeService.softDelete(likeId);
	}
	
	public List<Like> getAllLikes() {
		return likeService.getAllLikes();
	}
	
	public Optional<Like> getLikeById(Long id) {
		return likeService.getLikeById(id);
	}
	
	public int countLikesByVideoId(Long videoId) {
		return likeService.countLikesByVideoId(videoId);
	}
	
	public int countDislikesByVideoId(Long videoId) {
		return likeService.countDislikesByVideoId(videoId);
	}
	
	public List<Like> findLikesByUserId(Long userId) {
		return likeService.findLikesByUserId(userId);
	}
	
	public Optional<Like> findByUserIdAndVideoId(Long userId, Long videoId) {
		return likeService.findByUserIdAndVideoId(userId, videoId);
	}
	
	public boolean isLikeExist(Long userId, Long videoId) {
		return likeService.isLikeExist(userId, videoId);
	}
}