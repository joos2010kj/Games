public class Time implements Runnable{

    int sec;

    public Time(){
        sec = 0;
    }


    public void run() {
        try{
            while(true){

                Thread.sleep(1000);
                sec++;
            }
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}
