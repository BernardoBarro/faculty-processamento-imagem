import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Espelhamento extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton img, juntar;
	private File path;
	private JLabel label1, labelNormal, labelEspelhado;
	private BufferedImage imgA = null, imgNormal = null, imgEspelhado = null;

	public Espelhamento() {

		img = new JButton("Carregar imagem A");
		img.setBounds(196, 570, 150, 40);
		add(img);

		juntar = new JButton("Espelhar");
		juntar.setBounds(196, 620, 150, 40);
		add(juntar);

		label1 = new JLabel();
		label1.setBounds(15, 20, 512, 532);
		add(label1);

		labelNormal = new JLabel();
		labelNormal.setBounds(547, 20, 512, 532);
		add(labelNormal);

		labelEspelhado = new JLabel();
		labelEspelhado.setBounds(1059, 20, 512, 532);
		add(labelEspelhado);

		img.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int result = fileChooser.showSaveDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					path = new File(selectedFile.getAbsolutePath());
					try {
						imgA = ImageIO.read(path);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					label1.setIcon(new ImageIcon(imgA));
				} else if (result == JFileChooser.CANCEL_OPTION) {
					System.out.println("No Data");
				}
			}
		});

		juntar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					imgNormal = ImageIO.read(path);
					imgEspelhado = ImageIO.read(path);
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				int width = imgEspelhado.getWidth();
				int height = imgEspelhado.getHeight();
				Graphics2D g = imgEspelhado.createGraphics();
				g.drawImage(imgEspelhado, 0, 0, width, height, width, 0, 0, height, null);

				labelNormal.setIcon(new ImageIcon(imgNormal));
				labelEspelhado.setIcon(new ImageIcon(imgEspelhado));
			}
		});

		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Espelhamento();
	}
}
