var lc;
var PracticeSongs = function(){
  this.song_data;
  this.run = false;
  this.selectedd = null;
  this.songLength = 0;
  var metronomS;
  this.loadSong_data = function(song_data){
    this.song_data = song_data;
    console.log('******');
    console.log(song_data[0][2].length);
    console.log(song_data[0][2]);
    console.log('******');
    for (i=0;i<song_data.length;i++){
      for(j=0;j<song_data[i][2].length;j++)
        this.songLength+=song_data[i][2][j].length;
      this.song_data[i].push(this.songLength);
      this.songLength = 0;
    }
    lc = song_data;
    radioS = createRadio();
    radioS.parent('song_list');
    radioS.id('slist');
    for(i=0;i<song_data.length;i++){
      radioS.option(song_data[i][0].toString(),song_data[i][0].toString());
      //alert(cord_data[i]);
    }
    radioS.elt.onclick = function(){
      if(radioS.value()){
        //metronomS = null;
        var playing_song = radioS.value();
        var i=1;
        console.log(playing_song);
        for(i=0;lc[i][0].localeCompare(playing_song.toString())!=0;i++);
        if(this.selectedd != i){
          this.selectedd = i;
          if(document.getElementsByClassName('metronome').length)
          {
            document.getElementById('wraper').removeChild(document.getElementsByClassName('metronome')[0]);
          }

          // console.log("###"+i+"***");
          // console.log(lc[i][2].length);
          bts = 0;
          for(j=0;j<lc[i][2].length;j++){
            bts+=lc[i][2][j].length;
          }
          metronomS = new Metronome(bts);
          metronomS.display();
          metronomS.passString(lc[i][2],lc[i][1]);
        }
      }
    };
    // console.log(radio);
    // document.getElementById('song_list').addEventListener('click',this.START);
  }
  this.Start = function(){
    this.run = true;
  }
  this.play = function(){
    if(this.run){
      metronomS.changed();
      metronomS.playCords(slider.value(),0);
    }
  }

  this.stop = function(){
    metronomS.stopCords();
    this.run = false;
  }
}
