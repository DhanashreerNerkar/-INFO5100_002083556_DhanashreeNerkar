import java.math.BigInteger;

public class PrimeNumbers {
    public void primenumbers()
    {
        System.out.println("1, 2");
        int count=3,n=3;
        while(count<=25)
        {
            n++;
            boolean isprime=true;
            for(int i=2;i<n;i++)
            {
                if(n%i==0){
                    isprime=false;
                    break;
                }
            }
            if(isprime){
                System.out.println("primeNo "+count+": "+n);
                count++;
            }
        }
    }
    public void fibbonacci()
    {
        System.out.println("0, 1");
        long no=0,first=0,second=1;
        int count=3;
        while(count<=50)
        {
            no=first+second;
            first=second;
            second=no;
            System.out.println(count+"rd no: "+no);
            count++;
        }
    }

    public void factorial(){
        BigInteger fact = BigInteger.ONE;
        for (int i = 50; i >=1; i--) {
            fact = fact.multiply(BigInteger.valueOf(i));
        }
        System.out.println("factorial of 1st 50 nums : "+fact);
    }
}
