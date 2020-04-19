package dto;

/**
 * @author chenzhilong
 * @date
 */
public class LsjzPt {
    private int ErrCode;
    private String ErrMsg;
    private int TotalCount;
    private String Expansion;
    private int PageSize;
    private int PageIndex;
    private LsjzData Data;

    public LsjzData getData() {
        return Data;
    }

    public void setData(LsjzData data) {
        Data = data;
    }

    public int getErrCode() {
        return ErrCode;
    }

    public void setErrCode(int errCode) {
        ErrCode = errCode;
    }

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String errMsg) {
        ErrMsg = errMsg;
    }

    public int getTotalCount() {
        return TotalCount;
    }

    public void setTotalCount(int totalCount) {
        TotalCount = totalCount;
    }

    public String getExpansion() {
        return Expansion;
    }

    public void setExpansion(String expansion) {
        Expansion = expansion;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int pageSize) {
        PageSize = pageSize;
    }

    public int getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(int pageIndex) {
        PageIndex = pageIndex;
    }
}
