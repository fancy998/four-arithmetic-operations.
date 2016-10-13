package com.four.user;

public class GenerateFormula {
	public static int n = 0;
    public static int nMax = 0;
    public static int s = 10;

    public static int maxyueshu(int a, int b) {
        return a % b == 0 ? b : maxyueshu(b, a % b);
    }

    public static String getTrueFraction() {
        int i, j;// i分子，j分母，分母要大于分子，要约粉
        while (true) {
            i = (int) (Math.random() * 100) % s + 1;
            j = (int) (Math.random() * 100) % s + 1;
            if ((j > i) && (j != 1)) {
                int max = maxyueshu(j, i);
                i /= max;
                j /= max;
                return i + "/" + j;
            }
        }
    }

    public static String fourRan(int pre) {
        // pre代表上一步的运算符，1+ 2- 3* 4/ 5（） 6数
        n++;
        int i = (int) (Math.random() * 100) % 6 + 1;

        String str = "";
        if (n < nMax)
            ;
        else
            i = 6;
        switch (i) {
            case 1:
                str = fourRan(1) + "+" + fourRan(1);
                break;
            case 2:
                str = fourRan(2) + "-" + fourRan(2);
                break;
            case 3:
                str = fourRan(3) + "*" + fourRan(3);
                break;
            case 4:
                str = fourRan(4) + "/" + fourRan(4);
                break;
            case 5:
                if(pre==5){
                    str=fourRan(5);
                }else{
                    str = "(" + fourRan(5) + ")";
                }
                break;
            case 6:
                int temp = (int) (Math.random() * 100) % 2 + 1;
                if (temp == 1) {
                    // 生成数字返回
                    if (pre == 4) {
                        return (int) (Math.random() * s) + 1 + "";
                    } else {
                        return (int) (Math.random() * s) + "";
                    }

                } else {
                    if(pre==5){
                        return  getTrueFraction() ;
                    }else
                        return "(" + getTrueFraction() + ")";

                }
        }

        return str;
    }

    public static String getFormula(int ni, int si) {
        n=0;
        nMax = ni;
        s = si;
        String str = "", result = "";
        int i;
        i = (int) (Math.random() * 100) % 4 + 1;
        switch (i) {
            case 1:
                str = fourRan(1) + "+" + fourRan(1);
                break;
            case 2:
                str = fourRan(2) + "-" + fourRan(2);
                break;
            case 3:
                str = fourRan(3) + "*" + fourRan(3);
                break;
            case 4:
                str = fourRan(4) + "/" + fourRan(4);
                break;

        }
/*
        // 对生成的式子进行去除多余括号处理
        String[] postfix = MidToPosT.Change(str);
        String[] infix = PostToMid.getInfixByPostfix(postfix);
        for (int in = 0; !infix[in].equals("#"); in++) {
            result = result + infix[in];
        }
        return result;
        */
        return str;
    }
}
