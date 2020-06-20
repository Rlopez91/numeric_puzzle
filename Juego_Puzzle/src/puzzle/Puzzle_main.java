package puzzle;

import java.awt.event.*;
import java.util.Random;
import javax.swing.*;


public class Puzzle_main implements ActionListener {

	private JFrame ventana;
	private JButton btn_piezas[];
	private JButton btn_inicio, btn_temp, btn_reinicio;
	private JLabel lbl_titulo1, lbl_seg, lbl_min, lbl_hora;
	
	Timer timer = new Timer(500, new ActionListener(){
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
		btn_temp = (JButton)e.getSource();
		int blanco_x, blanco_y;
		blanco_x = btn_piezas[15].getLocation().x;
		blanco_y = btn_piezas[15].getLocation().y;
		
		if((btn_temp.getLocation().x == (btn_piezas[15].getLocation().x)-80) && (btn_temp.getLocation().y == btn_piezas[15].getLocation().y)) {
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
		}	
		
		
	}
	
	public void iniciar1() {
		int j, x, y;
		Random rnd = new Random();
		
		//btn_piezas[15].setLocation(840, 390);
		
		for(int i=0; i<btn_piezas.length-1; i++) { 
			//btn_piezas[i].setBounds(600+80*(i%4),150+80*(i/4),80,80);
			
			x = btn_piezas[i].getLocation().x;
			y = btn_piezas[i].getLocation().y;
			j = rnd.nextInt(15);
				
			btn_piezas[i].setLocation(btn_piezas[j].getLocation().x, btn_piezas[j].getLocation().y);
			btn_piezas[j].setLocation(x, y);	
			//btn_piezas[15].setLocation(840, 390);
			
		}
		
		//btn_piezas[15].setLocation(840, 390);
		System.out.println("espacio en blanco: "+btn_piezas[15].getLocation().x);
	}
	
	//crear un boton de reinicio
	//intentar la condicion de reinicio para cuando la j sea = 15
	
	public void reiniciar() {
		for(int i=0; i<btn_piezas.length; i++) {
			btn_piezas[i].setText(String.valueOf(i+1));
			btn_piezas[i].setLocation(600+80*(i%4),150+80*(i/4));
			ventana.add(btn_piezas[i]);
		}
		btn_piezas[15].setVisible(false);
	}

}
