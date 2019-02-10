import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Fractal extends JFrame {

	private JSlider slider;
	private JLabel label;
	private FractalPanel panel;
	private SettingsPanel settings;

	public Fractal() {
		super();
		panel = new FractalPanel(800, 600);
		settings = new SettingsPanel(panel);
		slider = new JSlider(JSlider.HORIZONTAL, 0, 628, 0);
		
		label = new JLabel();
		
		label.setText("Ângulo: ");
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				panel.setAngle((double) slider.getValue()/100);
			}
		});
		

		setLayout(new BorderLayout());
		setFocusable(true);
		requestFocus();
		setResizable(false);
		setSize(1200, 600);
		
		add(panel, BorderLayout.CENTER);
		
		
		add(settings, BorderLayout.SOUTH);
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Fractal");
		setVisible(true);
	}

	public static void main(String[] args) {
		new Fractal();
	}


}
