$(document).ready(function(){
		
		function getPaperById(id) {
				$.get("/papers/search/findByPaperId?id=" + encodeURIComponent(id),
								function (data) {
								if (!data || !data.nodes) return;
					fillSearchResults(data.nodes);
								}, "json");
				return false;
		}
		function getPaperByTitle(title) {
				$.get("/papers/search/findByTitle?title=" + encodeURIComponent(title), // todo fix paramter in SDN
								function (data) {
								if (!data || !data.nodes) return;
					fillSearchResults(data.nodes);
								}, "json");
				return false;
		}
		
		function fillPaperDetails(paper){
				if (!paper ) return;
			 $("#title").text(paper.title);
				 var $list = $("#people").empty();
				 paper.roles.forEach(function (cast) {
						 $.get(cast._links.person.href, function(personData) {
								 var person = personData.name;
								 $list.append($("<li>" + person + "</li>"));
						 });
				 });
		}
		
		/* Get all papers */
		function getPapers(limit = 50) {
			limit = parseInt(limit);
				$.get("/api-papers?limit=" + limit,
								function (data) {
										if (!data || !data.nodes) return;
							fillSearchResults(data.nodes);
								}, "json");
				return false;
		}
		
		function fillSearchResults(data){
			var r = $("#stories");
			var r_entries = r.find(".entries");
			
			if (!data) return;
			
			r_entries.empty();
				
			var story_template = $('#hidden-story-template').html();
			data.forEach(function (entry) {
			
				// Edit story template and insert it to results
				var entry_template = $(story_template).clone();
				$(entry_template).attr("data-id", entry.id);
				$(entry_template).find('.title').html(entry.title);
				$(entry_template).find('.content').html(entry.year);
				$(entry_template).click(function(){
					getPaperById($(this).attr("data-id"));
				});
				
				// Append entry to entries list
				r_entries.append(entry_template);
			});
			
			r.show();
		}
		
		$("#search-form").submit(function(){
				var btn = $(this).find("button");
				var stories = $("#stories");
				var query = $(this).find("input[name=s]").val();
				
				if (query != ''){
					stories.hide();
					btn.html("Searching...");
					$.get("/papers/search/findByTitleLike?title=*" + encodeURIComponent(query) + "*", function (data) {
						data = data["_embedded"].papers;
						fillSearchResults(data);
						btn.html("Search");
					}, "json");
				}
				
				return false;
		});
})