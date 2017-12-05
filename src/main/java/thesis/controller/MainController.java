package thesis.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	
	@RequestMapping("/api-papers")
	public Map<String, Object> getPapers(@RequestParam(value = "limit",required = false) Integer limit) {
		return paperService.getPapers(limit == null ? 100 : limit);
	}
}
