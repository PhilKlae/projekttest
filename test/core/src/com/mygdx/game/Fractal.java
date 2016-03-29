package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by philo on 25.03.2016.
 */
public class Fractal implements Disposable {


    //test

     double xCentre = 0;
     double yCentre = +0.0;
     int    nx      = 400;
     int    ny      = 400;
     double dxy     = 0.0002;


    int N = 500;

    double x0;
    double y0;


    double xc = 0;//Double.parseDouble(args[0]);
    double yc = 0;// Double.parseDouble(args[1]);
    double size = 1; //Double.parseDouble(args[2]);

    double zoomx=0;
    double zoomy=0;
    private Pixmap pixmap;


    int max=5;

    double width;
    double height ;


    //etst mandelbrot

    double maxiteration=0;
    double c_re ;
    double c_im;

    int iteration = 0;
    double x_new;

    public Fractal(int l,int w){
        pixmap = new Pixmap(4000, 4000, Pixmap.Format.RGBA8888);

    }

    public Pixmap drawFractal(){

         width=pixmap.getWidth();
         height =pixmap.getHeight();


         maxiteration=0;
       // double delta=c_re - size / 2 + size * row / width;
      /*  try {



            pixmap.dispose();


        } catch (Exception e) {

        }*/

        for (int row = 0; row < pixmap.getHeight(); row++) {
            c_im= zoomx + (row - height/2)*dxy;
            for (int col = 0; col < width; col++) {
                c_re = zoomy + (col - width/2)*dxy;
           //     c_re = c_re - size / 2 + size * row / width;
            //    c_im =c_im - size / 2 + size * col / width;

              //  c_re = c_re - size / 2 + size * row / width; //((row - width+zoomx)/size);//*(zoomx);
               // c_im = c_im - size / 2 + size * col / height;//(col - height*zoomy )/size;//*zoomy;

                //System.out.println(c_re);
                double x =0;// c_re - size / 2 + size * row / width;

                double y =0;// c_im - size / 2 + size * col / width;
                iteration = 0;
                while (x*x+y*y <= 4 && iteration < max) {
                    x_new = x*x - y*y + c_re;
                    y = 2*x*y + c_im;
                    x = x_new;
                    iteration++;
                    //System.out.println(iteration);
                    if(iteration>maxiteration){
                        maxiteration=iteration;
                    }

                }

                if (iteration < max) {

                   // pixmap.setColor((float) (255/maxiteration*iteration),(float) (255/maxiteration*iteration),(float) (255/maxiteration*iteration),(float) (255/maxiteration*iteration));
                   pixmap.setColor((float) (max-iteration),(float) (max-iteration),(float) (max-iteration),(float) (max-iteration));

                    pixmap.drawPixel(col, row);
                }
                else {
                    pixmap.setColor(Color.BLACK);
                    pixmap.drawPixel(col, row);

                }
            }
        }

      return pixmap;
    }

    public void dispose(){

        try {


            pixmap.dispose();


        } catch (Exception e) {

        }
    }

    public  void setZoomx(double z){
        zoomx+=z;
    }
    public  void setZoomy(double z){
        zoomy+=z;
    }

    public void setSize(double s){
        size+=s;
      //  zoomx+=s;
     //   zoomy-=s/820;
       // zoomx-=s/835;
        max++; //(int) (s/1000*0.5);
        System.out.println(max);

    }

    public Pixmap drawFractal2() {


           // create N-by-N image
         max++;   // maximum number of iterations


        for (int b = 0; b < N; b++) {
            for (int j = 0; j < N; j++) {
                 x0 = xc - size / 2 + size * b / N;
                 y0 = yc - size / 2 + size * j / N;
                Complex z0 = new Complex(x0, y0);
                int gray = max - mand(z0, max);
                //color = new Color(gray, gray, gray);
                pixmap.setColor(new Color(gray, gray, gray, 256));

                pixmap.drawPixel(b, N - 1 - j);



            }
        }
        return pixmap;
    }




    //	}
    public static int mand(Complex z0, int max) {
    Complex z = z0;
    for (int t = 0; t < max; t++) {
        if (z.abs() > 4.0) return t;
        z = z.times(z).plus(z0);

    }
    return max;
}
}
