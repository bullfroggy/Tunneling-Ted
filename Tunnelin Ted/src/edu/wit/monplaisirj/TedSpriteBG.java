package edu.wit.monplaisirj;


import java.util.Random;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.util.Log;


public class TedSpriteBG {
	
    private static final int BMP_ROWS = 3;
    private static final int BMP_COLUMNS = 3;
    private static int x = 275;
    private static int y = 400;
    private int xSpeed = 5;
    private int ySpeed = 10;
    private GameView gameView;
    private Bitmap tedAnim;
    private Bitmap tunnelSprite;
    private Bitmap boulder1;
    private Bitmap boulder2;
    private Bitmap boulder3;
    private Bitmap boulder4;
    private int currentFrame = 0;
    private int width;
    private int height;
    private Bitmap mBitmapForTrails1;
    private Canvas mCanvasForTrails1;
    private Canvas currCan = mCanvasForTrails1;
    private Bitmap currBmp = mBitmapForTrails1;
    private Bitmap background;
    private Paint mPaint;
    private float startingXR = 520;
    private float startingXL = 60;
    private float startingYR = TedSpriteBG.getY()+1000;
    private float startingYL = TedSpriteBG.getY()+1000;
    

 

    public TedSpriteBG(GameView gameView, Bitmap bmp, Bitmap map, Bitmap background, Bitmap boulder1, Bitmap boulder2, Bitmap boulder3, Bitmap boulder4) {
          this.gameView = gameView;
          this.tedAnim = bmp;
          this.width = bmp.getWidth() / BMP_COLUMNS;
          this.height =  bmp.getHeight() / BMP_ROWS;
          this.tunnelSprite = map;
          this.background = background;
          this.boulder1 = boulder1;
          this.boulder2 = boulder2;
          this.boulder3 = boulder3;
          this.boulder4 = boulder4;
          mPaint = new Paint();
          initializeBitmapForTrails1();
    }

    private void update() {
    	  /*
          if (y >= gameView.getHeight() - height - ySpeed) {
                 ySpeed = -5;
          }*/
          if (y + ySpeed < 0) {
                 ySpeed = 10;
          }
          y = y + ySpeed;
          currentFrame = ++currentFrame % BMP_COLUMNS;
          
          if (y%200==0){
        	  drawBoulderHazard();
          }
          
          if (y%50==0){
        	  drawBoulderLeft();
        	  drawBoulderRight();
          }
          
          if (y%3==0){
        	  drawTrail();
          }
          
          if (y%3==0){
        	  drawTrail();
          }
          currCan.translate(0, -20);
    }
    
    public void initializeBitmapForTrails1()
    {
    	mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST));
 	   	mBitmapForTrails1 = Bitmap.createBitmap(background.getWidth(), background.getHeight()+1000, Config.ARGB_8888);
 	   	mCanvasForTrails1 = new Canvas(mBitmapForTrails1);
 	   	currCan = mCanvasForTrails1;
 	   	currBmp = mBitmapForTrails1;
    }    
    
    public void drawTrail()
    {
    	currCan.drawBitmap(tunnelSprite, TedSpriteBG.getX()+3 , TedSpriteBG.getY() - 30, null);  	
    }
    
    public void drawBoulderLeft()
    {
    	int min1 = -100;
    	int min2 = 1;
    	int max1 = 100;
    	int max2 = 4;

    	Random r = new Random();
    	int newX = r.nextInt(max1 - min1 + 1) + min1;
    	float boulderRand = r.nextInt(max2 - min2 + 1) + min2;
    	startingXL = startingXL + newX;
    	if (startingXL > startingXR - 250 || startingXL < 0){
    		startingXL = startingXL - newX;
    	}
    	if (boulderRand == 1){
	    	currCan.drawBitmap(boulder1, startingXL, y+1000, null);
    	}
    	else if(boulderRand == 2){
	    	currCan.drawBitmap(boulder2, startingXL, y+1000, null);
    	}
    	else if(boulderRand == 3){
	    	currCan.drawBitmap(boulder3, startingXL, y+1000, null);
	    	
    	}
    	else if(boulderRand == 4){
	    	currCan.drawBitmap(boulder4, startingXL, y+1000, null);
    	}
    }
    
    public void drawBoulderRight()
    {
    	int min1 = -100;
    	int min2 = 1;
    	int max1 = 100;
    	int max2 = 4;

    	Random r = new Random();
    	int newX = r.nextInt(max1 - min1 + 1) + min1;
    	float boulderRand = r.nextInt(max2 - min2 + 1) + min2;
    	startingXR = startingXR + newX;
    	if (startingXR < startingXL + 250 || startingXR > 620){
    		startingXR = startingXR - newX;
    	}
    	if (boulderRand == 1){
	    	currCan.drawBitmap(boulder1, startingXR, y+1000, null);
    	}
    	else if(boulderRand == 2){
	    	currCan.drawBitmap(boulder2, startingXR, y+1000, null);
    	}
    	else if(boulderRand == 3){
	    	currCan.drawBitmap(boulder3, startingXR, y+1000, null);
    	}
    	else if(boulderRand == 4){
	    	currCan.drawBitmap(boulder4, startingXR, y+1000, null);
    	}
    }
    
    public void drawBoulderHazard(){
    	int max1 = (int) startingXR;
    	int min1 = (int) startingXL;
    	if (max1 - min1 > 400){
	    	Random r = new Random();
	    	int newX = r.nextInt(max1 - min1 + 1) + min1;
	    	currCan.drawBitmap(boulder1, newX, y+1000, null);
    	}
    }
    
    @SuppressLint("DrawAllocation")
	public void onDraw(Canvas canvas) {
    	  
          update();
          canvas.drawBitmap(mBitmapForTrails1, 0, y-400, null);
          int srcX = currentFrame * width;
          int srcY = 1 * height;
          Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
          Rect dst = new Rect(x, y, x + width, y + height);
          canvas.drawBitmap(tedAnim, src, dst, null);
    }
    
    public static int getX(){
    	return x;
    }
    public static int getY(){
    	return y;
    }
}