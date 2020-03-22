package expressions;

import gui.Calculator;
import gui.Controller;
import numbers.Number;
import numbers.RealNumber;
import exceptions.*;

import java.io.*; 
import java.util.*; 

public class EvaluateExpression {

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

	public EvaluateExpression(String exp)
	{
		this.token = exp;
	}

	public RealNumber solveUnary(Expression num1, String op)
	{
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

	public RealNumber solveBinary(Expression num1, Expression num2, String op)
	{
		RealNumber def = new RealNumber();
		if(op.equals("+"))
		{
			Expression tempVal = new AddExpression(num1, num2); //ganti jadi AddExpression
			return tempVal.solve();
		} 
		else if(op.equals("-"))
		{
			Expression tempVal = new SubtractExpression(num1, num2); //ganti jadi SubtractExpression
			return tempVal.solve();
		} 
		else if(op.equals("*"))
		{
			Expression tempVal = new MultiplicationExpression(num1, num2); //ganti jadi MultiplyExpression
			return tempVal.solve();
		} 
		else if(op.equals("/"))
		{
			Expression tempVal = new DivideExpression(num1, num2); //etc..
			return tempVal.solve();
		}
		else if(op.equals("^"))
		{
			Expression tempVal = new PowerExpression(num1, num2); //etc..
			return tempVal.solve();
		}
		return def;
	}

	public RealNumber parse()
	{

		Stack<RealNumber> operand = new Stack<RealNumber>();
		Stack<String> operator = new Stack<String>();
		int i;

		for(i = 0; i < token.length(); i++)
		{

			if(token.charAt(i) == '(') 
			{
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

			// if encountering ans
<<<<<<< HEAD
			// else if(token.charAt(i) == 'a'){
			// 	RealNumber number = new RealNumber(ans);
			// 	operand.push(number);
			// 	i += 2;
			// 	if(!operator.empty()){
			// 		if(operator.peek().equals("neg")){
			// 			RealNumber val1 = operand.peek();
			// 			Expression num1 = new TerminalExpression(val1);
			// 			operand.pop();
			// 			String op = operator.peek();
			// 			operator.pop();
			// 			operand.push(solveUnary(num1, op));
			// 		}
			// 	}
			// }
=======
			else if(token.charAt(i) == 'A' ){
				RealNumber number = new RealNumber(Controller.ans.toString());
				operand.push(number);
				i += 2;
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
>>>>>>> ed797a441022d8aac60002d1c8407fc62a44454a

			//encountering euler constant as operand, not exponent component
			else if(token.charAt(i) == 'e'){
				RealNumber number = new RealNumber(Math.exp(1));
				operand.push(number);
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

			else if(token.charAt(i) == ')')
			{	
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
					}

				} 
				else 
				{
					BaseException exp = new ImbalancedParanthesesException();
					throw exp;
				}
				
			}

			else if(token.charAt(i)=='-' && (i==0 || (i>0 && token.charAt(i-1)=='('))) {
				operator.push("neg");
			}
			
			else 
			{	
				String cur = String.valueOf(token.charAt(i));
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
				if(!operator.empty()){
					while((pred.get(operator.peek()) >= pred.get(cur))){
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