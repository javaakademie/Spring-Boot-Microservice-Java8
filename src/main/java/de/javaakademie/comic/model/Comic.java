package de.javaakademie.comic.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comics")
public class Comic implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "comic_id")
	private int comicId;

	@Column(name = "title")
	private String title;

	@Column(name = "category")
	private String category;

	@Column(name = "created")
	LocalDate created;

	public Comic() {
		this.created = LocalDate.now();
	}

	public int getComicId() {
		return comicId;
	}

	public void setComicId(int comicId) {
		this.comicId = comicId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public LocalDate getCreated() {
		return created;
	}

	public void setCreated(LocalDate created) {
		this.created = created;
	}

}