package View;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import Model.BurningShip;
import Model.Julia;
import Model.Mandelbrot;
import Model.Model;
import Model.Multibrot;
import edu.buffalo.fractal.FractalPanel;

public class UI {

	public UI() {

		createPanel();

	}

	FractalPanel fp;
	JFrame frame;
	JMenuBar menuBar;

	public void createPanel() {

		fp = new FractalPanel();
		frame = new JFrame("Fractal Panel");
		menuBar = new JMenuBar();

		JMenu file = new JMenu("File");
		JMenu fractal = new JMenu("Fractal");
		JMenu color = new JMenu("Color");

		menuBar.add(file);
		menuBar.add(fractal);
		menuBar.add(color);

		// Adding fractal options to Fractal Menu bar
		JMenuItem mandelbrot = new JMenuItem("Mandelbrot");
		JMenuItem julia = new JMenuItem("Julia");
		JMenuItem burningShip = new JMenuItem("Burning Ship");
		JMenuItem multibrot = new JMenuItem("Multibrot");

		fractal.add(mandelbrot);
		fractal.add(julia);
		fractal.add(burningShip);
		fractal.add(multibrot);

		// Adding ActionListerners to fractal options
		ActionListener mb = new MandelbrotEventHandler();
		mandelbrot.addActionListener(mb);
		ActionListener j = new JuliaEventHandler();
		julia.addActionListener(j);
		ActionListener bs = new BurningShipHandler();
		burningShip.addActionListener(bs);
		ActionListener mu = new MultibrotHandler();
		multibrot.addActionListener(mu);
		//
		// Adding colors schemes to Color Menu bar
		JMenuItem red = new JMenuItem("Red");
		JMenuItem green = new JMenuItem("Green");
		JMenuItem blue = new JMenuItem("Blue");
		JMenuItem black = new JMenuItem("Black");

		color.add(red);
		color.add(green);
		color.add(blue);
		color.add(black);

		// Adding ActionListeners to color options
		// red.addActionListener();
		// green.addActionListener();
		// blue.addActionListener();
		// black.addActionListener();

		// Adding Exit and change escape distance option to Menu bar
		JMenuItem exit = new JMenuItem("Exit");
		JMenuItem ced = new JMenuItem("Edit Escape Distance");
		file.add(ced);
		file.add(exit);

		frame.setJMenuBar(menuBar);
		frame.setSize(512, 512);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public class MandelbrotEventHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Mandelbrot mb = new Mandelbrot();
			fp = new FractalPanel();
			fp.updateImage(mb.createMandel());
			frame.add(fp);
			frame.pack();

		}
	}

	public class JuliaEventHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Julia j = new Julia();
			fp = new FractalPanel();
			fp.updateImage(j.createJulia());
			frame.add(fp);
			frame.pack();

		}
	}

	public class BurningShipHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			BurningShip bs = new BurningShip();
			fp = new FractalPanel();
			fp.updateImage(bs.createBS());
			frame.add(fp);
			frame.pack();
		}
	}

	public class MultibrotHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Multibrot mu = new Multibrot();
			fp = new FractalPanel();
			fp.updateImage(mu.createMulti());
			frame.add(fp);
			frame.pack();

		}

	}

}
