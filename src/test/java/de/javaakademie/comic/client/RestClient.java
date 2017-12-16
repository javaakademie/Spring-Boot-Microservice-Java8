package de.javaakademie.comic.client;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import de.javaakademie.comic.model.Comic;

public class RestClient {

	public void getComicById() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/comics/{id}";
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		ResponseEntity<Comic> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Comic.class,
				1);
		Comic comic = responseEntity.getBody();
		System.out.println(
				"Id:" + comic.getComicId() + ", Title:" + comic.getTitle() + ", Category:" + comic.getCategory());
	}

	public void getAllComics() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/comics";
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		ResponseEntity<Comic[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
				Comic[].class);
		Comic[] comics = responseEntity.getBody();
		for (Comic comic : comics) {
			System.out.println(
					"Id:" + comic.getComicId() + ", Title:" + comic.getTitle() + ", Category: " + comic.getCategory());
		}
	}

	public void addComic() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/comics";
		Comic objComic = new Comic();
		objComic.setTitle("Tales from the Crypt");
		objComic.setCategory("Horror");
		HttpEntity<Comic> requestEntity = new HttpEntity<Comic>(objComic, headers);
		URI uri = restTemplate.postForLocation(url, requestEntity);
		System.out.println(uri.getPath());
	}

	public void updateComic() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/comics";
		Comic objComic = new Comic();
		objComic.setComicId(1);
		objComic.setTitle("Silent Hill");
		objComic.setCategory("Horror");
		HttpEntity<Comic> requestEntity = new HttpEntity<Comic>(objComic, headers);
		restTemplate.put(url, requestEntity);
	}

	public void deleteComic() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/comics/{id}";
		HttpEntity<Comic> requestEntity = new HttpEntity<Comic>(headers);
		restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, 4);
	}

	public static void main(String args[]) {
		RestClient util = new RestClient();
		System.out.println("Get all comics");
		util.getAllComics();
		System.out.println("Get comic by Id");
		util.getComicById();
		System.out.println("Add comic");
		util.addComic();
		System.out.println("Update comic");
		util.updateComic();
		System.out.println("Delete comic");
		util.deleteComic();
	}
}
