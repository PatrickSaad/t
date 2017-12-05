package thesis.services;

import java.util.*;

import thesis.domain.Paper;
import thesis.repositories.PaperRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PaperService {

	@Autowired PaperRepository paperRepository;

	private Map<String, Object> toD3Format(Collection<Paper> papers) {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		List<Map<String, Object>> nodes = new ArrayList<>();

		try {
			Iterator<Paper> results = papers.iterator();
			while (results.hasNext()) {
				Paper paper = results.next();
				String paperJson = objectMapper.writeValueAsString(paper);
				
				@SuppressWarnings("unchecked")
				Map<String, Object> map = objectMapper.readValue(paperJson, Map.class);
				
				nodes.add(map);
			}
		}
		catch(Exception s){}

		jsonResult.put("nodes", nodes);
		return jsonResult;
	}
	
	@Transactional(readOnly = true)
	public Map<String, Object> getPapers(int limit) {
		Collection<Paper> result = paperRepository.getPapers(limit);
		return toD3Format(result);
	}
}
