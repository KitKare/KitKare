package kitkare.kitkare.app.activities;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.VideoView;

import kitkare.kitkare.R;
import kitkare.kitkare.app.common.GlobalConstants;
import kitkare.kitkare.app.tasks.device.TurnCameraOffTask;

//http://code.tutsplus.com/tutorials/streaming-video-in-android-apps--cms-19888
public class CameraActivity extends Activity {
    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        VideoView vidView = (VideoView)findViewById(R.id.cameraView);
        String vidAddress = GlobalConstants.TURNCAMERAON;
        Uri vidUri = Uri.parse(vidAddress);
        vidView.setVideoURI(vidUri);
        vidView.start();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabCamera);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TurnCameraOffTask turnCameraOffTask = new TurnCameraOffTask(context);
                turnCameraOffTask.execute();
            }
        });
    }
}
