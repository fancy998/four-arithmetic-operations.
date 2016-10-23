package ljf;

public class PostToMid {

	/**
	 * ��׺ת��׺����׺�ѡ�#��������������ȥ�����ɵĶ��������
	 * 
	 * @param op
	 * @return
	 */
	public static boolean isOp(char op) {
		return op == '-' || op == '+' || op == '*' || op == '/';
	}

	public static String[] getInfixByPostfix(String[] postfix) {
		// stack<char>sptr; ������ջ
		// stack<char>A; ������ջ
		int post = 0, in = 0;// ָ���׺���ʽ
		Stack<Character> sptr = new Stack<Character>(Test.MAX_RANGE);
		Stack<String> A = new Stack<String>(Test.MAX_RANGE);
		String[] infix = new String[Test.MAX_RANGE];
		int len = 0; // ��׺���ʽ�ĸ���
		for (; !postfix[len].equals("#"); len++)
			;
		post = len - 1;
		boolean isLeft = false;
		for (int i = 0; i < len; i++, post--) {
			if (isOp(postfix[post].charAt(0))) {
				if (isLeft) {
					if (!A.isEmpty()
							&& (A.getTop().charAt(0) == '*' || A.getTop()
									.charAt(0) == '/')
							&& (postfix[post].charAt(0) == '+' || postfix[post]
									.charAt(0) == '-')) {
						A.push(")");
						sptr.push('(');
					}
					isLeft = false;
				} else if (!sptr.isEmpty()) {
					if (sptr.getTop() == '-'
							&& postfix[post].charAt(0) != '*'
							&& postfix[post].charAt(0) != '/'
							|| sptr.getTop() == '/'
							|| sptr.getTop() == '*'
							&& (postfix[post].charAt(0) == '+' || postfix[post]
									.charAt(0) == '-')) {
						A.push(")");
						sptr.push('(');
					}
				}
				sptr.push(postfix[post].charAt(0));
			} else {
				A.push(postfix[post]);
				while (!sptr.isEmpty() && sptr.getTop() == '(') {
					A.push(String.valueOf(sptr.getTop()));
					sptr.pop();
				}
				if (!sptr.isEmpty()) {
					A.push(String.valueOf(sptr.getTop()));
					sptr.pop();
					isLeft = true;
				}
			}
		}

		while (!A.isEmpty()) {
			infix[in++] = A.getTop();
			A.pop();
		}
		infix[in++] = "#";
		return infix;
	}
}
