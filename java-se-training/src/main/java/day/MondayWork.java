package day;

public class MondayWork implements DayWork{

    @Override
    public void doWork() {
        System.out.println("Monday lalala");
    }

    public boolean isImportant() {
        return false;
    }
}
