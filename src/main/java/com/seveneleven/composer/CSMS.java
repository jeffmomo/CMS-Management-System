package com.seveneleven.composer;

/**
 * 
 * @author Frensky
 *
 */
public class CSMS {

	 private final String strTo;
	 private final String strMsg;
	 
	 public CSMS(String StrTo, String StrMsg) {
		   
	     this.strMsg = StrMsg;
	     this.strTo = StrTo;
	    
	 }

    /**
     * @return the strTo
     */
	 public String getTo(){
        return this.strTo;
    }

    /**
     * @return the strMsg
     */
    public String getMsg() {
        return this.strMsg;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Msg : ");
        sb.append(this.getMsg());
        sb.append("\n");
        
        sb.append("To : ");    
        sb.append(strTo);
        sb.append("\n");
        
        return sb.toString();
	        
	   }
}
