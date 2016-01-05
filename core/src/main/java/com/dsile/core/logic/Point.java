package com.dsile.core.logic;

/**
 * Created by DeSile on 09.12.2015.
 */
public class Point {
    public double x;
    public double y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Point(double angle){
        x = Math.cos(Math.toRadians(angle));
        y = Math.sin(Math.toRadians(angle));
    }

    public Point(){
        x = y = 0;
    }

    public static Point mult(Point point, double d){
        return new Point(point.x*d,point.y*d);
    }

    public Point mult(double d){
        x *= d;
        y *= d;
        return this;
    }

    public Point calcVelocity(double angle, double speed){
        x = Math.cos(Math.toRadians(angle))*speed;
        y = Math.sin(Math.toRadians(angle))*speed;
        return this;
    }


}
