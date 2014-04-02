package edu.wit.monplaisirj;


import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;


public class TedSprite {
	
    private static final int BMP_ROWS = 2;
    private static final int BMP_COLUMNS = 2;
    private static int x = 250;
    private static int y = 100;
    private int xSpeed = 5;
    private int ySpeed = 5;
    private GameView gameView;
    private Bitmap tedAnim;
    private int currentFrame = 0;
    private int width;
    private int height;

    public TedSprite(GameView gameView, Bitmap bmp) {
          this.gameView = gameView;
          this.tedAnim = bmp;
          this.width = bmp.getWidth() / BMP_COLUMNS;
          this.height =  bmp.getHeight() / BMP_ROWS;
    }

    private void update() {
          if (y >= gameView.getHeight() - height - ySpeed) {
                 ySpeed = -5;
          }
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
          canvas.drawBitmap(tedAnim, src, dst, null);
   
    }
    public static int getX(){
    	return x;
    }
    public static int getY(){
    	return y;
    }
}