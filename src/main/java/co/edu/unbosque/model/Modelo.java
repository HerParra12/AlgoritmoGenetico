package co.edu.unbosque.model;
import java.util.*;

/**
 * 
 * @author Hernan Alvarado
 * @version 2022
 * @since 1.0
 * 
 */
public class Modelo {
	
	private final int CANTIDAD = 362880;
	private final int MAX_BITS = 35;
	
	public Modelo() {}
	
	
	public List <int [][]> addMatrix() {
		List <int[][]> list = new ArrayList<>();
		for(int i = 0; i < CANTIDAD; i++) 
			list.add(matrix());
		return list;
	}
	
	public List <int [][]> partition(List <int [][]> listMatrix){
		return listMatrix.stream()
					     .filter(this :: isCentral)
					     .filter(this :: isMiddleCount)
					     .toList();
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
				mapping.put(listMatrix.get(random), listMatrix.get(index));
			}
		}
		return mapping;
	}
	
	public List <int [][]> bestMatch(Map <int [][], int [][]> mapping){
		return mapping.entrySet()
					  .stream()
					  .map(this :: bestCase)
					  .toList();
	}
	
	public List <String> crossing(List <int [][]> list) {
		return match(list).entrySet()
				   		  .stream()
				   		  .map(this :: convertMatrixToString)
				   		  .map(this :: crossingBit)
				   		  .map(this :: joinBits)
				   		  .toList();
	}
	
	public List <int [][]> valueCrossing(List <String> listString) {
		return listString.stream()
				         .map(this :: splitBits)
				         .map(this :: buildMatrix)
				         .map(this :: valueInt)
				         .toList();
	}
	
	public List <int [][]> bestCandidates(List <int [][]> listCrossing){
		return listCrossing.stream()
						   .filter(this :: isMediumCount)
						   .sorted((x,y) -> countMatch(y) - countMatch(x))
						   .limit(10)
						   .toList();
	}
	
	public boolean isMiddleCount(int matrix [][]) {
		return countMatch(matrix) >= 4;
	}
	
	public boolean isCentral(int matrix [][]) {
		return matrix[1][1] == 5;
	}
	
	public int [][] bestCase(Map.Entry <int [][], int [][]> entry){
		return countMatch(entry.getKey()) > countMatch(entry.getValue()) ? entry.getKey() : entry.getValue();
	}
		
	private Map.Entry <String, String> convertMatrixToString(Map.Entry <int [][], int [][]> entry){
		return Map.entry(binary(new StringBuilder(), entry.getKey(), 0, 0), binary(new StringBuilder(), entry.getValue(), 0, 0));
	}
	
	public Map.Entry <Map.Entry <String, String>, Integer> crossingBit(Map.Entry <String, String> entry){
		return Map.entry(entry, random(MAX_BITS));
	}
	
	public String joinBits(Map.Entry<Map.Entry<String, String>, Integer> entry) {
		return entry.getKey().getKey().substring(0, entry.getValue()).concat(entry.getKey().getValue().substring(entry.getValue()));
	}
	
	public String [] splitBits(String string) {
		return string.split("(?<=\\G.{4})");
	}
	
	public String [][] buildMatrix(String [] splits){
		String matrix [][] = new String [3][3];
		int index = 0;
		for(int i = 0; i < matrix.length; i++) 
			for(int j = 0; j < matrix.length; j++) 
				matrix[i][j] = splits[index ++];
		return matrix;
	}
	
	public int [][] valueInt(String [][] matrixString){
		int matrix [][] = new int [3][3];
		for(int i = 0; i < matrix.length; i++) 
			for(int j = 0; j < matrix.length; j++) 
				matrix[i][j] = Integer.parseInt(matrixString[i][j], 2);
		return matrix;
	}
	
	public boolean isMediumCount(int matrix [][]) {
		return countMatch(matrix) >= 5;
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
				while(!isFind(nums, 0, random))
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
	
	private String binary(StringBuilder builder, int nums [][], int i, int j) {
		if(i < nums.length) {
			if(j < nums.length) {
				builder.append(bits(nums[i][j]));
				return binary(builder, nums, i, j +1);
			}else {
				return binary(builder, nums, i +1, j = 0);
			}
		}
		return builder.toString();
	}
		
	private String toMatrix(int matrix [][], int i, int j) {
		if(i < matrix.length) {
			if(j < matrix[i].length) 
				return "[ " + matrix[i][j] + " ]" + toMatrix(matrix, i, j +1);
			else 
				return "\n" + toMatrix(matrix, i +1, j = 0);
		}
		return "";
	}

	
	private String bits(int number) {
		StringBuilder builder = new StringBuilder(Integer.toBinaryString(number));
		while(builder.length() != 4)
			builder.insert(0, "0");
		return builder.toString();
	}
	
	private int random(int max) {
		return (int) Math.round(Math.random()*max);
	}
	
	public static void main(String[] args) {
		Modelo model = new Modelo();
		List <int [][]> list = model.partition(model.addMatrix());
		Map <int [][], int [][]> match = model.match(list);
		List <int [][]> bestMatch = model.bestMatch(match);
		List <String> crossing = model.crossing(bestMatch);
		List <int [][]> value = model.valueCrossing(crossing);
		List <int [][]> candidates = model.bestCandidates(value);
		candidates.forEach(x -> System.out.println(model.toMatrix(x, 0, 0)));
	}	
}