package edu.wit.monplaisirj;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;
 
@SuppressLint("WrongCall")
public class GameView extends SurfaceView {
       private Bitmap tedAnim;
       private Bitmap tedBgAnim;
       private Bitmap tunnelBitmap;
       private Bitmap boulder1;
       private Bitmap boulder2;
       private Bitmap boulder3;
       private Bitmap boulder4;
       private Bitmap map;
       private Bitmap map2;
       private SurfaceHolder holder;
       private GameLoopThread gameLoopThread;
       private TedSprite tedSprite;
       private TedSpriteBG bgSprite;
       private int x = 0; 
       private int xSpeed = 1;
       private int moveBGX = 0;
       private int moveBGY = 0;
       private ImageView imageView;
      
       
       public GameView(Context context) {
             super(context);
             gameLoopThread = new GameLoopThread(this);
             holder = getHolder();
             holder.addCallback(new SurfaceHolder.Callback() {
 
                    @Override
                    public void surfaceDestroyed(SurfaceHolder holder) {
                           boolean retry = true;
                           gameLoopThread.setRunning(false);
                           while (retry) {
                                  try {
                                        gameLoopThread.join();
                                        retry = false;
                                  } catch (InterruptedException e) {
                                  }
                           }
                    }
 
                    @Override
                    public void surfaceCreated(SurfaceHolder holder) {
                           gameLoopThread.setRunning(true);
                           gameLoopThread.start();
                    }
 
                    @Override
                    public void surfaceChanged(SurfaceHolder holder, int format,
                                  int width, int height) {
                    }
             });
             boulder1 = BitmapFactory.decodeResource(getResources(), R.drawable.boulder1);
             boulder2 = BitmapFactory.decodeResource(getResources(), R.drawable.boulder2);
             boulder3 = BitmapFactory.decodeResource(getResources(), R.drawable.boulder3);
             boulder4 = BitmapFactory.decodeResource(getResources(), R.drawable.boulder4);
             tedAnim = BitmapFactory.decodeResource(getResources(), R.drawable.ted_extended);
             tedBgAnim = BitmapFactory.decodeResource(getResources(), R.drawable.ted_background_soil);
             tunnelBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ted_tunnel);
             map = BitmapFactory.decodeResource(getResources(), R.drawable.soil);
             map2 = BitmapFactory.decodeResource(getResources(), R.drawable.soil);
             bgSprite = new TedSpriteBG(this, tedBgAnim, tunnelBitmap, map, boulder1, boulder2, boulder3, boulder4);
             tedSprite = new TedSprite(this, tedAnim);
             Drawable drawable = new BitmapDrawable(getResources(), map);
            // imageView.setBackground(drawable);
             
       }
 
       @Override
	protected void onDraw(Canvas canvas) {
    	  // canvas.translate(0, TedSprite.getY() + 5);
    	   moveBGY = moveBGY - 10;
    	   if(moveBGY < -128){
    		   moveBGY = 0;
    	   }
    	   canvas.drawBitmap(map, 0, moveBGY, null);
    	   canvas.translate(0, -TedSpriteBG.getY() + 250);
    	   bgSprite.onDraw(canvas);    	   
    	   tedSprite.onDraw(canvas);
       }
       

}