package expressions;

import gui.Calculator;
import gui.Controller;
import numbers.Number;
import numbers.RealNumber;
import exceptions.*;

import java.io.*; 
import java.util.*; 

public class EvaluateExpression {

	//atribut dari sebuah objek EvaluateExpression
	protected String token;
	protected static final Map<String, Integer> pred  = new HashMap<String, Integer>() {{
    put("+", 1);
    put("-", 1);
    put("*", 2);
    put("/", 2);
    put("^", 3);
    put("(", 0);
    put(")", 0);
    put("sin", 4);
    put("cos", 4);
    put("tan", 4);
    put("sqrt", 4);
    put("neg", 5);
	}};

	//Konstruktor 
	public EvaluateExpression(String exp)
	{
		this.token = exp;
	}

	//Method untuk menyelesaikan ekspresi unary
	public RealNumber solveUnary(Expression num1, String op) throws BaseException {
		RealNumber def = new RealNumber();
		if(op.equals("sin"))
		{
			Expression tempVal = new SineExpression(num1);
			return tempVal.solve();
		}
		else if(op.equals("cos"))
		{
			Expression tempVal = new CosineExpression(num1);
			return tempVal.solve();
		}
		else if(op.equals("tan"))
		{
			Expression tempVal = new TangentExpression(num1);
			return tempVal.solve();
		}
		else if(op.equals("sqrt"))
		{
			Expression tempVal = new SquareRootExpression(num1);
			return tempVal.solve();
		}
		else if(op.equals("neg"))
		{
			Expression tempVal = new NegativeExpression(num1);
			return tempVal.solve();
		}
		return def;
	}

	//Method untuk menyelesaikan ekspresi binary
	public RealNumber solveBinary(Expression num1, Expression num2, String op) throws BaseException {
		RealNumber def = new RealNumber();
		if(op.equals("+"))
		{
			Expression tempVal = new AddExpression(num1, num2);
			return tempVal.solve();
		} 
		else if(op.equals("-"))
		{
			Expression tempVal = new SubtractExpression(num1, num2);
			return tempVal.solve();
		} 
		else if(op.equals("*"))
		{
			Expression tempVal = new MultiplicationExpression(num1, num2);
			return tempVal.solve();
		} 
		else if(op.equals("/"))
		{
			try {
				Expression tempVal = new DivideExpression(num1, num2);
				return tempVal.solve();
			} catch (BaseException exp){
				throw exp;
			}
		}
		else if(op.equals("^"))
		{
			Expression tempVal = new PowerExpression(num1, num2);
			return tempVal.solve();
		}
		return def;
	}

