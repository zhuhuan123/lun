package test;

import java.util.Random;

public class Te {
	
	static void tes(){
		for(int i=0;i<10;i++){
			System.out.print(Math.round(Math.random())+"  ");
		}
		double d = 410-336;
		double d1 = 4380000-1730000;
		System.out.println();
		System.out.println(Math.sqrt(d*d+d1*d1));
		System.out.println(Math.sqrt(74*74));
	}
	
	public static void main(String[] args) {
		tesss("浙江中国小商品城集团股份有限公司");
		
		
//		int ran1 = 
	}

	static void tesss(String one){
		boolean ha = false;
		for(int i=0;i<one.length();i++){
			char ch = one.charAt(i);
			System.out.println(ch);
			int indd = one.indexOf(ch);
			if(indd==-1){
				ha= true;
				break;
			}
		}
	}
	
	static void calmat(){
		int a[][] = new int[][]{{1,2,3},{4,5,6}};//2*3
		int b[][] = new int[][]{{1,2},{3,4},{5,6}};//3*2
		int al = a.length;
		int bl = b[0].length;
		int c[][] = new int[a.length+1][b[0].length+1];
		int j=0;
		for(int i=0;i<2;i++){
			int su =0;
			for(j=0;j<2;j++){
				for(int k=0;k<a[0].length;k++){
					su+=a[i][k]*b[k][j];
				}
				c[i][j] = su;
			}
		}
		
		for(int t=0;t<c.length;t++){
			for( j=0;j<c[0].length;j++){
				System.out.print(c[t][j]+" ");
			}
			System.out.println();
		}
	}
	
	static void mates(){
		int C= 10;
		int Num =5;
		int [][]ro = new int[Num+1][C+1];
		int []weig = new int[]{2,2,6,5,4};
		int []val = new int[]{6,3,5,4,6};
		for(int i=1;i<Num+1;i++){
			for(int j=1;j<C+1;j++){
				if(j<weig[i-1]){
					ro[i][j] = ro[i-1][j];
				}else{
					ro[i][j]  = Math.max(ro[i-1][j],ro[i-1][j-weig[i-1]]+val[i-1] );
				}
			}
		}
		
		for(int i=0;i<Num+1;i++){
			for(int j=1;j<C+1;j++){
				System.out.print(ro[i][j]+"  ");
			}
			System.out.println();
		}
	}
	
	private static void calR() {
		int r = 1;
		Random random = new Random();
		int coun =0;
		int coall = 100000;
		for(int i=0;i<coall;i++){
			double on  = Math.random();
			double on1  = Math.random();
			
			if(on*on+on1*on1<1){
				coun++;
			}
		}
		
		double  rea = coun/((1.0)*coall);
		System.out.println(rea);
		double ress = 4*rea;
		System.out.println(ress);
	}
}
