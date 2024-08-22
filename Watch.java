import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.sleep;

public class Watch{
    JFrame frame;
    JPanel upperPanel,hourPanel,minutePanel,secondPanel,buttonPanel;
    JLabel hourLabel,hourMove,minuteLable,minuteMove,secondLable,secondMove;
    JButton reset,start,pause;
    String space="   ";
    boolean check=false;
    int s=0,min=0,hr=0;

    public Watch(){
        frame=new JFrame("Stop Watch");
        frame.setLayout(new FlowLayout());
        frame.setSize(250,200);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        upPanel();
        bPanel();
        buttonAction();

        frame.setVisible(true);
    }
    public void upPanel(){
        upperPanel=new JPanel();
        hPanel();
        mPanel();
        sPanel();
    }
    public void hPanel(){
        hourPanel=new JPanel();

        hourLabel=new JLabel("HOUR");
        hourMove=new JLabel(space+"00");
        hourPanel.setLayout(new GridLayout(2,1));
        hourMove.setFont(new Font(hourMove.getFont().getName(),Font.PLAIN,15));
        hourPanel.add(hourLabel);
        hourPanel.add(hourMove);
        upperPanel.add(hourPanel);
        frame.add(upperPanel);
    }
    public void mPanel(){
        minutePanel=new JPanel();
        minuteLable=new JLabel("MINUTE");
        minuteMove=new JLabel(space+"00");
        minuteMove.setFont(new Font(minuteMove.getFont().getName(),Font.PLAIN,15));
        minutePanel.add(minuteLable);
        minutePanel.add(minuteMove);
        minutePanel.setLayout(new GridLayout(2,1));
        upperPanel.add(minutePanel);
        frame.add(upperPanel);
    }
    public void sPanel(){
        secondPanel=new JPanel();
        secondLable=new JLabel("SECOND");
        secondMove=new JLabel(space+"00");
        secondMove.setFont(new Font(secondMove.getFont().getName(),Font.PLAIN,15));
        secondPanel.add(secondLable);
        secondPanel.add(secondMove);
        secondPanel.setLayout(new GridLayout(2,1));
        upperPanel.add(secondPanel);
        frame.add(upperPanel);

    }
    public void bPanel(){
        reset=new JButton("RESET");
        start=new JButton("START");
        pause=new JButton("PAUSE");
        buttonPanel=new JPanel();
        buttonPanel.add(reset);
        buttonPanel.add(start);
        buttonPanel.add(pause);
        frame.add(buttonPanel);
    }

    public void buttonAction(){
        start.addActionListener(e -> {
            if(!check){
                check=true;
                new Thread(new Runnable() {
                    public void run() {
                        while (check){
                            try {
                                ++s;
                                checking();
                                Thread.sleep(1000);

                            } catch (InterruptedException ex) {
                                System.out.println("error");
                            }
                            limit();
                        }
                    }
                }).start();
            }

        });
        reset.addActionListener(lambda -> {
            check=false;
            s=0;min=0;hr=0;
            hourMove.setText(space+"00");
            minuteMove.setText(space+"00");
            secondMove.setText(space+"00");

        });
        pause.addActionListener(lambda ->
                check=false);
    }
    public void checking(){
        if(s==60){
            ++min;
            if(min<10)
                minuteMove.setText(space+"0"+min);
            else
                minuteMove.setText(space+min);

            if(min==60)
            {
                ++hr;
                if(hr<10)
                    hourMove.setText(space+"0"+(hr));
                else
                    hourMove.setText(space+(hr));
                min=0;
            }
            s=0;
        }
        if(s<10)
            secondMove.setText(space+"0"+s);
        else
            secondMove.setText(space+s);

    }
    public void limit(){
        if(hr==99)
        {
           check=false;
        }
    }

}
