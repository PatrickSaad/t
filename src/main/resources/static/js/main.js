$(document).ready(function(){
	
	$(".btn-read-this").on('click', function(){
		$(this).toggleClass("btn-primary btn-success active");
		return false;
	});
	$("#view-quizz").on('click', function(){
		
		$("#view-quizz-alert").hide();
		$("#quizz-questions").hide();
		
		var error = false;
		
		$('.btn-read-this').each(function(i,item){
			if ( !$(item).hasClass('active') ){
				error = true;
				$("#view-quizz-alert").show().html("You did not read all pre-requesite papers");
				return false;
			}
		});
		
		if (!error){
			$("#quizz-questions").show();
			
			 $('html, body').animate({
					scrollTop: $("#quizz-questions").offset().top
			}, 500);
		}
		return false;
		
	});
		
		function getPaperById(id) {
				$.get("/papers/search/findByPaperId?id=" + encodeURIComponent(id),function (data) {
					if (!data || !data.nodes) return;
					fillSearchResults(data.nodes);
				}, "json");
				return false;
		}
		function getPaperByTitle(title) {
				$.get("/papers/search/findByTitle?title=" + encodeURIComponent(title), function (data) {
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
				$(entry_template).attr("data-id", entry.paperId);
				$(entry_template).find('.title').html(entry.title);
				$(entry_template).find('.entry').attr("href", "/paper/" + entry.paperId);
				$(entry_template).find('.content').html(entry.year);
				$(entry_template).click(function(){
					getPaperById(entry.paperId);
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
		
		if ($("#cy").length > 0){
			var elesJson = {
				nodes: [
					{ data: { id: 'a', foo: 3, bar: 5, baz: 7 } },
					{ data: { id: 'b', foo: 7, bar: 1, baz: 3 } },
					{ data: { id: 'c', foo: 2, bar: 7, baz: 6 } },
					{ data: { id: 'd', foo: 9, bar: 5, baz: 2 } },
					{ data: { id: 'e', foo: 2, bar: 4, baz: 5 } }
				],

				edges: [
					{ data: { id: 'ae', weight: 1, source: 'a', target: 'e' } },
					{ data: { id: 'ab', weight: 3, source: 'a', target: 'b' } },
					{ data: { id: 'be', weight: 4, source: 'b', target: 'e' } },
					{ data: { id: 'bc', weight: 5, source: 'b', target: 'c' } },
					{ data: { id: 'ce', weight: 6, source: 'c', target: 'e' } },
					{ data: { id: 'cd', weight: 2, source: 'c', target: 'd' } },
					{ data: { id: 'de', weight: 7, source: 'd', target: 'e' } }
				]
			};

			cytoscape({
				container: document.getElementById('cy'),
				style: cytoscape.stylesheet()
					.selector('node')
						.css({
							'background-color': '#B3767E',
							'width': 'mapData(baz, 0, 10, 10, 40)',
							'height': 'mapData(baz, 0, 10, 10, 40)',
							'content': 'data(id)'
						})
					.selector('edge')
						.css({
							'line-color': '#F2B1BA',
							'target-arrow-color': '#F2B1BA',
							'width': 2,
							'target-arrow-shape': 'circle',
							'opacity': 0.8
						})
					.selector(':selected')
						.css({
							'background-color': 'black',
							'line-color': 'black',
							'target-arrow-color': 'black',
							'source-arrow-color': 'black',
							'opacity': 1
						})
					.selector('.faded')
						.css({
							'opacity': 0.25,
							'text-opacity': 0
						}),

				elements: elesJson,

				layout: {
					name: 'circle',
					padding: 10
				},

				ready: function(){
					// ready 1
				}
			});
		}
		
})