	//Method untuk melakukan parsing pada ekspresi yang diketikkan oleh pengguna kalkulator
	public RealNumber parse() throws BaseException {

		//terdapat dua stack untuk melakukan proses perhitungan serta penyimpanan operand dan operator
		Stack<RealNumber> operand = new Stack<RealNumber>();
		Stack<String> operator = new Stack<String>();
		int i;
		boolean afterNumber = false;

		for(i = 0; i < token.length(); i++)
		{

			// jika bertemu tanda kurung buka
			if(token.charAt(i) == '(') 
			{
				if(afterNumber)
				{
					operator.push("*");
					afterNumber = false;
				}
				operator.push("(");
			}

			//encountering real numbers
			else if(Character.isDigit(token.charAt(i)))
			{
				String tempNumber = "";
				while((i < token.length()) && (Character.isDigit(token.charAt(i)) || token.charAt(i)=='.')){
					tempNumber += String.valueOf(token.charAt(i));
					i++;
				}
				i--;
				RealNumber number = new RealNumber(tempNumber);
				operand.push(number);
				afterNumber = true;
				if(!operator.empty()){
					if(operator.peek().equals("neg")){
						RealNumber val1 = operand.peek();
						Expression num1 = new TerminalExpression(val1);
						operand.pop();
						String op = operator.peek();
						operator.pop();
						operand.push(solveUnary(num1, op));
					}
				}
			}

			// jika bertemu karakter A, maka akan dianggap sebagai Ans, yakni jawaban dari ekspresi ebelumnya
			else if(token.charAt(i) == 'A' ){
				RealNumber number = new RealNumber(Controller.ans.toString());
				operand.push(number);
				i += 2;
				afterNumber = true;
				if(!operator.empty()) {
					if (operator.peek().equals("neg")) {
						RealNumber val1 = operand.peek();
						Expression num1 = new TerminalExpression(val1);
						operand.pop();
						String op = operator.peek();
						operator.pop();
						operand.push(solveUnary(num1, op));
					}
				}
			}

			//encountering euler constant as operand, not exponent component
			else if(token.charAt(i) == 'e'){
				RealNumber number = new RealNumber(Math.exp(1));
				operand.push(number);
				afterNumber = true;
				if(!operator.empty()){
					if(operator.peek().equals("neg")){
						RealNumber val1 = operand.peek();
						Expression num1 = new TerminalExpression(val1);
						operand.pop();
						String op = operator.peek();
						operator.pop();
						operand.push(solveUnary(num1, op));
					}
				}
			}

			//encountering pi constant
			else if(token.charAt(i) == 'p'){
				RealNumber number = new RealNumber(Math.PI);
				i += 1;
				operand.push(number);
				afterNumber = true;
				if(!operator.empty()){
					if(operator.peek().equals("neg")){
						RealNumber val1 = operand.peek();
						Expression num1 = new TerminalExpression(val1);
						operand.pop();
						String op = operator.peek();
						operator.pop();
						operand.push(solveUnary(num1, op));
					}
				}
			}

			// jika bertemu tanda kurung tutup, proses seluruh operator dan operand yang ada di antara kedua kurung
			else if(token.charAt(i) == ')')
			{
				afterNumber = false;
				boolean found = false;
				if(!operator.empty()){
					while(!operator.empty()){
						if(!operator.peek().equals("("))
						{
							RealNumber val2 = operand.peek();
							Expression num2 = new TerminalExpression(val2);
							operand.pop();

							RealNumber val1 = operand.peek();
							Expression num1 = new TerminalExpression(val1);
							operand.pop();

							String op = operator.peek();
							operator.pop();

							operand.push(solveBinary(num1, num2, op));
						}
						else 
						{	
							found = true;
							break;
						}
					}

					if(operator.empty() && !found){
						BaseException exp = new ImbalancedParanthesesException();
						throw exp;
					}
					
					operator.pop();
					if(!operator.empty()){
						String op = operator.peek();
						if(op.equals("sin"))
						{
							RealNumber val1 = operand.peek();
							Expression num1 = new TerminalExpression(val1);
							operand.pop();
							operator.pop();
							operand.push(solveUnary(num1, op));
						}
						else if(op.equals("cos"))
						{
							RealNumber val1 = operand.peek();
							Expression num1 = new TerminalExpression(val1);
							operand.pop();
							operator.pop();
							operand.push(solveUnary(num1, op));
						}
						else if(op.equals("tan"))
						{
							RealNumber val1 = operand.peek();
							Expression num1 = new TerminalExpression(val1);
							operand.pop();
							operator.pop();
							operand.push(solveUnary(num1, op));
						}
						else if(op.equals("sqrt"))
						{
							RealNumber val1 = operand.peek();
							Expression num1 = new TerminalExpression(val1);
							operand.pop();
							operator.pop();
							operand.push(solveUnary(num1, op));
						}
						else if(op.equals("neg"))
						{
							RealNumber val1 = operand.peek();
							Expression num1 = new TerminalExpression(val1);
							operand.pop();
							operator.pop();
							operand.push(solveUnary(num1, op));
						}
					}
					afterNumber = true;
				} 
				else {
					BaseException exp = new ImbalancedParanthesesException();
					throw exp;
				}
			}

			//untuk handling tanda negatif
			else if(token.charAt(i)=='-' && (i==0 || (i>0 && token.charAt(i-1)=='(')))
			{
				operator.push("neg");
			}

			//handling tanda positif di awal ekspresi
			else if(token.charAt(i)=='+' && i==0)
			{
				continue;
			}
			
			//jika bertemu operator-operator lain
			else 
			{

				String cur = String.valueOf(token.charAt(i));
				if(token.charAt(i)=='-'){
					if(!afterNumber){
						cur = "neg";
					}
				}
				if(token.charAt(i)=='t'){
					cur = "tan";
					i += 2;
				}
				else if(token.charAt(i)=='c'){
					cur = "cos";
					i += 2;
				}
				else if(token.charAt(i)=='s' && token.charAt(i+1)=='i'){
					cur = "sin";
					i += 2;
				}
				else if(token.charAt(i)=='s' && token.charAt(i+1)=='q'){
					cur = "sqrt";
					i += 3;
				}
				afterNumber = false;
				if(!operator.empty()){
					while((pred.get(operator.peek()) >= pred.get(cur))){
						if(operand.empty()){
							break;
						}
						RealNumber val2 = operand.peek();
						Expression num2 = new TerminalExpression(val2);
						operand.pop();

						RealNumber val1 = operand.peek();
						Expression num1 = new TerminalExpression(val1);
						operand.pop();

						String op = operator.peek();
						operator.pop();

						operand.push(solveBinary(num1, num2, op));
						if(operator.empty()) break;
					}
				}
				operator.push(cur);
			}

		}

		while(!operator.empty())
		{
			String op = operator.peek();
			operator.pop();

			if(op.equals("(")){
				BaseException exp = new ImbalancedParanthesesException();
				throw exp;
			}

			if(operand.size()<2){
				throw new InvalidSyntaxException();
			}

//			try{
//				RealNumber val2 = operand.peek();
//				Expression num2 = new TerminalExpression(val2);
//				operand.pop();
//
//				RealNumber val1 = operand.peek();
//				Expression num1 = new TerminalExpression(val1);
//				operand.pop();
//
//				operand.push(solveBinary(num1, num2, op));
//			} catch (Exception e){
//				throw new InvalidSyntaxException();
//			}

			RealNumber val2 = operand.peek();
			Expression num2 = new TerminalExpression(val2);
			operand.pop();

			RealNumber val1 = operand.peek();
			Expression num1 = new TerminalExpression(val1);
			operand.pop();

			operand.push(solveBinary(num1, num2, op));
		}
		
		return operand.peek();
	}
}