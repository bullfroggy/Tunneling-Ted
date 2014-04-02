package edu.wit.monplaisirj;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
 
@SuppressLint("WrongCall")
public class GameView extends SurfaceView {
       private Bitmap tedAnim;
       private Bitmap tedBgAnim;
       private Bitmap tunnelBitmap;
       private Bitmap map;
       private SurfaceHolder holder;
       private GameLoopThread gameLoopThread;
       private TedSprite tedSprite;
       private TedSpriteBG bgSprite;
       private TunnelSprite tunnelSprite;
       private int x = 0; 
       private int xSpeed = 1;
      
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
             tedAnim = BitmapFactory.decodeResource(getResources(), R.drawable.ted_extended);
             tedBgAnim = BitmapFactory.decodeResource(getResources(), R.drawable.ted_background_soil);
             tunnelBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ted_tunnel_sprite);
             map = BitmapFactory.decodeResource(getResources(), R.drawable.soil);
             tedSprite = new TedSprite(this, tedAnim);
             bgSprite = new TedSpriteBG(this, tedBgAnim);            
             tunnelSprite = new TunnelSprite(this, tunnelBitmap);
       }
 
       protected void onDraw(Canvas canvas) {
    	   //canvas.drawColor(Color.BLACK);
    	   canvas.drawBitmap(map, 0, 0, null);
           tunnelSprite.onDraw(canvas);
    	   bgSprite.onDraw(canvas);
           tedSprite.onDraw(canvas);
       }
}