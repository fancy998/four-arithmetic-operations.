package com.four.user;

public class Calculation {
    //判断是否为数字
    public static boolean isNumber(char ch){
        switch (ch) {
            case '+':return false;
            case '-':return false;
            case '*':return false;
            case '/':return false;
            default:
                return true;
        }
    }

    public static Fraction cal(String str){
        Stack<Fraction> stack=new Stack<Fraction>(20);
        FractionCalculation fc=new FractionCalculation();
        String[] array=MidToPosT.Change(str);
        int i=0;
        Fraction fa,fb;
        char op;
        while(!array[i].equals("#")){
            //是数字
            if(isNumber(array[i].charAt(0))){
                //化成分数进栈
                stack.push(new Fraction(Integer.parseInt(array[i++]), 1));
            }else{
                fb=stack.pop();
                fa=stack.pop();
                op=array[i++].charAt(0);
                fa=fc.fraccalculate(fa, op, fb);
                stack.push(fa);
            }
        }
        fa=stack.getTop();
        return fa;
    }
}