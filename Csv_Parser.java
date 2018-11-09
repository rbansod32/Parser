import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Csv_Parser {
	
	Connection con = null;
	Statement stmt = null;
	
	public String ReadFile(String fileName,int skipLine) throws IOException, SQLException {
		
		con = DatabaseConnection.getConnection();
		stmt = con.createStatement();
		
		BufferedReader br = null;
		FileReader fr = null;
		
		String sql= "";
		String line = "";
		String [] lineSplit = null;
		int iterator = 0;
		int counter = 0;
		
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			
			while ((line = br.readLine()) != null) {
				
				br.lines();
				
				if(iterator >= skipLine) {
					counter++;

					sql = "INSERT INTO CSV_STORE_TABLE VALUES (";
					
					lineSplit = line.split("\\,");
									
					for(int i=0; i<lineSplit.length; i++) {
						sql += "'"+lineSplit[i]+"',";
					}

					if(lineSplit.length < 9) {
						for (int j=0; j<9-lineSplit.length;j++) {
							sql += "'',";
						}
					}
					
					sql = sql.substring(0,sql.lastIndexOf(","));
							
					sql += ")";
										
					stmt.addBatch(sql);
							
					sql = "";

					if(counter%1000 == 0) {
						stmt.executeBatch();
						stmt.clearBatch();
					}
					
				}else {
					iterator++;
				}
			}

			stmt.executeBatch();
			
			return "SUCCESS";
			
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR";
		} catch (Throwable th) {
			System.out.println(th);
		}finally {
			if(br != null) {
				br.close();
			}
			
			if(fr != null) {
				fr.close();
			}
		}
		
		return "ERROR";
	}
}