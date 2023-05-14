import java.util.*;



public class A1113313_0512{
    
    public static void main(String[] args) {
        System.out.printf("請輸入同時來的顧客數 : ");
        int input = 0;
        Scanner sc = new Scanner(System.in);
        input = sc.nextInt();
        DumpSales[] staff = new DumpSales[input];

        for(int i=0;i<input;i++){
            staff[i]=new DumpSales(i);
        }
        for(int i=0;i<input;i++){
            staff[i].start();
        }
        for(int i=0;i<input;i++){
            try {
                staff[i].join();
            } catch (Exception e) {
            }
        }
        System.out.println("水餃已全部販售完畢");
    }

    synchronized static void salesReport(int kind,int num,int no){
        no+=1;
        if (kind==0 && DumpSales.Pork>0){
            if(num>=DumpSales.Pork){
                num=DumpSales.Pork;
                System.out.println("豬肉售出最後一份!!");
            }
            DumpSales.Pork-=num;
            System.out.printf("%3d號店員\t豬肉售出%2d顆\t剩餘%4d顆\n",no,num,DumpSales.Pork);
        }else if(kind==1 && DumpSales.Beef>0){
            if(num>=DumpSales.Beef){
                num=DumpSales.Beef;
                System.out.println("牛肉售出最後一份!!");
            }
            DumpSales.Beef-=num;
            System.out.printf("%3d號店員\t牛肉售出%2d顆\t剩餘%4d顆\n",no,num,DumpSales.Beef);
        }else if(kind==2 && DumpSales.Vage>0){
            if(num>=DumpSales.Vage){
                num=DumpSales.Vage;
                System.out.println("蔬菜售出最後一份!!");
            }
            DumpSales.Vage-=num;
            System.out.printf("%3d號店員\t蔬菜售出%2d顆\t剩餘%4d顆\n",no,num,DumpSales.Vage);
        }
    }
}

class DumpSales extends Thread{
    static int Pork=5000,Beef=3000,Vage=1000;
    int no;
    DumpSales(int i){
        this.no=i;
    }

    public void run(){
        while(Pork>0||Beef>0||Vage>0){
            int num=0,kind=0;
            num=(int)(Math.random()*40)+10;
            do{
                kind=((int)(Math.random()*100))%3;
            }while((kind==0 && Pork==0) || (kind==1 && Beef==0) || (kind==2 && Vage==0));
            A1113313_0512.salesReport(kind, num,no);
            try {
                sleep(3000);
            } catch (Exception e) {
            
            }
        }
    }
}