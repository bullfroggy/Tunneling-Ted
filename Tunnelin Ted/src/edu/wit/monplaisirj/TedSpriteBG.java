package edu.wit.monplaisirj;


import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;


public class TedSpriteBG {
	
    private static final int BMP_ROWS = 3;
    private static final int BMP_COLUMNS = 3;
    private int x = 248;
    private int y = 100;
    private int xSpeed = 5;
    private int ySpeed = 5;
    private GameView gameView;
    private Bitmap tedBgAnim;
    private int currentFrame = 0;
    private int width;
    private int height;
    private Paint paint;

    public TedSpriteBG(GameView gameView, Bitmap bmp) {
          this.gameView = gameView;
          this.tedBgAnim = bmp;
          this.width = bmp.getWidth() / BMP_COLUMNS;
          this.height =  bmp.getHeight() / BMP_ROWS;
          paint = new Paint();
          paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));
 
    }

    private void update() {
    	
    	/*
          if (y >= gameView.getHeight() - height - ySpeed) {
                 ySpeed = -5;
          }
          
          */
          if (y + ySpeed < 0) {
                 ySpeed = 5;
          }
          y = y + ySpeed;
          currentFrame = ++currentFrame % BMP_COLUMNS;
    }

    @SuppressLint("DrawAllocation")
	public void onDraw(Canvas canvas) {
          update();
          int srcX = currentFrame * width;
          int srcY = 1 * height;
          Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
          Rect dst = new Rect(x, y, x + width, y + height);
          canvas.drawBitmap(tedBgAnim, src, dst, null);
          
    }
}