package com.seveneleven;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class reportGenerator{
	public reportGenerator(){}
	
	public static void ReadFromFile(String fName)
	{
		try
		{
			BufferedReader Reader = new BufferedReader(new FileReader(fName));
			String s="";
    		while((s=Reader.readLine())!=null)
    		{	
    			System.out.println(s);
    			s += "\n" + s;
    			
  
    		}
    		System.out.println();
    		Reader.close();
    		
    		DBAdaptor.addSituationReport(s);

		}
		catch(Exception e)
		{
			System.out.println("File Reading Error for obstacles!");
		}
	}
	
	public static void WriteToFile(){
		ArrayList<String[]> incidents=new ArrayList<>();
		 ArrayList<String[]> hazards=new ArrayList<>();
		 ArrayList<String[]> reports=new ArrayList<>();		 
		incidents= DBAdaptor.getIncidents();
		
		hazards=DBAdaptor.getHazards();
		
		reports=DBAdaptor.getSituationReports();
		
		 /*incidents.add(new String[]{"id1","description1","location1","datetime1","status"});
		 incidents.add(new String[]{"id2","description2","location2","datetime2","status"});
		 hazards.add(new String[]{"id1","description1","location1","datetime1","status"});
		 hazards.add(new String[]{"id1","description1","location1","datetime1","status"});
		 //reports.add(new String[]{"id1","description1","datetime1",});
		 */
		int count=0,j=0;
		PrintWriter writer = null;
		try
		{
			writer= new PrintWriter("report.txt", "UTF-8");
			writer.println("incidents: ");
			while (count<incidents.size()){
				while(j<5){
					writer.println(incidents.get(count)[j]);
					j++;
				}
				j=0;
				count++;
			}
			count=0;
			writer.println("\n hazards: ");
			while (count<hazards.size()){
				while(j<5){
					writer.println(hazards.get(count)[j]);
					j++;
				}
				j=0;
				count++;
			}
			/*count=0;
			writer.println("\n reports: ");
			while (count<reports.size()){
				while(j<3){
					writer.println(reports.get(count)[j]);
					j++;
				}
				j=0;
				count++;
			}
			*/
			writer.close();

		}
		catch ( IOException e)
		{
			System.out.println("write failed");
		}
		
	}
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		WriteToFile();
		ReadFromFile("report.txt");
	}*/

}