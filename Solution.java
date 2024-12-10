import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
	public static int calculate(String s) {
		int currentNum = 0;
		char sign = '+';
		int length = s.length();
		Deque<Integer> stack = new ArrayDeque<>();
		
		for (int i = 0; i < length; i++) {
			char currentChar = s.charAt(i);
			
			if (Character.isDigit(currentChar)) {
				currentNum = currentNum * 10 + (currentChar - '0');
			}
			
			if (!Character.isDigit(currentChar) && currentChar != ' ' || i == length - 1) {
				switch (sign) {
					case '+' -> stack.push(currentNum);
					case '-' -> stack.push(-currentNum);
					case '*' -> stack.push(stack.pop() * currentNum);
					case '/' -> {
						int top = stack.pop();
						stack.push(top / currentNum);
					}
				}
				
				sign = currentChar;
				currentNum = 0;
			}
		}
		int result = 0;
		while (!stack.isEmpty()) {
			result += stack.pop();
		}
		
		return result;
	}
	public static void main(String[] args) {
		System.out.println(calculate("3+2*2")); // Виведе 7
		System.out.println(calculate(" 3/2 ")); // Виведе 1
		System.out.println(calculate(" 3+5 / 2 ")); // Виведе 5
		System.out.println(calculate("8/ 4 +2*3 ")); // Виведе 8
	}
}