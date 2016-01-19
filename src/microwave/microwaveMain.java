package microwave;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class microwaveMain extends JFrame implements Runnable,ActionListener{
	Thread clockThread = null;
	Calendar calendar;
	int hour=0,minute=0,second=0,ampm=0,timer_clock=0,over=0,tigger=0,toggle=0,counter=0;
	JLabel label_clock,label_message;
	JToggleButton btn_open,btn_start,btn_red,btn_blue;
	String time_ampm = "",timer_choices="";
	 Object	input_choices="";
	String timing="";
	protected Font font = new Font("Arial Rounded MT Bold", Font.BOLD,30);
	JButton label_image;
	String[] choices = { "Popcorn- 15min", "Potato-20min", "Pizza-60min", "Bevarage-10min", "Soup-15min", "Dinner Plate-3min","Fresh Vegetable-5min","Frozen vegetable-10min" };
	String[] timer={"15","20","60","10","15","3","5","10"};
	boolean selected,selected2=false,selected3;
	Image bg;
	/**
	 * Create the applet.
	 */
	public microwaveMain() {
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.white);
		label_clock = new JLabel("");
		label_clock.setBounds(0, 0, 86, 34);
		getContentPane().add(label_clock);
		
		label_message = new JLabel();
		label_message.setBounds(101, 3, 215, 30);
		getContentPane().add(label_message);
		
		btn_open = new JToggleButton("START");
		btn_open.setBounds(0, 46, 86, 29);
		getContentPane().add(btn_open);
		btn_open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent open_ae) {
		        AbstractButton open_ab = (AbstractButton) open_ae.getSource();
		        selected = open_ab.getModel().isSelected();
		        System.out.println("Action - selected=" + selected + "\n");
		        if(tigger==0){
		        if(selected==true){
		        	input_choices= JOptionPane.showInputDialog(null,"Type of Food","Selected",
		        			JOptionPane.QUESTION_MESSAGE,null,choices,choices[1]);
			        System.out.println("Action - selected=" + input_choices.toString());
			        for(int i=0;i<8;i++){
			        	if(input_choices.toString()== choices[i]){
			        	JOptionPane.showMessageDialog(null, "Your timer is set for "+timer[i]+" minutes");
			        	label_message.setText("Timer set to "+timer[i]+" minutes.");
			        	timer_choices=timer[i];
						timer_clock= (Integer.parseInt(timer_choices))*60;
			        	}
			        }
			        btn_open.setText("STOP");
			        }

		        if(selected==false){
		        	int response = JOptionPane.showConfirmDialog(null, "Do you want to Stop the timer?", "Confirm",
		        	        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		        	if(response == JOptionPane.NO_OPTION){
		        		JOptionPane.showMessageDialog(null,"Please click the choice");
		        	}
		        	else if (response == JOptionPane.YES_OPTION){
		        		over=1;
		        	}else if (response == JOptionPane.CLOSED_OPTION){
		        		over=0;
		        	}
		        	btn_open.setText("START");
		        }
			}
		    
		        
			}
		});
		
		btn_start = new JToggleButton("OPEN");
		btn_start.setBounds(111, 49, 86, 29);
		getContentPane().add(btn_start);
		btn_start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent start_ae) {
		        AbstractButton open_start = (AbstractButton) start_ae.getSource();
		        selected2 = open_start.getModel().isSelected();
				
			}
		});
		
		btn_red = new JToggleButton("ON");
		btn_red.setBounds(341, 48, 33, 29);
		getContentPane().add(btn_red);
		btn_red.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent on_ae) {
		        AbstractButton on_start = (AbstractButton) on_ae.getSource();
		        selected3 = on_start.getModel().isSelected();
		        if(selected3=true)
				counter++;
		 
				
			}
		});

		
		btn_blue = new JToggleButton("New button");
		btn_blue.setBounds(374, 48, 39, 29);
		getContentPane().add(btn_blue);
		
		JButton btn_setTimer = new JButton("SET TIMER");
		btn_setTimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			timing=	JOptionPane.showInputDialog("Set Time(minutes)");
			label_message.setText("Timer set to "+timing+" minutes.");
			timer_clock= (Integer.parseInt(timing))*60;
			tigger=1;
			}
		});
		btn_setTimer.setBounds(211, 49, 115, 29);
		getContentPane().add(btn_setTimer);
		
		this.setTitle("MicroWave");
		getContentPane().setVisible(true);
		
	}
	public void start(){
		setSize(700,400);
			if(clockThread == null){
				clockThread = new Thread(this);
				clockThread.start();
			}

	}
	public void stop(){
		clockThread = null;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(Thread.currentThread()== clockThread){
			repaint();
			try{
				Thread.currentThread().sleep(1000);
			}
			catch(InterruptedException e){
				
			}
			
		}
		
	}
	public void paint(Graphics g){
		
		calendar = Calendar.getInstance();
		hour = calendar.get(Calendar.HOUR);
		minute = calendar.get(Calendar.MINUTE);
		second = calendar.get(Calendar.SECOND);
		ampm = calendar.get(Calendar.AM_PM);
		time_ampm="";
		
		if(ampm == 0){
			time_ampm = time_ampm + "AM";
		}
		 else {
			 time_ampm = time_ampm + "PM";
		 }
		label_clock.setText(hour+":"+minute / 10 + minute % 10 +":"+second / 10 + second % 10+" "+time_ampm);
		
		if(selected== true){
			toggle=1;
			if(timer_clock!=0){
				label_clock.setText("00:"+(timer_clock));
				btn_blue.setIcon(new ImageIcon("C:\\Users\\aelay\\workspace\\MicrowaveSimulator\\blue.png"));
				label_image.setIcon(new ImageIcon("C:\\Users\\aelay\\workspace\\MicrowaveSimulator\\microwave_cooking.jpeg"));
				timer_clock--;
				if(timer_clock==0){
					over=3;
					btn_blue.setIcon(new ImageIcon("C:\\Users\\aelay\\workspace\\MicrowaveSimulator\\bin\\microwave\\off.jpg"));
					label_image.setIcon(new ImageIcon("C:\\Users\\aelay\\workspace\\MicrowaveSimulator\\microwave_cooked.jpg"));
				}
			}
		}
		else{
			btn_open.setText("START");
		}
		
		if(selected==false){
			if(toggle==1)
			toggle++;
		}
		if(over==3){
			JOptionPane.showMessageDialog(null,"Your Food is ready!");
			label_message.setText("");
			over=0;
			toggle=0;
			btn_blue.setIcon(new ImageIcon("C:\\Users\\aelay\\workspace\\MicrowaveSimulator\\bin\\microwave\\off.jpg"));
			label_clock.setText(hour+":"+minute / 10 + minute % 10 +":"+second / 10 + second % 10+" "+time_ampm);
		}
		
		if(over==1|| toggle==2){
			JOptionPane.showMessageDialog(null,"Your Food is not yet ready!");
			label_message.setText("");
			over=0;
			toggle=0;
			btn_blue.setIcon(new ImageIcon("C:\\Users\\aelay\\workspace\\MicrowaveSimulator\\bin\\microwave\\off.jpg"));
			label_clock.setText(hour+":"+minute / 10 + minute % 10 +":"+second / 10 + second % 10+" "+time_ampm);
		}
		
        if(selected2==true){
        	label_image.setIcon(new ImageIcon("C:\\Users\\aelay\\workspace\\MicrowaveSimulator\\microwaveopen.png"));
        	btn_start.setText("CLOSE");
        }
        else{
        	btn_start.setText("OPEN");
    		label_image = new JButton(new ImageIcon("microwave_empty.jpg"));
    		label_image.setBounds(33, 91, 309, 153);
    		getContentPane().add(label_image);
        }
        
        if(counter%2!=0){
        	btn_red.setIcon(new ImageIcon("C:\\Users\\aelay\\workspace\\MicrowaveSimulator\\red.png"));
        	counter++;
        }
        
        else
        	btn_red.setIcon(new ImageIcon("C:\\Users\\aelay\\workspace\\MicrowaveSimulator\\bin\\microwave\\off.jpg"));
		
	}
	public static void main(String[] args)
	{
	microwaveMain f = new microwaveMain();
	f.start();
	f.setSize(700,400);
	f.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		
	}
}
