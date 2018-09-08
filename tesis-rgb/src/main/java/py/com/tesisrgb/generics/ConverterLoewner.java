package py.com.tesisrgb.generics;

public class ConverterLoewner {

	private static float[] rgbToHcl(int[] rgb){


		double h1 = 0d, s1 = 0d, l1 = 0d;
		double r =  (double)(rgb[0])/255D, g = (double)(rgb[1]) /255D, b = (double)(rgb[2])/255D;
//		System.out.println("rgb: "+ r +" "+ g + " " + b);
		
		
		// rgb a hsl
		double maxRGB = Math.max(b, Math.max(r, g));
		double minRGB = Math.min(b, Math.min(r, g));
//		System.out.println("max: " + maxRGB +", min: " + minRGB +" r:" + r + " g: " + g+ " b:" + b);
		
		if (maxRGB ==minRGB){
			h1 = 0;
//			System.out.println("1");
		}else if(maxRGB ==r){
//			h1 = (60 * ((g-b)/(maxRGB-minRGB)) + 360 )%360;
			h1 = ((g-b)/(maxRGB-minRGB))/6;
//			System.out.println("2");
		}else if(maxRGB ==g){
//			h1= 60 * ((b-r)/(maxRGB-minRGB)) + 120;
			h1= (2+(b-r)/(maxRGB-minRGB))/6;
//			System.out.println("3");
		}else if(maxRGB==b){
//			h1= 60 * ((r-g)/(maxRGB-minRGB)) + 240;;
			h1= (4+(r-g)/(maxRGB-minRGB))/6;
//			System.out.println("4");
		}
		
		l1 = (maxRGB + minRGB) /2;
		
//		System.out.println("hsl: " +h1 + " " + s1 + " " + l1);
		if (h1<0) h1=h1+1; // if hue is negative, add 1 to get it within 0 and 1
//		System.out.println("hsl: " +h1 + " " + s1 + " " + l1);
		float[] hcl = new float[3];
		hcl[0] = (float)h1; 
//		hcl[0] = (float)h1 / 360; ;
		hcl[1] = (float)(Math.max(r,Math.max(g, b)) - Math.min(r,Math.min(g, b)));
		hcl[2] = (float)(2 * l1  - 1);;
		return hcl;
		 
	}

	private static float[] hclToXyz(float[] hcl){
		float[] xyz = new float[3];
//		xyz[0] = (float)(hcl[1] * Math.cos(2 * Math.PI  * Math.toRadians(hcl[0])));
		xyz[0] = (float)(hcl[1] * Math.cos(2 * Math.PI  * hcl[0]));
//		System.out.println("C: "+hcl[1]+ ", H: " + hcl[0]);
//		xyz[1] = (float)(hcl[1] * Math.sin(2 * Math.PI * Math.toRadians(hcl[0])));;
		xyz[1] = (float)(hcl[1] * Math.sin(2 * Math.PI * hcl[0]));;
		xyz[2] = hcl[2];
		return xyz;
	}
	
}
