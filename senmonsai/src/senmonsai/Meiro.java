package senmonsai;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Meiro extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Meiro frame = new Meiro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	private static int[][] MakeMaze(int[][] maze, int x, int y, int c) {
		// TODO 自動生成されたメソッド・スタブ
		int Make[][] = maze;

		switch (c) {

		case 0:
			Make[x - 1][y] = 0;
				Make[x - 2][y] = 0; break;

		case 1:
			Make[x][y + 1] = 0;
				Make[x][y + 2] = 0; break;

		case 2:
			Make[x + 1][y] = 0;
				Make[x + 2][y] = 0; break;

		case 3:
			Make[x][y - 1] = 0;
				Make[x][y - 2] = 0; break;
		}
		return Make;
	}

	private static int SerchMaze(int[][]a, int x, int y, int b, int i) {
		// TODO 自動生成されたメソッド・スタブ
		int o = -1;

		switch (b) {
		case 0:
			if (x - 2 > 1 && a[x - 2][y] == 1 && a[x - 1][y] == 1)	//0の場合上を調べる
				o = 1; break;

		case 1:
			if ( y + 2 < i - 2 && a[x][y + 2] == 1 && a[x][y + 1] == 1)	//1の場合右を調べる
				o = 1; break;

		case 2:
			if (x + 2 < i - 2 && a[x + 2][y] == 1 && a[x + 1][y] == 1)	//2の場合下を調べる
				o = 1; break;

		case 3:
			if (y - 2 > 1 && a[x][y - 2] == 1 && a[x][y - 1] == 1)	//3の場合左を調べる
				o = 1; break;

		}
		return o;
	}

	private static int[][] Maze(int i) {
		// TODO 自動生成されたメソッド・スタブ
		Random rand = new Random();
		int o;
		int x;
		int y;
		int d = 1;

		int[][] rog = new int[2][i * i];	//通った場所の保存
		Integer a [] = new Integer[4];	//四方に進めるか調べた結果を入れる
		int n = 0;	//配列の中身を調べる
		int c = 0;	//方向の確定
		int[][] maze = new int[i][i];

		for (x = 0; i > x; x++) {
			for (y = 0; i > y; y++) {

				maze[x][y] = 1;	//1で壁にする
			}
		}

		int Goal = rand.nextInt(i - 5) + 2;
		x = Goal;
		maze[x][i - 2] = 0;
		maze[x][i - 1] = 0;	//ゴール地点

		int Start;
		do {
			Start = rand.nextInt((i - 5) / 2 + 2);
			}while (Start == 0);
		Start = Start * 2;

		x = Start;
		maze[x][0] = 0;
		maze[x][1] = 0;	//スタート地点
		y = 2;
		maze[x][y] = 0;	//1マス目

		rog[0][0] = x;
		rog[1][0] = y;

		for(int l = 0; ;) {

		for(int b = 0; b <= 3; b++) {

		o = SerchMaze(maze, rog[0][l], rog[1][l], b, i);	//壁か調べる(2マス先が1なら1、そうでなければ-1を返す)
		a[b] = o;

		}

		if (y > 1 && y == i - 3 && ((maze[x + 1][y + 1] == 0 && maze[x + 2][y] == 1) || (maze[x - 1][y + 1] == 0 && maze[x - 2][y] == 1))) {	//ゴール近くで止まった場合

			if(maze[x + 1][y + 1] == 0)
				c = 2;
			else
			c = 0;
		}

		else if(Arrays.asList(a).contains(1)) {
			do {
				c = rand.nextInt(4);//while文と乱数を使う
				n = a[c];
			} while (n != 1);
		}

			else n = -1;

		if (n == 1) {
			maze = MakeMaze(maze,rog[0][l], rog[1][l], c);	//迷路を作る

			switch (c) {	//rogに入る数字を変える
			case 0:
				x = x - 2; break;
			case 1:
				y = y + 2; break;
			case 2:
				x = x + 2; break;
			case 3:
				y = y - 2; break;
			}

			l = l + 1;
			rog[0][l] = x;
			rog[1][l] = y;
		}

		else {	//作れなかった場合、通った場所の検索
			for (d = l; d > -1; d--) {
				for(int b = 0; b <= 3; b++) {

					o = SerchMaze(maze, rog[0][d], rog[1][d], b, i);	//壁か調べる(2マス先が1なら1、そうでなければ-1を返す)
					a[b] = o;
					}
				if (Arrays.asList(a).contains(1)) {
					l = d;
					x = rog[0][l];
					y = rog[1][l];
					break;
					}
				}
			}

		if (d < 0) {

			/*for (x = 0; i > x; x++) {
				for (y = 0; i > y; y++) {
					if (maze[x][y] == 0)
					System.out.print("  ");
					else
						System.out.print("[]");
				}
				System.out.println();
				}*/
			break;
			}
		}
		return maze;
	}
	/**
	 * Create the frame.
	 */

	public static int x;
	public static int y;
	public static int i;
	public static int maze [][];

	public Meiro() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 700, 720);
		contentPane = new JPanel();
		contentPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		x = 0;
		y = 0;
		i = 2 * 15 + 1;
		maze = new int[i][i];
		maze =Maze(i);

		JLabel label = new JLabel("ゴール!!");
		label.setForeground(Color.RED);
		label.setFont(new Font("HG創英角ﾎﾟｯﾌﾟ体", Font.BOLD | Font.ITALIC, 99));
		label.setBounds(118, 249, 461, 150);
		label.setVisible(false);
		contentPane.add(label);

		for (x = 0; i > x; x++) {
			for (y = 0; i > y; y++) {
				if (maze[x][y] == 0) {

				System.out.print("  ");




				}
				else {
					System.out.print("[]");

				panel = new JPanel();
				panel.setBackground(Color.BLACK);
				panel.setBounds(y * 10, x * 10, 10, 10);
				contentPane.add(panel);
				}

			}
			System.out.println();
			}
		for(x = 0; x < i; x++) {
			if (maze[x][0] == 0)
				break;
		}

		y = 0;




		panel_1 = new JPanel();
		panel_1.setBackground(Color.GREEN);
		panel_1.setBounds(y * 10, x * 10, 10, 10);
		contentPane.add(panel_1);

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {


				switch (e.getKeyCode()) {
		        case KeyEvent.VK_UP:
		            // up arrow
		        	if(maze[x - 1][y] == 0) {
		        		x = x - 1;
			        	panel_1.setBounds(y * 10, x * 10, 10, 10);
		        	}

		            break;
		        case KeyEvent.VK_DOWN:
		            // down arrow
		        	if(maze[x + 1][y] == 0) {
			        	x = x + 1;
			        	panel_1.setBounds(y * 10, x * 10, 10, 10);
		        	}
		            break;
		        case KeyEvent.VK_RIGHT:
		            // right arrow
		        	if(maze[x][y + 1] == 0) {
		        	y = y + 1;
		        		panel_1.setBounds(y * 10, x * 10, 10, 10);
		        		if (y == i - 1) {
		        			label.setVisible(true);

		        		}
		        	}
		            break;
		        case KeyEvent.VK_LEFT:
		            // left arrow
		        	if(maze[x][y - 1] == 0) {
				        	y = y - 1;
			        	panel_1.setBounds(y * 10, x * 10, 10, 10);
		        	}
		            break;
				}


			}
		});

	}
}

