import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalTime;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Pursuer extends GameObj{
    int old_purX=this.x;
    int old_purY=this.y;
    //免疫时间
    long immuTime=1000*5;
     int xStep=0;
     int yStep=-2;
    int rcd=0;
    int everyRcd=1;
    int everyStep=1;
    int rcdmin=2;
    int stopStep=0;
    int forwardStep=2;
    int backwardStep=-2;
    int multiplyT=2;
    int multiplyF=4;
    //是否吃到特殊食物
    public  boolean isEatSpe = false;
    //追赶者图片
    public static Image purser1Img=Toolkit.getDefaultToolkit().getImage("image/enemy.png");
    public static Image purser2Img=Toolkit.getDefaultToolkit().getImage("image/enemy02.png");
    public static Image purser3Img=Toolkit.getDefaultToolkit().getImage("image/enemy03.png");
    public static Image purser4Img=Toolkit.getDefaultToolkit().getImage("image/enemy04.png");
    public static Image purser0Img=Toolkit.getDefaultToolkit().getImage("image/enemy_0.png");
    public Pursuer(int x, int y, int height, int weight, Image img, GameFrame frame) {
        super(x, y, height, weight, img, frame);
    }
    public int getRcd() {
        return rcd;
    }

    public void setRcd(int rcd) {
        this.rcd = rcd;
    }
    public void randomChangeDirect(){//用于随机改变enemy的运动方向 此处感谢为我提供思路的作者@RookieStupidCat 
            if (GameFrame.old_myMap[this.y][this.x]==GameFrame.pointNum) {//判断是否为拐点
                if (rcd<rcdmin) {
                    rcd=rcd+everyRcd;
                }else{
                    if (xStep!=stopStep) {//横向移动时
                        if (xStep>stopStep) {//初始右向
                            if(GameFrame.old_myMap[this.y][this.x+everyStep]!=GameFrame.wallNum){//右边可通过
                                xStep=forwardStep-(int)(Math.random()*multiplyT)*multiplyT;//停止或继续走
                            }else xStep=stopStep;
                        }else if (xStep<stopStep) {//初始左向
                            if (GameFrame.old_myMap[this.y][this.x-everyStep]!=GameFrame.wallNum) {//左边可通过
                                xStep=backwardStep+(int)(Math.random()*multiplyT)*multiplyT;//停止或继续走
                            }else xStep=stopStep;
                        }
                        if (xStep == stopStep) {
                            if (GameFrame.old_myMap[this.y+everyStep][this.x]!=stopStep && GameFrame.old_myMap[this.y-everyStep][this.x]!=GameFrame.wallNum) {//此时上下都可通行
                                yStep=backwardStep+(int)(Math.random()*multiplyT)*multiplyF;//-2或2
                            }else if (GameFrame.old_myMap[this.y-everyStep][this.x]==GameFrame.wallNum) //不可上行则下行
                                yStep=forwardStep;
                            else yStep=backwardStep;//上行
                        }
                    }else if (yStep!=stopStep) {//纵向移动时
                        if (yStep>stopStep){//初始向下
                            if (GameFrame.old_myMap[this.y+everyStep][this.x]!=GameFrame.wallNum) {//下边可通过
                                yStep=forwardStep-(int)(Math.random()*multiplyT)*multiplyT;//停止或继续走
                            }else yStep=stopStep;
                        }else if (yStep<stopStep) {//初始向上
                                if (GameFrame.old_myMap[this.y-everyStep][this.x]!=GameFrame.wallNum) {//上边可通过
                                    yStep=backwardStep+(int)(Math.random()*multiplyT)*multiplyT;//停止或继续走
                                }else yStep=stopStep;
                            }
                        if (yStep == stopStep) {
                                if (GameFrame.old_myMap[this.y][this.x+everyStep]!=GameFrame.wallNum && GameFrame.old_myMap[this.y][this.x-everyStep]!=GameFrame.wallNum) {//可左可右
                                    xStep=backwardStep+(int)(Math.random()*multiplyT)*multiplyF;//-2或2
                                }else if (GameFrame.old_myMap[this.y][this.x+everyStep]==GameFrame.wallNum) //右边不通，则左行
                                    xStep=backwardStep;
                                else xStep=forwardStep;
                            }
                    }
                }
            }
    }
    public void move(){
        if (xStep>0 && this.frame.isPath(x+1,y)) {
            this.x+=1;
        }else if (xStep<0 && this.frame.isPath(x-1,y)) {
            this.x-=1;
        }
        if (yStep>0 && this.frame.isPath(x,y+1)) {
            this.y+=1;
        }else if (yStep<0 && this.frame.isPath(x,y-1)) {
            this.y-=1;
        }
    }

    public void paintSelf(Graphics g) {
        g.drawImage(img, x * GameFrame.eleWidth, y * GameFrame.eleHeight + GameFrame.titleWidth, GameFrame.eleWidth, GameFrame.eleHeight, this.frame);
        randomChangeDirect();
        if (!GameFrame.state.equals("notstarted")) {
            old_purX=this.x;
            old_purY=this.y;
            move();
        }
       //当吃到特殊食物时，吃豆人对追赶者免疫，追赶者图片变为免疫模式enemy_0.png。
        if (GameFrame.myMap[this.frame.eater.y][this.frame.eater.x] == GameFrame.speNum) {
            img=Toolkit.getDefaultToolkit().getImage("image/enemy_0.png");
            isEatSpe = true;
            //5秒后免疫失效，图片恢复之前的图片。
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    img=Toolkit.getDefaultToolkit().getImage("image/enemy.png");
                    isEatSpe=false;
                }
            },immuTime);

        }
        //当吃豆人进入免疫模式时，碰到追赶者不会死亡，在碰到一次追赶者后，被碰到的追赶者免疫模式失效，图片也恢复为原来的图片。
        System.out.println("eater:" + this.frame.eater.x + "," + this.frame.eater.y + "pursuer:" + this.x+ ","+this.y);
        if ((this.frame.eater.x == this.x && this.frame.eater.y == this.y)||(this.frame.eater.x == old_purX && this.frame.eater.y ==old_purY)) {
            if (isEatSpe) {
                System.out.println("playing");
                GameFrame.state="playing";
                move();
                img=Toolkit.getDefaultToolkit().getImage("image/enemy.png");
                isEatSpe=false;
            }else {
                int eachLifeNum=1;
                GameFrame.lifeNum=GameFrame.lifeNum-eachLifeNum;
                GameFrame.state="die";
                //游戏失败判定
                if (GameFrame.lifeNum == GameFrame.lifeNumMin) {
                    GameFrame.state="over";
                }
            }
        }
        //越界处理
        if (x > 27) {
            this.x=0;
        }else if (x < 0) {
            this.x = 27;
        }
    }
}
