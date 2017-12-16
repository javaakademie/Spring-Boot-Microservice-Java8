package de.javaakademie.comic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import de.javaakademie.comic.model.Comic;
import de.javaakademie.comic.service.ComicService;

@RestController
@RequestMapping("comics")
public class ComicController {

	@Autowired
	private ComicService comicService;

	@GetMapping
	public ResponseEntity<List<Comic>> getAllComics() {
		List<Comic> list = comicService.getAllComics();
		return new ResponseEntity<List<Comic>>(list, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<Comic> getComicById(@PathVariable("id") Integer id) {
		Comic comic = comicService.getComicById(id);
		return new ResponseEntity<Comic>(comic, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Void> addComic(@RequestBody Comic comic, UriComponentsBuilder builder) {
		boolean flag = comicService.addComic(comic);
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/comic/{id}").buildAndExpand(comic.getComicId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Comic> updateComic(@RequestBody Comic comic) {
		comicService.updateComic(comic);
		return new ResponseEntity<Comic>(comic, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteComic(@PathVariable("id") Integer id) {
		comicService.deleteComic(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}