package edu.neu.coe.info6205.symbolTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import edu.neu.coe.info6205.symbolTable.BSTSimple;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

public class Driver {
	//----------M A I N-------------------------------------------------------------------------------------------------------
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BSTSimple a = new BSTSimple();
		int i;

		ArrayList<String> key_string= new ArrayList<String>();//initial key
		ArrayList<String> key_string2= new ArrayList<String>();//add key
		ArrayList<String> key_string3= new ArrayList<String>();//delete key
		ArrayList<Integer> value_int= new ArrayList<Integer>();//initial value
		ArrayList<Integer> value_int2= new ArrayList<Integer>();//add value
		
		//exp start-------------------------------------------------------------------
		System.out.print("Please input N:");
		Scanner sc = new Scanner(System.in); 
        int N = sc.nextInt(); 
        int outlayer_i;
		//initial a BST (some size)
        
		for(outlayer_i=0;outlayer_i<11;outlayer_i++) {
			key_string.clear();
			key_string2.clear();
			key_string3.clear();
			value_int.clear();
			value_int2.clear();
		
		for(i=0;i<outlayer_i*50;i++) {//SIZE->outlayer_i*50
			String temp;
			int t;
			Random random = new Random();
			t = random.nextInt(5);//first random a length,1-5
			t++;
			temp = getRandomString(t);
			key_string.add(temp);
		}
		for(i=0;i<outlayer_i*50;i++) {//SIZE->outlayer_i*50
			int k;
			Random random = new Random();
			k = random.nextInt(10000);//random value
			value_int.add(k);
		}
		Map<Object,Object> map = new HashMap<Object, Object>();
		for(int yy=0;yy<outlayer_i*50;yy++) {//SIZE->outlayer_i*50
			map.put(key_string.get(yy), value_int.get(yy));
			}
		a.putAll(map);

		
        //N times insertion
			//prepare insertion
	        for(i=0;i<N;i++) {
				String temp;
				int t;
				Random random = new Random();
				t = random.nextInt(5);
				t++;
				temp = getRandomString(t);
				key_string2.add(temp);
			}
			for(i=0;i<N;i++) {
				int k;
				Random random = new Random();
				k = random.nextInt(10000);
				value_int2.add(k);
			}
			
			//N times deletion
			for(i=0;i<N;i++) {
				String temp;
				int t;
				Random random = new Random();
				t = random.nextInt(5);
				t++;
				temp = getRandomString(t);
				key_string3.add(temp);
			}
			for(int j=0;j<N;j++) {
				a.put(key_string2.get(j), value_int2.get(j));
				}
			for(int k=0;k<N;k++) {
				a.delete(key_string3.get(k));
				}
			System.out.println("Initial nodes in BST = "+outlayer_i*50+"   Final depth = " + a.showDepth());
			
        //exp end---------------------------------------------------------------------
		
	}
	}
	
	//random generate a string 
	public static String getRandomString(int length){
		String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random=new Random();
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<length;i++){
			int number=random.nextInt(62);
			sb.append(str.charAt(number));
		}
		return sb.toString();
	}


}
