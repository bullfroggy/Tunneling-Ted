package edu.wit.monplaisirj;


import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;


public class TunnelSprite {
	
    private static final int BMP_ROWS = 2;
    private static final int BMP_COLUMNS = 2;
    private int x = TedSpriteBG.getX()-2;
    private int y = TedSpriteBG.getY();
    private int xSpeed = 0;
    private int ySpeed = 0;
    private GameView gameView;
    private Bitmap tunnelBitmap;
    private int currentFrame = 0;
    private int width;
    private int height;
    private Canvas pathCanvas;

    public TunnelSprite(GameView gameView, Bitmap bmp) {
          this.gameView = gameView;
          this.tunnelBitmap = bmp;
          this.width = bmp.getWidth() / BMP_COLUMNS;
          this.height =  bmp.getHeight() / BMP_ROWS;
    }

    private void update() {
          if (y >= gameView.getHeight() - height - ySpeed) {
                 ySpeed = -10;
          }
          if (y + ySpeed < 0) {
                 ySpeed = 10;
          }
          y = y + ySpeed;
          currentFrame = ++currentFrame / BMP_COLUMNS;
    }

    @SuppressLint("DrawAllocation")
	public void onDraw(Canvas canvas) {
    	  if(y%20==0){
	          update();
	          int srcX = currentFrame * width;
	          int srcY = 1 * height;
	          Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
	          Rect dst = new Rect(x, y, x + width, y + height);
	          canvas.drawBitmap(tunnelBitmap, src, dst, null);
    	  }
         
   
    }
    public int getX(){
    	return x;
    }
    public int getY(){
    	return y;
    }
}