package stockmarket;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;



public class DataReader {

    /*
     *
     * make a new instance with a bufferedreader provided: new DataReader(buff)
     * nextLine() is a boolean stating there are more lines
     * Is also required for the next line to be read.
     *
     * verifyLine() pretty much how it was before with DataVerify
     * getLineData() returns OrderTrade with all the required information.
     * getLineData automatically verifies a line.
     *
     * Also to note is that in the case of unknown attributes in a data file,
     * OrderTrade has a hashtable which has ALL attributes pushed onto it in
     * it's original string form (so every possible attribute is available).
     */

    protected TableValues[] fileHeaders;
    protected String[] fileExtra;
    protected int lineNum;
    protected String currentLine;
    protected final BufferedReader readData;

    public DataReader(BufferedReader buff) throws IOException, TradeDataException {

        readData = buff;

        currentLine = readData.readLine();
        if(!currentLine.startsWith("#")) {
            throw new TradeDataException("Expected header data not provided in required format.");
        }
        currentLine = currentLine.substring(1);

        String[] tokens = currentLine.split(",");
        if(tokens.length < TableValues.values().length)
            throw new TradeDataException("Insufficient amount of attributes provided in File Header");

        fileHeaders = new TableValues[tokens.length];
        lineNum = 1;

        boolean[] checked = new boolean[TableValues.values().length];
        fileExtra = new String[tokens.length - TableValues.values().length];
        int numFound = 0;
        int numExtra = 0;


        for(int col = 0; col < tokens.length; col++) {
            boolean found = false;
            for(TableValues v : TableValues.values()) {
                if(v.getAttribute().equalsIgnoreCase(tokens[col])) {
                    if(checked[v.ordinal()])
                        throw new TradeDataException("Repeated Attribute " + tokens[col] + " found in File Header");
                    fileHeaders[col] = v;
                    checked[v.ordinal()] = true;
                    found = true;
                    numFound++;

                }
            }
            if(!found) {
                fileHeaders[col] = null;
                fileExtra[numExtra] = tokens[col];
                numExtra++;
            }
        }
        if(numFound < TableValues.values().length) {
            String notFound = "";
            for(TableValues v : TableValues.values()) {
                if(!checked[v.ordinal()])
                    notFound += v.getAttribute() + " ";
            }
            throw new TradeDataException("Required Attribute(s) not found in Header: " + notFound);
        }



    }

    public boolean nextLine() throws IOException {
        lineNum++;
        currentLine = readData.readLine();
        if(currentLine == null)
            return false;
        return true;
    }

    public void verifyLine() throws TradeDataException {
        String[] tokens = currentLine.split(",");
        if(tokens.length < (TableValues.values().length + fileExtra.length))
            throw new TradeDataException("Insufficient columns of data provided on line " + lineNum + ": " + currentLine);
        if(tokens.length > (TableValues.values().length + fileExtra.length))
            throw new TradeDataException("Unknown extra columns of data provided on line " + lineNum + ": " + currentLine);

        for(int col = 0; col < tokens.length; col++) {
            switch(fileHeaders[col]) {
                case SECURITY:
                    checkSecurity(tokens[col], lineNum);
                    break;
                case DATE:
                    checkDate(tokens[col], lineNum);
                    break;
                case TIME:
                    checkTime(tokens[col], lineNum);
                    break;
                case STATUS:
                    checkStatus(tokens[col], lineNum);
                    break;
                case PRICE:
                    checkPrice(tokens[col], lineNum);
                    break;
                case VOLUME:
                    checkVolume(tokens[col], lineNum);
                    break;
                case VALUE:
                    checkValue(tokens[col], lineNum);
                    break;
                case TRANS:
                    checkTrans(tokens[col], lineNum);
                    break;
                case BID:
                    checkBid(tokens[col], lineNum);
                    break;
                case ASK:
                    checkAsk(tokens[col], lineNum);
                    break;
                case TYPE:
                    checkType(tokens[col], lineNum);
                    break;
                default:
                    break;

            }
        }
    }

