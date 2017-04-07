package code.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.IndexColorModel;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import code.fractals.*;
import edu.buffalo.fractal.FractalPanel;

public class UI {

	public UI() {
		createPanel();
	}

	/** Instance Variables */
	FractalPanel fp;
	JFrame frame;
	JMenuBar menuBar;
	JPanel panel;
	
	/** Global Variables */
	public double escapeDistance = 2; 	// Default Escape Distance: 2
	public int escapeTime = 255;		// Default Escape Time: 255
	int currentFractal = 1;				// Default Fractal: Mandelbrot

	public void createPanel() {

		/** JFrame Skeleton */
		fp = new FractalPanel();
		frame = new JFrame("Fractal Panel");
		panel = new JPanel();
		menuBar = new JMenuBar(); 

		/** Menubar Menus */
		JMenu file = new JMenu("File");
		JMenu fractal = new JMenu("Fractal");
		JMenu color = new JMenu("Color");
		menuBar.add(file);
		menuBar.add(fractal);
		menuBar.add(color);
		
		/** Escape Distance & Exit Menu */
		JMenuItem ced = new JMenuItem("Escape Distance...");
		JMenuItem cet = new JMenuItem("Escape Time...");
		JMenuItem exit = new JMenuItem("Quit");
		file.add(ced);
		file.add(cet);
		file.add(exit);
		/** Escape Distance ActionListener */
		ActionListener ed = new escapeDistanceHandler();
		ced.addActionListener(ed);
		/** Escape Time ActionListener */
		ActionListener et = new escapeTimeHandler();
		cet.addActionListener(et);
		/** Quit ActionListener */
		ActionListener eh = new exitHandler();
		exit.addActionListener(eh);

		/** Fractal Menu Buttons */
		JMenuItem mandelbrot = new JMenuItem("Mandelbrot");
		JMenuItem julia = new JMenuItem("Julia");
		JMenuItem burningShip = new JMenuItem("Burning Ship");
		JMenuItem multibrot = new JMenuItem("Multibrot");
		fractal.add(mandelbrot);
		fractal.add(julia);
		fractal.add(burningShip);
		fractal.add(multibrot);
		/** Fractal Menu ActionListerners */
		ActionListener mb = new MandelbrotEventHandler();
		mandelbrot.addActionListener(mb);
		ActionListener j = new JuliaEventHandler();
		julia.addActionListener(j);
		ActionListener bs = new BurningShipHandler();
		burningShip.addActionListener(bs);
		ActionListener mu = new MultibrotHandler();
		multibrot.addActionListener(mu);

		/** Color Menu Buttons */
		JMenuItem rainbow = new JMenuItem("Rainbow");
		JMenuItem blue = new JMenuItem("Blue");
		JMenuItem green = new JMenuItem("Green");
		JMenuItem gray = new JMenuItem("Gray");
		color.add(rainbow);
		color.add(blue);
		color.add(green);
		color.add(gray);
		/** Color Menu ActionListerners */
		ActionListener rh = new rainbowHandler();
		rainbow.addActionListener(rh);
		ActionListener bh = new blueHandler();
		blue.addActionListener(bh);
		ActionListener gh = new greenHandler();
		green.addActionListener(gh);
		ActionListener grh = new grayHandler();
		gray.addActionListener(grh);

		/** Finish Creating GUI */
		frame.setJMenuBar(menuBar);
		frame.setSize(512, 512);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		/** Draws Default Fractal */
		updateColor(1); // For some reason, the update color method must be called at least once in order to draw the fractals correctly.
		
		// updateFractal(currentFractal);
	}
	
