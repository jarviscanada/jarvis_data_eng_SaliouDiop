package ca.jrvs.apps.tradingapp.model.domain;

public class Quote implements Entity<String>{

    private String ticker;
    private String bidPrice;
    private String askPrice;
    private String lastPrice;
    private String askSize;
    private String bidSize;

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(String bidPrice) {
        this.bidPrice = bidPrice;
    }

    public String getAskPrice() {
        return askPrice;
    }

    public void setAskPrice(String askPrice) {
        this.askPrice = askPrice;
    }

    public String getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(String lastPrice) {
        this.lastPrice = lastPrice;
    }

    public String getAskSize() {
        return askSize;
    }

    public void setAskSize(String askSize) {
        this.askSize = askSize;
    }

    public String getBidSize() {
        return bidSize;
    }

    public void setBidSize(String bidSize) {
        this.bidSize = bidSize;
    }

    @Override
    public String getId() {
        return ticker;
    }

    @Override
    public void setId(String id) {
        this.ticker = id;
    }
}