    public OrderTrade getLineData() throws TradeDataException, ParseException {
        verifyLine();
        String[] tokens = currentLine.split(",");

        OrderTrade ot = new OrderTrade(tokens.length);
        int other = 0;
        
        for(int col = 0; col < tokens.length; col++) {
            String token = tokens[col];
            switch(fileHeaders[col]) {

                case SECURITY:
                    ot.setSecurity(token);
                    ot.put("Instrument", token);
                    break;

                case DATE:
                    DateFormat df = new SimpleDateFormat("dd-MMM-yy");
                    ot.setDate(df.parse(token));
                    ot.put("Date", token);
                    break;

                case TIME:
                    DateFormat dt;
                    if(token.length() > 8)
                        dt = new SimpleDateFormat("HH:mm:ss.SSS");
                    else
                        dt = new SimpleDateFormat("HH:mm:ss");
                    ot.setTime(dt.parse(token));
                    ot.put("Time", token);
                    break;

                case STATUS:
                    ot.setOrderType(token);
                    ot.put("Record Type", token);
                    break;

                case PRICE:
                    ot.setPrice(Double.parseDouble(token));
                    ot.put("Price", token);
                    break;

                case VOLUME:
                    ot.setVolume(Double.parseDouble(token));
                    ot.put("Volume", token);
                    break;

                case VALUE:
                    ot.setValue(Double.parseDouble(token));
                    ot.put("Value", token);
                    break;

                case TRANS:
                    ot.setTransId(Long.parseLong(token));
                    ot.put("Trans ID", token);
                    break;

                case BID:
                    if(token.length() > 0)
                        ot.setBidId(Long.parseLong(token));
                    ot.put("Bid ID", token);
                    break;

                case ASK:
                    if(token.length() > 0)
                        ot.setAskId(Long.parseLong(token));
                    ot.put("Ask ID", token);
                    break;

                case TYPE:
                    ot.setTradeType(token);
                    ot.put("Bid/Ask", token);
                    break;
                    
                default:
                    ot.put(fileExtra[other], token);
                    other++;
                    break;

            }
        }

        return ot;
    }

        public void checkSecurity(String token, int lineNum) throws TradeDataException {
            if(token.length() == 0)
                throw new TradeDataException("No security provided on line " + lineNum);
        }

        public void checkDate(String token, int lineNum) throws TradeDataException {
            DateFormat df = new SimpleDateFormat("dd-MMM-yy");
            try {
                df.parse(token);
            } catch(Exception e) {
                e.printStackTrace();
                throw new TradeDataException("Unexpected format for DATE on line " + lineNum + ": " + token);
            }
        }

         public void checkTime(String token, int lineNum) throws TradeDataException {
             DateFormat df = null;
             if(token.length() > 8)
                 df = new SimpleDateFormat("HH:mm:ss.SSS");
             else
                 df = new SimpleDateFormat("HH:mm:ss");
            try {
                df.parse(token);

            } catch(Exception e) {
                throw new TradeDataException("Unexpected format for TIME on line " + lineNum + ": " + token);
            }
         }

         public void checkStatus(String token, int lineNum) throws TradeDataException {
             if(token.equalsIgnoreCase("Enter"))
                 return;
             if(token.equalsIgnoreCase("Amend"))
                 return;
             if(token.equalsIgnoreCase("Delete"))
                 return;
             throw new TradeDataException("Unknown RECORD TYPE on line " + lineNum + ": " + token);
         }

         public void checkPrice(String token, int lineNum) throws TradeDataException {
             try {
                Double.parseDouble(token);
             } catch(Exception e) {
                 throw new TradeDataException("Invalid number provided for PRICE on line " + lineNum + ": " + token);
             }
         }

         public void checkVolume(String token, int lineNum) throws TradeDataException {
             try {
                Double.parseDouble(token);
             } catch(Exception e) {
                 throw new TradeDataException("Invalid number provided for VOLUME on line " + lineNum + ": " + token);
             }
         }

         public void checkValue(String token, int lineNum) throws TradeDataException {
             try {
                Double.parseDouble(token);
             } catch(Exception e) {
                 throw new TradeDataException("Invalid number provided for VALUE on line " + lineNum + ": " + token);
             }
         }

         public void checkTrans(String token, int lineNum) throws TradeDataException {
            try {
                Long.parseLong(token);
             } catch(Exception e) {
                 throw new TradeDataException("Invalid number provided for TRANSACTION ID on line " + lineNum + ": " + token);
             }
         }

         public void checkBid(String token, int lineNum) throws TradeDataException {
            if(token.length() == 0)
                return;
            try {
                Long.parseLong(token);
             } catch(Exception e) {
                 throw new TradeDataException("Invalid number provided for BID ID on line " + lineNum + ": " + token);
             }
         }


