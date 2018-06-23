import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.List;

import edu.wlu.cs.levy.CG.KDTree;

class p  implements Comparable<p>{
	private double [] values ;
	public	p(double [] values){
		this.values = values;
	}
	public p(){
		
	}
	
	@Override
	public int compareTo(p o) {
		
		return 0;
	}
	
}

public class MKD {
	public static void main(String[] args) {
//		KDTree<String> kdTree = new KDTree<>(3);
//		System.out.println("开方后的值为  "+Math.sqrt(4));
		
//		ArrayList<Integer> arrayList = new ArrayList<>();
//		arrayList.add(12);
//		arrayList.add(23);
//		arrayList.add(33);
//		Collections.sort(arrayList);
//		for(Integer o:arrayList){
//			System.out.print(o+" ");
//		}
//		System.out.println();
		
//		AtomicInteger atomicInteger = new AtomicInteger(1);
//		int res = atomicInteger.incrementAndGet();  //2
//		res = atomicInteger.incrementAndGet();  //3
//		System.out.println("得到的第二个结果值为  "+res);
//		ArrayList<Integer> arrayList = new ArrayList<>();
//		arrayList.add(1);
//		arrayList.add(2);
//		arrayList.add(3);
//		List<Integer> list = new ArrayList<>(arrayList);
//		
//		for(Integer io:list){
//			System.out.print(io+" ");
//		}
//		System.out.println();
		
		File fi = new File("d:\\datada");
		if(!fi.exists()){
			boolean bb = fi.mkdirs();
			System.out.println(bb);
		}
		
		System.out.println("创建成功与否 "+fi.exists());
	}
}
