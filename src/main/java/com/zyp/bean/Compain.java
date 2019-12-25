package com.zyp.bean;

public class Compain {
	private Integer id;
	private String complaintype;
	private String urlip;
	private int article_id;
	private int user_id;
	private User user;
	private String title;
	private String content;
	private String complainCnt;
	private String username;
	public Compain() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Compain(Integer id, String complaintype, String urlip, int article_id, User user) {
		super();
		this.id = id;
		this.complaintype = complaintype;
		this.urlip = urlip;
		this.article_id = article_id;
		this.user = user;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getComplainCnt() {
		return complainCnt;
	}
	public void setComplainCnt(String complainCnt) {
		this.complainCnt = complainCnt;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getComplaintype() {
		return complaintype;
	}
	public void setComplaintype(String complaintype) {
		this.complaintype = complaintype;
	}
	public String getUrlip() {
		return urlip;
	}
	public void setUrlip(String urlip) {
		this.urlip = urlip;
	}
	public int getArticle_id() {
		return article_id;
	}
	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Compain [id=" + id + ", complaintype=" + complaintype + ", urlip=" + urlip + ", article_id="
				+ article_id + ", user=" + user + "]";
	}
	
	
}
