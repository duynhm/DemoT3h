package dzumi.app.demo.demot3h.modules.resource.animation;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import dzumi.app.demo.demot3h.R;

public class SecondActivity extends Activity implements OnClickListener, AnimationListener{

	Button btnFadeIn, btnFadeOut;
	Button btnCrossFade, btnBlink;
	Button btnZoomIn, btnZoomOut;
	Button btnRotate, btnMove;
	Button btnSlideUp, btnSlideOut;

	Button btnBounce, btnSequentialAnimation, btnTogetherAnimation;

	Animation animation;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sample_animation);

		btnFadeIn = (Button) findViewById(R.id.btnFadeIn);
		btnFadeIn.setOnClickListener(this);

		btnFadeOut = (Button) findViewById(R.id.btnFadeOut);
		btnFadeOut.setOnClickListener(this);

		btnCrossFade = (Button) findViewById(R.id.btnCrossFade);
		btnCrossFade.setOnClickListener(this);

		btnBlink = (Button) findViewById(R.id.btnBlink);
		btnBlink.setOnClickListener(this);

		btnZoomIn = (Button) findViewById(R.id.btnZoomIn);
		btnZoomIn.setOnClickListener(this);

		btnZoomOut = (Button) findViewById(R.id.btnZoomOut);
		btnZoomOut.setOnClickListener(this);

		btnRotate = (Button) findViewById(R.id.btnRotate);
		btnRotate.setOnClickListener(this);

		btnMove = (Button) findViewById(R.id.btnMove);
		btnMove.setOnClickListener(this);

		btnSlideOut = (Button) findViewById(R.id.btnSlideDown);
		btnSlideOut.setOnClickListener(this);

		btnSlideUp = (Button) findViewById(R.id.btnSlideUp);
		btnSlideUp.setOnClickListener(this);

		btnBounce = (Button) findViewById(R.id.btnBounce);
		btnBounce.setOnClickListener(this);

		btnSequentialAnimation = (Button) findViewById(R.id.btnSequentialAnimation);
		btnSequentialAnimation.setOnClickListener(this);

		btnTogetherAnimation = (Button) findViewById(R.id.btnTogetherAnimation);
		btnTogetherAnimation.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@SuppressLint("NewApi")
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		//buoc 1: khai doi tuong cung ten voi the tag trong xml
		ObjectAnimator objectAnimator = null;	

		switch (v.getId()) {
		case R.id.btnFadeIn:
			Log.d("dzumi", "fade in");
			//buoc 1: khai doi tuong cung ten voi the tag trong xml
			ObjectAnimator fadeIn = null;	

			//buoc 2: khoi tao gia tri cho doi tuong vua khai bao
			fadeIn = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.fade);

			//buoc 3: thiet lap view can thuc hien hieu ung
			fadeIn.setTarget(v);

			//buoc 4: start animator
			fadeIn.start();
			break;

		case R.id.btnFadeOut://ve nha lam
			break;

		case R.id.btnCrossFade:
			break;
		case R.id.btnBlink:
			animation = AnimationUtils.loadAnimation(this, R.anim.blink);
			animation.setAnimationListener(this);
			btnBlink.startAnimation(animation);
			break;

		case R.id.btnZoomIn:
			animation = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
			animation.setAnimationListener(this);
			btnZoomIn.startAnimation(animation);
			break;

		case R.id.btnZoomOut:
			animation = AnimationUtils.loadAnimation(this, R.anim.zoom_out);
			animation.setAnimationListener(this);
			btnZoomOut.startAnimation(animation);
			break;
		case R.id.btnRotate:
			animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
			animation.setAnimationListener(this);
			btnRotate.startAnimation(animation);
			break;
		case R.id.btnMove:
			//buoc 2: khoi tao gia tri cho doi tuong vua khai bao
			objectAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.translate_x);
			break;
		case R.id.btnSlideDown:
			animation = AnimationUtils.loadAnimation(this, R.anim.slide_down);
			animation.setAnimationListener(this);
			btnSlideOut.startAnimation(animation);
			break;
		case R.id.btnSlideUp:
			animation = AnimationUtils.loadAnimation(this, R.anim.slide_up);
			animation.setAnimationListener(this);
			btnSlideUp.startAnimation(animation);
			break;
		case R.id.btnBounce:
			animation = AnimationUtils.loadAnimation(this, R.anim.bounce);
			animation.setAnimationListener(this);
			btnBounce.startAnimation(animation);
			break;
		case R.id.btnSequentialAnimation:
			AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.set_animator);
			animatorSet.setTarget(v);
			animatorSet.start();
			break;
		case R.id.btnTogetherAnimation:
			animation = AnimationUtils.loadAnimation(this, R.anim.together);
			animation.setAnimationListener(this);
			btnTogetherAnimation.startAnimation(animation);
			break;
		default:
			break;
		}

		if(objectAnimator != null)
		{
			//buoc 3: thiet lap view can thuc hien hieu ung
			objectAnimator.setTarget(v);

			//buoc 4: start animator
			objectAnimator.start();
		}
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub
		Log.d("dzumi", "animation end");
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
		Log.d("dzumi", "animation repeate");
	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub
		Log.d("dzumi", "animation start");
	}

}
