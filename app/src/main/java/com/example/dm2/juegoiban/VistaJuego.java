package com.example.dm2.juegoiban;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class VistaJuego extends SurfaceView {
   // private Bitmap bmp;
    private SurfaceHolder holder;
    private GameLoopThread gameLoopThread;
    private int x = 0;
    private int xSpeed=1;
    //private Sprite sprite;
    private List<Sprite> sprites = new ArrayList<Sprite>();
    private List<TempSprite> temps = new ArrayList<TempSprite>();
    private long lastClick;
    private Bitmap bmpBlood;

    public VistaJuego(Context context) {
        super(context);
        gameLoopThread = new GameLoopThread(this);
        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                sprites.add(createSprite(R.drawable.elfo));
                sprites.add(createSprite(R.drawable.persona));
                sprites.add(createSprite(R.drawable.elfo));
                sprites.add(createSprite(R.drawable.persona));



                gameLoopThread.setRunning(true);
                gameLoopThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                gameLoopThread.setRunning(false);
                while (retry){
                    try{
                        gameLoopThread.join();
                        retry = false;
                    }catch (InterruptedException e){

                    }
                }
            }
        });
        /*bmp = BitmapFactory.decodeResource(getResources(), R.drawable.skeleton);
        sprite = new Sprite(this, bmp);*/
        bmpBlood = BitmapFactory.decodeResource(getResources(), R.drawable.blood1);
    }

    private Sprite createSprite(int resouce) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(),resouce);
        return new Sprite(this, bmp);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);

       /*
        icono android
        if (x == getWidth() - bmp.getWidth()) {
            xSpeed = -1;
        }
        if (x == 0) {
            xSpeed = 1;
        }
        x = x + xSpeed;
        canvas.drawBitmap(bmp, x, 10, null);*/
        for(Sprite sprite: sprites)
        {
            sprite.onDraw(canvas);
        }

        for (int i = temps.size() - 1; i >= 0; i--){
            temps.get(i).onDraw(canvas);
        }

    }

    public boolean onTouchEvent(MotionEvent event) {


        if (System.currentTimeMillis() - lastClick > 300) {
            lastClick = System.currentTimeMillis();
            float x = event.getX();
            float y = event.getY();

            synchronized (getHolder()) {
                for (int i = sprites.size() - 1; i >= 0; i--) {
                    Sprite sprite = sprites.get(i);

                    if (sprite.isCollition(x ,y )) {
                        sprites.remove(sprite);
                        temps.add(new TempSprite(temps, this, x, y, bmpBlood));
                        break;
                    }
                }
            }
        }
        return true;
    }
}

