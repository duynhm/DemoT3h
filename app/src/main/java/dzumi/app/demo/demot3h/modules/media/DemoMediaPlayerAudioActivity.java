package dzumi.app.demo.demot3h.modules.media;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;

import dzumi.app.demo.demot3h.R;

public class DemoMediaPlayerAudioActivity extends AppCompatActivity {

    CheckBox ckbMute;
    TextView tvName, tvTimer, tvVol;
    SeekBar skbTimer, skbVol;

    MediaPlayer mediaPlayer;
    AudioManager audioManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_media_player_audio);


        ckbMute = (CheckBox) findViewById(R.id.ckbMute);
        tvName = (TextView) findViewById(R.id.tvName);
        tvTimer = (TextView) findViewById(R.id.tvTimer);
        tvVol = (TextView) findViewById(R.id.tvVolume);
        skbTimer = (SeekBar) findViewById(R.id.skbTimer);
        skbVol = (SeekBar) findViewById(R.id.skbVolume);

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        initAudioManager();
        initPlayer();

        skbTimer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mediaPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


    //runnable chi run 1 lan
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //TODO: tvTimer phải định dạng theo format: hh:mm:ss
            tvTimer.setText(mediaPlayer.getCurrentPosition() + "");
            skbTimer.setProgress(mediaPlayer.getCurrentPosition());
            if(mediaPlayer.isPlaying()){
                //run them 1 lan nua
                //runnable trong postDelayed --> run trên main thread
                skbTimer.postDelayed(runnable, 1000);
            }
        }
    };

    void initAudioManager(){
        //thiet lap max cho skbVol
        skbVol.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        //thiet lap gia tri hien tai cua vol device
        skbVol.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));

        skbVol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //thiet lap lai gia tri moi cho StreamVolume
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                        progress, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //TODO: bo check khi seek
        ckbMute.setOnCheckedChangeListener((buttonView, isChecked) -> {
            audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                    isChecked? AudioManager.ADJUST_MUTE : AudioManager.ADJUST_UNMUTE ,
                    AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
        });

    }
    void initPlayer(){
        mediaPlayer = MediaPlayer.create(this, R.raw.huyen_thoai_nhau);
        mediaPlayer.setOnPreparedListener(mp -> {
            //init thong tin
            //set duration cua file nhac cho skbTimer
            skbTimer.setMax(mp.getDuration());

            //TODO: format theo dinh dang hh:mm:ss
            tvTimer.setText("00:00:00");

            //dựa vào list ds file
            tvName.setText("Name");


        });
    }

    public void doStart(View v){
        mediaPlayer.start();
        skbTimer.postDelayed(runnable, 1000);
    }
    public void doStop(View v){
        //If you wish to later replay the media, then you must
        //reset() and prepare() the MediaPlayer object before calling start() again. (create()
        //calls prepare() the first time.)
        if(mediaPlayer.isPlaying())
            mediaPlayer.stop();
    }
    public void doPause(View v){
        if(mediaPlayer.isPlaying())
            mediaPlayer.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer.isPlaying())
            mediaPlayer.stop();
    }
}
