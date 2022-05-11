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
import javax.swing.JTextField;

public class ProcessamentoImagem extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton img1;
	private JButton img2;
	private JButton gray1;
	private JButton blackWhite1;
	private JButton gray2;
	private JButton blackWhite2;
	private JButton soma;
	private JButton subtracao;
	private JButton multiplicacao;
	private JTextField multiText;
	private JButton divisao;
	private JTextField divText;
	private JButton media;
	private JButton blending;
	private JTextField blendText;
	private JButton and;
	private JButton or;
	private JButton xor;
	private JButton not1;
	private JButton not2;
	private File path;
	private File pathBackup;
	private JLabel label1, label2, labelFinal;
	private BufferedImage imgA = null, imgB = null, imgFinal = null;

	public ProcessamentoImagem() {

		img1 = new JButton("Carregar imagem A");
		img1.setBounds(196, 570, 150, 40);
		add(img1);

		img2 = new JButton("Carregar imagem B");
		img2.setBounds(728, 570, 150, 40);
		add(img2);
		
		gray1 = new JButton("8bits");
		gray1.setBounds(111, 630, 100, 40);
		add(gray1);
		
		blackWhite1 = new JButton("1bit");
		blackWhite1.setBounds(221, 630, 100, 40);
		add(blackWhite1);
		
		not1 = new JButton("Not");
		not1.setBounds(331, 630, 100, 40);
		add(not1);
		
		gray2 = new JButton("8bits");
		gray2.setBounds(643, 630, 100, 40);
		add(gray2);
		
		blackWhite2 = new JButton("1bit");
		blackWhite2.setBounds(753, 630, 100, 40);
		add(blackWhite2);
		
		not2 = new JButton("Not");
		not2.setBounds(863, 630, 100, 40);
		add(not2);

		soma = new JButton("Soma");
		soma.setBounds(1079, 50, 110, 25);
		add(soma);
		
		subtracao = new JButton("Subtração");
		subtracao.setBounds(1079, 85, 110, 25);
		add(subtracao);
		
		multiplicacao = new JButton("Multiplicação");
		multiplicacao.setBounds(1079, 120, 110, 25);
		add(multiplicacao);
		
		multiText = new JTextField(20);
		multiText.setBounds(1199, 120, 110, 25);
		add(multiText);
		
		divisao = new JButton("Divisão");
		divisao.setBounds(1079, 155, 110, 25);
		add(divisao);
		
		divText = new JTextField(20);
		divText.setBounds(1199, 155, 110, 25);
		add(divText);
		
		media = new JButton("Media");
		media.setBounds(1079, 190, 110, 25);
		add(media);
		
		blending = new JButton("Blending");
		blending.setBounds(1079, 225, 110, 25);
		add(blending);
		
		blendText = new JTextField(20);
		blendText.setBounds(1199, 225, 110, 25);
		add(blendText);
		
		and = new JButton("And");
		and.setBounds(1079, 410, 110, 25);
		add(and);
		
		or = new JButton("Or");
		or.setBounds(1079, 445, 110, 25);
		add(or);
		
		xor = new JButton("Xor");
		xor.setBounds(1079, 480, 110, 25);
		add(xor);

		label1 = new JLabel();
		label1.setBounds(15, 20, 512, 532);
		add(label1);

		label2 = new JLabel();
		label2.setBounds(547, 20, 512, 532);
		add(label2);

		labelFinal = new JLabel();
		labelFinal.setBounds(1359, 20, 512, 532);
		add(labelFinal);

		img1.addActionListener(new ActionListener() {
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

		img2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int result = fileChooser.showSaveDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					String path = selectedFile.getAbsolutePath();
					File f = new File(path);
					pathBackup = f;
					try {
						imgB = ImageIO.read(f);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					label2.setIcon(new ImageIcon(imgB));
				} else if (result == JFileChooser.CANCEL_OPTION) {
					System.out.println("No Data");
				}
			}
		});
		
		gray1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initiateImage();
				int width = imgA.getWidth();
				int height = imgA.getHeight();
				for (int y = 0; y < height; y++) {
					for (int x = 0; x < width; x++) {
						
						int p = imgA.getRGB(x, y);
						
						int r = (p >> 16) & 0xff;
						int g = (p >> 8) & 0xff; 
						int b = p & 0xff;
						
						int avg = (r + g + b) / 3;
						
						p = (avg << 16) | (avg << 8) | avg;
						
						imgFinal.setRGB(x, y, p);
					}
				}
				labelFinal.setIcon(new ImageIcon(imgFinal));
			}
		});
		
		gray2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initiateImage();
				int width = imgB.getWidth();
				int height = imgB.getHeight();
				for (int y = 0; y < height; y++) {
					for (int x = 0; x < width; x++) {
						
						int p = imgB.getRGB(x, y);
						
						int r = (p >> 16) & 0xff;
						int g = (p >> 8) & 0xff; 
						int b = p & 0xff;
						
						int avg = (r + g + b) / 3;
						
						p = (avg << 16) | (avg << 8) | avg;
						
						imgFinal.setRGB(x, y, p);
					}
				}
				labelFinal.setIcon(new ImageIcon(imgFinal));
			}
		});
		
		blackWhite1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initiateImage();
				image1bit(imgA);
			}
		});
		
		blackWhite2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initiateImage();
				image1bit(imgB);
			}
		});
		
		not1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initiateImage();
				notImage1bit(imgA);
			}
		});
		
		not2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initiateImage();
				notImage1bit(imgB);
			}
		});
		
		soma.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initiateImage();
				int widthA = imgA.getWidth();
				int heightA = imgA.getHeight();
				int widthB = imgB.getWidth();
				int heightB = imgB.getHeight();
				if (widthA == widthB && heightA == heightB) {
					for (int y = 0; y < widthA; y++) {
						for (int x = 0; x < heightA; x++) {
							int A = imgA.getRGB(x, y);
							int B = imgB.getRGB(x, y);
							
							int redA = (A >> 16) & 0xff;
							int greenA = (A >> 8) & 0xff;
							int blueA = A & 0xff;
							
							int redB = (B >> 16) & 0xff;
							int greenB = (B >> 8) & 0xff;
							int blueB = B & 0xff;
							
							int red, green, blue;
							
							if((redA + redB)>255) {
								red = 255;
							}else {
								red = redA + redB;
							}
							
							if((greenA + greenB)>255) {
								green = 255;
							}else {
								green = greenA + greenB;
							}
							
							if((blueA + blueB)>255) {
								blue = 255;
							}else {
								blue = blueA + blueB;
							}
							
							int p = (red << 16) | (green << 8) | blue;
							
							imgFinal.setRGB(x, y, p);
						}
					}
					labelFinal.setIcon(new ImageIcon(imgFinal));
				} else {
					System.out.println("As imagens tem tamanho diferente");
				}
			}
		});
		
		subtracao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initiateImage();
				int widthA = imgA.getWidth();
				int heightA = imgA.getHeight();
				int widthB = imgB.getWidth();
				int heightB = imgB.getHeight();
				if (widthA == widthB && heightA == heightB) {
					for (int y = 0; y < widthA; y++) {
						for (int x = 0; x < heightA; x++) {
							int A = imgA.getRGB(x, y);
							int B = imgB.getRGB(x, y);
							
							int redA = (A >> 16) & 0xff;
							int greenA = (A >> 8) & 0xff;
							int blueA = A & 0xff;
							
							int redB = (B >> 16) & 0xff;
							int greenB = (B >> 8) & 0xff;
							int blueB = B & 0xff;
							
							int red, green, blue;
							
							if((redA - redB)<0) {
								red = 0;
							}else {
								red = redA - redB;
							}
							
							if((greenA - greenB)<0) {
								green = 0;
							}else {
								green = greenA - greenB;
							}
							
							if((blueA - blueB)<0) {
								blue = 0;
							}else {
								blue = blueA - blueB;
							}
							
							int p = (red << 16) | (green << 8) | blue;
							
							imgFinal.setRGB(x, y, p);
						}
					}
					labelFinal.setIcon(new ImageIcon(imgFinal));
				} else {
					System.out.println("As imagens tem tamanho diferente");
				}
			}
		});
		
		multiplicacao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initiateImage();
				String txtNumber = multiText.getText();
				int number = Integer.parseInt(txtNumber);
				int widthA = imgA.getWidth();
				int heightA = imgA.getHeight();
				for (int y = 0; y < widthA; y++) {
					for (int x = 0; x < heightA; x++) {
						int A = imgA.getRGB(x, y);
						
						int redA = (A >> 16) & 0xff;
						int greenA = (A >> 8) & 0xff;
						int blueA = A & 0xff;
						
						int red, green, blue;
						
						if((redA * number)>255) {
							red = 255;
						}else {
							red = redA * number;
						}
						
						if((greenA * number)>255) {
							green = 255;
						}else {
							green = greenA * number;
						}
						
						if((blueA * number)>255) {
							blue = 255;
						}else {
							blue = blueA * number;
						}
						
						int p = (red << 16) | (green << 8) | blue;
						
						imgFinal.setRGB(x, y, p);
					}
				}
				labelFinal.setIcon(new ImageIcon(imgFinal));
			}
		});
		
		divisao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initiateImage();
				String txtNumber = divText.getText();
				int number = Integer.parseInt(txtNumber);
				int widthA = imgA.getWidth();
				int heightA = imgA.getHeight();
				for (int y = 0; y < widthA; y++) {
					for (int x = 0; x < heightA; x++) {
						int A = imgA.getRGB(x, y);
						
						int redA = (A >> 16) & 0xff;
						int greenA = (A >> 8) & 0xff;
						int blueA = A & 0xff;
						
						int red, green, blue;
						
						if((redA / number)<0) {
							red = 0;
						}else {
							red = redA / number;
						}
						
						if((greenA / number)<0) {
							green = 0;
						}else {
							green = greenA / number;
						}
						
						if((blueA / number)<0) {
							blue = 0;
						}else {
							blue = blueA / number;
						}
						
						int p = (red << 16) | (green << 8) | blue;
						
						imgFinal.setRGB(x, y, p);
					}
				}
				labelFinal.setIcon(new ImageIcon(imgFinal));
			}
		});

		media.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initiateImage();
				int widthA = imgA.getWidth();
				int heightA = imgA.getHeight();
				int widthB = imgB.getWidth();
				int heightB = imgB.getHeight();
				if (widthA == widthB && heightA == heightB) {
					for (int y = 0; y < widthA; y++) {
						for (int x = 0; x < heightA; x++) {
							int A = imgA.getRGB(x, y);
							int B = imgB.getRGB(x, y);

							int redA = (A >> 16) & 0xff;
							int greenA = (A >> 8) & 0xff;
							int blueA = A & 0xff;

							int redB = (B >> 16) & 0xff;
							int greenB = (B >> 8) & 0xff;
							int blueB = B & 0xff;

							int somaRed = (int) ((redA + redB) * 0.5);
							int somaGreen = (int) ((greenA + greenB) * 0.5);
							int somaBlue = (int) ((blueA + blueB) * 0.5);

							int p = (somaRed << 16) | (somaGreen << 8) | somaBlue;

							imgFinal.setRGB(x, y, p);
						}
					}
					labelFinal.setIcon(new ImageIcon(imgFinal));
				} else {
					System.out.println("As imagens tem tamanho diferente");
				}
			}
		});
		
		blending.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initiateImage();
				String txtNumber = blendText.getText();
				txtNumber = txtNumber.replaceAll(",", ".");
				double number = Double.parseDouble(txtNumber);
				int widthA = imgA.getWidth();
				int heightA = imgA.getHeight();
				int widthB = imgB.getWidth();
				int heightB = imgB.getHeight();
				if (widthA == widthB && heightA == heightB) {
					for (int y = 0; y < widthA; y++) {
						for (int x = 0; x < heightA; x++) {
							int A = imgA.getRGB(x, y);
							int B = imgB.getRGB(x, y);

							int redA = (A >> 16) & 0xff;
							int greenA = (A >> 8) & 0xff;
							int blueA = A & 0xff;

							int redB = (B >> 16) & 0xff;
							int greenB = (B >> 8) & 0xff;
							int blueB = B & 0xff;

							int red = (int) Math.round(number * redA + (1-number) * redB);
							int green = (int) Math.round(number * greenA + (1-number) * greenB);
							int blue = (int) Math.round(number * blueA + (1-number) * blueB);

							int p = (red << 16) | (green << 8) | blue;

							imgFinal.setRGB(x, y, p);
						}
					}
					labelFinal.setIcon(new ImageIcon(imgFinal));
				} else {
					System.out.println("As imagens tem tamanho diferente");
				}
			}
		});
		
		and.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initiateImage();
				int widthA = imgA.getWidth();
				int heightA = imgA.getHeight();
				int widthB = imgB.getWidth();
				int heightB = imgB.getHeight();
				if (widthA == widthB && heightA == heightB) {
					for (int y = 0; y < widthA; y++) {
						for (int x = 0; x < heightA; x++) {
							int A = imgA.getRGB(x, y);
							int B = imgB.getRGB(x, y);
							
							int redA = (A >> 16) & 0xff;
							int greenA = (A >> 8) & 0xff; 
							int blueA = A & 0xff;
							
							int redB = (B >> 16) & 0xff;
							int greenB = (B >> 8) & 0xff; 
							int blueB = B & 0xff;
							
							int avgA = (redA + greenA + blueA) / 3;
							int avgB = (redB + greenB + blueB) / 3;
							int p;
							
							if(avgA > 127 && avgB > 127) {
								p = (255 << 16) | (255 << 8) | 255;
							}else {
								p = (0 << 16) | (0 << 8) | 0;
							}
							imgFinal.setRGB(x, y, p);
						}
					}
					
					labelFinal.setIcon(new ImageIcon(imgFinal));
				} else {
					System.out.println("As imagens tem tamanho diferente");
				}
			}
		});
		
		or.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initiateImage();
				int widthA = imgA.getWidth();
				int heightA = imgA.getHeight();
				int widthB = imgB.getWidth();
				int heightB = imgB.getHeight();
				if (widthA == widthB && heightA == heightB) {
					for (int y = 0; y < widthA; y++) {
						for (int x = 0; x < heightA; x++) {
							int A = imgA.getRGB(x, y);
							int B = imgB.getRGB(x, y);
							
							int redA = (A >> 16) & 0xff;
							int greenA = (A >> 8) & 0xff; 
							int blueA = A & 0xff;
							
							int redB = (B >> 16) & 0xff;
							int greenB = (B >> 8) & 0xff; 
							int blueB = B & 0xff;
							
							int avgA = (redA + greenA + blueA) / 3;
							int avgB = (redB + greenB + blueB) / 3;
							int p;
							
							if(avgA < 128 && avgB < 128) {
								p = (0 << 16) | (0 << 8) | 0;
							}else {
								p = (255 << 16) | (255 << 8) | 255;
							}
							
							imgFinal.setRGB(x, y, p);
						}
					}
					
					labelFinal.setIcon(new ImageIcon(imgFinal));
				} else {
					System.out.println("As imagens tem tamanho diferente");
				}
			}
		});
		
		xor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initiateImage();
				int widthA = imgA.getWidth();
				int heightA = imgA.getHeight();
				int widthB = imgB.getWidth();
				int heightB = imgB.getHeight();
				if (widthA == widthB && heightA == heightB) {
					for (int y = 0; y < widthA; y++) {
						for (int x = 0; x < heightA; x++) {
							int A = imgA.getRGB(x, y);
							int B = imgB.getRGB(x, y);
							
							int redA = (A >> 16) & 0xff;
							int greenA = (A >> 8) & 0xff; 
							int blueA = A & 0xff;
							
							int redB = (B >> 16) & 0xff;
							int greenB = (B >> 8) & 0xff; 
							int blueB = B & 0xff;
							
							int avgA = (redA + greenA + blueA) / 3;
							int avgB = (redB + greenB + blueB) / 3;
							int p;
							
							if((avgA < 128 && avgB < 128) || (avgA > 127 && avgB > 127)) {
								p = (0 << 16) | (0 << 8) | 0;
							}else {
								p = (255 << 16) | (255 << 8) | 255;
							}
							
							imgFinal.setRGB(x, y, p);
						}
					}
					
					labelFinal.setIcon(new ImageIcon(imgFinal));
				} else {
					System.out.println("As imagens tem tamanho diferente");
				}
			}
		});

		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}

	public static void main(String[] args) {
		new ProcessamentoImagem();
	}

	private void initiateImage() {
		try {
			if(path == null) {
				imgFinal = ImageIO.read(pathBackup);
			}else {
				imgFinal = ImageIO.read(path);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private void image1bit(BufferedImage img) {
		int width = img.getWidth();
		int height = img.getHeight();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				
				int p = img.getRGB(x, y);
				
				int r = (p >> 16) & 0xff;
				int g = (p >> 8) & 0xff; 
				int b = p & 0xff;
				
				int avg = (r + g + b) / 3;
				
				if(avg > 127) {
					p = (255 << 16) | (255 << 8) | 255;
				}else {
					p = (0 << 16) | (0 << 8) | 0;
				}
				
				imgFinal.setRGB(x, y, p);
			}
		}
		labelFinal.setIcon(new ImageIcon(imgFinal));
	}
	
	private void notImage1bit(BufferedImage img) {
		int width = img.getWidth();
		int height = img.getHeight();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				
				int p = img.getRGB(x, y);
				
				int r = (p >> 16) & 0xff;
				int g = (p >> 8) & 0xff; 
				int b = p & 0xff;
				
				int avg = (r + g + b) / 3;
				
				if(avg > 127) {
					p = (0 << 16) | (0 << 8) | 0;
				}else {
					p = (255 << 16) | (255 << 8) | 255;
				}
				
				imgFinal.setRGB(x, y, p);
			}
		}
		labelFinal.setIcon(new ImageIcon(imgFinal));
	}
}