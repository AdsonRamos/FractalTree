import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SettingsPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JSlider sliderAngle;
	private JSlider sliderSize;
	private JLabel angleLabel;
	private JLabel sizeLabel;
	private JCheckBox antialiasing;
	private JButton snapButton;
	private FractalPanel fractal;
	
	public SettingsPanel(FractalPanel fractal) {
		setSize(200, 800);
		
		this.fractal = fractal;
		
		setLayout(new FlowLayout(FlowLayout.CENTER, 100, 5));
		
		sliderAngle = new JSlider(JSlider.HORIZONTAL, 0, 628, 141);
		sliderAngle.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				fractal.setAngle((double) sliderAngle.getValue()/100);
			}
		});
		
		
		angleLabel = new JLabel("Ângulo: ");
		sliderSize = new JSlider(JSlider.HORIZONTAL, 0, 400, 200);
		sliderSize.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				fractal.setTreeLength(sliderSize.getValue());
			}
		});
		
		sizeLabel = new JLabel("Tamanho: ");
		snapButton = new JButton("Tirar foto");
		
		snapButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					fractal.takePicture();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		add(new SliderLabel(sliderAngle, angleLabel));
		add(new SliderLabel(sliderSize, sizeLabel));
		
		antialiasing = new JCheckBox("Antialiasing");
		antialiasing.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fractal.setAntiAliasing(antialiasing.isSelected());
			}
		});
		
		
		add(antialiasing);
		
		add(snapButton);
		
	}
		
	
	private class SliderLabel extends JPanel{
		private JLabel label;
		private JSlider slider;
		
		public SliderLabel(JSlider slider, JLabel label) {
			this.label = label;
			this.slider = slider;
			
			add(label);
			add(slider);
		}
	}
	
}

