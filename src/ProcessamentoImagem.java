import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ProcessamentoImagem extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton img1, img2, gray1, blackWhite1, gray2, blackWhite2, soma, subtracao, multiplicacao, 
		divisao, media, blending, and, or, xor, not1, not2, histograma, max, min, mediaFiltro, mediana, ordem,
		suavizacao, gaussiana;
	private JTextField multiText, divText, blendText, ordemText, gaussianaText;
	private File path, pathBackup;
	private JLabel label1, label2, labelFinal;
//	private JFreeChart red, green, blue, redEq, greenEq, blueEq;
	private BufferedImage imgA = null, imgB = null, imgFinal = null;

	public ProcessamentoImagem() {

		img1 = new JButton("Imagem A");
		img1.setBounds(115, 330, 90, 20);
		add(img1);

		img2 = new JButton("Imagem B");
		img2.setBounds(450, 330, 90, 20);
		add(img2);
		
		gray1 = new JButton("8bits");
		gray1.setBounds(45, 360, 70, 20);
		add(gray1);
		
		blackWhite1 = new JButton("1bit");
		blackWhite1.setBounds(125, 360, 70, 20);
		add(blackWhite1);
		
		not1 = new JButton("Not");
		not1.setBounds(205, 360, 70, 20);
		add(not1);
		
		gray2 = new JButton("8bits");
		gray2.setBounds(380, 360, 70, 20);
		add(gray2);
		
		blackWhite2 = new JButton("1bit");
		blackWhite2.setBounds(460, 360, 70, 20);
		add(blackWhite2);
		
		not2 = new JButton("Not");
		not2.setBounds(540, 360, 70, 20);
		add(not2);

		soma = new JButton("Soma");
		soma.setBounds(680, 30, 110, 25);
		add(soma);
		
		subtracao = new JButton("Subtração");
		subtracao.setBounds(680, 65, 110, 25);
		add(subtracao);
		
		multiplicacao = new JButton("Multiplicação");
		multiplicacao.setBounds(680, 100, 110, 25);
		add(multiplicacao);
		
		multiText = new JTextField(20);
		multiText.setBounds(800, 100, 70, 25);
		add(multiText);
		
		divisao = new JButton("Divisão");
		divisao.setBounds(680, 135, 110, 25);
		add(divisao);
		
		divText = new JTextField(20);
		divText.setBounds(800, 135, 70, 25);
		add(divText);
		
		media = new JButton("Media");
		media.setBounds(680, 170, 110, 25);
		add(media);
		
		blending = new JButton("Blending");
		blending.setBounds(680, 205, 110, 25);
		add(blending);
		
		blendText = new JTextField(20);
		blendText.setBounds(800, 205, 70, 25);
		add(blendText);
		
		and = new JButton("And");
		and.setBounds(680, 270, 60, 25);
		add(and);
		
		or = new JButton("Or");
		or.setBounds(745, 270, 60, 25);
		add(or);
		
		xor = new JButton("Xor");
		xor.setBounds(810, 270, 60, 25);
		add(xor);
		
		histograma = new JButton("Histograma");
		histograma.setBounds(910, 30, 110, 25);
		add(histograma);
		
		max = new JButton("Max");
		max.setBounds(910, 65, 110, 25);
		add(max);
		
		min = new JButton("Min");
		min.setBounds(910, 100, 110, 25);
		add(min);
		
		mediaFiltro = new JButton("Media");
		mediaFiltro.setBounds(910, 135, 110, 25);
		add(mediaFiltro);
		
		mediana = new JButton("Mediana");
		mediana.setBounds(910, 170, 110, 25);
		add(mediana);
		
		ordem = new JButton("Ordem");
		ordem.setBounds(910, 205, 110, 25);
		add(ordem);
		
		ordemText = new JTextField(20);
		ordemText.setBounds(1030, 205, 30, 25);
		add(ordemText);
		
		suavizacao = new JButton("Conservativa");
		suavizacao.setBounds(910, 240, 110, 25);
		add(suavizacao);
		
		gaussiana = new JButton("Gaussiana");
		gaussiana.setBounds(910, 275, 110, 25);
		add(gaussiana);
		
		gaussianaText = new JTextField(20);
		gaussianaText.setBounds(1030, 275, 30, 25);
		add(gaussianaText);

		label1 = new JLabel();
		label1.setBounds(10, 10, 310, 320);
		add(label1);

		label2 = new JLabel();
		label2.setBounds(340, 10, 310, 320);
		add(label2);

		labelFinal = new JLabel();
		labelFinal.setBounds(1070, 10, 310, 320);
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
					if(imgA.getWidth()>310 || imgA.getHeight()>310) {						
						BufferedImage resized = resizeImage(310, 310, imgA);
						label1.setIcon(new ImageIcon(resized));
					}else {
						label1.setIcon(new ImageIcon(imgA));
					}
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
					if(imgB.getWidth()>310 || imgB.getHeight()>310) {						
						BufferedImage resized = resizeImage(310, 310, imgB);
						label2.setIcon(new ImageIcon(resized));
					}else {
						label2.setIcon(new ImageIcon(imgB));
					}
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
				if(imgFinal.getWidth()>310 || imgFinal.getHeight()>310) {						
					BufferedImage resized = resizeImage(310, 310, imgFinal);
					labelFinal.setIcon(new ImageIcon(resized));
				}else {
					labelFinal.setIcon(new ImageIcon(imgFinal));
				}
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
				if(imgFinal.getWidth()>310 || imgFinal.getHeight()>310) {						
					BufferedImage resized = resizeImage(310, 310, imgFinal);
					labelFinal.setIcon(new ImageIcon(resized));
				}else {
					labelFinal.setIcon(new ImageIcon(imgFinal));
				}
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
					if(imgFinal.getWidth()>310 || imgFinal.getHeight()>310) {						
						BufferedImage resized = resizeImage(310, 310, imgFinal);
						labelFinal.setIcon(new ImageIcon(resized));
					}else {
						labelFinal.setIcon(new ImageIcon(imgFinal));
					}
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
					if(imgFinal.getWidth()>310 || imgFinal.getHeight()>310) {						
						BufferedImage resized = resizeImage(310, 310, imgFinal);
						labelFinal.setIcon(new ImageIcon(resized));
					}else {
						labelFinal.setIcon(new ImageIcon(imgFinal));
					}
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
				if(imgFinal.getWidth()>310 || imgFinal.getHeight()>310) {						
					BufferedImage resized = resizeImage(310, 310, imgFinal);
					labelFinal.setIcon(new ImageIcon(resized));
				}else {
					labelFinal.setIcon(new ImageIcon(imgFinal));
				}
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
				if(imgFinal.getWidth()>310 || imgFinal.getHeight()>310) {						
					BufferedImage resized = resizeImage(310, 310, imgFinal);
					labelFinal.setIcon(new ImageIcon(resized));
				}else {
					labelFinal.setIcon(new ImageIcon(imgFinal));
				}
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
					if(imgFinal.getWidth()>310 || imgFinal.getHeight()>310) {						
						BufferedImage resized = resizeImage(310, 310, imgFinal);
						labelFinal.setIcon(new ImageIcon(resized));
					}else {
						labelFinal.setIcon(new ImageIcon(imgFinal));
					}
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
					if(imgFinal.getWidth()>310 || imgFinal.getHeight()>310) {						
						BufferedImage resized = resizeImage(310, 310, imgFinal);
						labelFinal.setIcon(new ImageIcon(resized));
					}else {
						labelFinal.setIcon(new ImageIcon(imgFinal));
					}
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
					if(imgFinal.getWidth()>310 || imgFinal.getHeight()>310) {						
						BufferedImage resized = resizeImage(310, 310, imgFinal);
						labelFinal.setIcon(new ImageIcon(resized));
					}else {
						labelFinal.setIcon(new ImageIcon(imgFinal));
					}
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
					if(imgFinal.getWidth()>310 || imgFinal.getHeight()>310) {						
						BufferedImage resized = resizeImage(310, 310, imgFinal);
						labelFinal.setIcon(new ImageIcon(resized));
					}else {
						labelFinal.setIcon(new ImageIcon(imgFinal));
					}
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
					if(imgFinal.getWidth()>310 || imgFinal.getHeight()>310) {						
						BufferedImage resized = resizeImage(310, 310, imgFinal);
						labelFinal.setIcon(new ImageIcon(resized));
					}else {
						labelFinal.setIcon(new ImageIcon(imgFinal));
					}
				} else {
					System.out.println("As imagens tem tamanho diferente");
				}
			}
		});

		histograma.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initiateImage();
				int widthA = imgA.getWidth();
				int heightA = imgA.getHeight();
				int[] histogramaRed = new int[256];
				int[] histogramaGreen = new int[256];
				int[] histogramaBlue = new int[256];
		        for (int y = 0; y < imgA.getHeight(); y++) {
		            for (int x = 0; x < imgA.getWidth(); x++) {
		                Color color = new Color(imgA.getRGB(x, y));
		                int red = color.getRed();
		                int green = color.getGreen();
		                int blue = color.getBlue();
		                histogramaRed[red] += 1;
		                histogramaGreen[green] += 1;
		                histogramaBlue[blue] += 1;
		            }
		        }
				int[] CFDRed = new int[256];
				int[] CFDGreen = new int[256];
				int[] CFDBlue = new int[256];
				CFDRed[0] = histogramaRed[0];
				CFDGreen[0] = histogramaGreen[0];
				CFDBlue[0] = histogramaBlue[0];
				for(int i=1;i<256;i++) {
					CFDRed[i] = CFDRed[i-1] + histogramaRed[i];
					CFDGreen[i] = CFDGreen[i-1] + histogramaGreen[i];
					CFDBlue[i] = CFDBlue[i-1] + histogramaBlue[i];
				}
				System.out.println("Histograma matriz Red");
				for(int i=0;i<256;i++) {
					System.out.print(histogramaRed[i] + ", ");
				}
				System.out.println();
				System.out.println("Histograma matriz Green");
				for(int i=0;i<256;i++) {
					System.out.print(histogramaGreen[i] + ", ");
				}
				System.out.println();
				System.out.println("Histograma matriz Blue");
				for(int i=0;i<256;i++) {
					System.out.print(histogramaBlue[i] + ", ");
				}
				System.out.println();
				System.out.println();
				float menorValorRed = menorValor(histogramaRed);
				float menorValorGreen = menorValor(histogramaGreen);
				float menorValorBlue = menorValor(histogramaBlue);
				int[] histogramaRedEq = new int[256];
				int[] histogramaGreenEq = new int[256];
				int[] histogramaBlueEq = new int[256];
				int pixels = (widthA*heightA);
				for(int i = 0; i<256;i++) {
					histogramaRedEq[i] = Math.round(((CFDRed[i] - menorValorRed) / (pixels - menorValorRed)) * 255);
					histogramaGreenEq[i] = Math.round(((CFDGreen[i] - menorValorGreen) / (pixels - menorValorGreen)) * 255);
					histogramaBlueEq[i] = Math.round(((CFDBlue[i] - menorValorBlue) / (pixels - menorValorBlue)) * 255);
				}
