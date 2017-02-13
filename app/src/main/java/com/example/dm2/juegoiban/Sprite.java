package com.example.dm2.juegoiban;

/**
 * Created by dm2 on 10/02/2017.
 */

import android.graphics.Bitmap;
import android.graphics.Canvas;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.Random;

public class Sprite {
    int [] DIRECTION_TO_ANIMATION_MAP = {3, 1, 0, 2};
    private static final int BMP_ROWS = 4;
    private static final int BMP_COLUMNS = 3;
    private int x = 0;
    private int y = 0;
    private int xSpeed = 5;
    private VistaJuego vistaJuego;
    private Bitmap bmp;
    private int currentFrame = 0;
    private int width;
    private int height;
    private int ySpeed;
    private static final int MAX_SPEED = 5;

    public Sprite(VistaJuego vistaJuego,Bitmap bmp) {
        this.vistaJuego = vistaJuego;
        this.bmp = bmp;
        this.width = bmp.getWidth() / BMP_COLUMNS;
        this.height = bmp.getHeight() / BMP_ROWS;
        Random rnd = new Random(System.currentTimeMillis());
        x = rnd.nextInt(vistaJuego.getWidth() - width);
        y = rnd.nextInt(vistaJuego.getHeight() - height);
        xSpeed = rnd.nextInt(MAX_SPEED * 2) - MAX_SPEED;
        ySpeed = rnd.nextInt(MAX_SPEED * 2) - MAX_SPEED;

    }

    private void update(){
        if (x > vistaJuego.getWidth() - width - xSpeed || x + xSpeed <= 0) { //Para que llegue al final
            xSpeed = -xSpeed;
        }
        x = x + xSpeed;
        if (y > vistaJuego.getHeight() - height - ySpeed || y + ySpeed <= 0) {
            ySpeed = -ySpeed;
        }
        y = y + ySpeed;


        currentFrame = ++currentFrame % BMP_COLUMNS;
    }

    public void onDraw (Canvas canvas){
        update();
        int srcX = currentFrame * width;
        int srcY = getAnimationRow() * height;
        Rect src = new Rect (srcX, srcY, srcX + width, srcY + height);
        Rect dst = new Rect (x, y, x + width, y + height);
        canvas.drawBitmap(bmp, src, dst, null);
        //canvas.drawBitmap(bmp, x, 10, null);
    }
    //direction = 0 up, 1 left, 2 down, 3 right
    //animation = 3 back, 1 left, 0 front, 2 right
    private int getAnimationRow(){
        double dirDouble = (Math.atan2(xSpeed, ySpeed)/ (Math.PI / 2)+ 2 );
        int direction = (int)Math.round(dirDouble) % BMP_ROWS;
        return DIRECTION_TO_ANIMATION_MAP[direction];
    }
    public boolean isCollition(float x2, float y2) {
        return x2 > x && x2 < x + width && y2 > y && y2 < y + height;
    }
}