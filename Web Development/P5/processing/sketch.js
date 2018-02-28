var cn,sound,beat,slider,freq,start,stop,d2,t=-1,cnv,sh = 50,cord_data = [],song_data=[],checkbox=[],practice_cords = [];
var ref,run=false,controls,uploads,Name,new_string='XXXXXX';
var radio,SP=null,CP,CLOCK;
var cd = [' ',4,0,'XXXXXX',notes=[]];
var time = 0;
var e = 0;

function preload(){
	sound = loadSound("MetroBeat1.wav");
	beat = loadSound("MetroBar1.wav");
	var config = {
     apiKey: "AIzaSyBKYePhGxUcWv2xGP2DmM7YpLYBEMsdalg",
     authDomain: "guitar-learner.firebaseapp.com",
     databaseURL: "https://guitar-learner.firebaseio.com",
     projectId: "guitar-learner",
     storageBucket: "",
     messagingSenderId: "206644498703"
   };
   firebase.initializeApp(config);
	 var database = firebase.database();
	 ref = database.ref('cords');
	 ref.on('value',gotData,errData);
}
var c_note = [],s_note=[0,0,0];


function neck_diagram(frats=24,start_frat=0,strings='XXXXXX',notes=[]){
	if(frats<4)
		frats=4;
	h = 80;
	w = 50;
	var x= 50;
	var y= 50;
	// neck bar
	strokeWeight(15);
	textSize(15);
	line(x,y,w*5+x,y);
	strokeWeight(4);
	text(start_frat.toString(),x-20,y);
	
	// strings
	Yy = y;
	Xx = x;
	for(var i=0;i<6;i++){
		text(strings[i].toString(),Xx,Yy-20);
		line(Xx,Yy,Xx,Yy+h*(frats));
		Xx+=w;
	}

	// frats	
	Yy+=h;
	pos = start_frat+1;
	for(i=0;i<frats;i++){
		text(pos.toString(),x-20,Yy);
		pos+=1;
		line(x,Yy,x+5*w,Yy);
		Yy+=h;
	}

	//notes
	for(i=0;i<notes.length;i++){
		Xx = x+(6-notes[i][0])*(w);
		Yy = y+h*(notes[i][1]-1)+h/2;
		text(notes[i][2].toString(),Xx-w/2,Yy);
		fill(255);
		ellipse(Xx, Yy, w/2, w/2);
		fill(0);
	}

	//add 6-notes
	if (!controls){
		Xx=50*Math.floor((mouseX+25)/50);
		Yy=80*Math.floor((25-sh+mouseY)/80)+10;
		if(Xx>=50 && Yy>=50 && Xx<=300 && Yy<=2000){
			ellipse(Xx, Yy, w/2, w/2);
			s_note[0] = 7-Xx/50;
			s_note[1] = (Yy-10)/80;
			s_note[2] = 0;
			text(s_note[0],Xx-w/2,Yy);
			text(s_note[1],Xx-w/2,Yy+40);
		}
	}
}

String.prototype.replaceAt=function(index, char) {
    var a = this.split("");
    a[index] = char;
    return a.join("");
}

function setup() {
	cnv = createCanvas(800, 600);
	cnv.mouseWheel(function(event){
			if(controls){}
			else{
				if(event.deltaY > 0){
					if(sh<0)
						sh+=5;
				}
				else{
					if(sh>-1600)
						sh-=5;
			}
		}
	},false);
	cnv.doubleClicked(function(event){
		if(!controls){
			z = JSON.stringify(s_note);
      v = JSON.stringify(c_note);
			if(!v.includes(z)){
				console.log(c_note.includes(s_note));
				c_note.push(s_note.slice());
				new_string = new_string.replaceAt(6-s_note[0]," ");
				console.log(c_note);
			}
			else{
				c_note.splice(v.indexOf(z)-1,1);
				console.log(v.indexOf(z));
				new_string = new_string.replaceAt(6-s_note[0],"X");
				console.log(c_note);
			}
		}
	});
	cnv.mouseClicked(function(event){
		if(!controls){
			if(new_string[6-s_note[0]]=='X'){
				new_string = new_string.replaceAt(6-s_note[0],"O");
			}
			else{
			new_string = new_string.replaceAt(6-s_note[0],"X");
			}
		}
	});
	radio = createRadio();
	practice();

}

