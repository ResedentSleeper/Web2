package server;

import java.io.Serializable;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.StrictMath.sqrt;

public class Dot implements Serializable {
    private double x;
    private double y;
    private double R;
    private boolean hit;

    Dot(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.R = r;
        hit = batman(x, y, r);
    }



    public double getX() {
        return (double) Math.round(x * 1000d) / 1000d;
    }

    public double getY() {
        return (double) Math.round(y * 1000d) / 1000d;
    }

    public double getR() {
        return R;
    }

    public boolean isHit() {
        return hit;
    }


    private boolean batman (double xx, double y, double R){
        double rx = R/7.0;
        double ry = R/6.0;
        double x = xx+0.0;

        boolean elipce = ( (pow(x,2))/(49*pow(rx,2)) + (pow(y,2))/(9*pow(ry,2)) -1.0 ) <= 0.00000000000001;

        final double x1 = pow(x, 2) / (49 * pow(rx, 2)) + pow(y, 2) / (9 * pow(ry, 2)) - 1.0;

        boolean elipce_down_x = (abs(x/rx)) >= 4;
        boolean elipce_down_y = (y/ry >= -3*sqrt(33)/7.0) && (y/ry <= 0);
        boolean elipce_up_x = (abs(x/rx)) >= 3;
        boolean elipce_up_y = y>=0;
        boolean full_elipce = (elipce&elipce_down_x&elipce_down_y) || (elipce&elipce_up_x&elipce_up_y);

        boolean smile = ( ((3*sqrt(33)-7)*pow(x,2))/(-112.0*pow(rx,2)) + abs(x/rx)/2
                +sqrt(1-pow(abs((abs(x/rx))-2)-1,2)) - y/ry -3) <=0;
        boolean smile_y = (y/ry>=-3) && (y/ry<=0);
        boolean smile_x = (x/ry<=4) && (x/ry>=-4);
        boolean full_smile = smile&smile_x&smile_y;

        boolean ears_y = y>=0;
        boolean ears_x = abs(x/rx)<=1 && abs(x/rx)>=0.75;
        boolean ears = -8*abs(x/rx)-y/ry+9>=0;
        boolean full_ears = ears&ears_x&ears_y;

        boolean ears2_x = abs(x/rx)<=0.75 && abs(x/rx)>=0.5;
        boolean ears2 = 3*abs(x/rx)-y/ry+0.75>=0;
        boolean full_ears2 = ears2&ears2_x&ears_y;


        boolean chelka_y = y>=0;
        boolean chelka_x = abs(x/rx)<=0.5;
        boolean chelka=9.0/4.0 - y/ry >=0;
        boolean chelka_full = chelka_x&&chelka_y&&chelka;

        boolean wings_x = abs(x/rx)<=3 && abs(x/rx)>=1;
        boolean wings_y = y>=0;
        boolean wings = -abs(x/rx)/2 - (3.0/7.0)*sqrt(10)*sqrt(4-pow(abs(x/rx)-1,2)) - y/ry + (6*sqrt(10))/7.0 + 1.5 >=0;
        boolean full_wings = wings&&wings_y&&wings_x;

        return full_elipce || full_smile || full_ears || full_ears2 || full_wings || chelka_full;
    }

    //Максимальное значение радиуса
    private boolean mainGraf(double dot_x, double dot_y) {
        //check this dumb part/
        if ((dot_y <= 0) && (
                (dot_y >= (-3 * sqrt(1 - pow(((dot_x) / 5), 2)) * sqrt((abs(abs(dot_x) - 4)) / (abs(dot_x) - 4))))
                        || (dot_y >= (-3 * sqrt(1 - (pow((dot_x / 5), 2)) * sqrt((abs(abs(dot_x) - 4)) / (abs(dot_x) - 4)))))
                        || (dot_y >= (abs((dot_x) / 2) - 0.0913722 * pow(dot_x, 2) - 3 +
                        sqrt(1 - pow((abs(abs(dot_x) - 2) - 1), 2)) + 0.4867225632463)))) {
            return true;
        } else if ((dot_y >= 0) && (
                (dot_y <= (2 * sqrt(-abs(abs(dot_x) - 1) * (abs(3 - abs(dot_x))) / ((abs(dot_x) - 1) * (3 - abs(dot_x))))
                        * (1 + (abs(abs(dot_x) - 3)) / (abs(dot_x) - 3)) * sqrt(1 - pow((dot_x / 5), 2))))
                        || (dot_y <= ((2 + 1.5 - 0.5 * abs(dot_x) - 1.35 * sqrt(4 - pow(abs(dot_x) - 1, 2)))
                        * sqrt((abs(abs(dot_x) - 1)) / (abs(dot_x) - 1)) + 0.6))
                        || (dot_y <= ((5 + 0.97 * (abs(dot_x - 0.482383360737) + abs(dot_x + 0.517616639263)) -
                        3 * (abs(dot_x - 0.732383360737) + abs(dot_x + 0.767616639263))) *
                        (1 + (abs(1 - abs(dot_x + 0.017616639263))) / (1 - abs(dot_x + 0.017616639263))) - 1.2871284816515))
        )) {
            return true;
        } else
            return false;
    }


    private boolean checkDot(double dot_x, double dot_y, double dot_r) {
        boolean hom = false;
        switch ((int) dot_r) {
            //1)проверяем попадает ли в "зону элипса" c радиусом от 1 до 5
            //2) Проверяем больше или меньше нуля у и есть ли совпадения с той частью к которой он относиться
            case 1:
                if (pow(dot_x, 2) + pow(dot_y, 2) == 1) {
                    hom = mainGraf(dot_x * 5, dot_y * 5);
                } else
                    hom = false;
                break;
            case 2:
                if (pow(dot_x, 2) / 4 + pow(dot_y, 2) / 4 == 1) {
                    hom = mainGraf(dot_x * 5 / 2, dot_y * 5 / 2);
                } else
                    hom = false;
                break;
            case 3:
                if (pow(dot_x, 2) / 9 + pow(dot_y, 2) / 9 == 1) {
                    hom = mainGraf(dot_x * 5 / 3, dot_y * 5 / 3);
                } else
                    hom = false;
                break;
            case 4:
                if (pow(dot_x, 2) / 16 + pow(dot_y, 2) / 16 == 1) {
                    hom = mainGraf(dot_x * 5 / 4, dot_y * 5 / 4);
                } else
                    hom = false;
                break;
            case 5:
                if (pow(dot_x, 2) / 25 + pow(dot_y, 2) / 25 == 1) {
                    hom = mainGraf(dot_x, dot_y);
                } else
                    hom = false;
                break;
            default:
                hom = false;
                break;
        }
        return hom;
    }

}