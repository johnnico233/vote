package vote.result;

public class Result {
    private ResultCode resultCode;
    private String resultText;
    private Object additionInfo;

    public Result() {
    }

    public Result(ResultCode resultCode, String resultText) {
        this.resultCode = resultCode;
        this.resultText = resultText;
    }

    public Result(ResultCode resultCode, String resultText, Object additionInfo) {
        this.resultCode = resultCode;
        this.resultText = resultText;
        this.additionInfo = additionInfo;
    }

    public Object getAdditionInfo() {
        return additionInfo;
    }

    public void setAdditionInfo(Object additionInfo) {
        this.additionInfo = additionInfo;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultText() {
        return resultText;
    }

    public void setResultText(String resultText) {
        this.resultText = resultText;
    }

    @Override
    public String toString() {
        return "Result{" +
                "resultCode=" + resultCode +
                ", resultText='" + resultText + '\'' +
                ", additionInfo=" + additionInfo +
                '}';
    }
}
