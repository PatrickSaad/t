package thesis.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import thesis.services.PaperService;

@Controller
public class MainController {

	final PaperService paperService;

	@Autowired
	public MainController(PaperService paperService) {
		this.paperService = paperService;
	}
	
	@RequestMapping("/")
	public String home() {
		return "index";
	}
	
	@RequestMapping("me")
	public String me() {
		return "me";
	}
	
	@RequestMapping("sign")
	public String sign() {
		return "sign";
	}
	
	@RequestMapping("quizz")
	public String quizz() {
		return "quizz";
	}
	
	
	@RequestMapping("/paper/{id}")
	public String paper(@PathVariable("id") String id, Model model) {
		Map<String, Object> results = paperService.getPaper(id);
		System.out.println(results);
		
		model.addAttribute("paper", results);
		return "paper";
	}
	
	@RequestMapping("/story/{id}")
	public String story(@PathVariable("id") String id, Model model) {
		Map<String, Object> results = paperService.getPaper(id);
		model.addAttribute("paper", results);
		return "story";
	}
	
	@RequestMapping("/api-papers")
	public Map<String, Object> getPapers(@RequestParam(value = "limit",required = false) Integer limit) {
		return paperService.getPapers(limit == null ? 100 : limit);
	}
}
