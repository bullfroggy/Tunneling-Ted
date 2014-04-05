package edu.wit.monplaisirj;


import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.widget.ImageView;


public class TedSprite {
	
    private static final int BMP_ROWS = 2;
    private static final int BMP_COLUMNS = 2;
    private int x = 277;
    private int y = 400;
    private int xSpeed = 5;
    private int ySpeed = 10;
    private GameView gameView;
    private Bitmap tedBgAnim;
    private int currentFrame = 0;
    private int width;
    private int height;
    private Paint paint;

    public TedSprite(GameView gameView, Bitmap bmp) {
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
                 ySpeed = 10;
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