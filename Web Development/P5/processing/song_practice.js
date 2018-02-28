class song_practice{
	constructor(radio,controls,song_data){
		if(document.getElementById('list_cords'))
			document.getElementById('controls').removeChild(document.getElementById('list_cords'));
		this.song_selector = createDiv('Songs');
		this.song_selector.id('list_songs');
		this.song_selector.parent(controls);
		this.radio = radio;
		this.radio.parent(this.song_selector);
		this.radio.class('min1');
		for (i=0;i<song_data.length;i++){
			this.radio.option((i+1).toString()+' .'+song_data[i][0], 1);
		}
		document.getElementById('mode').innerHTML = 'practice cords';

	}
}

class songLine{
	constructor(j){
		this.cord_selector = createSelect();
		for(i=0;i<cord_data.length;i++){
			this.cord_selector.option(i.toString() +'. ' +cord_data[i][0]);
		}
		this.struming_pattern = createInput();
		this.line = createDiv('&#8942 ');
		this.line.id('song_line');											// change this to class
		this.line.class('song_line'+j.toString());										// change this to id
		this.cord_selector.parent(this.line);
		this.struming_pattern.parent(this.line);
		this.close = createButton('x');
		this.close.id('close');												// change this to class
		this.close.parent(this.line);
		this.close.mousePressed(function(){
			document.getElementById('new_song_lines').removeChild(document.getElementsByClassName('song_line'+j.toString())[0]);
		});
	}

	getobj_div(){
		return this.line;
	}
	getobj_cord(){
		return parseInt(this.cord_selector.value().split(".")[0]);
	}
	getStruming_p(){
		return this.struming_pattern.value();
	}

}

class song_load{
	constructor(){
		add_songs.html('add_cords');
		var new_song = createDiv('Enter the cords and struming patterns <br/>');
		new_song.id('new_song');
		var new_song_lines = createDiv('');
		new_song_lines.id('new_song_lines');
		new_song_lines.parent(new_song);
		new_song.parent(uploads);
		var struming = [];
		struming.push(new songLine(0));
		struming[0].getobj_div().parent(new_song_lines);
		var new_line = createButton('new_line');
		new_line.parent(new_song); 									// temp
		new_line.mousePressed(function(){
			var l = struming.length;
			struming.push(new songLine(l));
			struming[l].getobj_div().parent(new_song_lines);	
		});
		var sub = createButton('Submit');
		sub.parent(new_song); 										 // temp
		sub.mousePressed(function(){
				for(i=0;i<struming.length;i++)
				{
					console.log(struming[i].getobj_cord());
					console.log(struming[i].getStruming_p());
				}
			});
	}
}