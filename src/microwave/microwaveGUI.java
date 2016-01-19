package microwave;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

public class microwaveGUI extends JFrame implements Runnable,ActionListener {

	private JPanel contentPane;
	protected Thread clockThread = null;
	Calendar calendar;
	int hour=0,minute=0,second=0,ampm=0,timer_clock=0,over=0;
	JLabel label_clock,label_message,label_image;
	JToggleButton btn_lock,btn_open,btn_start;
	String time_ampm = "",timer_choices="";
	 Object	input_choices="";
	protected Font font = new Font("Arial Rounded MT Bold", Font.BOLD,30);
	private JButton btn_red,btnNewButton;
	String[] choices = { "Popcorn- 15min", "Potato-20min", "Pizza-60min", "Bevarage-10min", "Soup-15min", "Dinner Plate-3min","Fresh Vegetable-5min","Frozen vegetable-10min" };
	String[] timer={"15","20","60","10","15","3","5","10"};
	boolean selected;
	Image bg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
					microwaveGUI frame = new microwaveGUI();
					frame.start();
					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}

	/**
	 * Create the frame.
	 */
	public microwaveGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		getContentPane().setBackground(Color.white);
		label_clock = new JLabel("");
		label_clock.setBounds(0, 0, 86, 34);
		getContentPane().add(label_clock);
		
		label_message = new JLabel("Welcome!");
		label_message.setBounds(101, 3, 215, 30);
		getContentPane().add(label_message);
		
		btn_lock = new JToggleButton("New toggle button");
		btn_lock.setBounds(331, 3, 82, 29);
		getContentPane().add(btn_lock);
		
		btn_open = new JToggleButton("START");
		btn_open.setBounds(0, 46, 86, 29);
		getContentPane().add(btn_open);
		btn_open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent open_ae) {
		        AbstractButton open_ab = (AbstractButton) open_ae.getSource();
		        selected = open_ab.getModel().isSelected();
		        System.out.println("Action - selected=" + selected + "\n");
		        if(selected==true){
		        	input_choices= JOptionPane.showInputDialog(null,"Type of Food","Selected",
		        			JOptionPane.QUESTION_MESSAGE,null,choices,choices[1]);
			        System.out.println("Action - selected=" + input_choices.toString());
			        for(int i=0;i<8;i++){
			        	if(input_choices.toString()== choices[i]){
			        	JOptionPane.showMessageDialog(null, "Your timer is set for "+timer[i]+" minutes");
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
		});
		
		btn_start = new JToggleButton("OPEN");
		btn_start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent start_ae) {
				
			}
		});
		btn_start.setBounds(167, 46, 163, 29);
		getContentPane().add(btn_start);
		
		btn_red = new JButton("New button");
		btn_red.setBounds(341, 48, 37, 29);
		getContentPane().add(btn_red);
		
		btnNewButton = new JButton("New button");
		btnNewButton.setBounds(380, 48, 33, 29);
		getContentPane().add(btnNewButton);
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
			if(timer_clock!=0){
				label_clock.setText("00:"+(timer_clock));
				timer_clock--;
				if(timer_clock==0){
					over=1;
				}
			}
			if(over==1){
				JOptionPane.showMessageDialog(null,"Your Food is ready!");
				label_clock.setText(hour+":"+minute / 10 + minute % 10 +":"+second / 10 + second % 10+" "+time_ampm);
			}
		}

		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
