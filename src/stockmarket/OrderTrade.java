/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package stockmarket;

import java.util.HashMap;
import java.util.Date;

/**
 *
 * @author Jean-Paul Jacques <jjac403@cse.unsw.edu.au>
 */
public class OrderTrade {
    
    
    private static final float LOAD_FACTOR = 1;
    protected HashMap map;

    protected String security;
    protected Date date;
    protected Date time;

    protected double price;
    protected double volume;
    protected double value;

    protected long transId;
    protected long bidId;
    protected long askId;

    protected OrderType orderType;
    protected TradeType tradeType;
    

    public OrderTrade(int tableSize) {
        map = new HashMap(tableSize, LOAD_FACTOR);
    }
    
    public void put(String att, String val) {
        map.put(att.toLowerCase(), val);
    }
    
    public String get(String att) {
        return (String)map.get(att.toLowerCase());
    }

    public String getSecurity() { return security; }
    public Date getDate() { return date; }
    public Date getTime() { return time; }

    public double getPrice() { return price; }
    public double getVolume() { return volume; }
    public double getValue() { return value; }

    public long getTransId() { return transId; }
    public long getBidId() { return bidId; }
    public long getAskId() { return askId; }

    public OrderType getOrderType() { return orderType; }
    public TradeType getTradeType() { return tradeType; }

    public void setSecurity(String sec) { security = sec; }
    public void setDate(Date d) { date = d; }
    public void setTime(Date t) { time = t; }

    public void setPrice(double p) { price = p; }
    public void setVolume(double v) { volume = v; }
    public void setValue(double v) { value = v; }

    public void setTransId(long id) { transId = id; }
    public void setBidId(long id) { bidId = id; }
    public void setAskId(long id) { askId = id; }

    public void setOrderType(String os) {
        if(os.equalsIgnoreCase("Enter"))
             orderType = OrderType.ENTER;
         if(os.equalsIgnoreCase("Amend"))
             orderType = OrderType.AMEND;
         if(os.equalsIgnoreCase("Delete"))
             orderType = OrderType.DELETE;
    }

    public void setTradeType(String ot) {
        if(ot.equalsIgnoreCase("A") || ot.equalsIgnoreCase("Ask"))
            tradeType = TradeType.ASK;
        if(ot.equalsIgnoreCase("B") || ot.equalsIgnoreCase("Bid"))
            tradeType = TradeType.BID;
    }



    public enum OrderType { ENTER, AMEND, DELETE }
    public enum TradeType { ASK, BID }
    
}
