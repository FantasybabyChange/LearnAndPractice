package com.augmentum.lakana.newMedia.model;

import java.util.List;

public class Video {
	private String id;
	private String type;
	private String title;

	private List<VideoPoster> videoPosters;
	private List<VideoSource> videoSources;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<VideoPoster> getVideoPosters() {
		return videoPosters;
	}
	public void setVideoPosters(List<VideoPoster> videoPosters) {
		this.videoPosters = videoPosters;
	}
	public List<VideoSource> getVideoSources() {
		return videoSources;
	}
	public void setVideoSources(List<VideoSource> videoSources) {
		this.videoSources = videoSources;
	}
}
