package day;

public enum Day {
    MONDAY(new MondayWork()), TUESDAY(new TuesdayWork());

    private DayWork dayWork;

    Day(DayWork dayWork) {
        this.dayWork = dayWork;
    }

    public DayWork getDayWork() {
        return dayWork;
    }

    public static void main(String[] args) {
        Day day = MONDAY;

        day.getDayWork().doWork();

    }
}
