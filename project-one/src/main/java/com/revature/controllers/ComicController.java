package com.revature.controllers;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.exceptions.GlobalExceptionHandler;
import com.revature.models.Comics;
import com.revature.services.ComicService;

@RestController
@RequestMapping("/comics")
public class ComicController {

	private ComicService cs;

	private static final Logger LOG = LoggerFactory.getLogger(ComicController.class);

	public ComicController(ComicService cs) {
		super();
		this.cs = cs;
	} 
	
	@GetMapping
	public ResponseEntity<List<Comics>> getAllComics(@RequestParam(name="name",required=false)String name,
			@RequestParam(name="genre",required=false)String genre, @RequestParam(name ="type", required=false)String type) {
		
		if(name != null) {
			LOG.info("Name param was used.");
			return new ResponseEntity<>(cs.getComicByName(name),HttpStatus.OK);
		}
		if(genre != null) {
			LOG.info("Genre param was used.");
			return new ResponseEntity<>(cs.getComicByGenre(genre),HttpStatus.OK);
		}
		if(type != null) {
			LOG.info("Type param was used.");
			return new ResponseEntity<>(cs.getComicByType(type), HttpStatus.OK);
		}

		return new ResponseEntity<>(cs.getAllComics(), HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Comics> getComicById(@PathVariable("id") int id) {

		return new ResponseEntity<>(cs.getComicById(id), HttpStatus.OK);
	}
	
	

	@PostMapping
	public ResponseEntity<String> createComic(@RequestBody Comics comic) {
		MDC.put("requestId", UUID.randomUUID().toString());
		
		cs.createComic(comic);
		LOG.info("A new comic was created");
		return new ResponseEntity<>("Comic was created!", HttpStatus.CREATED);
	}

	

	@PutMapping("/{id}")
	public ResponseEntity<Comics> updateComic(@PathVariable("id") int id, @RequestBody Comics comic) {
		MDC.put("requestId", UUID.randomUUID().toString());
		
		
		LOG.info("The comic of id: "+id+ " was updated!");
		return new ResponseEntity<>(cs.updateComic(id, comic), HttpStatus.OK);
	}

	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteComic(@PathVariable("id") int id) {
		MDC.put("requestId", UUID.randomUUID().toString());
		cs.deleteComic(id);
		LOG.info("The comic of id: "+id+" was deleted!");
		return new ResponseEntity<>("Comic of id: " + id + " was deleted", HttpStatus.OK);

	}
	
	
	
	
	
	
	
	
	
}//end of CC
