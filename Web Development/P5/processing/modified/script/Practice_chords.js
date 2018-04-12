var cl = [],lc;
function checkedChords(){
    var m = this.elt.children[1].innerHTML;
    for(i=0;lc[i][0]!=m;i++);
    if(this.checked()){
  		cl.push(lc[i]);
  		this.cd = lc[i];
  		//if(practice_cords.length == 1)
  		 //   start.removeAttribute('disabled');
  	}
  	else{
      console.log(cl);
  		index = cl.findIndex(x => x[0]==m);
  		cl.splice(index,1);
  		//if(practice_cords.length == 0){
  		//	start.attribute('disabled', '');
  		//	run = false;
  		//}
  	}
  };

  var MinChanges = function(Bfreq,beats){
  this.startTime = 0;
  this.Bfreq = Bfreq;
  this.beats = beats;
  this.metro = new Metronome(beats);
  this.metro.display();
  this.cord_data = [];
  this.diagrams = new Diagrams();
  this.cd = ['',4,0,'OOOOOO',[]];
  this.run = false;

  this.cord_change = function(){
    if(this.cord_data.length>0)
      this.cd = this.cord_data[Math.floor(Math.random()*3)];
    console.log(this.cd);
  }

  this.loadCord_data = function(cord_data){
    this.cord_data = cord_data;
    lc = cord_data;
    for(i=0;i<cord_data.length;i++){
      var cbox = createElement('ul');
      checkbox = createCheckbox(cord_data[i][0], false);
      checkbox.class('clist');
      checkbox.changed(checkedChords);
      checkbox.parent(cbox);
      cbox.parent('chord_list');
      //alert(cord_data[i]);
    }
  }

  this.play = function(){
    if(this.run){
  this.metro.playCords(this.Bfreq,1);
    if(this.metro.changed()){
      if(cl.length>0)
        this.cd = cl[Math.floor(Math.random()*cl.length)];
      else
        this.cd = ['',4,0,'OOOOOO',[]];
      }
    }
    this.diagrams.chord_diagram(4,0,this.cd[3],[],1,0);
  };

  // stop in middle
  this.stop = function(){
    this.metro.stopCords();
    this.run = false;
  };
  // start playing
  this.Start = function(){
    this.run = true;
  };
};
