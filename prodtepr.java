package prodtExpression;

import java.text.DecimalFormat;
import java.util.Random;

public class prodtepr {
	 public static int Max = 100; //算式中数值1到Max 
	    
	    public static Expression prodE(int num, int kind){ //num 运算符数目； kind 1:+  2:-  3:*  4:/  5:混合
	    	if(num<1){
	    		System.out.println("!运算符数目至少为1");
	    		return null;
	    	}
	    	
	        Expression Expr = new Expression();
	        
	    	int counts = num+1; // counts 运算值的数目
	    	int[] val = new int[counts];
	    	
	    	double result=0;
	    	String epr="";
	    	
	    	Random rand = new Random();
	   	
	    	for(int i=0; i<counts; i++){
	    	    val[i]= rand.nextInt(Max)+1;
	    	    System.out.println(val[i]);
	    	}
	    	
	    	if(kind==1){
	    	   for(int i=0; i<counts; i++){
	    		   result+=val[i];
	    	   }
	    	   epr=val[0]+"";
	    	   for(int i=1; i<counts;i++){
	    		   epr+="+"+val[i];
	    	   }
	    	}else if(kind==2){ //"-"
	    		
	    		//保证结果大于0
	    		result = val[0];
	    		for(int i=1; i<counts; i++){
	     		   result-=val[i];
	     	   }
	    		while(result<0){
	    			for(int i=0; i<counts; i++){
	    	    	    val[i]= rand.nextInt(Max)+1;    	    	    
	    	    	}
	    			result = val[0];
	    			for(int i=1; i<counts; i++){
	    	     		   result-=val[i];
	    	     	   }
	    		}//end while
	    		
	     	   epr=val[0]+"";
	     	   for(int i=1; i<counts;i++){
	     		   epr+="-"+val[i];
	     	   }
	    	}else if(kind==3){ // "*"
	    		result = val[0];
	    		epr=val[0]+"";
	    		for(int i=1; i<counts; i++){
	    			result*=val[i];
	    			epr+="*"+val[i];
	    		}
	    	}else if(kind==4){ // "/"
	    		result= val[0];
	    		epr=val[0]+"";
	    		DecimalFormat df = new DecimalFormat("#.00"); 
	    		for(int i=1 ; i<counts; i++){
	    			result=result/val[i];
	    			result=Double.parseDouble(df.format(result)); //保留两位小数
	    			epr+="/" +val[i];
	    		}
	    	}else if(kind == 5){ //混合
	    		if(num<2){
	    			System.out.println("！混合运算输入不正确");
	    			return null;
	    		}
	    		num =2;  //混合运算只能生成2个运算符的；
	    		if(num==2){
	    			int n = rand.nextInt(Max)%4+1;
	    			System.out.println(n);
	    			Expr = multiOp2(n);
	    			return Expr;
	    		}
	    		
	    	}else{
	    		System.out.println("wrong kind");
	    	}
	    	
	        Expr.epr= epr;
	        Expr.result=result;
			return Expr;	
	    }
	    
	    static Expression multiOp2(int model){
	    	
	    	Random rand = new Random();
	    	double result =0;
	    	String epr = "";
	        int[] val=new int[3];
	        for(int i=0; i<3; i++){
	    	    val[i]= rand.nextInt(Max)+1;  	  
	    	}
	        int flag = rand.nextInt(Max)%2;
	        int flag2 = rand.nextInt(Max)%2;
	        
	        DecimalFormat df = new DecimalFormat("#.00"); 
	        
	    	if(model==1){ //加减混合
	    		if(flag==0){ 
	    			result=val[0]+val[1]-val[2];
	    			epr=val[0]+"+"+val[1]+ "-"+val[2];
	    		}else{
	    			result=val[0]-val[1]+val[2];
	    			epr=val[0]+"-"+val[1]+ "+"+val[2];
	    		}
	    	}else if(model==2){//乘除混合
	    		if(flag==0){ 
	    			result=val[0]*val[1]/val[2];
	    			epr=val[0]+"*"+val[1]+ "/"+val[2];
	    		}else{
	    			result=val[0]/val[1]*val[2];
	    			epr=val[0]+"/"+val[1]+ "*"+val[2];
	    		}
	    	}else if(model==3){//题型：(1+2)*3
	    		if(flag==0 && flag2==0){
	    			result =( val[0]+val[1] ) * val[2];
	    			epr = "(" +val[0] + "+" + val[1] +")" +"*" + val[2];
	    		}else if(flag==0 && flag2==1){
	    			result =( val[0]+val[1] ) / val[2];
	    			result = Double.parseDouble(df.format(result));
	    			epr = "(" +val[0] + "+" + val[1] +")" +"/" + val[2];
	    		}else if(flag==1 && flag2==0){
	    			result =( val[0]-val[1] ) * val[2];
	    			epr = "(" +val[0] + "-" + val[1] +")" +"*" + val[2];
	    		}else{
	    			result =( val[0]-val[1] ) / val[2];
	    			result = Double.parseDouble(df.format(result));
	    			epr = "(" +val[0] + "-" + val[1] +")" +"/" + val[2];
	    		}
	    	}else if(model==4){//题型：1*(2+3)
	    		if(flag==0 && flag2==0){
	    			result = val[0] * ( val[1] + val[2] );
	    			epr = val[0] + "*(" +val[1] + "+" +val[2]+")" ;
	    		}else if(flag==0 && flag2==1){
	    			result = val[0] * ( val[1] - val[2] );
	    			epr = val[0] + "*(" +val[1] + "-" +val[2]+")" ;
	    		}else if(flag==1 && flag2==0){
	    			result = val[0] / ( val[1] + val[2] );
	    			result = Double.parseDouble(df.format(result));
	    			epr = val[0] + "/(" +val[1] + "+" +val[2]+")" ;
	    		}else{
	    			result = val[0] / ( val[1] - val[2] );
	    			result = Double.parseDouble(df.format(result));
	    			epr = val[0] + "/(" +val[1] + "-" +val[2]+")" ;
	    		}
	    	}else{
	    		System.out.println("multiOp2 has wrong input");
	    	}
	    	Expression E = new Expression();
	    	E.epr =epr;
	    	E.result=result;
	    	
			return E;
	    }
}
