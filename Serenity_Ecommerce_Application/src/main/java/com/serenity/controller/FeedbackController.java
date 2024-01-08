package com.serenity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serenity.exception.CategoryException;
import com.serenity.exception.FeedbackException;
import com.serenity.model.Category;
import com.serenity.model.Feedback;
import com.serenity.service.CategoryService;
import com.serenity.service.FeedbackService;

@RestController
@RequestMapping("/Feedback")
public class FeedbackController {

	@Autowired
	public FeedbackService feedbackService;

	@PostMapping("/addFeedback")
	public ResponseEntity<Feedback> addFeedback(@RequestBody Feedback feedback) throws FeedbackException {
		Feedback cat = feedbackService.addFeedback(feedback);
		return new ResponseEntity<>(cat, HttpStatus.CREATED);
	}

	@GetMapping("/getAllProductsByCategoryId/{fid}")
	public ResponseEntity<Feedback> getFeedbackById(@PathVariable int fid) throws FeedbackException {

		Feedback cat = feedbackService.getFeedbackById(fid);
		return new ResponseEntity<>(cat, HttpStatus.OK);
	}

	@PutMapping("/updateCategoryById/{fid}")
	public ResponseEntity<Feedback> updateFeedback(@PathVariable int fid, @RequestBody Feedback feedback)
			throws FeedbackException {
		Feedback cat = feedbackService.updateFeedback(fid, feedback);
		return new ResponseEntity<>(cat, HttpStatus.OK);
	}

	@DeleteMapping("/deleteCategoryById/{fid}")
	public ResponseEntity<Feedback> deleteFeedback(@PathVariable int fid) throws FeedbackException {
		Feedback cat = feedbackService.deleteFeedback(fid);
		return new ResponseEntity<>(cat, HttpStatus.OK);

	}

	@GetMapping("/getAllCategory")
	public ResponseEntity<List<Feedback>> getAllFeedback() throws FeedbackException {
		List<Feedback> list = feedbackService.getAllFeedback();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