	public class escapeDistanceHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			updateEscapeDistance();
		}
	}
	
	public class escapeTimeHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			updateEscapeTime();
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
			updateFractal(1);
		}
	}

	public class JuliaEventHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			updateFractal(2);
		}
	}

	public class BurningShipHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			updateFractal(3);
		}
	}

	public class MultibrotHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			updateFractal(4);
		}
	}

	public class rainbowHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			IndexColorModel icm = ColorModelFactory.createGrayColorModel(255);
			fp.setIndexColorModel(icm);
			updateColor(1);
		}
	}
	
	public class blueHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			IndexColorModel icm = ColorModelFactory.createBlueColorModel(255);
			fp.setIndexColorModel(icm);
			updateColor(2);
		}
	}
	
	public class greenHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			IndexColorModel icm = ColorModelFactory.createGreenColorModel(255);
			fp.setIndexColorModel(icm);
			updateColor(3);
		}
	}

	public class grayHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			IndexColorModel icm = ColorModelFactory.createGrayColorModel(255);
			fp.setIndexColorModel(icm);
			updateColor(4);
		}
	}

	/** The user inputs a value that is stored as a string. The string is then converted into a double using parseDouble.
	 * 	If the user enters anything that isn't a number, a NumberFormatException is thrown, and the user will be asked to input a new value.
	 * 	If the user enters a number less than or equal to zero, it is rejected, and the user is asked to input once more.
	 * 	This will continue until the user enters a valid number, or clicks the "Cancel" button.
	 */
	public void updateEscapeDistance() {
		String ed = JOptionPane.showInputDialog(frame, "Default: 2.0\n\nEnter number greater than 0:");
		try { 
			if (Double.parseDouble(ed) > 0) {
				escapeDistance = Double.parseDouble(ed);
			}
			else {
				updateEscapeDistance();
			}
			
		}
		catch (NumberFormatException InvalidFormat) {
			updateEscapeDistance();
		}
		catch (NullPointerException EmptyString) {
			/** Assume user hit the "Cancel" button and do nothing. */
		}
		updateFractal(currentFractal);
	}
	
	public void updateEscapeTime() {
		String et = JOptionPane.showInputDialog(frame, "Default: 255\n\nEnter integer greater than 0:");
		try { 
			if (Integer.parseInt(et) > 0) {
				escapeTime = Integer.parseInt(et);
			}
			else {
				updateEscapeTime();
			}
			
		}
		catch (NumberFormatException InvalidFormat) {
			updateEscapeTime();
		}
		catch (NullPointerException EmptyString) {
			/** Assume user hit the "Cancel" button and do nothing. */
		}
		updateFractal(currentFractal);
	}

	public void updateFractal(int num) {
		panel.removeAll();
		currentFractal = num;
		panel.add(fp);
		frame.add(panel);
		frame.pack();
		if (currentFractal == 1) {
			Mandelbrot mb = new Mandelbrot();
			fp.updateImage(mb.createMandel(escapeDistance, escapeTime));
		}

		if (currentFractal == 2) {
			Julia j = new Julia();
			fp.updateImage(j.createJulia(escapeDistance, escapeTime));
		}

		if (currentFractal == 3) {
			BurningShip bs = new BurningShip();
			fp.updateImage(bs.createBS(escapeDistance, escapeTime));
		}

		if (currentFractal == 4) {
			Multibrot mu = new Multibrot();
			fp.updateImage(mu.createMulti(escapeDistance, escapeTime));
		}

	}

	public void updateColor(int n) {
		if (n == 1) {
			if (currentFractal == 1) {
				Mandelbrot mb = new Mandelbrot();
				fp.setIndexColorModel(ColorModelFactory.createRainbowColorModel(255));
				fp.updateImage(mb.createMandel(escapeDistance, escapeTime));
			}
			if (currentFractal == 2) {
				Julia j = new Julia();
				fp.setIndexColorModel(ColorModelFactory.createRainbowColorModel(255));
				fp.updateImage(j.createJulia(escapeDistance, escapeTime));
			}

			if (currentFractal == 3) {
				BurningShip bs = new BurningShip();
				fp.setIndexColorModel(ColorModelFactory.createRainbowColorModel(255));
				fp.updateImage(bs.createBS(escapeDistance, escapeTime));
			}

			if (currentFractal == 4) {
				Multibrot mu = new Multibrot();
				fp.setIndexColorModel(ColorModelFactory.createRainbowColorModel(255));
				fp.updateImage(mu.createMulti(escapeDistance, escapeTime));
			}
		}
		if (n == 2) {
			if (currentFractal == 1) {
				Mandelbrot mb = new Mandelbrot();
				fp.setIndexColorModel(ColorModelFactory.createBlueColorModel(255));
				fp.updateImage(mb.createMandel(escapeDistance, escapeTime));
			}
			if (currentFractal == 2) {
				Julia j = new Julia();
				fp.setIndexColorModel(ColorModelFactory.createBlueColorModel(255));
				fp.updateImage(j.createJulia(escapeDistance, escapeTime));
			}

			if (currentFractal == 3) {
				BurningShip bs = new BurningShip();
				fp.setIndexColorModel(ColorModelFactory.createBlueColorModel(255));
				fp.updateImage(bs.createBS(escapeDistance, escapeTime));
			}

			if (currentFractal == 4) {
				Multibrot mu = new Multibrot();
				fp.setIndexColorModel(ColorModelFactory.createBlueColorModel(255));
				fp.updateImage(mu.createMulti(escapeDistance, escapeTime));
			}
		}
		if (n == 3) {
			if (currentFractal == 1) {
				Mandelbrot mb = new Mandelbrot();
				fp.setIndexColorModel(ColorModelFactory.createGreenColorModel(255));
				fp.updateImage(mb.createMandel(escapeDistance, escapeTime));
			}
			if (currentFractal == 2) {
				Julia j = new Julia();
				fp.setIndexColorModel(ColorModelFactory.createGreenColorModel(255));
				fp.updateImage(j.createJulia(escapeDistance, escapeTime));
			}

			if (currentFractal == 3) {
				BurningShip bs = new BurningShip();
				fp.setIndexColorModel(ColorModelFactory.createGreenColorModel(255));
				fp.updateImage(bs.createBS(escapeDistance, escapeTime));
			}

			if (currentFractal == 4) {
				Multibrot mu = new Multibrot();
				fp.setIndexColorModel(ColorModelFactory.createGreenColorModel(255));
				fp.updateImage(mu.createMulti(escapeDistance, escapeTime));
			}
		}
		if (n == 4) {
			if (currentFractal == 1) {
				Mandelbrot mb = new Mandelbrot();
				fp.setIndexColorModel(ColorModelFactory.createGrayColorModel(255));
				fp.updateImage(mb.createMandel(escapeDistance, escapeTime));
			}
			if (currentFractal == 2) {
				Julia j = new Julia();
				fp.setIndexColorModel(ColorModelFactory.createGrayColorModel(255));
				fp.updateImage(j.createJulia(escapeDistance, escapeTime));
			}

			if (currentFractal == 3) {
				BurningShip bs = new BurningShip();
				fp.setIndexColorModel(ColorModelFactory.createGrayColorModel(255));
				fp.updateImage(bs.createBS(escapeDistance, escapeTime));
			}

			if (currentFractal == 4) {
				Multibrot mu = new Multibrot();
				fp.setIndexColorModel(ColorModelFactory.createGrayColorModel(255));
				fp.updateImage(mu.createMulti(escapeDistance, escapeTime));
			}
		}
	}
}