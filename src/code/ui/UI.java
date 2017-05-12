package code.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.IndexColorModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import code.fractals.*;
import edu.buffalo.fractal.FractalPanel;

public class UI {

	/** Instance Variables */
	FractalPanel fp;
	JFrame frame;
	JMenuBar menuBar;
	JPanel panel;
	JLabel label;

	/** Global Variables */
	double escapeDistance = 2; 					// Default Escape Distance: 2
	int escapeTime = 255; 						// Default Escape Time: 255
	int threads = 1; 							// Default Threads: 1
	int currentFractal = 1; 					// Default Fractal: Mandelbrot
	int size = 2048;							// Default Size: 2048x2048
	double x_min, x_max, y_min, y_max;

	public UI() {
		createPanel();
	}

	public void createPanel() {

		/** JFrame Skeleton */
		frame = new JFrame("Fractal Panel");
		panel = new JPanel();
		fp = new FractalPanel();
		menuBar = new JMenuBar();

		/** Ability to use mouse controls */
		MouseDragHandler mdh = new MouseDragHandler();
		fp.addMouseListener(mdh);
		fp.addMouseMotionListener(mdh);

		/** MENUBAR MENUS */
		JMenu file = new JMenu("Options");
		JMenu fractal = new JMenu("Fractal");
		JMenu color = new JMenu("Color");
		menuBar.add(file);
		menuBar.add(fractal);
		menuBar.add(color);

		/** OPTIONS MENU */
		JMenuItem crz = new JMenuItem("Reset Fractal");
		JMenuItem ced = new JMenuItem("Escape Distance...");
		JMenuItem cet = new JMenuItem("Escape Time...");
		JMenuItem cth = new JMenuItem("Threads...");
		JMenuItem exit = new JMenuItem("Quit");
		file.add(crz);
		file.add(ced);
		file.add(cet);
		file.add(cth);
		file.add(exit);
		/** Reset Zoom ActionListener */
		ActionListener rz = new resetZoomHandler();
		crz.addActionListener(rz);
		/** Escape Distance ActionListener */
		ActionListener ed = new escapeDistanceHandler();
		ced.addActionListener(ed);
		/** Escape Time ActionListener */
		ActionListener et = new escapeTimeHandler();
		cet.addActionListener(et);
		/** Threads ActionListener */
		ActionListener th = new threadsHandler();
		cth.addActionListener(th);
		/** Quit ActionListener */
		ActionListener eh = new exitHandler();
		exit.addActionListener(eh);

		/** FRACTAL MENU */
		JMenuItem mandelbrot = new JMenuItem("Mandelbrot");
		JMenuItem julia = new JMenuItem("Julia");
		JMenuItem burningShip = new JMenuItem("Burning Ship");
		JMenuItem multibrot = new JMenuItem("Multibrot");
		fractal.add(mandelbrot);
		fractal.add(julia);
		fractal.add(burningShip);
		fractal.add(multibrot);
		/** Fractal Menu ActionListerners */
		ActionListener mb = new MandelbrotHandler();
		mandelbrot.addActionListener(mb);
		ActionListener j = new JuliaHandler();
		julia.addActionListener(j);
		ActionListener bs = new BurningShipHandler();
		burningShip.addActionListener(bs);
		ActionListener mu = new MultibrotHandler();
		multibrot.addActionListener(mu);

		/** COLOR MENU */
		JMenuItem rainbow = new JMenuItem("Rainbow");
		JMenuItem blue = new JMenuItem("Underwater");
		JMenuItem green = new JMenuItem("Night Vision");
		JMenuItem gray = new JMenuItem("Monochrome");
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
		label = new JLabel();
		label.setText("\u2191 Select a Fractal to Begin                                                                                                ");
		label.setFont(new Font("Serif", Font.PLAIN, 50));
		label.setVerticalAlignment(JLabel.TOP);
		label.setHorizontalAlignment(JLabel.CENTER);
		frame.add(label);

		frame.setJMenuBar(menuBar);
		Dimension d = new Dimension(size, size);
		frame.setSize(d);
		fp.setSize(d);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	
		/** Sets Default Fractal Properties */
		resetZoom();
		updateColor(1);
	}

