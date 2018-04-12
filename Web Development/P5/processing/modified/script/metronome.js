var Metronome = function(beats){
  this.beats = beats;
  this.beat = [];
  this.beatS = [];
  this.nu_state = false;
  this.run = true;
  this.startMetro = 0;
  this.progress = [-1,-1];
  this.display = function(){
      var metro = createDiv(' ');
      metro.class("metronome");
      metro.parent('wraper');
      for(j=0;j<this.beats;j++)
      {
        if(j%4==0){
          createElement('br').parent(metro);
          this.beatS.push(createDiv(' '));
          this.beatS[this.beatS.length -1].class('Cnames');
          this.beatS[this.beatS.length -1].parent(metro);
          this.beatS.push(createDiv(' '));
          this.beatS[this.beatS.length -1].class('Cnames');
          this.beatS[this.beatS.length -1].parent(metro);
          this.beatS.push(createDiv(' '));
          this.beatS[this.beatS.length -1].class('Cnames');
          this.beatS[this.beatS.length -1].parent(metro);
          this.beatS.push(createDiv(' '));
          this.beatS[this.beatS.length -1].class('Cnames');
          this.beatS[this.beatS.length -1].parent(metro);
          this.beatS.push(createDiv(' '));
          this.beatS[this.beatS.length -1].class('Cnames');
          this.beatS[this.beatS.length -1].parent(metro);
          this.beatS.push(createDiv(' '));
          this.beatS[this.beatS.length -1].class('Cnames');
          this.beatS[this.beatS.length -1].parent(metro);
          this.beatS.push(createDiv(' '));
          this.beatS[this.beatS.length -1].class('Cnames');
          this.beatS[this.beatS.length -1].parent(metro);
          this.beatS.push(createDiv(' '));
          this.beatS[this.beatS.length -1].class('Cnames');
          this.beatS[this.beatS.length -1].parent(metro);
          createElement('br').parent(metro);
        }
          this.beat.push(createDiv(' '));
          this.beat[this.beat.length -1].class('full_note');
          // this.beat[this.beat.length -1].elt.style.background = "#ff00ff";
          //this.beat[this.beat.length -1].innerHTML = ' ';
          this.beat[this.beat.length -1].parent(metro);
          this.beat.push(createDiv(" "));
          this.beat[this.beat.length -1].class('half_note');
          // this.beat[this.beat.length -1].elt.style.background = '#ff00ff';
          this.beat[this.beat.length -1].parent(metro);
          this.beatS[this.beatS.length -1].parent(metro);

        //   if((j+1)%3==0 && j!=0){
        //   createElement('br').parent(metro);
        //   this.beat.push(createDiv(' '));
        //   this.beat[this.beat.length -1].class('Cnames');
        //   this.beat[this.beat.length -1].parent(metro);
        //   this.beat.push(createDiv(' '));
        //   this.beat[this.beat.length -1].class('Cnames');
        //   this.beat[this.beat.length -1].parent(metro);
        //   this.beat.push(createDiv(' '));
        //   this.beat[this.beat.length -1].class('Cnames');
        //   this.beat[this.beat.length -1].parent(metro);
        //   this.beat.push(createDiv(' '));
        //   this.beat[this.beat.length -1].class('Cnames');
        //   this.beat[this.beat.length -1].parent(metro);
        //   this.beat.push(createDiv(' '));
        //   this.beat[this.beat.length -1].class('Cnames');
        //   this.beat[this.beat.length -1].parent(metro);
        //   this.beat.push(createDiv(' '));
        //   this.beat[this.beat.length -1].class('Cnames');
        //   this.beat[this.beat.length -1].parent(metro);
        //   this.beat.push(createDiv(' '));
        //   this.beat[this.beat.length -1].class('Cnames');
        //   this.beat[this.beat.length -1].parent(metro);
        //   this.beat.push(createDiv(' '));
        //   this.beat[this.beat.length -1].class('Cnames');
        //   this.beat[this.beat.length -1].parent(metro);
        // }
    }
  };

  this.passString = function(strumPattern,chordPattern){
    // console.log(chordPattern);
    pb = document.getElementsByClassName("Cnames");
    var count = 0;
    for(i=0;i<strumPattern.length;i++){
      for(j=0;j<strumPattern[i].length;j++){
        el = strumPattern[i][j].toString();
        // console.log((el));
        if(el.localeCompare("D") == 0){
          pb[2*count].innerHTML = el;
          this.beat[2*count].html(chordPattern[i]);
        }else if(el.localeCompare("U")==0){
          pb[(2*count)+1].innerHTML = el;
          this.beat[2*count+1].html(chordPattern[i]);
        }else{
          pb[2*count].innerHTML = el;
          this.beat[2*count].html(chordPattern[i]);
        }
        count++;
      }
    }
  };

  this.changed = function(){
    sound.setVolume(volume.value());
    beat.setVolume(volume.value());
    if(this.nu_state){
      if(this.progress[1]==this.beats-1)
        beat.play();
      else
        sound.play();
      this.nu_state = false;
      return true;
    }
    return false;
  };

  this.playCords = function(Bfreq,halfB){
    if(this.startMetro == 0){
      dat = new Date();
      this.startMetro = dat.getTime();
      console.log(this.startMetro);
    }
    if(this.run){
      dat = new Date();
      if(halfB)
        Btimeperiod = 30000/Bfreq;
      else {
        Btimeperiod = 15000/Bfreq;
      }
      note_count = Math.floor((dat.getTime() - this.startMetro)/Btimeperiod);
      full_count = Math.floor(note_count/2);
      //console.log(full_count);
      /*
      true should be changed to condition for full and half beat
      */
      var pb = document.getElementsByClassName('Cnames');


      if(halfB){
          if(this.progress[0]!=full_count){
              this.progress[1]++;
              this.progress[1]%=this.beats;
              this.progress[0] =full_count;
              this.nu_state = true;
          }
          if(this.progress[1]==0){
          this.beat[this.beat.length-2].elt.style.background = "#f4f4f4";
          this.beat[2*this.progress[1]].elt.style.background = "#ff00ff";
          pb[this.beat.length-2].style.background = "#00ffff";
          pb[2*this.progress[1]].style.background = "#ff00ff";
        }
        else{
          //console.log(this.progress[1]);
          this.beat[2*(this.progress[1]-1)].elt.style.background = "#f4f4f4";
          this.beat[2*(this.progress[1])].elt.style.background = "#ff00ff";
          pb[2*(this.progress[1]-1)].style.background = "#00ffff";
          pb[2*(this.progress[1])].style.background = "#ff00ff";
        }
      }else{
        if(this.progress[0]!=2*full_count){
            this.progress[1]++;
            this.progress[1]%=2*this.beats;
            this.progress[0] = 2*full_count;
            this.nu_state = true;
            }
            if(this.progress[1]==0){
            this.beat[this.beat.length-1].elt.style.background = "#f4f4f4";
            this.beat[this.progress[1]].elt.style.background = "#ff00ff";
            pb[this.beat.length-1].style.background = "#00ffff";
            pb[this.progress[1]].style.background = "#ff00ff";
          }
          else{
            //console.log(this.progress[1]);
            this.beat[(this.progress[1]-1)].elt.style.background = "#f4f4f4";
            this.beat[(this.progress[1])].elt.style.background = "#ff00ff";
            pb[(this.progress[1]-1)].style.background = "#00ffff";
            pb[(this.progress[1])].style.background = "#ff00ff";
          }
      }

    }
  }
  this.pause = function(){
    this.run = false;
  }
  this.stopCords = function(){
    var pb = document.getElementsByClassName('Cnames');
    this.beat[(this.progress[1])].elt.style.background = "#f4f4f4";
    pb[(this.progress[1])].style.background = "#00ffff";
    this.progress = [-1,-1];
    this.startMetro = 0;
  }
};
