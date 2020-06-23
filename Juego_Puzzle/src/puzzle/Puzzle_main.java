package puzzle;

import java.awt.event.*;
import java.util.Random;
import javax.swing.*;


public class Puzzle_main implements ActionListener {

	private JFrame ventana;
	private JButton btn_piezas[];
	private JButton btn_inicio, btn_temp, btn_reinicio, btn_inicio2;
	private JLabel lbl_titulo1, lbl_seg, lbl_min, lbl_hora;
	private int dir_x, dir_y;
	private int btn_temp2;
	
	Timer timer = new Timer(1000, new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			int seg, min, hora;
			
			seg = Integer.parseInt(lbl_seg.getText());
			min = Integer.parseInt(lbl_min.getText());
			hora = Integer.parseInt(lbl_hora.getText());
			
			if(seg<59) {
				lbl_seg.setText(String.valueOf(seg+1));
			}
			else {
				lbl_seg.setText(String.valueOf(00));
				
				if(min<59) {
					lbl_min.setText(String.valueOf(min+1));
				}
				else {
					lbl_min.setText(String.valueOf(00));
					lbl_hora.setText(String.valueOf(hora+1));
					
				}
			}
		}
	});
		
	Timer movimiento = new Timer (50, new ActionListener() { 
		public void actionPerformed(ActionEvent e) {
			//btn_temp = (JButton)e.getSource();
			int x, y;
			x = btn_piezas[btn_temp2].getLocation().x;
			y = btn_piezas[btn_temp2].getLocation().y;
			
			int blanco_x, blanco_y;
			blanco_x = btn_piezas[15].getLocation().x;
			blanco_y = btn_piezas[15].getLocation().y;
			
			
			if(x+dir_x <= blanco_x && y+dir_y == blanco_y) {
				btn_piezas[btn_temp2].setLocation(x+dir_x, y+dir_y);
				
				System.out.println(btn_piezas[15].getLocation());
				System.out.println(btn_piezas[btn_temp2].getLocation());
				System.out.println(x+dir_x);
				System.out.println(x+80);
			}
			else {
				movimiento.stop();
				btn_piezas[15].setLocation(x-80, y-0);
			}
			
		}
		
	}); 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new Puzzle_main();

	}
	
	Puzzle_main(){
		
		ventana = new JFrame("ROMPECABEZAS");
		ventana.setBounds(20,20,1000,900);
		ventana.setLayout(null);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		lbl_titulo1 = new JLabel("<html>UNIVERSIDAD TECNOLOGICA DE PANANA<br>FACULTAD DE INGENIERIA EN SISTEMAS COMPUTACIONALES<br><br>LIC. DESARROLLO DE SOFTWARE<br><br>DESARROLLO DE SFOTWARE III<br><br>PROFESOR<br>RICARDO CHAN<br><br>ESTUDIANTE<br>RICARDO LOPEZ<br>N-21-267<br><br>GRUPO 1LS221<br><br>28 de junio 2020</html>");
		lbl_titulo1.setBounds(50,50,350,280);
		ventana.add(lbl_titulo1);
		
		lbl_seg = new JLabel("00");
		lbl_seg.setBounds(680, 100, 20, 20);
		ventana.add(lbl_seg);
		
		lbl_min = new JLabel("00");
		lbl_min.setBounds(660, 100, 20, 20);
		ventana.add(lbl_min);
		
		lbl_hora = new JLabel("00");
		lbl_hora.setBounds(640, 100, 20, 20);
		ventana.add(lbl_hora);
		
		//creacion de botones
		btn_piezas = new JButton[16];
		for(int i=0; i<btn_piezas.length; i++) {
			btn_piezas[i] = new JButton(String.valueOf(i+1));
			btn_piezas[i].setBounds(600+80*(i%4),150+80*(i/4),80,80);
			btn_piezas[i].setActionCommand(String.valueOf(i));
			btn_piezas[i].addActionListener(this);
			ventana.add(btn_piezas[i]);
		}
		btn_piezas[15].setVisible(false); //ultimo boton invisible para controlar las posiciones
		System.out.println(btn_piezas[15].getLocation().x);
		System.out.println(btn_piezas[15].getLocation().y);
		System.out.println(btn_piezas[14].getLocation().x);
		System.out.println(btn_piezas[14].getLocation().y);//el juego se gana cuando btn_piezas[14] pos en Y es = a btn_piezas[15] pos en Y+80 AND pos en X sean iguales
		System.out.println(btn_piezas[11].getLocation().x);
		System.out.println(btn_piezas[11].getLocation().y);
		
		
		btn_inicio = new JButton("INICIAR");
		btn_inicio.setBounds(600, 510, 100, 30);
		btn_inicio.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				lbl_seg.setText("00");
				lbl_min.setText("00");
				lbl_hora.setText("00");
				timer.start();
				iniciar1();
			}
		});
		ventana.add(btn_inicio);
		
		btn_inicio2 = new JButton("INICIAR 2");
		btn_inicio2.setBounds(700, 510, 100, 30);
		btn_inicio2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lbl_seg.setText("00");
				lbl_min.setText("00");
				lbl_hora.setText("00");
				timer.start();
				iniciar2();
			}
		});
		
		btn_reinicio = new JButton("REINICIAR");
		btn_reinicio.setBounds(600, 550, 100, 30);
		btn_reinicio.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				timer.stop();
				
				reiniciar();
			}
		});
		ventana.add(btn_reinicio);
		
		ventana.setVisible(true);

	}
	
	public void actionPerformed(ActionEvent e) {
		btn_temp2 = Integer.parseInt(e.getActionCommand());
		int xClick = btn_piezas[btn_temp2].getLocation().x;
		int yClick = btn_piezas[btn_temp2].getLocation().y;
		
		//btn_temp = (JButton)e.getSource();
		int blanco_x, blanco_y;
		blanco_x = btn_piezas[15].getLocation().x;
		blanco_y = btn_piezas[15].getLocation().y;
		
		if((xClick == blanco_x-80) && (yClick == blanco_y)){
			dir_x = 10;
			dir_y = 0;
			movimiento.start();
		}
		
		
		/*if((btn_temp.getLocation().x == (btn_piezas[15].getLocation().x)-80) && (btn_temp.getLocation().y == btn_piezas[15].getLocation().y)) {
			//movimiento.start();
			//dir_x = -80;
			//dir_y = 0;  NO FUNCIONO 
			btn_piezas[15].setLocation(btn_temp.getLocation().x, btn_temp.getLocation().y);
			btn_temp.setLocation(blanco_x, blanco_y);
			
		}	
		
		
		else if((btn_temp.getLocation().x == btn_piezas[15].getLocation().x) && (btn_temp.getLocation().y == (btn_piezas[15].getLocation().y)-80)) {
			btn_piezas[15].setLocation(btn_temp.getLocation().x, btn_temp.getLocation().y);
			btn_temp.setLocation(blanco_x, blanco_y);
		}  
		
		else if((btn_temp.getLocation().x == (btn_piezas[15].getLocation().x)+80) && (btn_temp.getLocation().y == btn_piezas[15].getLocation().y)) {
			btn_piezas[15].setLocation(btn_temp.getLocation().x, btn_temp.getLocation().y);
			btn_temp.setLocation(blanco_x, blanco_y);
		}
	
		else if((btn_temp.getLocation().x == btn_piezas[15].getLocation().x) && (btn_temp.getLocation().y == (btn_piezas[15].getLocation().y)+80)) {
			btn_piezas[15].setLocation(btn_temp.getLocation().x, btn_temp.getLocation().y);
			btn_temp.setLocation(blanco_x, blanco_y);
		}	*/
		
		
	}
	
	public void iniciar1() {
		int j, x, y;
		Random rnd = new Random();
		
		for(int i=0; i<btn_piezas.length-1; i++) { 
			
			x = btn_piezas[i].getLocation().x;
			y = btn_piezas[i].getLocation().y;
			j = rnd.nextInt(15);
				
			btn_piezas[i].setLocation(btn_piezas[j].getLocation().x, btn_piezas[j].getLocation().y);
			btn_piezas[j].setLocation(x, y);	
			
		}
		
		System.out.println("espacio en blanco: "+btn_piezas[15].getLocation().x);
	}
	
	public void iniciar2() {
		int x, y;
		x = btn_piezas[15].getLocation().x;
		y = btn_piezas[15].getLocation().y;
		
		btn_piezas[15].setLocation(btn_piezas[14].getLocation().x, btn_piezas[14].getLocation().y);
		btn_piezas[14].setLocation(x, y);
	}

	
	public void reiniciar() {
		for(int i=0; i<btn_piezas.length; i++) {
			btn_piezas[i].setText(String.valueOf(i+1));
			btn_piezas[i].setLocation(600+80*(i%4),150+80*(i/4));
			ventana.add(btn_piezas[i]);
		}
		btn_piezas[15].setVisible(false);
		movimiento.stop();
	}

}
