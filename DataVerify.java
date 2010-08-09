
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class DataVerify {

	public void csvReader(File file) throws IOException, TradeDataException {

            BufferedReader read = new BufferedReader( new FileReader(file) );

            String line = null;
            int lineNum = 0;
            TableValues[] header = null;

            while((line = read.readLine()) != null) {
                lineNum++;

                boolean flag = false;
                if(line.startsWith("#")) {
                    flag = true;
                    line = line.substring(1);
                }
                StringTokenizer st = new StringTokenizer(line, ",");

                if(st.countTokens() > TableValues.values().length)
                    throw new TradeDataException("Unknown extra headers found on line " + lineNum);
                if(st.countTokens() < TableValues.values().length)
                        throw new TradeDataException("Insufficient columns of data provided on line " + lineNum);

                if(flag) {
                    header = new TableValues[11];
                    int col = 0;
                    boolean[] checked = new boolean[TableValues.values().length];
                    String token = "";
                    while(st.hasMoreTokens()) {
                        boolean found = false;
                        token = st.nextToken();
                        for(TableValues v : TableValues.values()) {
                            if(v.getAttribute().equalsIgnoreCase(token)) {
                                if(checked[v.ordinal()])
                                    throw new TradeDataException("Unexpected Repeated Header " + token + " on line " + lineNum);
                                header[col] = v;
                                found = true;

                            }
                        }
                        if(!found) {
                            throw new TradeDataException("Unknown Header: " + token + " on line " + lineNum);
                        }
                        col++;
                    }
                    continue;
                }
                if(header == null)
                    throw new TradeDataException("CSV File Headers undefined.");
                int col = 0;
                st.countTokens();
                while(st.hasMoreTokens()) {

                    String token = st.nextToken();

                    switch(header[col]) {
                        case SECURITY:
                            checkSecurity(token, lineNum);
                            break;
                        case DATE:
                            checkDate(token, lineNum);
                            break;
                        case TIME:
                            checkDate(token, lineNum);
                            break;
                        case STATUS:
                            checkStatus(token, lineNum);
                            break;
                        case PRICE:
                            checkPrice(token, lineNum);
                            break;
                        case VOLUME:
                            checkVolume(token, lineNum);
                            break;
                        case VALUE:
                            checkValue(token, lineNum);
                            break;
                        case TRANS:
                            checkTrans(token, lineNum);
                            break;
                        case BID:
                            checkBid(token, lineNum);
                            break;
                        case ASK:
                            checkAsk(token, lineNum);
                            break;
                        case TYPE:
                            checkType(token, lineNum);
                            break;

                    }
                    col++;
                }

            }



	}

        public void checkSecurity(String token, int lineNum) throws TradeDataException {
            if(token.length() == 0)
                throw new TradeDataException("No security provided on line " + lineNum);
        }

        public void checkDate(String token, int lineNum) throws TradeDataException {
            DateFormat df = new SimpleDateFormat("dd-MM-yy");
            try {
                df.parse(token);
            } catch(Exception e) {
                throw new TradeDataException("Unexpected format for DATE on line " + lineNum + ": " + token);
            }
        }

         public void checkTime(String token, int lineNum) throws TradeDataException {
             DateFormat df = null;
             if(token.length() > 8)
                 df = new SimpleDateFormat("HH-mm-ss.SSS");
             else
                 df = new SimpleDateFormat("HH-mm-ss");
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