function gotData(Data){
	//console.log(Data.val());
	dta = Data.val()
	var keys = Object.keys(dta);
	//console.log(keys);
	for(i=0;i<keys.length;i++){
		var k = keys[i];
		var nm = dta[k].name;
		var ft = dta[k].frats;
		var stf = dta[k].start_frat;
		var stg = dta[k].strings;
		var nts = dta[k].notes;
		dat = [nm,ft,stf,stg,nts.slice()]
		cord_data.push(dat);
	}
	console.log(cord_data.length);
	practice();
	practice();
}

function errData(Data){
	console.log(Data);
}

function practice(){
	CLOCK = new Clock();
	if (controls){
		resizeCanvas(400,600);
		document.getElementsByTagName('body')[0].removeChild(document.getElementById('controls'));
		b = document.getElementById('b');
		uploads = createDiv('Name : ');
		line_break = createElement('br');
		uploads.id('uploads');
		c_note = [];
		s_note=[0,0,0];
		new_string = 'XXXXXX';
		Name = createInput();
		Name.parent(uploads);
		submit = createButton('submit');
		reset = createButton('Reset');
		add_songs = createButton('add_songs');
		add_songs.id('add_songs');
		add_songs.mousePressed(function(){
			if(document.getElementById('new_song')){
				/*
				*********temp **************
				need to create a class new_cords
				till then temp
				*/
				document.getElementById('uploads').removeChild(document.getElementById('new_song'));
			}
			else{
				var load_song = new song_load();
			}
		});
		reset.mousePressed(function(){
			new_string = 'XXXXXX';
			c_note = [];
			s_note=[0,0,0];
			sh = 50;
		});
		submit.mousePressed(function(){
		mi = 30;
		mx = 0;
		for(i=0;i<c_note.length;i++){
			if(c_note[i][1]<mi){
				mi = c_note[i][1];
			}
			if(c_note[i][1]>mx){
				mx = c_note[i][1];
			}
			c_note[i][2] = (i+1);
		}
		Frats = mx-mi+1;
		sf = mi;
		if(Frats<4)
			sf-=1;
		

		data = {
				name : Name.value(),
				frats : Frats,
				start_frat : sf,
				strings : new_string,
				notes : c_note
			}
		console.log(data);
		ref.push(data);
		new_string = 'XXXXXX';
			c_note = [];
			s_note=[0,0,0];
		sh = 50;
		});
		submit.parent(uploads);
		reset.parent(uploads);
		line_break.parent(uploads);
		add_songs.parent(uploads);
		controls = null;
		b.innerHTML = 'Practice';
	}else{
		resizeCanvas(800,600);
		if(document.getElementById('uploads'))
			document.getElementsByTagName('body')[0].removeChild(document.getElementById('uploads'));
		sh = 50;
		controls = createDiv('Volume ');
		controls.id('controls');
		slider = createSlider(0,1,0.5,0.01);
		freq = createSlider(0,180,60,1);
		freq.style('width', '50%');
		start = createButton('start');
		start.attribute('disabled', '');
		slider.parent(controls);
		createElement('br').parent(controls);
		d2 = createElement('span','BPM : 60 ');
		d2.parent(controls);
		freq.parent(controls);
		createElement('br').parent(controls);
		start.parent(controls);
		start.mousePressed(function(){
			console.log(e);
			run = true;
		});
		stop = createButton('stop');
		stop.parent(controls);
		stop.mousePressed(function(){
			e=0;
			time = 0;
			run = false;
		});
		cord_selector = createDiv('');
		cord_selector.id('list_cords');
		mode = createButton('practice_songs');
		mode.id('mode');
		mode.mousePressed(function(){
			if(!SP){
				SP = new song_practice(radio,controls,song_data);
				CP = null;
			}
			else{
				CP = new cord_practice(checkbox,controls,cord_data,cord_selector);
				SP = null;
			}
		});
		b = document.getElementById('b');
		b.innerHTML = 'Add cords';
		createElement('br').parent(controls);
		mode.parent(controls);
		createElement('br').parent(controls);
		cord_selector.parent(controls);
		console.log(cord_data.length);
		//mode = createButton('Practice songs');
		for (i=0;i<cord_data.length;i++){
			if(checkbox.length!=cord_data.length){
					checkbox.push(createCheckbox((i+1).toString()+' .'+cord_data[i][0], false));
					checkbox[i].parent(cord_selector);
					checkbox[i].class('min1');
					checkbox[i].changed(min_change);
			}
			checkbox[i].parent(cord_selector);
							
		}
	}
}