         public void checkAsk(String token, int lineNum) throws TradeDataException {
             if(token.length() == 0)
                return;
            try {
                Long.parseLong(token);
             } catch(Exception e) {
                 throw new TradeDataException("Invalid number provided for ASK ID on line " + lineNum + ": " + token);
             }
         }

         public void checkType(String token, int lineNum) throws TradeDataException {
             if(token.length() == 0)
                return;

             if(token.equalsIgnoreCase("A") || token.equalsIgnoreCase("B")
                     || token.equalsIgnoreCase("Ask") || token.equalsIgnoreCase("Bid"))
                 return;
             throw new TradeDataException("Invalid ORDER TYPE provided on line " + lineNum + ": " + token);
         }

         /*public void csvReader(File file) throws IOException, TradeDataException {

            BufferedReader read = new BufferedReader( new FileReader(file) );

            String line = null;
            int lineNum = 0;
            TableValues[] header = null;

            int totalHeaders = 0;

            while((line = read.readLine()) != null) {
                lineNum++;

                boolean flag = false;
                if(line.startsWith("#")) {
                    flag = true;
                    line = line.substring(1);
                }
                String[] tokens = line.split(",");

                if(tokens.length != totalHeaders && totalHeaders != 0)
                    throw new TradeDataException("Unknown extra data found on " + lineNum + ": " + line);
                if(tokens.length < TableValues.values().length)
                        throw new TradeDataException("Insufficient columns of data provided on line " + lineNum + ": " + line);

                if(flag) {
                    header = new TableValues[tokens.length];
                    boolean[] checked = new boolean[TableValues.values().length];
                    int numFound = 0;

                    for(int col = 0; col < tokens.length; col++) {
                        boolean found = false;
                        for(TableValues v : TableValues.values()) {
                            if(v.getAttribute().equalsIgnoreCase(tokens[col])) {
                                if(checked[v.ordinal()])
                                    throw new TradeDataException("Unexpected Repeated Header " + tokens[col] + " on line " + lineNum);
                                header[col] = v;
                                found = true;
                                numFound++;

                            }
                        }
                        if(!found)
                            header[col] = null;
                    }
                    if(numFound < TableValues.values().length) {
                        String notFound = "";
                        for(TableValues v : TableValues.values()) {
                            if(!checked[v.ordinal()])
                                notFound += v.getAttribute() + " ";
                        }
                        throw new TradeDataException("Required headers not found: " + notFound);
                    }
                    continue;
                }
                if(header == null)
                    throw new TradeDataException("CSV File Headers undefined.");
                for(int col = 0; col < tokens.length; col++) {
                    switch(header[col]) {
                        case SECURITY:
                            checkSecurity(tokens[col], lineNum);
                            break;
                        case DATE:
                            checkDate(tokens[col], lineNum);
                            break;
                        case TIME:
                            checkTime(tokens[col], lineNum);
                            break;
                        case STATUS:
                            checkStatus(tokens[col], lineNum);
                            break;
                        case PRICE:
                            checkPrice(tokens[col], lineNum);
                            break;
                        case VOLUME:
                            checkVolume(tokens[col], lineNum);
                            break;
                        case VALUE:
                            checkValue(tokens[col], lineNum);
                            break;
                        case TRANS:
                            checkTrans(tokens[col], lineNum);
                            break;
                        case BID:
                            checkBid(tokens[col], lineNum);
                            break;
                        case ASK:
                            checkAsk(tokens[col], lineNum);
                            break;
                        case TYPE:
                            checkType(tokens[col], lineNum);
                            break;
                        default:
                            break;

                    }
                }

            }



	}*/

        public enum TableValues {
            SECURITY ("Instrument"), DATE ("Date"), TIME ("Time"),
            STATUS ("Record Type"), PRICE ("Price"), VOLUME ("Volume"),
            VALUE ("Value"), TRANS ("Trans ID"), BID ("Bid ID"), ASK ("Ask ID"),
            TYPE ("Bid/Ask");

            private final String attribute;
            private int column;

            TableValues(String att) { this.attribute = att; this.column = -1; }

            private String getAttribute() { return attribute; }
            public void setColumn(int num) { this.column = num; }
            public int getColumn() { return column; }


        }


}