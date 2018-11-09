import java.util.Scanner;

public class Data_Store {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		Csv_Parser csv_Parser = new Csv_Parser();
		Scanner sc = new Scanner(System.in);
		
		try{
			System.out.println("Enter File Path with File Name : ");
			String fileName = sc.nextLine();
			
			System.out.println("Enter no of lines to skip : ");
			int skipLine = Integer.parseInt(sc.nextLine());
			
			System.out.println("Data insertion in progress please wait...\n\n");
			
			long startTime = System.currentTimeMillis();
			
			String result  = csv_Parser.ReadFile(fileName,skipLine);
						
			long endTime = System.currentTimeMillis();
			
			float ddd = ((endTime-startTime)/60000);
			
			if("SUCCESS".equalsIgnoreCase(result)) {
				
				System.out.println("Data inserted Successfully.");

				System.out.println("Approx "+ddd+" Mins taken to insert data.");
				
			}else if("ERROR".equalsIgnoreCase(result)) {
				System.out.println("Something wrong happens..");
			}

		}catch(Exception e){ 
			System.out.println(e);
			}  
	}
}  

