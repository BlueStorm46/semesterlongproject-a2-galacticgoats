package View;

import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.IndexColorModel;
import java.beans.EventHandler;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Model.BurningShip;
import Model.ColorModelFactory;
import Model.Julia;
import Model.Mandelbrot;
import Model.Multibrot;
import edu.buffalo.fractal.FractalPanel;

public class UI {

	public UI() {
		createPanel();
	}

	FractalPanel fp;
	JFrame frame;
	JMenuBar menuBar;
	JPanel panel;
	int currentFractal = 0;
	private int currentColor = 0;
	public double escapeDistance = 1; // Default escape distance

	public void createPanel() {

		fp = new FractalPanel();
		frame = new JFrame("Fractal Panel");
		panel = new JPanel();
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

		JMenuItem blue = new JMenuItem("Blue");
		JMenuItem gray = new JMenuItem("Gray");
		JMenuItem rainbow = new JMenuItem("Rainbow");

		color.add(blue);
		color.add(gray);
		color.add(rainbow);

		// Adding ActionListeners to color options
		ActionListener bh = new blueHandler();
		blue.addActionListener(bh);
		ActionListener gh = new grayHandler();
		gray.addActionListener(gh);
		ActionListener rh = new rainbowHandler();
		rainbow.addActionListener(rh);

		// Adding Exit and change escape distance option to Menu bar
		JMenuItem exit = new JMenuItem("Exit");
		JMenuItem ced = new JMenuItem("Escape Distance...");
		file.add(ced);
		file.add(exit);

		// Adding ActionListeners to escapeDistance
		ActionListener ed = new escapeDistanceHandler();
		ced.addActionListener(ed);
		
		// Adding ActionListeners to exit
		ActionListener eh = new exitHandler();
		exit.addActionListener(eh);

		frame.setJMenuBar(menuBar);
		frame.setSize(512, 512);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		// Sets Mandelbrot to be the default fractal
		updateFractalPanel(1);
	}
	
	public class escapeDistanceHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String ed = "0";
			ed = JOptionPane.showInputDialog(frame, "Enter a number greater than 0");
			boolean done = false;
			while (done) {
				try { 
					while (Double.parseDouble(ed) <= 0) {
						ed = JOptionPane.showInputDialog(frame, "Invalid Escape Distance");
					}
					done = true;
				}
				catch (NumberFormatException InvalidFormat) {
					ed = JOptionPane.showInputDialog(frame, "Invalid Escape Distance");
				}
				catch (NullPointerException EmptyString) {
					ed = JOptionPane.showInputDialog(frame, "Invalid Escape Distance");
				}
			}
			escapeDistance = Double.parseDouble(ed);
			updateFractalPanel(currentFractal);
		}
	}
	
	public class exitHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	public class MandelbrotEventHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			updateFractalPanel(1);
		}
	}

	public class JuliaEventHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			updateFractalPanel(2);
		}
	}

	public class BurningShipHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			updateFractalPanel(3);
		}
	}

	public class MultibrotHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			updateFractalPanel(4);
		}
	}

	public class blueHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			IndexColorModel icm = ColorModelFactory.createBluesColorModel(255);
			fp.setIndexColorModel(icm);
			updateColor(1);
		}
	}

	public class grayHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			IndexColorModel icm = ColorModelFactory.createGrayColorModel(255);
			fp.setIndexColorModel(icm);
			updateColor(2);
		}
	}

	public class rainbowHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			IndexColorModel icm = ColorModelFactory.createGrayColorModel(255);
			fp.setIndexColorModel(icm);
			updateColor(3);
		}
	}

	public void updateFractalPanel(int num) {
		panel.removeAll();
		currentFractal = num;
		panel.add(fp);
		frame.add(panel);
		frame.pack();
		if (currentFractal == 1) {
			Mandelbrot mb = new Mandelbrot();
			fp.updateImage(mb.createMandel(escapeDistance));
		}

		if (currentFractal == 2) {
			Julia j = new Julia();
			fp.updateImage(j.createJulia(escapeDistance));
		}

		if (currentFractal == 3) {
			BurningShip bs = new BurningShip();
			fp.updateImage(bs.createBS(escapeDistance));
		}

		if (currentFractal == 4) {
			Multibrot mu = new Multibrot();
			fp.updateImage(mu.createMulti(escapeDistance));
		}

	}

	public void updateColor(int n) {
		currentColor = n;
		if (n == 1) {
			if (currentFractal == 1) {
				Mandelbrot mb = new Mandelbrot();
				fp.setIndexColorModel(ColorModelFactory.createBluesColorModel(255));
				fp.updateImage(mb.createMandel(escapeDistance));
			}
			if (currentFractal == 2) {
				Julia j = new Julia();
				fp.setIndexColorModel(ColorModelFactory.createBluesColorModel(255));
				fp.updateImage(j.createJulia(escapeDistance));
			}

			if (currentFractal == 3) {
				BurningShip bs = new BurningShip();
				fp.setIndexColorModel(ColorModelFactory.createBluesColorModel(255));
				fp.updateImage(bs.createBS(escapeDistance));
			}

			if (currentFractal == 4) {
				Multibrot mu = new Multibrot();
				fp.setIndexColorModel(ColorModelFactory.createBluesColorModel(255));
				fp.updateImage(mu.createMulti(escapeDistance));
			}
		}

		if (n == 2) {
			if (currentFractal == 1) {
				Mandelbrot mb = new Mandelbrot();
				fp.setIndexColorModel(ColorModelFactory.createGrayColorModel(255));
				fp.updateImage(mb.createMandel(escapeDistance));
			}
			if (currentFractal == 2) {
				Julia j = new Julia();
				fp.setIndexColorModel(ColorModelFactory.createGrayColorModel(255));
				fp.updateImage(j.createJulia(escapeDistance));
			}

			if (currentFractal == 3) {
				BurningShip bs = new BurningShip();
				fp.setIndexColorModel(ColorModelFactory.createGrayColorModel(255));
				fp.updateImage(bs.createBS(escapeDistance));
			}

			if (currentFractal == 4) {
				Multibrot mu = new Multibrot();
				fp.setIndexColorModel(ColorModelFactory.createGrayColorModel(255));
				fp.updateImage(mu.createMulti(escapeDistance));
			}
		}

		if (n == 3) {
			if (currentFractal == 1) {
				Mandelbrot mb = new Mandelbrot();
				fp.setIndexColorModel(ColorModelFactory.createRainbowColorModel(255));
				fp.updateImage(mb.createMandel(escapeDistance));
			}
			if (currentFractal == 2) {
				Julia j = new Julia();
				fp.setIndexColorModel(ColorModelFactory.createRainbowColorModel(255));
				fp.updateImage(j.createJulia(escapeDistance));
			}

			if (currentFractal == 3) {
				BurningShip bs = new BurningShip();
				fp.setIndexColorModel(ColorModelFactory.createRainbowColorModel(255));
				fp.updateImage(bs.createBS(escapeDistance));
			}

			if (currentFractal == 4) {
				Multibrot mu = new Multibrot();
				fp.setIndexColorModel(ColorModelFactory.createRainbowColorModel(255));
				fp.updateImage(mu.createMulti(escapeDistance));
			}

		}
	}
}