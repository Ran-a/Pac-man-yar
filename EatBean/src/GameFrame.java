import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GameFrame extends JFrame {
    //分数宽度
    public static int scoreWidth=50;
    //标题栏宽度
    public static int titleWidth=30;
    //窗口宽高
    private final int frameWidth=448;
    private final int frameHeight=496+titleWidth+scoreWidth;
    //行数
    public static int rowNum=31;
    //墙
    public static int wallNum=0;
    //路的数字
    public static int pathNum=1;
    //判断拐点
    public static int pointNum=2;
    //已经被吃的路
    public static int eatedNum=3;
    //特殊食物
    public static int speNum=4;
    //初始吃豆人坐标
    public static int eaterX=0;
    public static int eaterY=14;
    //初始追赶者坐标
    public static int pursuerX=14;
    public static int pursuerY=14;
    //初始rcd坐标
    public static int startRcd=0;
    //列数
    public static int columnNum=28;
    //元素宽
    public static int eleWidth=16;
    //元素长
    public static int eleHeight=16;
    //状态
    public static String state="notstarted";
    //分数
    public static int score=-1 ;
    //生命下限
    public static int lifeNumMin=0;
    //生命数
    public static int lifeNum=5;
    //生命图片
    public static Image lifeImage=Toolkit.getDefaultToolkit().getImage("image/life.png");
    //双缓冲图片
    Image offScreenImage = null;
    //追赶者数量
    public static int pursuerSum = 4;
    //边界
    int rightBorder=27;
    int leftBorder=0;
    //地图数组 0是墙，1是路,2是判断拐点,3是已经被吃的路,4是特殊食物
    public static int myMap[][]={
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//0
            {0,2,1,1,1,1,2,1,1,1,1,1,2,0,0,2,1,1,1,1,1,2,1,1,1,1,2,0},//1
            {0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,1,0},//2
            {0,4,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,4,0},//3
            {0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,1,0},//4
            {0,2,1,1,1,1,2,1,1,2,1,1,2,1,1,2,1,1,2,1,1,2,1,1,1,1,2,0},//5
            {0,1,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0},//6
            {0,1,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0},//7
            {0,2,1,1,1,1,2,0,0,2,1,1,2,0,0,2,1,1,2,0,0,2,1,1,1,1,2,0},//8
            {0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0},//9
            {0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0},//10
            {0,0,0,0,0,0,1,0,0,2,1,1,2,2,2,2,1,1,2,0,0,1,0,0,0,0,0,0},//11
            {0,0,0,0,0,0,1,0,0,1,0,0,0,1,1,0,0,0,1,0,0,1,0,0,0,0,0,0},//12
            {0,0,0,0,0,0,1,0,0,1,0,2,2,2,2,2,2,0,1,0,0,1,0,0,0,0,0,0},//13
            {1,1,1,1,1,1,2,1,1,2,0,2,1,1,1,1,2,0,2,1,1,2,1,1,1,1,1,1},//14
            {0,0,0,0,0,0,1,0,0,1,0,2,2,2,2,2,2,0,1,0,0,1,0,0,0,0,0,0},//15
            {0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0},//16
            {0,0,0,0,0,0,1,0,0,2,1,1,1,1,1,1,1,1,2,0,0,1,0,0,0,0,0,0},//17
            {0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0},//18
            {0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0},//19
            {0,2,1,1,1,1,2,1,1,2,1,1,2,0,0,2,1,1,2,1,1,2,1,1,1,1,2,0},//20
            {0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,1,0},//21
            {0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,1,0},//22
            {0,2,1,2,0,0,2,1,1,2,1,1,2,1,1,2,1,1,2,1,1,2,0,0,2,1,2,0},//23
            {0,0,0,1,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,1,0,0,0},//24
            {0,0,0,1,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,1,0,0,0},//25
            {0,2,4,2,1,1,2,0,0,2,1,1,2,0,0,2,1,1,2,0,0,2,1,1,2,1,2,0},//26
            {0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0},//27
            {0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0},//28
            {0,2,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,4,1,1,2,0},//29
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}//30
    };
    public static int old_myMap[][]={
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//0
        {0,2,1,1,1,1,2,1,1,1,1,1,2,0,0,2,1,1,1,1,1,2,1,1,1,1,2,0},//1
        {0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,1,0},//2
        {0,4,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,4,0},//3
        {0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,1,0},//4
        {0,2,1,1,1,1,2,1,1,2,1,1,2,1,1,2,1,1,2,1,1,2,1,1,1,1,2,0},//5
        {0,1,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0},//6
        {0,1,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0},//7
        {0,2,1,1,1,1,2,0,0,2,1,1,2,0,0,2,1,1,2,0,0,2,1,1,1,1,2,0},//8
        {0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0},//9
        {0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0},//10
        {0,0,0,0,0,0,1,0,0,2,1,1,2,2,2,2,1,1,2,0,0,1,0,0,0,0,0,0},//11
        {0,0,0,0,0,0,1,0,0,1,0,0,0,1,1,0,0,0,1,0,0,1,0,0,0,0,0,0},//12
        {0,0,0,0,0,0,1,0,0,1,0,2,2,2,2,2,2,0,1,0,0,1,0,0,0,0,0,0},//13
        {1,1,1,1,1,1,2,1,1,2,0,2,1,1,1,1,2,0,2,1,1,2,1,1,1,1,1,1},//14
        {0,0,0,0,0,0,1,0,0,1,0,2,2,2,2,2,2,0,1,0,0,1,0,0,0,0,0,0},//15
        {0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0},//16
        {0,0,0,0,0,0,1,0,0,2,1,1,1,1,1,1,1,1,2,0,0,1,0,0,0,0,0,0},//17
        {0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0},//18
        {0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0},//19
        {0,2,1,1,1,1,2,1,1,2,1,1,2,0,0,2,1,1,2,1,1,2,1,1,1,1,2,0},//20
        {0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,1,0},//21
        {0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,1,0},//22
        {0,2,1,2,0,0,2,1,1,2,1,1,2,1,1,2,1,1,2,1,1,2,0,0,2,1,2,0},//23
        {0,0,0,1,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,1,0,0,0},//24
        {0,0,0,1,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,1,0,0,0},//25
        {0,2,4,2,1,1,2,0,0,2,1,1,2,0,0,2,1,1,2,0,0,2,1,1,2,1,2,0},//26
        {0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0},//27
        {0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0},//28
        {0,2,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,4,1,1,2,0},//29
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}//30
    };
    //地图图片
    public static Image bgImg=Toolkit.getDefaultToolkit().getImage("image/bg.png");
    public static Image bgBlank=Toolkit.getDefaultToolkit().getImage("image/blank.png");
    //吃豆人
    Eater eater=new Eater(0,eaterY,20,20,Eater.rightImg,this);
    //追赶者
    Pursuer pursuer1=new Pursuer(14,14,20,20,Pursuer.purser1Img,this);
    Pursuer pursuer2=new Pursuer(14,14,20,20,Pursuer.purser1Img,this);
    Pursuer pursuer3=new Pursuer(14,14,20,20,Pursuer.purser1Img,this);
    Pursuer pursuer4=new Pursuer(14,14,20,20,Pursuer.purser1Img,this);
    //食物
    public Food food=new Food(1,1,eleHeight,eleWidth,Food.foodImg,this);
    //判断是墙还是路，是墙的话不可以移动，是路的话可以移动
    public Boolean isPath(int x,int y){
        if (x > rightBorder) {
            x=leftBorder;
        }else if (x < leftBorder) {
            x=rightBorder;
        }
        return myMap[y][x]>=pathNum;
    }
    public void launch(){
        this.setTitle("吃豆人");
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setFocusable(true);
        this.setSize(frameWidth,frameHeight);
        //键盘事件
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_SPACE) {
                    switch (state){
                        case "notstarted":
                            state="playing";
                            break;
                        case "playing":
                            state="stop";
                            repaint();
                            break;
                        case "stop":
                            state="playing";
                            repaint();
                            break;
                        case "die":
                            state="relife";
                            break;
                        case "relife":
                            state="playing";
                            break;
                        case "over":
                            state="restart";
                            break;
                        case "pass":
                            state="restart";
                            break;
                        case "restart":
                            state="playing";
                            break;
                        default:
                            break;
                    }
                }
            }
        });
        while (true){

            if (state.equals("playing")) {
                repaint();
            }
            if (state.equals("restart")) {
                restart();
                repaint();
            }
            if (state.equals("relife")) {
                relife();
                repaint();
            }

            try {
                Thread.sleep(180);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    //重新游戏
    public void restart(){
        lifeNum=5;
        score=-1;
        pursuer1.setX(pursuerX);
        pursuer1.setY(pursuerY);
        pursuer2.setX(pursuerX);
        pursuer2.setY(pursuerY);
        pursuer3.setX(pursuerX);
        pursuer3.setY(pursuerY);
        pursuer4.setX(pursuerX);
        pursuer4.setY(pursuerY);
        pursuer1.setRcd(startRcd);
        pursuer2.setRcd(startRcd);
        pursuer3.setRcd(startRcd);
        pursuer4.setRcd(startRcd);
        eater.setX(eaterX);
        eater.setY(eaterY);
        eater.setDirection("right");
        eater.setImg(Eater.rightImg);
        for (int i = 0; i < columnNum; i++) {
            for (int j = 0; j < rowNum; j++) {
                myMap[j][i]=old_myMap[j][i];
            }
        }
    }
    //新生命
    public void relife(){
        pursuer1.setX(pursuerX);
        pursuer1.setY(pursuerY);
        pursuer2.setX(pursuerX);
        pursuer2.setY(pursuerY);
        pursuer3.setX(pursuerX);
        pursuer3.setY(pursuerY);
        pursuer4.setX(pursuerX);
        pursuer4.setY(pursuerY);
        pursuer1.setRcd(startRcd);
        pursuer2.setRcd(startRcd);
        pursuer3.setRcd(startRcd);
        pursuer4.setRcd(startRcd);
        eater.setX(eaterX);
        eater.setY(eaterY);
        eater.setDirection("right");
        eater.setImg(Eater.rightImg);
    }
    @Override
    public void paint(Graphics g) {
        int startLifeImgX=320;
        int startLifeImgY=540;
        int lifeImgDistance=5;
        if (offScreenImage == null) {
            offScreenImage=this.createImage(frameWidth,frameHeight);
        }
        Graphics gImage=offScreenImage.getGraphics();
        gImage.setColor(Color.BLACK);
        gImage.fillRect(0,0,frameWidth,frameHeight);
        //绘制地图
        for (int i = 0; i < columnNum; i++) {
            for (int j = 0; j < rowNum; j++) {
                if (myMap[j][i] == wallNum) {
                    gImage.drawImage(bgImg,i*eleWidth,titleWidth+j*eleHeight,eleWidth,eleHeight,this);
                }
                if (myMap[j][i] == eatedNum) {
                    gImage.drawImage(bgBlank,i*eleWidth,titleWidth+j*eleHeight,eleWidth,eleHeight,this);
                }
            }
        }
        //绘制食物
        food.paintSelf(gImage);
        //绘制吃豆人
        eater.paintSelf(gImage);
        //绘制追赶者
        pursuer1.paintSelf(gImage);
        pursuer2.paintSelf(gImage);
        pursuer3.paintSelf(gImage);
        pursuer4.paintSelf(gImage);
        //绘制提示语
        gImage.setColor(Color.GRAY);
        prompt(gImage);
        //绘制分数
        drawWord(gImage,"Score："+score,Color.YELLOW,20,10,555);
        //绘制生命数
        drawWord(gImage,"Life:",Color.YELLOW,20,260,555);
        for (int i = 0; i < lifeNum; i++) {
            gImage.drawImage(lifeImage,startLifeImgX+(i*(eleWidth+lifeImgDistance)),startLifeImgY,eleWidth,eleHeight,this);
        }
        g.drawImage(offScreenImage,0,0,null);
    }
    //绘制文字
    void drawWord(Graphics g,String str,Color color,int size,int x,int y){
        g.setColor(color);
        g.setFont(new Font("仿宋",Font.BOLD,size));
        g.drawString(str,x,y);
    }
    //绘制提示语
    void prompt(Graphics g){
        switch (state){
            case "notstarted":System.out.println("notstarted");
                drawWord(g,"按空格键开始游戏",Color.green,35,70,290);
                break;
            case "stop":System.out.println("stop");
                drawWord(g,"游戏暂停，按空格键继续",Color.green,35,30,290);
                break;
            case "over":System.out.println("over");
                drawWord(g,"游戏失败",Color.RED,35,150,290);
            break;
            case "pass":System.out.println("PASS");
                drawWord(g,"成功通关",Color.GREEN,35,150,290);
            break;
            case "die":System.out.println("die");
                drawWord(g,"小命呜呼，按空格键继续",Color.RED,35,30,290);
            default:
                break;
        }
    }
    public static void main(String[] args) {
        GameFrame gameFrame=new GameFrame();
        gameFrame.launch();
    }
}
