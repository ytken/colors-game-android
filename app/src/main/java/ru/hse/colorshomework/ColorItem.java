package ru.hse.colorshomework;

import android.graphics.Color;
import android.widget.ImageView;

import java.util.Random;

public class ColorItem {
    private Byte r_component, g_component, b_component;
    private int number;

    public ColorItem(int number) {
        final Random random = new Random();
        r_component = (byte) (random.nextInt(255)+1);
        g_component = (byte) (random.nextInt(255)+1);
        b_component = (byte) (random.nextInt(255)+1);
        this.number = number;
    }

    public ColorItem(int number, Byte r_component, Byte g_component, Byte b_component){
        this.r_component = r_component;
        this.g_component = g_component;
        this.b_component = b_component;
        this.number = number;
    }

    public Byte getR_component() {return r_component;}

    public Byte getG_component() {return g_component;}

    public Byte getB_component() {return b_component;}

    public int getNumber() { return number; }

    public void show(ImageView view) {
        view.setColorFilter(Color.rgb(this.r_component, this.g_component, this.b_component));
    }
}
