import java.awt.*;

public class Food extends GameObj{
    public static Image foodImg=Toolkit.getDefaultToolkit().getImage("image/food.png");
    public static Image specialFoodImg=Toolkit.getDefaultToolkit().getImage("image/food_B.png");
    public static Image blankImg=Toolkit.getDefaultToolkit().getImage("image/blank.png");
    public Food(int x, int y, int height, int weight, Image img, GameFrame frame) {
        super(x, y, height, weight, img, frame);
    }

    public static Image getFoodImg() {
        return foodImg;
    }

    public static void setFoodImg(Image foodImg) {
        Food.foodImg = foodImg;
    }

    public static Image getSpecialFoodImg() {
        return specialFoodImg;
    }

    public static void setSpecialFoodImg(Image specialFoodImg) {
        Food.specialFoodImg = specialFoodImg;
    }

    public static Image getBlankImg() {
        return blankImg;
    }

    public static void setBlankImg(Image blankImg) {
        Food.blankImg = blankImg;
    }

    void paintSelf(Graphics g){
        int foodNum=300;
        int eatFood=0;
        int normalFoodScore=1;
        int specialFoodScore=10;
        Eater eater=this.frame.eater;
        for (int m = 0; m < GameFrame.columnNum; m++) {
            for (int n = 0; n < GameFrame.rowNum; n++) {
                if ((n>16||n<12)||(m>16||m<11)) {
                    if (GameFrame.myMap[n][m]>GameFrame.wallNum&&GameFrame.myMap[n][m]<GameFrame.eatedNum) {
                        g.drawImage(foodImg,m*GameFrame.eleWidth,n*GameFrame.eleHeight+GameFrame.titleWidth,weight,height,this.frame);
                        if (m== eater.x && n==eater.y) {
                            GameFrame.myMap[n][m] = GameFrame.eatedNum;
                            GameFrame.score+=normalFoodScore;
                        }
                    }else if (GameFrame.myMap[n][m]==GameFrame.speNum){
                        g.drawImage(specialFoodImg,m*GameFrame.eleWidth,n*GameFrame.eleHeight+GameFrame.titleWidth,weight,height,this.frame);
                        if (m== eater.x && n==eater.y) {
                            GameFrame.myMap[n][m] = GameFrame.eatedNum;
                            GameFrame.score+=specialFoodScore;
                        }
                    }
                }

            }
        }
        for (int i = 0; i < GameFrame.columnNum; i++) {
            for (int j = 0; j < GameFrame.rowNum; j++) {
                if (GameFrame.myMap[j][i] == 3) {
                    eatFood++;
                }
                }
            }
        if (eatFood == foodNum) {
            GameFrame.state="pass";
        }
    }
}
