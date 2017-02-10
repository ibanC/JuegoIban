package com.example.dm2.juegoiban;

/**
 * Created by dm2 on 10/02/2017.
 */

import android.graphics.Bitmap;
import android.graphics.Canvas;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Sprite {
    private static final int BMP_ROWS = 5;
    private static final int BMP_COLUMNS = 7;
    private int x = 0;
    private int y = 0;
    private int xSpeed = 5;
    private VistaJuego vistaJuego;
    private Bitmap bmp;
    private int currentFrame = 0;
    private int width;
    private int height;

    public Sprite(VistaJuego vistaJuego,Bitmap bmp) {
        this.vistaJuego = vistaJuego;
        this.bmp = bmp;
        this.width = bmp.getWidth() / BMP_COLUMNS;
        this.height = bmp.getHeight() / BMP_ROWS;
    }

    private void update(){
        if (x > vistaJuego.getWidth() - width - xSpeed) { //Para que llegue al final
            xSpeed = -5;
        }
        if (x + xSpeed < 0) {
            xSpeed = 5;
        }
        x = x + xSpeed;
        currentFrame = ++currentFrame % BMP_COLUMNS;
    }

    public void onDraw (Canvas canvas){
        update();
        int srcX = currentFrame * width;
        int srcY = 1 * height;
        Rect src = new Rect (srcX, srcY, srcX + width, srcY + height);
        Rect dst = new Rect (x, y, x + width, y + height);
        canvas.drawBitmap(bmp, src, dst, null);
        //canvas.drawBitmap(bmp, x, 10, null);
    }
}
