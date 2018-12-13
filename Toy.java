package toybattery;
class Battery implements Cloneable{
	int batnum;
	Battery(int p){
		batnum=p;
	}
	public String toString(){//can not reduce the visibility of the inherited method from Object
		String s=new String(Integer.toString(batnum));
		return s;
	}
	/***
	 * Battery中的克隆函数。
	 */
	public Object clone(){
		Object o=null;
		try{
			o=super.clone();
		}catch(CloneNotSupportedException e){
			System.err.println("Battery can not be cloned!");
		}
//		((Battery)o).batnum=0;
		return o;
	}
}
public class Toy implements Cloneable{
	Battery[] mybat=new Battery[10];
	/***
	 * 将mybat中所存的所有int转为String并用逗号分隔。
	 */
	public String toString(){
		int i=0;
		String s=new String("");
		while((i<10)&&mybat[i]!=null){
			s+=mybat[i];
			if(i<9){s+=",";}
			i++;
		}
		return s;
	}
	/***
	 * Toy的克隆函数
	 */
	public Object clone(){
		Toy o=null;
		try{
			o=(Toy)super.clone();
		}catch(CloneNotSupportedException e){
			System.err.println("Toy can not be cloned!");
		}
		int i=0;
		o.mybat=(Battery[])mybat.clone();
		while((i<10)&&(mybat[i]!=null)){
			o.mybat[i]=(Battery)mybat[i].clone();
			i++;
		}
		return o;
	}
	public static void main(String argc[]){
		Toy mytoy=new Toy();
		for(int i=0;i<10;i++){
			mytoy.mybat[i]=new Battery(i+1);
		}
		System.out.println(mytoy);
		Toy mytoy2=(Toy)mytoy.clone();
		System.out.println("orignal:"+mytoy);
		System.out.println("clone:"+mytoy2);
		mytoy.mybat[4].batnum=55;
		mytoy2.mybat[3].batnum=44;
		System.out.println("after changed orignal:"+mytoy);
		System.out.println("after changed clone:"+mytoy2);
	}
}
