package co.edu.unbosque.model;

import java.util.ArrayList;
import java.util.Arrays;
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
	
	public int [] matrixToArrayTwo(int matrix [][]) {
		int nums [] = new int [9];
		return matrixToArray(matrix, nums, 0, 0, 0);
	}
	
	private  int [] matrixToArray(int matrix [][], int nums [], int index, int i, int j) {
		if(i < matrix.length) {
			if(j < matrix.length) {
				nums[index++] = matrix[i][j];
				matrixToArray(matrix, nums, index, i, j +1);
			}else {
				matrixToArray(matrix, nums, index, i +1, j = 0);
			}
		}
		return nums;
	}
	
	public List <int [][]> distinct(List <int [][]> listMatrix){
		List <int [][]> listDistinct = new ArrayList <> ();
		for(int i = 0; i < listMatrix.size(); i++) {
			int nums [] = matrixToArray(listMatrix.get(i));
			int index = 0;
			for(int j = i +1; j < listMatrix.size(); j++) 
				if(Arrays.equals(matrixToArray(listMatrix.get(j)), nums)) 
					index ++;
			if(index == 0)
				listDistinct.add(listMatrix.get(i));
		}
		return listDistinct;
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