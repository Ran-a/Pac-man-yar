import java.awt.*;

public class GameObj {
    public int x;
    public int y;
    public int height;
    public int weight;
    public Image img;
    public GameFrame frame;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public GameFrame getFrame() {
        return frame;
    }

    public void setFrame(GameFrame frame) {
        this.frame = frame;
    }
    //无参构造方法
    public GameObj() {
    }
    //有参构造方法
    public GameObj(int x, int y, int height, int weight, Image img, GameFrame frame) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.weight = weight;
        this.img = img;
        this.frame = frame;
    }

}
