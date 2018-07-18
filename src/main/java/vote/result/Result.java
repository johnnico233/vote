package vote.result;

public class Result {
    private ResultCode resultCode;
    private String resultText;

    public Result() {
    }

    public Result(ResultCode resultCode, String resultText) {
        this.resultCode = resultCode;
        this.resultText = resultText;
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
                '}';
    }
}
