package com.augmentum.lakana.newMedia.model;


public class ArticleItem {
	private String headline;
	private String subHeadline;
	private String shortHeadline;
	private Categories categories;
	private String abstractString;
	private String link;
	private String body;
	private Inline inline;
	//private media:content
	private int guid;
	private String keywords;
	private String pubDate;
	private String lastEditDate;
	private String copyright;
	private RelatedNews relatedNews;
	private Issue issue;
	private Video video;
	public String getHeadline() {
		return headline;
	}
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	public String getSubHeadline() {
		return subHeadline;
	}
	public void setSubHeadline(String subHeadline) {
		this.subHeadline = subHeadline;
	}
	public String getShortHeadline() {
		return shortHeadline;
	}
	public void setShortHeadline(String shortHeadline) {
		this.shortHeadline = shortHeadline;
	}
	public Categories getCategories() {
		return categories;
	}
	public void setCategories(Categories categories) {
		this.categories = categories;
	}
	public String getAbstractString() {
		return abstractString;
	}
	public void setAbstractString(String abstractString) {
		this.abstractString = abstractString;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Inline getInline() {
		return inline;
	}
	public void setInline(Inline inline) {
		this.inline = inline;
	}
	public int getGuid() {
		return guid;
	}
	public void setGuid(int guid) {
		this.guid = guid;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public String getLastEditDate() {
		return lastEditDate;
	}
	public void setLastEditDate(String lastEditDate) {
		this.lastEditDate = lastEditDate;
	}
	public String getCopyright() {
		return copyright;
	}
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}
	public RelatedNews getRelatedNews() {
		return relatedNews;
	}
	public void setRelatedNews(RelatedNews relatedNews) {
		this.relatedNews = relatedNews;
	}
	public Issue getIssue() {
		return issue;
	}
	public void setIssue(Issue issue) {
		this.issue = issue;
	}
	public Video getVideo() {
		return video;
	}
	public void setVideo(Video video) {
		this.video = video;
	}

}
