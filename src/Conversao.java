import java.util.Scanner;

public class Conversao {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Utilizar a separação das casas decimais com vírgula");
		System.out.println("1: Normalizar RGB");
		System.out.println("2: Converter RGB para HSV");
		System.out.println("3: Converter HSV para RGB");
		System.out.println("4: Converter RGB para CMYK");
		System.out.println("5: Converter CMYK para RGB");
		System.out.println("6: Converter RGB para tons de cinza");
		
		int index = input.nextInt();
		
		if(index==1) {
			System.out.println("Digite o RGB:");
			
			double R = input.nextDouble();
			double G = input.nextDouble();
			double B = input.nextDouble();
			
			double rgb = R+G+B;
			
			R /= rgb;
			G /= rgb;
			B /= rgb;
			
			System.out.println("Valor de r: " + R);
			System.out.println("Valor de g: " + G);
			System.out.println("Valor de b: " + B);
			System.out.println("Soma dos tres valores: " + (R + G + B));
			
		}else if(index==2) {
			
			System.out.println("Digite o RGB entre os numero 0,0 e 1,0:");
			
			double R = input.nextDouble();
			double G = input.nextDouble();
			double B = input.nextDouble();
			
			double maxValue = getMaxValue(R, G, B);
			double minValue = getMinValue(R, G, B);
			
			double H = 0, S, V = maxValue;
			
			if(maxValue==R) {
				if(G>=B) {
					H = 60 * ((G - B)/(maxValue - minValue)) + 0;
				}else if(G<B) {
					H = 60 * ((G - B)/(maxValue - minValue)) + 360;
				}
			}else if(maxValue==G) {
				H = 60 * ((B - R)/(maxValue - minValue)) + 120;
			}else if(maxValue==B){
				H = 60 * ((R - G)/(maxValue - minValue)) + 240;
			}
			
			S = (maxValue - minValue) / maxValue;
			
			System.out.println("H: " + H);
			System.out.println("S: " + S);
			System.out.println("v: " + V);
		
		}else if(index==3) {
			System.out.println("Digite o HSV:");
			
			double H = input.nextInt();
			double S = input.nextFloat();
			double V = input.nextFloat();
			
			double C = V * S;
			double X = C * (1 - Math.abs((H / 60) % 2 - 1));
			double m = V - C;
			
			double Rdecimal=0;
			double Gdecimal=0;
			double Bdecimal=0;
			
			if(H < 60 && H >= 0) {
				Rdecimal = C;
				Gdecimal = X;
				Bdecimal = 0;
			}else if(H < 120 && H >= 60) {
				Rdecimal = X;
				Gdecimal = C;
				Bdecimal = 0;
			}else if(H < 180 && H >= 120) {
				Rdecimal = 0;
				Gdecimal = C;
				Bdecimal = X;
			}else if(H < 240 && H >= 180) {
				Rdecimal = 0;
				Gdecimal = X;
				Bdecimal = C;
			}else if(H < 300 && H >= 240) {
				Rdecimal = X;
				Gdecimal = 0;
				Bdecimal = C;
			}else if(H < 360 && H >= 300) {
				Rdecimal = C;
				Gdecimal = 0;
				Bdecimal = X;
			}
			
			int R = (int) ((Rdecimal+m) * 255);
			int G = (int) ((Gdecimal+m) * 255);
			int B = (int) ((Bdecimal+m) * 255);
			
			System.out.println("R: " + R);
			System.out.println("G: " + G);
			System.out.println("B: " + B);
			
		}else if(index==4) {
			
			System.out.println("Digite o RGB:");
			
			int R = input.nextInt();
			int G = input.nextInt();
			int B = input.nextInt();
			
			double Rdecimal = R/255.0;
			double Gdecimal = G/255.0;
			double Bdecimal = B/255.0;
			
			double K = 1 - getMaxValue(Rdecimal, Gdecimal, Bdecimal);
			double C = (1-Rdecimal-K)/(1-K);
			double M = (1-Gdecimal-K)/(1-K);
			double Y = (1-Bdecimal-K)/(1-K);
			
			System.out.println("K: " + K);
			System.out.println("C: " + C);
			System.out.println("M: " + M);
			System.out.println("Y: " + Y);
			
		}else if(index==5) {
			
			System.out.println("Digite o CMYK:");
			
			double C = input.nextDouble();
			double M = input.nextDouble();
			double Y = input.nextDouble();
			double K = input.nextDouble();
			
			int R = (int) Math.round(255 * (1 - C) * (1 - K));
			int G = (int) Math.round(255 * (1 - M) * (1 - K));
			int B = (int) Math.round(255 * (1 - Y) * (1 - K));
			
			System.out.println("R: " + R);
			System.out.println("G: " + G);
			System.out.println("B: " + B);
		
		}else if(index==6) {

			System.out.println("Digite o RGB:");
			
			int R = input.nextInt();
			int G = input.nextInt();
			int B = input.nextInt();
			
			System.out.println("Para tons de cinza: " + (R+G+B)/3);
		}
		
		input.close();
		
	}

	private static double getMinValue(double R, double G, double B) {
		double minRG = Math.min(R, G);
		double minValue = Math.min(minRG, B);
		return minValue;
	}

	private static double getMaxValue(double R, double G, double B) {
		double maxRG = Math.max(R, G);
		double maxValue = Math.max(maxRG, B);
		return maxValue;
	}
}
