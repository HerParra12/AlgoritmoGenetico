package co.edu.unbosque.model;
import java.util.*;

public class Modelo {
	
	private final int cantidad = 362880;
	private final int maxLimit = 3;
	private Map <int [][], Integer> map;
	
	public Modelo() {
		map = new HashMap <> ();
	}
	
	public List <int [][]> addMatrix() {
		List <int[][]> list = new ArrayList<>();
		for(int i = 0; i < cantidad; i++) 
			list.add(matrix());
		return list;
	}
	
	public List <int [][]> partitionMatrixIsPais(List <int [][]> listMatrix){
		return listMatrix.stream()
					     .filter(this :: isPair)
					     .toList();
	}
	
	public List <int [][]> partitionQuantity(List <int [][]> listMatrix){
		return listMatrix.stream()
				  	     .filter(this :: isMiddleCount)
				  	     .toList();
	}
	
	public boolean isMiddleCount(int matrix [][]) {
		return countMatch(matrix) > 3;
	}
	
	public boolean isPair(int matrix [][]) {
		return matrix[0][0] % 2 == 0 && matrix[0][2] % 2 == 0 && matrix[2][0] % 2 == 0 && matrix[2][2] % 2 == 0;
	}
	
	public Map <int [][], int [][]> emparejar(List <int [][]> list) {
		Map <int [][], int [][]> mapping = new HashMap <> ();
		boolean busy [] = new boolean [list.size()];
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
				mapping.put(list.get(index), list.get(random));
				mapping.put(list.get(random), list.get(index));
			}
		}
		return mapping;
	}
	
	public int lack(boolean busy []) {
		int count = 0;
		for(int i = 0; i < busy.length; i++)
			count = !busy[i]? count +1 : count;
		return count;
	}
	
	
	
	public void toList(List <int [][]> list) {
		for(int i = 0; i < list.size(); i++)
			System.out.println(toMatrix(list.get(i), 0, 0));
	}
	
	public int countMatch(int matrix [][]) {
		int count = matrix[0][0] + matrix[1][1] + matrix[2][2] == 15? 1 : 0;
		count = matrix[0][2] + matrix[1][1] + matrix[2][0] == 15? count +1 : count;
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
	
	public int [][] addAll(int matrix [][], int i, int j){
		if(i < matrix.length) {
			if(j < matrix.length) {
				matrix[i][j] = random(9);
				return addAll(matrix, i, j +1);
			}else {
				return addAll(matrix, i +1, j = 0);
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
		System.out.println("\n---------------------\n");
		System.out.println(model.emparejar(model.partitionQuantity(model.partitionMatrixIsPais(model.addMatrix()))).size() );
		
	}
}