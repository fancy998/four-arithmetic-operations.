
public class jisuanfenshu {public class Demo01 {  
  
    /** 
     * @param args 
     */  
    public static void main��String[] args�� {  
        // TODO Auto-generated method stub  
        fracAdd(1,5,7,20);//���Ϊ��11/20  
        fracSub(1,5,7,20);//�������  
        fracMul(1,5,7,20);//�������  
        fractDiv(1,5,7,20);//�������  
  
    }  
    static void fracAdd ��int first_numerator,int first_denominator,int second_numrator,int second_denominator){  
        //���´����ܹ��ڿ���̨����ʾ���  
        //��Ҫ���������Լ���ĺ���  
        //��Ҫ��������С�������ĺ���  
        int denominator;  
        int numerator;  
          
        if(first_denominator==second_denominator)  //��ĸ��ͬʱ�ӷ���       
        {        
             denominator=first_denominator;        
             numerator=first_numerator+second_numrator;        
        }        
        else  //����ͬ�ֱȽϷ���       
        {        
            denominator=first_denominator*second_denominator;        
            numerator=first_numerator*second_denominator+first_denominator*second_numrator;        
        }      
        int gcd = gcd(numerator,denominator);  
        denominator = denominator / gcd;  
        numerator = numerator / gcd;          
        System.out.println("����Ľ����"+numerator+"/"+denominator);   
        return ;        
  
    }  
    static void   fracSub��int first_numerator,int first_denominator,int second_numrator,int second_denominator��{  
        //���´����ܹ��ڿ���̨����ʾ���  
        //��Ҫ���������Լ���ĺ���  
          
        int denominator;  
        int numerator;  
          
        if(first_denominator==second_denominator)  //��ĸ��ͬʱ�ӷ���       
        {        
             denominator=first_denominator;        
             numerator=first_numerator-second_numrator;        
        }        
        else  //����ͬ�ֱȽϷ���       
        {        
            denominator=first_denominator*second_denominator;        
            numerator=first_numerator*second_denominator-first_denominator*second_numrator;        
        }      
        int gcd = gcd(numerator,denominator);  
        denominator = denominator / gcd;  
        numerator = numerator / gcd;          
        System.out.println("����Ľ����"+numerator+"/"+denominator);   
        return ;        
  
    }  
    static void fracMul(int first_numerator,int first_denominator,int second_numerator,int second_denominator){  
        //���´����ܹ��ڿ���̨����ʾ���  
        //��Ҫ���������Լ���ĺ���  
          
        int denominator;  
        int numerator;  
          
         
            denominator=first_denominator*second_denominator;        
            numerator=first_numerator*second_numerator;   
            
        int gcd = gcd(numerator,denominator);  
        denominator = denominator / gcd;  
        numerator = numerator / gcd;          
        System.out.println("����Ľ����"+numerator+"/"+denominator);   
        return ;        
  
    }  
    static void fractDiv(int first_numerator,int first_denominator,int second_numerator,int second_denominator){  
        //���´����ܹ��ڿ���̨����ʾ���  
        //��Ҫ���������Լ���ĺ���  
          
        int denominator;  
        int numerator;  
          
        numerator = first_numerator*second_denominator;  
        denominator = first_denominator*second_numerator;  
          
              
            
        int gcd = gcd(numerator,denominator);  
        denominator = denominator / gcd;  
        numerator = numerator / gcd;          
        System.out.println("����Ľ����"+numerator+"/"+denominator);   
        return ;        
  
    }  
    static int gcd(int x,int y){  
         int r;        
            while( y!= 0)        
            {        
                r = x%y;        
                x = y;        
                y = r;        
            }        
  
        return x;  
          
    }  
      
} 

}
