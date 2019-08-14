package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class PigAndCoinPanel extends JPanel implements KeyListener, ActionListener {
    //加载图像
    ImageIcon pig=new ImageIcon("src/resources/pig1.jpg");
    ImageIcon coin=new ImageIcon("src/resources/coin1.jpg");
    //pig的位置
    int pigX=450;
    int pigY=700;
    //金币的位置
    int[][] coins=new int[18][14];
    int score=0;
    boolean isStarted=false;
    //设置定时器
    Timer timer =new Timer(300,this);
    //构造函数
    public PigAndCoinPanel(){
        this.setFocusable(true);
        initPig();//放置静态猪
        this.addKeyListener(this);//添加监听
        timer.start();//打开定时器
    }
    //初始化
    public void initPig(){
        int pigX=450;
        int pigY=700;
        isStarted=false;
    }

    public void paint(Graphics graphic) {
        this.setBackground(Color.CYAN);
        graphic.fillRect(0,50,900,700);
        //写标题
        graphic.setColor(Color.PINK);
        graphic.setFont(new Font("arial",Font.BOLD,20));
        graphic.drawString("Pig and coin",25,45);
        //写提示语
        if(!isStarted){
            graphic.setColor(Color.PINK);
            graphic.setFont(new Font("arial",Font.BOLD,20));
            graphic.drawString("Press Space to Start/Pause",320,300);
        }
        //放置pig
        pig.paintIcon(this,graphic,pigX,pigY);
        //放置金币
        for(int i=0;i<18;i++){
            for(int j=0;j<14;j++){
               if(coins[i][j]==1){
                   coin.paintIcon(this,graphic,i*50,j*50+50);
               }
            }
        }
        //写金币数
        graphic.setColor(Color.pink);
        graphic.setFont(new Font("arial",Font.BOLD,20));
        graphic.drawString("Score: "+score,500,45);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (isStarted){
            //调整金币位置
            for(int i=0;i<18;i++){
                for(int j=13;j>0;j--){
                    coins[i][j]=coins[i][j-1];
                }
            }
            //第一排随机放置金币
            Random r=new Random();
            for(int i=0;i<18;i++) {
                coins[i][0]=r.nextInt(100);
            }

            //判断是否吃到
            for(int i=0;i<18;i++){
                if(coins[i][13]==1&&i*50==pigX){
                    score++;
                    coins[i][13]=0;
                }
            }
        }
        this.repaint();//会自动调用我们自己写的paint方法
    }





    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode=e.getKeyCode();
        if(keyCode==KeyEvent.VK_SPACE){
            isStarted=!isStarted;
        }
        if(isStarted){
            if(keyCode==KeyEvent.VK_LEFT&&pigX -50>=0){
                pigX-=50;
            } else if(keyCode==KeyEvent.VK_RIGHT&&pigX+50<=850){
                pigX+=50;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
