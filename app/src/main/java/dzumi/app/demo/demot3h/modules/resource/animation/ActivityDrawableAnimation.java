package dzumi.app.demo.demot3h.modules.resource.animation;


import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import dzumi.app.demo.demot3h.R;

public class ActivityDrawableAnimation extends Activity {

	/**
	 * Reference to the ImageView which will display the animation.
	 */
	ImageView animation;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable_animation);
        animation = (ImageView)findViewById(R.id.imageAnimation);
        animation.setBackgroundResource(R.drawable.animation);		// the frame-by-frame animation defined as a xml file within the drawable folder
        /*
         * NOTE: It's not possible to start the animation during the onCreate.
         */
    }
    @Override
    /**
     * This method is called whenever the Activity becomes visible or invisible to the user.
     * During this method call its possible to start the animation.
     */
	public void onWindowFocusChanged (boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		AnimationDrawable frameAnimation = 
			(AnimationDrawable) animation.getBackground();
		if(hasFocus) {
			frameAnimation.start();
		} else {
			frameAnimation.stop();
		}
	}
}