//				criarGraficoHistograma(histogramaRed, histogramaGreen, histogramaBlue);
				for (int x = 0; x < widthA; x++) {
					for (int y = 0; y < heightA; y++) {
		                Color color = new Color(imgA.getRGB(x, y));
		                int red = color.getRed();
		                int green = color.getGreen();
		                int blue = color.getBlue();
		                int newRed = histogramaRedEq[red];
		                int newGreen = histogramaGreenEq[green];
		                int newBlue = histogramaBlueEq[blue];
		                Color newColor = new Color(newRed, newGreen, newBlue);
		                imgFinal.setRGB(x, y, newColor.getRGB());
					}
				}
				System.out.println("Histograma Equalizado matriz Red");
				for(int i=0;i<256;i++) {
					System.out.print(histogramaRedEq[i] + ", ");
				}
				System.out.println();
				System.out.println("Histograma Equalizado matriz Green");
				for(int i=0;i<256;i++) {
					System.out.print(histogramaGreenEq[i] + ", ");
				}
				System.out.println();
				System.out.println("Histograma Equalizado matriz Blue");
				for(int i=0;i<256;i++) {
					System.out.print(histogramaBlueEq[i] + ", ");
				}
				System.out.println();
				System.out.println();
				
				if(imgFinal.getWidth()>310 || imgFinal.getHeight()>310) {						
					BufferedImage resized = resizeImage(310, 310, imgFinal);
					labelFinal.setIcon(new ImageIcon(resized));
				}else {
					labelFinal.setIcon(new ImageIcon(imgFinal));
				}
			}
		});

		max.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initiateImage();
				int widthA = imgA.getWidth();
				int heightA = imgA.getHeight();
				int[] red = new int[9];
				int[] green = new int[9];
				int[] blue = new int[9];
				int maiorRed = 0;
				int maiorGreen = 0;
				int maiorBlue = 0;
				for (int x = 1; x < widthA-1; x++) {
					for (int y = 1; y < heightA-1; y++) {
						
						red = vetorFiltroRed(red, x, y);
						green = vetorFiltroGreen(green, x, y);
						blue = vetorFiltroBlue(blue, x, y);
						
						for(int i=0;i<9;i++) {
							if(red[i]>maiorRed) {
								maiorRed=red[i];
							}
							if(green[i]>maiorGreen) {
								maiorGreen=green[i];
							}
							if(blue[i]>maiorBlue) {
								maiorBlue=blue[i];
							}
						}
						
						int p = (maiorRed << 16) | (maiorGreen << 8) | maiorBlue;
						
						imgFinal.setRGB(x, y, p);
						maiorRed=0;
						maiorGreen=0;
						maiorBlue=0;
					}
				}
				if(imgFinal.getWidth()>310 || imgFinal.getHeight()>310) {						
					BufferedImage resized = resizeImage(310, 310, imgFinal);
					labelFinal.setIcon(new ImageIcon(resized));
				}else {
					labelFinal.setIcon(new ImageIcon(imgFinal));
				}
			}
		});

		min.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initiateImage();
				int widthA = imgA.getWidth();
				int heightA = imgA.getHeight();
				int[] red = new int[9];
				int[] green = new int[9];
				int[] blue = new int[9];
				int menorRed = 255;
				int menorGreen = 255;
				int menorBlue = 255;
				for (int x = 1; x < widthA-1; x++) {
					for (int y = 1; y < heightA-1; y++) {
						
						red = vetorFiltroRed(red, x, y);
						green = vetorFiltroGreen(green, x, y);
						blue = vetorFiltroBlue(blue, x, y);
						
						for(int i=0;i<9;i++) {
							if(red[i]<menorRed) {
								menorRed=red[i];
							}
							if(green[i]<menorGreen) {
								menorGreen=green[i];
							}
							if(blue[i]<menorBlue) {
								menorBlue=blue[i];
							}
						}
						
						int p = (menorRed << 16) | (menorGreen << 8) | menorBlue;
						
						imgFinal.setRGB(x, y, p);
						menorRed=255;
						menorGreen=255;
						menorBlue=255;
					}
				}
				if(imgFinal.getWidth()>310 || imgFinal.getHeight()>310) {						
					BufferedImage resized = resizeImage(310, 310, imgFinal);
					labelFinal.setIcon(new ImageIcon(resized));
				}else {
					labelFinal.setIcon(new ImageIcon(imgFinal));
				}
			}
		});

		mediaFiltro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initiateImage();
				int widthA = imgA.getWidth();
				int heightA = imgA.getHeight();
				int[] red = new int[9];
				int[] green = new int[9];
				int[] blue = new int[9];
				int somaRed = 0;
				int somaGreen = 0;
				int somaBlue = 0;
				for (int x = 1; x < widthA-1; x++) {
					for (int y = 1; y < heightA-1; y++) {
						
						red = vetorFiltroRed(red, x, y);
						green = vetorFiltroGreen(green, x, y);
						blue = vetorFiltroBlue(blue, x, y);
						
						for(int i=0;i<9;i++) {
							somaRed+=red[i];
							somaGreen+=green[i];
							somaBlue+=blue[i];
						}
						
						int p = ((somaRed/9) << 16) | ((somaGreen/9) << 8) | (somaBlue/9);
						
						imgFinal.setRGB(x, y, p);
						somaRed=0;
						somaGreen=0;
						somaBlue=0;
					}
				}
				if(imgFinal.getWidth()>310 || imgFinal.getHeight()>310) {						
					BufferedImage resized = resizeImage(310, 310, imgFinal);
					labelFinal.setIcon(new ImageIcon(resized));
				}else {
					labelFinal.setIcon(new ImageIcon(imgFinal));
				}
			}
		});

		mediana.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initiateImage();
				int widthA = imgA.getWidth();
				int heightA = imgA.getHeight();
				int[] red = new int[9];
				int[] green = new int[9];
				int[] blue = new int[9];
				for (int x = 1; x < widthA-1; x++) {
					for (int y = 1; y < heightA-1; y++) {
						
						red = vetorFiltroRed(red, x, y);
						green = vetorFiltroGreen(green, x, y);
						blue = vetorFiltroBlue(blue, x, y);
						
						for(int i=0;i<9;i++) {
							Arrays.sort(red);
							Arrays.sort(green);
							Arrays.sort(blue);
						}
						
						int p = (red[4] << 16) | (green[4] << 8) | blue[4];
						
						imgFinal.setRGB(x, y, p);
					}
				}
				if(imgFinal.getWidth()>310 || imgFinal.getHeight()>310) {						
					BufferedImage resized = resizeImage(310, 310, imgFinal);
					labelFinal.setIcon(new ImageIcon(resized));
				}else {
					labelFinal.setIcon(new ImageIcon(imgFinal));
				}
			}
		});

		ordem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initiateImage();
				String txtPosition = ordemText.getText();
				int position = Integer.parseInt(txtPosition);
				int widthA = imgA.getWidth();
				int heightA = imgA.getHeight();
				int[] red = new int[9];
				int[] green = new int[9];
				int[] blue = new int[9];
				for (int x = 1; x < widthA-1; x++) {
					for (int y = 1; y < heightA-1; y++) {
						
						red = vetorFiltroRed(red, x, y);
						green = vetorFiltroGreen(green, x, y);
						blue = vetorFiltroBlue(blue, x, y);
						
						for(int i=0;i<9;i++) {
							Arrays.sort(red);
							Arrays.sort(green);
							Arrays.sort(blue);
						}
						
						int p = (red[position] << 16) | (green[position] << 8) | blue[position];
						
						imgFinal.setRGB(x, y, p);
					}
				}
				if(imgFinal.getWidth()>310 || imgFinal.getHeight()>310) {						
					BufferedImage resized = resizeImage(310, 310, imgFinal);
					labelFinal.setIcon(new ImageIcon(resized));
				}else {
					labelFinal.setIcon(new ImageIcon(imgFinal));
				}
			}
		});

		suavizacao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initiateImage();
				int widthA = imgA.getWidth();
				int heightA = imgA.getHeight();
				int[] red = new int[9];
				int[] green = new int[9];
				int[] blue = new int[9];
				int maiorRed = 0;
				int maiorGreen = 0;
				int maiorBlue = 0;
				int menorRed = 255;
				int menorGreen = 255;
				int menorBlue = 255;
				for (int x = 1; x < widthA-1; x++) {
					for (int y = 1; y < heightA-1; y++) {
						
						red = vetorFiltroRed(red, x, y);
						green = vetorFiltroGreen(green, x, y);
						blue = vetorFiltroBlue(blue, x, y);
						
						int pRed = red[0];
						int pGreen = green[0];
						int pBlue = blue[0];
						
						for(int i=1;i<9;i++) {
							if(red[i]>maiorRed) {
								maiorRed=red[i];
							}
							if(green[i]>maiorGreen) {
								maiorGreen=green[i];
							}
							if(blue[i]>maiorBlue) {
								maiorBlue=blue[i];
							}
							if(red[i]<menorRed) {
								menorRed=red[i];
							}
							if(green[i]<menorGreen) {
								menorGreen=green[i];
							}
							if(blue[i]<menorBlue) {
								menorBlue=blue[i];
							}
						}
						if(pRed > maiorRed) {
							pRed = maiorRed;
						}else if(pRed < menorRed) {
							pRed = menorRed;
						}
						if(pGreen > maiorGreen) {
							pGreen = maiorGreen;
						}else if(pGreen < menorGreen) {
							pGreen = menorGreen;
						}
						if(pBlue > maiorBlue) {
							pBlue = maiorBlue;
						}else if(pBlue < menorBlue) {
							pBlue = menorBlue;
						}
						
						int p = (pRed << 16) | (pGreen << 8) | pBlue;
						
						imgFinal.setRGB(x, y, p);
						maiorRed=0;
						maiorGreen=0;
						maiorBlue=0;
						menorRed=255;
						menorGreen=255;
						menorBlue=255;
					}
				}
				if(imgFinal.getWidth()>310 || imgFinal.getHeight()>310) {						
					BufferedImage resized = resizeImage(310, 310, imgFinal);
					labelFinal.setIcon(new ImageIcon(resized));
				}else {
					labelFinal.setIcon(new ImageIcon(imgFinal));
				}
			}
		});

		gaussiana.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initiateImage();
				String txtNumber = gaussianaText.getText();
				txtNumber = txtNumber.replaceAll(",", ".");
				double number = Double.parseDouble(txtNumber);
				int widthA = imgA.getWidth();
				int heightA = imgA.getHeight();
				int[][] red = new int[3][3];
				int[][] green = new int[3][3];
				int[][] blue = new int[3][3];
				float sumRed = 0;
				float sumGreen = 0;
				float sumBlue = 0;
				float div = 0;
				double[][] mostrar = new double[3][3];
				for (int x = 1; x < widthA-1; x++) {
					for (int y = 1; y < heightA-1; y++) {

						red = matrizFiltroRed(red, x, y);
						green = matrizFiltroGreen(green, x, y);
						blue = matrizFiltroBlue(blue, x, y);

						for(int i=-1; i<=1;i++) {
							for(int j = -1;j <= 1;j++) {
								double val = ((1/(2*3.14*(Math.pow(number,2))))*Math.exp(-((Math.pow(i,2) + Math.pow(j,2))/(2*Math.pow(number,2)))));
								sumRed+=red[i+1][j+1] * val;
								sumGreen+=green[i+1][j+1] * val;
								sumBlue+=blue[i+1][j+1] * val;
								div+=val;
								mostrar[i+1][j+1] = val;
							}
						}
						int pixelRed = (int) Math.floor(sumRed/div);
						int pixelGreen = (int) Math.floor(sumGreen/div);
						int pixelBlue = (int) Math.floor(sumBlue/div);
						
						int p = (pixelRed << 16) | (pixelGreen << 8) | pixelBlue;
						
						imgFinal.setRGB(x, y, p);
						sumRed = 0;
						sumGreen = 0;
						sumBlue = 0;
						div = 0;
					}
				}
				for(int i=0;i<3;i++) {
					for(int j=0;j<3;j++) {
						System.out.print(mostrar[i][j] + ", ");
					}
					System.out.println();
					System.out.println();
				}
				if(imgFinal.getWidth()>310 || imgFinal.getHeight()>310) {						
					BufferedImage resized = resizeImage(310, 310, imgFinal);
					labelFinal.setIcon(new ImageIcon(resized));
				}else {
					labelFinal.setIcon(new ImageIcon(imgFinal));
				}
			}
		});

		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setSize(2000,1500);        
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
		if(imgFinal.getWidth()>310 || imgFinal.getHeight()>310) {						
			BufferedImage resized = resizeImage(310, 310, imgFinal);
			labelFinal.setIcon(new ImageIcon(resized));
		}else {
			labelFinal.setIcon(new ImageIcon(imgFinal));
		}
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
		if(imgFinal.getWidth()>310 || imgFinal.getHeight()>310) {						
			BufferedImage resized = resizeImage(310, 310, imgFinal);
			labelFinal.setIcon(new ImageIcon(resized));
		}else {
			labelFinal.setIcon(new ImageIcon(imgFinal));
		}
	}
	
	private BufferedImage resizeImage(int wight, int height, BufferedImage imagem) {
		BufferedImage resized = new BufferedImage(wight, height, imagem.getType());
		Graphics2D g2 = resized.createGraphics();
		g2.drawImage(imagem, 0, 0, wight, height, null);
		g2.dispose();
		return resized;
	}

	private int[] vetorFiltroRed(int[] red, int x, int y) {
		red[0] = (imgA.getRGB(x, y) >> 16) & 0xff;
		red[1] = (imgA.getRGB(x-1, y) >> 16) & 0xff;
		red[2] = (imgA.getRGB(x-1, y+1) >> 16) & 0xff;
		red[3] = (imgA.getRGB(x, y-1) >> 16) & 0xff;
		red[4] = (imgA.getRGB(x-1, y-1) >> 16) & 0xff;
		red[5] = (imgA.getRGB(x, y+1) >> 16) & 0xff;
		red[6] = (imgA.getRGB(x+1, y-1) >> 16) & 0xff;
		red[7] = (imgA.getRGB(x+1, y) >> 16) & 0xff;
		red[8] = (imgA.getRGB(x+1, y+1) >> 16) & 0xff;
		return red;
	}

	private int[] vetorFiltroGreen(int[] green, int x, int y) {
		green[0] = (imgA.getRGB(x, y) >> 8) & 0xff;
		green[1] = (imgA.getRGB(x-1, y) >> 8) & 0xff;
		green[2] = (imgA.getRGB(x-1, y+1) >> 8) & 0xff;
		green[3] = (imgA.getRGB(x, y-1) >> 8) & 0xff;
		green[4] = (imgA.getRGB(x-1, y-1) >> 8) & 0xff;
		green[5] = (imgA.getRGB(x, y+1) >> 8) & 0xff;
		green[6] = (imgA.getRGB(x+1, y-1) >> 8) & 0xff;
		green[7] = (imgA.getRGB(x+1, y) >> 8) & 0xff;
		green[8] = (imgA.getRGB(x+1, y+1) >> 8) & 0xff;
		return green;
	}

	private int[] vetorFiltroBlue(int[] blue, int x, int y) {
		blue[0] = (imgA.getRGB(x, y)) & 0xff;
		blue[1] = (imgA.getRGB(x-1, y)) & 0xff;
		blue[2] = (imgA.getRGB(x-1, y+1)) & 0xff;
		blue[3] = (imgA.getRGB(x, y-1)) & 0xff;
		blue[4] = (imgA.getRGB(x-1, y-1)) & 0xff;
		blue[5] = (imgA.getRGB(x, y+1)) & 0xff;
		blue[6] = (imgA.getRGB(x+1, y-1)) & 0xff;
		blue[7] = (imgA.getRGB(x+1, y)) & 0xff;
		blue[8] = (imgA.getRGB(x+1, y+1)) & 0xff;
		return blue;
	}
	
	private int[][] matrizFiltroRed(int[][] red, int x, int y) {
		red[0][0] = (imgA.getRGB(x-1, y-1) >> 16) & 0xff;
		red[0][1] = (imgA.getRGB(x-1, y) >> 16) & 0xff;
		red[0][2] = (imgA.getRGB(x-1, y+1) >> 16) & 0xff;
		red[1][0] = (imgA.getRGB(x, y-1) >> 16) & 0xff;
		red[1][1] = (imgA.getRGB(x, y) >> 16) & 0xff;
		red[1][2] = (imgA.getRGB(x, y+1) >> 16) & 0xff;
		red[2][0] = (imgA.getRGB(x+1, y-1) >> 16) & 0xff;
		red[2][1] = (imgA.getRGB(x+1, y) >> 16) & 0xff;
		red[2][2] = (imgA.getRGB(x+1, y+1) >> 16) & 0xff;
		return red;
	}
	
	private int[][] matrizFiltroGreen(int[][] green, int x, int y) {
		green[0][0] = (imgA.getRGB(x-1, y-1) >> 8) & 0xff;
		green[0][1] = (imgA.getRGB(x-1, y) >> 8) & 0xff;
		green[0][2] = (imgA.getRGB(x-1, y+1) >> 8) & 0xff;
		green[1][0] = (imgA.getRGB(x, y-1) >> 8) & 0xff;
		green[1][1] = (imgA.getRGB(x, y) >> 8) & 0xff;
		green[1][2] = (imgA.getRGB(x, y+1) >> 8) & 0xff;
		green[2][0] = (imgA.getRGB(x+1, y-1) >> 8) & 0xff;
		green[2][1] = (imgA.getRGB(x+1, y) >> 8) & 0xff;
		green[2][2] = (imgA.getRGB(x+1, y+1) >> 8) & 0xff;
		return green;
	}
	
	private int[][] matrizFiltroBlue(int[][] blue, int x, int y) {
		blue[0][0] = (imgA.getRGB(x-1, y-1)) & 0xff;
		blue[0][1] = (imgA.getRGB(x-1, y)) & 0xff;
		blue[0][2] = (imgA.getRGB(x-1, y+1)) & 0xff;
		blue[1][0] = (imgA.getRGB(x, y-1)) & 0xff;
		blue[1][1] = (imgA.getRGB(x, y)) & 0xff;
		blue[1][2] = (imgA.getRGB(x, y+1)) & 0xff;
		blue[2][0] = (imgA.getRGB(x+1, y-1)) & 0xff;
		blue[2][1] = (imgA.getRGB(x+1, y)) & 0xff;
		blue[2][2] = (imgA.getRGB(x+1, y+1)) & 0xff;
		return blue;
	}
	
    private int menorValor(int[] histograma) {
        for(int i=0; i <histograma.length; i++) {
            if(histograma[i] != 0){
                return histograma[i];
            }
        }
        return 0;
    }
    
    //tentativa falha de adicionar um grafico ao histograma
    
