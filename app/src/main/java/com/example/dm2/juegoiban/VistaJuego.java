package com.example.dm2.juegoiban;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class VistaJuego extends SurfaceView {
        private Bitmap bmp;
        private SurfaceHolder holder;
        private GameLoopThread gameLoopThread;
        private int x = 0;
        private int xSpeed=1;
        private Sprite sprite;


    public VistaJuego(Context context) {
        super(context);
        gameLoopThread = new GameLoopThread(this);
        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
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
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.skeleton);
        sprite = new Sprite(this, bmp);
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
        sprite.onDraw(canvas);

    }

}