function min_change(){
	m = this.elt.children[1].innerHTML;
	i=parseInt(m.split(" ")[0]);
	m=m.split(".")[1];
	if(this.checked()){
		practice_cords.push(cord_data[i-1]);
		cd = cord_data[i-1];
		if(practice_cords.length == 1)
		    start.removeAttribute('disabled');
	}
	else{
		index = practice_cords.findIndex(x => x[0]==m);
		practice_cords.splice(index,1);
		if(practice_cords.length == 0){
			start.attribute('disabled', '');
			run = false;
		}
	}
}

function draw() {
	background(255);
	translate(0,sh);
	if(controls){
		d2.html('BPM '+freq.value().toString());
		beatf = freq.value();
		bf = beatf/60;
		var T = 1000/bf;
		if(run){
			var millisecond = CLOCK.freqBPM(freq.value());
			if(t!=millisecond){
				cd = practice_cords[Math.floor(Math.random()*(practice_cords.length))];
				if(millisecond%4!=3)
					sound.play();
				else
					beat.play();
			}
			xyz = CLOCK.analogClock(e,time);
			e = xyz[0];
			time = xyz[1];			
			sound.setVolume(slider.value());
			beat.setVolume(slider.value());
			t=millisecond;
			metronome(t);
			/*if(millisecond%2){
				text("#",450,150);
			}
			else{
				text(" ",450,150);
			}
			*/
			t = millisecond;
		}
		neck_diagram(cd[1],cd[2],cd[3],cd[4]);
		
	}
	else{
		neck_diagram(24,0,new_string,c_note);
	}
	/*
	data = {
		name : "D maj",
		frats : 2,
		start_frat : 0,
		strings : "XXO   ",
		notes : [[3,2,1],[2,3,3],[1,2,2]]
	}
*/
	//ref.push(data);
}

function metronome(t){
	var pnk='#ff00ff';
	var lpnk ='#a70be0';
	c0 = pnk;
	c1 = pnk;
	c2 = pnk;
	c3 = pnk;
	textSize(25);
	switch(t%4){
    case 0:
        c0 = lpnk;
        text(cd[0],350, 130)
        break;
    case 1:
    	c1 = lpnk;
        text(cd[0],405, 130)
        break;
    case 2:
		c2 = lpnk;
        text(cd[0],460, 130)
        break;
    case 3:
		c3 = lpnk;
        text(cd[0],515, 130)
        break;
}
	strokeWeight(0);
	fill(c0);
	rect(350, 50, 50, 50, 10);
	fill(c1);
	rect(405, 50, 50, 50, 10);
	fill(c2);
	rect(460, 50, 50, 50, 10);
	fill(c3);
	rect(515, 50, 50, 50, 10);
	fill(0);
	strokeWeight(4);
}