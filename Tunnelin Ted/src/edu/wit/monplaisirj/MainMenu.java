package edu.wit.monplaisirj;

import android.net.Uri;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainMenu extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		final ImageView play = (ImageView)findViewById(R.id.play);
		final ImageView score = (ImageView)findViewById(R.id.score);
		play.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent game = new Intent(MainMenu.this, MainActivity.class);
				startActivity(game);
			}
		});
		play.setOnTouchListener(new View.OnTouchListener(){
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN)
					play.setImageResource(R.drawable.play_pressed);
				else if (event.getAction() == MotionEvent.ACTION_UP){
					play.setImageResource(R.drawable.start);
				}
				return false;
			}
				
        });
		score.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Uri uri = Uri.parse("http://www.google.com");
				Intent link = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(link);
			}
		});
		score.setOnTouchListener(new View.OnTouchListener(){
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN)
					score.setImageResource(R.drawable.score_pressed);
				else if (event.getAction() == MotionEvent.ACTION_UP){
					score.setImageResource(R.drawable.scores);
				}
				return false;
			}
				
        });
	}
}