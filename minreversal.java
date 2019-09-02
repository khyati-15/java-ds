import khyati.ds.*;
import java.util.Scanner;
class Reversal{
	
	
	public static void main(String args[]){
		
		Scanner sc=new Scanner(System.in);
		String str=sc.nextLine();
		int len=str.length();
		if(len%2==1){
			System.out.println("Cant be made balanced");
		}
		else{
			ArrayStack<Character> as=new ArrayStack<>();
			for(int i=0;i<len;i++){
				if(str.charAt(i)=='[')
					as.push(str.charAt(i));
				else if(str.charAt(i)==']'){
					if(!as.isEmpty() && as.top()=='[')
						as.pop();
					else
						as.push(str.charAt(i));
				}
			}
		
		
		int stacksize=as.size(),n=0;
		while(!as.isEmpty() && as.top()=='['){
			as.pop();
			n++;
		}
		int result=stacksize/2 + n%2;
		System.out.println(result);
		}
	}
}