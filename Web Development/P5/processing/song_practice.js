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

class song_load{
	constructor(){
		
	}
}