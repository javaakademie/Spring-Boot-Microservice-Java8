package de.javaakademie.comic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.javaakademie.comic.dao.ComicDAO;
import de.javaakademie.comic.model.Comic;

@Service
public class ComicService {

	@Autowired
	private ComicDAO comicDAO;

	public Comic getComicById(int comicId) {
		Comic comic = comicDAO.getComicById(comicId);
		return comic;
	}

	public List<Comic> getAllComics() {
		return comicDAO.getAllComics();
	}

	public synchronized boolean addComic(Comic comic) {
		if (comicDAO.comicExists(comic.getTitle(), comic.getCategory())) {
			return false;
		} else {
			comicDAO.addComic(comic);
			return true;
		}
	}

	public void updateComic(Comic comic) {
		comicDAO.updateComic(comic);
	}

	public void deleteComic(int comicId) {
		comicDAO.deleteComic(comicId);
	}

}
