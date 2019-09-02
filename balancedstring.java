import khyati.ds.*;
import java.util.Scanner;
class Balanced{
	public static boolean ismatchingpair(char ch,char ch1){
		if(ch=='{' && ch1=='}')
			return true;
		else if(ch=='[' && ch1==']')
			return true;
		else if(ch=='(' && ch1==')')
			return true;
		else
			return false;
	}
	
	
	public static void main(String args[]){
		ArrayStack<Character> as=new ArrayStack<>();
		Scanner sc=new Scanner(System.in);
		String str=sc.nextLine();
		String result="Balanced";
		for(int i=0;i<str.length();i++){
			if(str.charAt(i)=='{' || str.charAt(i)=='(' || str.charAt(i)=='[')
				as.push(str.charAt(i));
			else if(str.charAt(i)==']' || str.charAt(i)=='}' || str.charAt(i)==')')
			{
				if(as.isEmpty()){
					result="Unbalanced";
					break;
				}
				
				if(!ismatchingpair(as.pop(),str.charAt(i)))
				{
					result="Unbalanced";
					break;
				}
			}
		}
		System.out.println(result);
	}
}