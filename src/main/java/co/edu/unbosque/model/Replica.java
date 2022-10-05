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

	
}
