// Global variables
var clock,sound,beat,metronom,sh = 50,cord_data = [],cd =['D',4,0,'XXO232',[]];
// Global variables

function preload(){
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
  practice.loadCord_data(cord_data)
}

function errData(Data){
	console.log('Data************');
}


function setup() {
  cnv = createCanvas(800, 600);
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
  sound = loadSound("files\\MetroBar1.wav");
  beat = loadSound("files\\MetroBar1.wav");
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
  ref.once('value',gotData,errData);
  console.log(cord_data.length);

  practice = new MinChanges(60,4,cord_data);
  cnv.parent('canvs');
  clock = new Clock();
  clock.start_analog_clock(1,1,400,200);
  //metronom = new Metronome(22);
  //metronom.display();
  slider = createSlider(0,120,60,1);
  console.log('****');
  console.log(cord_data.length);
  console.log('****');
  //diagrams = new Diagrams();
}

function draw(){
  background(255);
  practice.Bfreq = slider.value();
  practice.play();
  translate(0,sh);
  //metronom.playCords(slider.value());
  clock.analog_clock();
  //diagrams.neckDiagram(24,1,0,sh);
}
