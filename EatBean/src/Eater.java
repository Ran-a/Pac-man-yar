import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Eater extends GameObj{


    //方向默认向右
    public String direction="right";
    //图片
    public static Image rightImg=Toolkit.getDefaultToolkit().getImage("image/eater_0_r.png");
    public static Image leftImg=Toolkit.getDefaultToolkit().getImage("image/eater_0_l.png");
    public static Image upImg=Toolkit.getDefaultToolkit().getImage("image/eater_0_u.png");
    public static Image downtImg=Toolkit.getDefaultToolkit().getImage("image/eater_0_d.png");
    public String getDirection() {
        return direction;
    }
    public void setDirection(String direction) {
        this.direction = direction;
    }
    public Eater(int x, int y, int height, int weight, Image img, GameFrame frame) {
        super(x, y, height, weight, img, frame);
        this.frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                changeDirection(e);
            }
        });
    }

    public static Image getRightImg() {
        return rightImg;
    }

    public static void setRightImg(Image rightImg) {
        Eater.rightImg = rightImg;
    }

    public static Image getLeftImg() {
        return leftImg;
    }

    public static void setLeftImg(Image leftImg) {
        Eater.leftImg = leftImg;
    }

    public static Image getUpImg() {
        return upImg;
    }

    public static void setUpImg(Image upImg) {
        Eater.upImg = upImg;
    }

    public static Image getDowntImg() {
        return downtImg;
    }

    public static void setDowntImg(Image downtImg) {
        Eater.downtImg = downtImg;
    }

    //改变方向
    public void changeDirection(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                if (frame.isPath(x,y-1)) {
                    direction="up";
                    img=upImg;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (frame.isPath(x,y+1)) {
                    direction="down";
                    img=downtImg;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (frame.isPath(x+1,y)) {
                    direction="right";
                    img=rightImg;
                }
                break;
            case KeyEvent.VK_LEFT:
                if (frame.isPath(x-1,y)) {
                    direction="left";
                    img=leftImg;
                }
                break;
            default:
                break;
        }
    }
    public void move(){
        System.out.println("move call");
        switch (direction){
            case "up":
                if (frame.isPath(x,y-1)){
                    y-=1;
                }
                break;
            case "down":
                if (frame.isPath(x,y+1)){
                    y+=1;
                }
                break;
            case "right":
                if (frame.isPath(x+1,y)){
                    x+=1;
                }
                break;
            case "left":
                if (frame.isPath(x-1,y)) {
                    x-=1;
                }
                break;
            default:
                break;
        }

    }
    public  void paintSelf(Graphics g){
        g.drawImage(img,x*GameFrame.eleWidth,GameFrame.titleWidth+y*GameFrame.eleHeight,weight,height,this.frame);
        if (!GameFrame.state.equals("notstarted")) {
            move();
        }
        //越界处理
        if (x > 27) {
            x=0;
        }else if (x < 0) {
            x=27;
        }
    }
}
