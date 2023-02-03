public class Task {

    private String value;
    private Boolean isFinished;

    public Task(String value, Boolean isFinished) {
        this.value = value;
        this.isFinished = isFinished;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean isFinished() {
        return isFinished;
    }

    public void setFinished(Boolean finished) {
        isFinished = finished;
    }
}
