package de.javaakademie.comic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.javaakademie.comic.model.Comic;

@Transactional
@Repository
public class ComicDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public Comic getComicById(int comicId) {
		return entityManager.find(Comic.class, comicId);
	}

	@SuppressWarnings("unchecked")
	public List<Comic> getAllComics() {
		String hql = "FROM Comic as c ORDER BY c.comicId";
		return (List<Comic>) entityManager.createQuery(hql).getResultList();
	}

	public void addComic(Comic comic) {
		entityManager.persist(comic);
	}

	public void updateComic(Comic comic) {
		Comic comicDB = getComicById(comic.getComicId());
		comicDB.setTitle(comic.getTitle());
		comicDB.setCategory(comic.getCategory());
		entityManager.flush();
	}

	public void deleteComic(int comicId) {
		entityManager.remove(getComicById(comicId));
	}

	public boolean comicExists(String title, String category) {
		String hql = "FROM Comic AS c WHERE c.title = :title AND c.category = :category";
		int count = entityManager.createQuery(hql).setParameter("title", title).setParameter("category", category)
				.getResultList().size();
		return count > 0 ? true : false;
	}
}
