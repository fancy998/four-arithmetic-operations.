


	  
	import java.util.Stack;  
	import java.util.regex.Matcher;  
	import java.util.regex.Pattern;  
	  
	import sun.security.krb5.internal.ccache.CCacheInputStream;  
	  
	public class Calculate {  
	      
	    private Stack<Double> numStack = new Stack<Double>();  
	    private Stack<Character> sybStack = new Stack<Character>();   
	      
	    public String calResult ( String equation ) {  
	          
	        //�滻�˳���  
	        equation = equation.replace("X", "*");  
	        equation = equation.replace("��", "/");  
	          
	        //������  
	        equation = negativeNumTransfer(equation);  
	          
	        if ( !checkFormat(equation) ) {  
	            return "��ʽ��ʽ����";  
	        }  
	          
	        equation += "#";  
	        StringBuffer tempNum = new StringBuffer();  
	        StringBuffer exp = new StringBuffer().append(equation);  
	          
	        while ( exp.length() != 0 ) {  
	              
	            String temp = exp.substring(0,1);  
	            exp.delete(0, 1);  
	              
	            if( isNum(temp) )  { // temp������  
	                tempNum.append(temp);  
	            }  
	            else { // temp��������  
	                  
	                if (!"".equals(tempNum.toString())) {  
	                    // �����ʽ�ĵ�һ������Ϊ����  
	                    double num = Double.parseDouble(tempNum.toString());  
	                    numStack.push(num);  
	                    tempNum.delete(0, tempNum.length());  
	                }  
	                // �õ�ǰȡ�õ��������ջ��������Ƚ����ȼ��������ڣ�����Ϊ�������㣬����ջ���������ڣ���Ϊ�����ں��棬  
	                // ���Ի����㣬����ջ��Ԫ�س�ջ��ȡ�����������㣻��С�ڣ���ͬ��ȡ��ջ��Ԫ�����㣬������������ջ��  
	  
	                // �жϵ�ǰ�������ջ��Ԫ�����ȼ���ȡ��Ԫ�أ����м���(��Ϊ���ȼ�����С��ջ��Ԫ�أ���С�ڵڶ���Ԫ�صȵȣ���Ҫ��ѭ���ж�)  
	                while ( !compare(temp.charAt(0)) && (!sybStack.empty()) ) {  
	                    double a = numStack.pop();  
	                    double b = numStack.pop();  
	                    char ope = sybStack.pop();  
	                      
	                    // ���м򵥵ļ���  
	                    if( simpleCal(ope, a, b) == false ) {  
	                        return "��������Ϊ0";  
	                    }  
	                      
	                }  
	                  
	                // �жϵ�ǰ�������ջ��Ԫ�����ȼ��� ����ߣ����ߵ���ƽ��������󣬽���ǰ�������ţ����������ջ  
	                refreshSybStack(temp);  
	                  
	            }  
	              
	        }  
	          
	        return getResultStr(numStack.pop());  
	    }  
	      
	    private void refreshSybStack ( String temp) {  
	        if (temp.charAt(0) != '#') {  
	            sybStack.push(new Character(temp.charAt(0)));  
	            if (temp.charAt(0) == ')') {// ��ջ��Ϊ'('������ǰԪ��Ϊ')'ʱ�����������������꣬ȥ������  
	                sybStack.pop();  
	                sybStack.pop();  
	            }  
	        }  
	    }   
	      
	    private boolean simpleCal ( char ope, double a, double b ) {  
	          
	        double result = 0;  
	          
	        switch (ope) {  
	        case '+':  
	            result = b + a;  
	            numStack.push(result);  
	            break;  
	        case '-':  
	            result = b - a;  
	            numStack.push(result);  
	            break;  
	        case '*':  
	            result = b * a;  
	            numStack.push(result);  
	            break;  
	        case '/':  
	              
	            if ( a == 0.0 ) {  
	                return false;  
	            }  
	            else {  
	                result = b / a;  
	                numStack.push(result);  
	                break;  
	            }  
	              
	        }  
	          
	        return true;  
	    }  
	      
	    private String negativeNumTransfer( String equation ) {  
	        // ������ʽ������ʾ�����Ĳ��ֽ��иĶ���ת��calResult����֧�ֵ�   
	          
	        if( equation.length() <= 1 ) {  
	            return equation;  
	        }  
	          
	        StringBuffer str = new StringBuffer().append(equation);  
	          
	        for ( int i = 0; i < str.length()-1; ++i ) {  
	              
	            if( !str.substring(i, i+1).equals("-") ) {  
	                continue;  
	            }  
	              
	            if ( i == 0 ) {  
	                char temp = str.charAt(1);  
	                if( isNumChar(temp) || isDecimalPoint(temp) || isLeftBracket(temp) ) {  
	                    str.insert(0, "0");  
	                    i++;  
	                }  
	            }  
	            else {  
	                char last = str.charAt(i-1);  
	                char next = str.charAt(i+1);  
	                  
	                if( isLeftBracket(last) &&  
	                    ( isNumChar(next) || isDecimalPoint(next) || isLeftBracket(next) ) ) {  
	                    str.insert(i, "0");  
	                    i++;  
	                }  
	            }  
	        }  
	                  
	          
	        return str.toString();  
	    }  
	      
	      
	      
	    private boolean checkFormat ( String equation ) {  
	        char[] c = equation.toCharArray();  
	        int singleBracket = 0;  
	          
	        for( int i = 0; i < c.length; ++i ) {  
	              
	            if( isLeftBracket(c[i]) ) {  
	                singleBracket++;  
	            }  
	            if ( isRightBracket(c[i]) ) {  
	                singleBracket--;  
	            }  
	              
	            if ( i == 0 ) { //��1��Ԫ��ֻ����[0-9]������������  
	                if( !isLeftBracket(c[i]) && !isNumChar(c[i]) ) {  
	                    return false;  
	                }  
	            }  
	            else if ( isNumChar(c[i]) || isDecimalPoint(c[i]) ) { //������߲�����������  
	                if ( isRightBracket(c[i-1]) ) {  
	                    return false;  
	                }  
	            }  
	            else if( isLeftBracket(c[i]) )  { // �����ŵ���߲��������ֺ�������  
	                if ( isNumChar(c[i-1]) || isDecimalPoint(c[i-1]) || isRightBracket(c[i-1]) ) {  
	                    return false;  
	                }  
	            }  
	            else {  // �����ź���������������ֻ�������ֻ���������  
	                if ( !isNumChar(c[i-1]) && !isRightBracket(c[i-1]) ) {  
	                    return false;  
	                }  
	            }  
	              
	        }  
	          
	        return singleBracket == 0;  
	    }  
	  
	    private static boolean isNum ( String temp ) {  
	        return temp.matches("[0-9]") || temp.equals(".");  
	    }  
	      
	    private static boolean isLeftBracket ( char c ) {  
	        return c == '(';  
	    }  
	      
	    private static boolean isRightBracket ( char c ) {  
	        return c == ')';  
	    }  
	      
	    private static boolean isDecimalPoint ( char c ) {  
	        return c == '.';  
	    }  
	      
	    private static boolean isNumChar ( char c ) {  
	        return ( c >= '0' && c <= '9' );  
	    }  
	  
	    private boolean compare (char str) {  
	        if ( sybStack.empty() ) {  
	            // ��Ϊ��ʱ����Ȼ ��ǰ���ȼ���ͣ����ظ�  
	            return true;  
	        }  
	        char last = (char) sybStack.lastElement();  
	        // ���ջ��Ϊ'('��Ȼ�����ȼ���ͣ�')'������Ϊջ����  
	        if (last == '(') {  
	            return true;  
	        }  
	        switch (str) {  
	        case '#':  
	            return false;// ������  
	        case '(':  
	            // '('���ȼ����,��Ȼ����true  
	            return true;  
	        case ')':  
	            // ')'���ȼ���ͣ�  
	            return false;  
	        case '*': {  
	            // '*/'���ȼ�ֻ��'+-'��  
	            if (last == '+' || last == '-')  
	                return true;  
	            else  
	                return false;  
	        }  
	        case '/': {  
	            if (last == '+' || last == '-')  
	                return true;  
	            else  
	                return false;  
	        }  
	        // '+-'Ϊ��ͣ�һֱ����false  
	        case '+':  
	            return false;  
	        case '-':  
	            return false;  
	        }  
	        return true;  
	    }  
	      
	    private String getResultStr ( double result ) {  
	        StringBuffer s = new StringBuffer().append( result + "" );  
	          
	        if ( s.substring(s.length() - 2).equals(".0") ) {  
	            s.delete( s.length()-2 , s.length() );  
	        }  
	          
	        return s.toString();  
	    }  
	      
	}  
}