//    private void criarGraficoHistograma(int[] histogramaRed, int[] histogramaGreen, int[] histogramaBlue) {
//        JFrame graficoFrame = new JFrame("Histograma");
//        graficoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        JPanel raiz = new JPanel();
//        raiz.setLayout(new BorderLayout());
//        Dimension tamanho = new Dimension(2000, 1500);
//        raiz.setPreferredSize(tamanho);
//        raiz.setMinimumSize(tamanho);
//        graficoFrame.add(raiz);
//        graficoFrame.pack();
//        Dimension sd = Toolkit.getDefaultToolkit().getScreenSize();
//        graficoFrame.setLocation((sd.width - graficoFrame.getWidth()) / 2, (sd.height - graficoFrame.getHeight()) / 2);
//
//        // Cria o painel aonde o gráfico será mostrado.
//        JPanel primeiroGrafico = new JPanel();
//        Dimension tamanhoArea1 = new Dimension(600, 300);
//        primeiroGrafico.setPreferredSize(tamanhoArea1);
//        primeiroGrafico.setMinimumSize(tamanhoArea1);
//        raiz.add(primeiroGrafico);
//
//        DefaultCategoryDataset dpd1 = new DefaultCategoryDataset();
//        for(int i=0;i<256;i++) {
//        	dpd1.setValue(histogramaRed[i], "", "");        	
////        	dpd1.setValue(1200, "", "");        	
////        	dpd1.setValue(1100, "", "");        	
//        }
//
//        JFreeChart grafico1 = ChartFactory.createBarChart("", "", "", dpd1, PlotOrientation.VERTICAL, true, true, true);
//
//        ChartPanel chartPanel1 = new ChartPanel(grafico1);
//        primeiroGrafico.add(chartPanel1);
//        primeiroGrafico.validate();
//
//        // Mostra a tela.
//        graficoFrame.setVisible(true);
//    }
}