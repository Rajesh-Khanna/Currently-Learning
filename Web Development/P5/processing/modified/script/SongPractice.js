// Global variables
var clock,sound,beat,metronom,sh = 50,song_data = [],cd =['D',4,0,'XXO232',[]],volume_button,run = false;
var MINutes = 1;
// Global variables

function preload(){
	// loading sounds
  sound = loadSound("../files/MetroBeat1.wav");
  beat = loadSound("../files/MetroBar1.wav");
}
/*
	Loading chords data from database
*/

function gotSongData(Data){
	//console.log(Data.val());
	dta = Data.val()
	var keys = Object.keys(dta);
	//console.log(keys);
	for(i=0;i<keys.length;i++){
		var k = keys[i];
		var nm = dta[k].name;
		var crd = dta[k].cord;
		var Strm = dta[k].strum;
		dat = [nm,crd.slice(),Strm.slice()];
		song_data.push(dat);
	}
	console.log(song_data.length);
  practice.loadSong_data(song_data);
}

function errData(Data){
	console.log('Data************');
}
function setup() {
  // cnv = createCanvas(400, 300);
  // cnv.parent('canvs');
	/*
	// Scrolling action not requited for chord practice
  cnv.mouseWheel(function(event){
      if(event.deltaY > 0){
        if(sh<0)
          sh+=5;
      }
      else{
        if(sh>-1600)
          sh-=5;
  }
	},false);
	*/

	// configuring database
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
  song_ref = database.ref().child('songs');
  song_ref.on('value',gotSongData,errData);
  // button for volume
	volume_button = document.getElementById('volume');
	volume = createSlider(0,1,0.5,0.01);
  volume.parent('controls');
	// BPM slider
  SLider = createDiv('Beats per minute : ');

  slider = createSlider(0,120,60,1);
  //SLider.parent('controls');
  slider.id('slide');
  slider.parent(SLider);
	SLider.parent('controls');
  // Start minute changes and clock for that
	practice = new PracticeSongs();
  clock = new Clock();
  //clock.start_analog_clock(MINutes,1,400,200);
  //metronom = new Metronome(8);
  //metronom.display();
  //diagrams = new Diagrams();
}

function draw(){
  //background(rgba(255,255,255,1));
	// background(255);
	if(volume.value()==0){
    volume_button.innerHTML = 'volume_off'
  }else if (volume.value()<0.5) {
    volume_button.innerHTML = 'volume_down'
  }
  else {
    volume_button.innerHTML = 'volume_up'
  }
  practice.Bfreq = slider.value();
	if(run){
		practice.play();
  	//translate(0,sh);
  	//metronom.playCords(slider.value());
		// if(!clock.analog_clock()){
		// 	document.getElementById('state').innerHTML = 'play_arrow';
		// 	practice.stop();
		// 	clock = null;
		// 	run = false;
		// }
	}else{
		//****************************need to change font and size******************************//
		practice.play();
		//textSize(20);
		//text("Click play to start practice",100,50);
	}
  //diagrams.neckDiagram(24,1,0,sh);
}

function play_button(){
  if(document.getElementById('state').innerHTML == 'play_arrow'){
    document.getElementById('state').innerHTML = 'pause';
		practice.Start();
		clock = new Clock();
	  clock.start_analog_clock(MINutes,1,400,200);
		run = true;
	}  else{
    document.getElementById('state').innerHTML = 'play_arrow';
		practice.stop();
		clock = null;
		run = false;
	}
}

function minutesm(){
	console.log(MINutes);
	if(MINutes>1){
		console.log(MINutes);
		MINutes--;
		document.getElementById('period').innerHTML = MINutes.toString();
	}
}
function minutesp(){
	console.log(MINutes);
	if(MINutes<5){
		console.log(MINutes);
		MINutes++;
		document.getElementById('period').innerHTML = MINutes.toString();
	}
}
