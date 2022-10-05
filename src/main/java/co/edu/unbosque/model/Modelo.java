package co.edu.unbosque.model;
import java.security.KeyStore.Entry;
import java.util.*;

@SuppressWarnings("unused")
public class Modelo {
	
	private final int cantidad = 362880;
	private final int maxLimit = 3;
	
	public Modelo() {}
	
	
	public List <int [][]> addMatrix() {
		List <int[][]> list = new ArrayList<>();
		for(int i = 0; i < cantidad; i++) 
			list.add(matrix());
		return list;
	}
	
	public List <int [][]> partition(List <int [][]> listMatrix){
		return listMatrix.stream()
					     .filter(this :: isPair)
					     .filter(this :: isCentral)
					     .filter(this :: isMiddleCount)
					     .toList();
	}
	
	public List <int [][]> cleanMap(Map <int [][], int [][]> map){
		List <int [][]> list = new ArrayList <> ();
		for(Map.Entry<int[][], int[][]> entry : map.entrySet()) {
			 if(countMatch(entry.getKey()) > countMatch(entry.getValue()))
				 list.add(entry.getKey());
			 else
				 list.add(entry.getValue());
		}
		return list;
	}
	
	public List <int [][]> distinct(List <int [][]> listMatrix){
		
		return null;
	}
	
	public void nose(List <int [][]> list) {
		
	}
	
	public Map <int [][], int [][]> match(List <int [][]> listMatrix) {
		Map <int [][], int [][]> mapping = new HashMap <> ();
		boolean busy [] = new boolean [listMatrix.size()];
		int index = 0;
		while(index < busy.length) {
			if(busy[index]) {
				index ++;
			}else {
				int random;
				if(lack(busy) == 1) {
					random = index;
				}else {
					random = random(busy.length -1);
					busy[index] = true;
					while(busy[random]) 
						random = random(busy.length -1);
				}
				busy[random] = true;
				mapping.put(listMatrix.get(index), listMatrix.get(random));
			}
		}
		return mapping;
	}
	
	public boolean isMiddleCount(int matrix [][]) {
		return countMatch(matrix) > 3;
	}
	
	public boolean isPair(int matrix [][]) {
		return matrix[0][0] % 2 == 0 && matrix[0][2] % 2 == 0 && matrix[2][0] % 2 == 0 && matrix[2][2] % 2 == 0;
	}
	
	public boolean isCentral(int matrix [][]) {
		return matrix[1][1] == 5;
	}
		
	public int lack(boolean busy []) {
		int count = 0;
		for(int i = 0; i < busy.length; i++)
			count = !busy[i]? count +1 : count;
		return count;
	}
			
	public int countMatch(int matrix [][]) {
		int count = 0;
		for(int i = 0; i < matrix.length; i++) {
			int row = 0;
			int column = 0;
			for(int j = 0; j < matrix.length; j++) {
				row += matrix[i][j];
				column += matrix[j][i];
			}
			count = row == 15? count +1 : count;
			count = column == 15? count +1 : count;
		}
		count = matrix[0][0] + matrix[1][1] + matrix[2][2] == 15? count +1 : count;
		count = matrix[0][2] + matrix[1][1] + matrix[2][0] == 15? count +1 : count;
		return count;
	}
	
	private int [][] matrix(){
		int matrix [][] = new int [3][3];
		int nums [] = new int [9];
		int index = 0;
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix.length; j++) {
				int random = random(9);
				while(!isFind(nums, 0,random)) 
					random = random(9);
				matrix[i][j] = random;
				nums[index ++] = random;
			}
		}
		return matrix;
	}
	
	public boolean isFind(int nums [], int index, int number) {
		if(index < nums.length) {
			if(nums[index] == number)
				return false;
			else
				return isFind(nums, index +1, number);
		}
		return true;
	}
	
	private int random(int max) {
		return (int) Math.round(Math.random()*max);
	}
	
	public double randomDouble() {
		return Math.random();
	}
	
	public String toMatrix(int matrix [][], int i, int j) {
		if(i < matrix.length) {
			if(j < matrix[i].length) 
				return "[ " + matrix[i][j] + " ]" + toMatrix(matrix, i, j +1);
			else 
				return "\n" + toMatrix(matrix, i +1, j = 0);
		}
		return "";
	}
	
	public static void main(String[] args) {
		Modelo model = new Modelo();
		List <int [][]> list = model.cleanMap(model.match(model.partition(model.addMatrix())));
		list.forEach(x -> System.out.println(model.toMatrix(x, 0, 0)));
		System.out.println(list.size());
	}
}