	/** EVENT HANDLERS */

	public class resetZoomHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			resetZoom();
			updateFractal();
		}
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

	public class threadsHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			updateThreads();
		}
	}

	public class exitHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	public class MandelbrotHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			currentFractal = 1;
			resetZoom();
			updateFractal();
		}
	}

	public class JuliaHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			currentFractal = 2;
			resetZoom();
			updateFractal();
		}
	}

	public class BurningShipHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			currentFractal = 3;
			resetZoom();
			updateFractal();
		}
	}

	public class MultibrotHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			currentFractal = 4;
			resetZoom();
			updateFractal();
		}
	}

	public class rainbowHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			IndexColorModel icm = ColorModelFactory.createGrayColorModel(255);
			fp.setIndexColorModel(icm);
			updateColor(1);
			updateFractal();
		}
	}

	public class blueHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			IndexColorModel icm = ColorModelFactory.createBlueColorModel(255);
			fp.setIndexColorModel(icm);
			updateColor(2);
			updateFractal();
		}
	}

	public class greenHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			IndexColorModel icm = ColorModelFactory.createGreenColorModel(255);
			fp.setIndexColorModel(icm);
			updateColor(3);
			updateFractal();
		}
	}

	public class grayHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			IndexColorModel icm = ColorModelFactory.createGrayColorModel(255);
			fp.setIndexColorModel(icm);
			updateColor(4);
			updateFractal();
		}
	}

	/** MOUSE HANDLER */
	public class MouseDragHandler extends MouseAdapter {
		Point startDrag, endDrag;

		public void mousePressed(MouseEvent e) {
			startDrag = new Point(e.getX(), e.getY());
			endDrag = startDrag;
			System.out.println("StartDrag: " + startDrag);
		}

		public void mouseDragged(MouseEvent e) {
			endDrag = new Point(e.getX(), e.getY());
		}

		public void mouseReleased(MouseEvent e) {
			double x_range = (x_max - x_min) / size;
			double y_range = (y_max - y_min) / size;
			x_min = x_min + (startDrag.x * x_range);
			x_max = x_min + (endDrag.x * x_range);
			y_min = y_min + (startDrag.y * y_range);
			y_max = y_min + (endDrag.y * y_range);

			System.out.println("EndDrag:   " + endDrag + "\n");

			startDrag = null;
			endDrag = null;

			updateFractal();
		}

		public void mouseMoved(MouseEvent arg0) {
		}

		public void mouseClicked(MouseEvent arg0) {
		}

		public void mouseEntered(MouseEvent arg0) {
		}

		public void mouseExited(MouseEvent arg0) {
		}
	}

	/** USEFUL METHODS */

	public void resetZoom() {
		if (currentFractal == 1) {
			x_min = -2.15;
			x_max = 0.6;
			y_min = -1.3;
			y_max = 1.3;
		}
		if (currentFractal == 2) {
			x_min = -1.7;
			x_max = 1.7;
			y_min = -1.0;
			y_max = 1.0;
		}
		if (currentFractal == 3) {
			x_min = -1.8;
			x_max = -1.7;
			y_min = -0.08;
			y_max = 0.025;
		}
		if (currentFractal == 4) {
			x_min = -1;
			x_max = 1;
			y_min = -1.3;
			y_max = 1.3;
		}
	}

	public void updateEscapeDistance() {
		/**
		 * The user inputs a value that is stored as a string. The string is
		 * then converted into a double using parseDouble. If the user enters
		 * anything that isn't a number, a NumberFormatException is thrown, and
		 * the user will be asked to input a new value. If the user enters a
		 * number less than or equal to zero, it is rejected, and the user is
		 * asked to input once more. This will continue until the user enters a
		 * valid number, or clicks the "Cancel" button.
		 */
		String ed = JOptionPane.showInputDialog(frame, "Default: 2.0\n\nEnter number greater than 0:");
		try {
			if (Double.parseDouble(ed) > 0) {
				escapeDistance = Double.parseDouble(ed);
			} else {
				updateEscapeDistance();
			}

		} catch (NumberFormatException InvalidFormat) {
			updateEscapeDistance();
		} catch (NullPointerException EmptyString) {
			/** Assume user hit the "Cancel" button and do nothing. */
			return;
		}
		updateFractal();
	}

	public void updateEscapeTime() {
		/**
		 * The user inputs a value that is stored as a string. The string is
		 * then converted into an int using parseInt. If the user enters
		 * anything that isn't a number, a NumberFormatException is thrown,
		 * which is caught by the method and restarts the method to prompt the
		 * user for a valid input. If the user enters a number less than or
		 * equal to zero, it is rejected, and the user is asked to input once
		 * more. This will continue until the user either enters a valid number,
		 * or clicks the "Cancel" button.
		 */
		String et = JOptionPane.showInputDialog(frame, "Default: 255\n\nEnter integer greater than 0:");
		if (et == null) {
			/** Assume user hit the "Cancel" button and do nothing. */
			return;
		}
		try {
			if (Integer.parseInt(et) >= 1) {
				escapeTime = Integer.parseInt(et);
			} else {
				updateEscapeTime();
			}
		} catch (NumberFormatException InvalidFormat) {
			updateEscapeTime();
		}
		updateFractal();
	}

	public void updateThreads() {
		/**
		 * The user inputs a value that is stored as a string. The string is
		 * then converted into an int using parseInt. If the user enters
		 * anything that isn't a number, a NumberFormatException is thrown,
		 * which is caught by the method and restarts the method to prompt the
		 * user for a valid input. If the user enters a number less than 0 or
		 * greater than 128, it is rejected, and the user is asked to input once
		 * more. This will continue until the user either enters a valid number,
		 * or clicks the "Cancel" button.
		 */
		String th = JOptionPane.showInputDialog(frame, "Default: 1\n\nEnter integer between 0 and 128:");
		if (th == null) {
			/** Assume user hit the "Cancel" button and do nothing. */
			return;
		}
		try {
			if (Integer.parseInt(th) >= 1 && Integer.parseInt(th) <= 128) {
				threads = Integer.parseInt(th);
			} else {
				updateThreads();
			}
		} catch (NumberFormatException InvalidFormat) {
			updateThreads();
		}
		updateFractal();
	}

	public void updateFractal() {
		frame.remove(label);
		panel.removeAll();
		panel.add(fp);
		frame.add(panel);
		frame.pack();
		if (currentFractal == 1) {
			Mandelbrot mb = new Mandelbrot();
			fp.updateImage(mb.createMandel(x_min, x_max, y_min, y_max, escapeDistance, escapeTime, threads));
		}
		if (currentFractal == 2) {
			Julia j = new Julia();
			fp.updateImage(j.createJulia(x_min, x_max, y_min, y_max, escapeDistance, escapeTime, threads));
		}
		if (currentFractal == 3) {
			BurningShip bs = new BurningShip();
			fp.updateImage(bs.createBS(x_min, x_max, y_min, y_max, escapeDistance, escapeTime, threads));
		}
		if (currentFractal == 4) {
			Multibrot mu = new Multibrot();
			fp.updateImage(mu.createMulti(x_min, x_max, y_min, y_max, escapeDistance, escapeTime, threads));
		}
	}

	public void updateColor(int color) {
		if (color == 1) {
			fp.setIndexColorModel(ColorModelFactory.createRainbowColorModel(255));
		}
		if (color == 2) {
			fp.setIndexColorModel(ColorModelFactory.createBlueColorModel(255));
		}
		if (color == 3) {
			fp.setIndexColorModel(ColorModelFactory.createGreenColorModel(255));
		}
		if (color == 4) {
			fp.setIndexColorModel(ColorModelFactory.createGrayColorModel(255));
		}
	}
}