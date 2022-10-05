package co.edu.unbosque.model;

import java.util.List;

public class Replica {

	
	public List <String [][]> convertToBinary(List <int[][]> list){
		return list.stream()
				   .map(matrix -> convertToBinaryMatrix(new String [3][3], matrix, 0, 0))
				   .toList();
	}
	
	private String [][] convertToBinaryMatrix(String matrix [][],int nums [][], int i, int j) {
		if(i < nums.length) {
			if(j < nums.length) {
				matrix[i][j] = bits(nums[i][j]);
				return convertToBinaryMatrix(matrix, nums, i, j +1);
			}else {
				return convertToBinaryMatrix(matrix, nums, i +1, j = 0);
			}
		}
		return matrix;
	}
	
	public String bits(int number) {
		StringBuilder builder = new StringBuilder(Integer.toBinaryString(number));
		while(builder.length() != 4)
			builder.insert(0, "0");
		return builder.toString();
	}
	
	public int [] matrixToArray(int matrix [][]) {
		int nums [] = new int [9];
		int index = 0;
		for(int i = 0; i < matrix.length; i++) 
			for(int j = 0; j < matrix[i].length; j++)
				nums[index ++] = matrix[i][j];
		return nums;
	}

	public int [][] matrix(){
		int matrix [][] = new int [3][3];
		int nums [] = new int [9];
		int index = 0;
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix.length; j++) {
				int random = (9);
				//while(!isFind(nums, 0,random)) 
					//random = random(9);
				matrix[i][j] = random;
				nums[index ++] = random;
			}
		}
		return matrix;
	}
	
	public int [][] addAll(int matrix [][], int i, int j){
		if(i < matrix.length) {
			if(j < matrix.length) {
				matrix[i][j] = 9;
				return addAll(matrix, i, j +1);
			}else {
				return addAll(matrix, i +1, j = 0);
			}
		}
		return matrix;
	}
}