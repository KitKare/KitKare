package kitkare.kitkare.app.activities;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.VideoView;

import java.io.File;
import java.util.ArrayList;

import kitkare.kitkare.R;
import kitkare.kitkare.app.data.interfaces.IUpdatePageData;
import kitkare.kitkare.app.tasks.device.TurnCameraOffTask;
import kitkare.kitkare.app.tasks.device.TurnCameraOnTask;

//http://code.tutsplus.com/tutorials/streaming-video-in-android-apps--cms-19888
public class CameraActivity extends Activity { //implements IUpdatePageData
    private final Context context = this;
    VideoView vidView;
    ProgressBar progressBar;
   // Object fileResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
//        TurnCameraOnTask giveWaterTask = new TurnCameraOnTask(context, this);
//        giveWaterTask.execute();

        progressBar = (ProgressBar) findViewById(R.id.progressBarCamera);
        vidView = (VideoView) findViewById(R.id.cameraView);
        String vidAddress = "android.resource://" + getPackageName() + "/" + R.raw.cat;; //GlobalConstants.TURNCAMERAON; //"http://kitkare.apphb.com/api/Device/TurnCameraOn";
        Uri vidUri = Uri.parse(vidAddress);
        vidView.setVideoURI(vidUri);
        vidView.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
        {

            public void onPrepared(MediaPlayer mp)
            {
                progressBar.setVisibility(View.GONE);
                vidView.start();
            }
        });

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabCamera);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TurnCameraOffTask turnCameraOffTask = new TurnCameraOffTask(context);
                turnCameraOffTask.execute();
            }
        });
    }

//    @Override
//    public void updatePageData(ArrayList list) {
//        if (list.size() > 0){
//            fileResponse = list.get(0);
//            File file = (File)fileResponse;
//            Uri vidUri = Uri.fromFile(file);
//            vidView.setVideoURI(vidUri);
//            vidView.start();
//        }
//    }
}
