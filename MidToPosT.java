package com.four.user;

public class MidToPosT {

    // 定义符号的优先级
    public static int Precedence(char sign) {
        switch (sign) {
            // +、-都为1
            case '+':
            case '-':
                return 1;
            // *、/为2
            case '*':
            case '/':
                return 2;
            case '(':
            case ')':
            default:
                return 0;
        }
    }

    /**************** 中缀表达式转换成后缀表达式 ********************/
    public static String[] Change(String str) {
        String[] s2 = new String[Test.MAX_RANGE];
        // #为结束符
        String s1 = str + "#";
        // 定义大小为20的String类型的栈
        Stack<Character> T = new Stack<Character>(Test.MAX_RANGE);
        int i = 0, j = 0;
        char ch;
        String temp = " ";
        T.push('@');
        ch = s1.charAt(i);
        while (ch != '#') {
            // 遇到'('就进栈
            if (ch == '(') {
                T.push(ch);
                ch = s1.charAt(++i);
            } else if (ch == ')') {
                // 遇到')'就把栈中'('后的符号全部出栈
                while (T.getTop() != '(')
                    s2[j++] = String.valueOf(T.pop());
                T.pop();
                ch = s1.charAt(++i);
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                char w = T.getTop();
                while (Precedence(w) >= Precedence(ch)) {
                    s2[j++] = String.valueOf(w);
                    T.pop();
                    w = T.getTop();
                }
                T.push(ch);
                ch = s1.charAt(++i);
            } else {
                if ((ch >= '0' && ch <= '9') || ch == '.') {
                    int k = i;
                    int flag = 1;
                    int arr = 0;
                    int q = 0;// 记录到下一个位置
                    for (; s1.charAt(i) >= '0' && s1.charAt(i) <= '9'; i++) {
                        if (i == s1.length() - 1) {
                            flag = 0;
                            break;
                        }
                    }
                    if (flag == 1) {
                        q = i;
                        i--;
                        for (int t = k; t <= i; t++) {
                            int single = Integer.parseInt(String.valueOf(s1
                                    .charAt(t)));
                            for (int p = t; p < i; p++) {
                                single = single * 10;
                            }
                            arr = arr + single;
                        }
                        i = q;
                    } else {
                        q = i;
                        q++;
                        for (int p = k; p <= i; p++) {
                            int single = Integer.parseInt(String.valueOf(s1
                                    .charAt(p)));
                            for (int t = p; t < i; t++) {
                                single = single * 10;
                            }
                            arr = arr + single;
                        }
                        i = q;
                    }

                    s2[j++] = String.valueOf(arr);
                    ch = s1.charAt(i);
                }
            }
        }
        ch = T.pop();
        while (ch != '@') {
            s2[j++] = String.valueOf(ch);
            ch = T.pop();
        }
        s2[j++] = "#";
        return s2;
    }


}