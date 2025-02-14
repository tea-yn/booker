package API;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class BookAPI {
	 private static final String API_KEY = "a43a61cfe2a33cfe595e67f280d7938d";  // ここにAPIキーを設定
	 	
	 public static String fetchBookInfo(String title) {
	 	try {
	 		// APIのURLを作成
	 		String apiUrl = "https://api.calil.jp/book?appkey=" + API_KEY + "&title=" + title + "&format=json";
	 		URL url = new URL(apiUrl);
	 		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	 		conn.setRequestMethod("GET");
	 		conn.setRequestProperty("Accept", "application/json");
	 		
	 		// レスポンスを取得
	 		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	 		StringBuilder response = new StringBuilder();
	 		String line;
	 		while ((line = in.readLine()) != null) {
	 			response.append(line);
	 		}
	 		in.close();
	 		conn.disconnect();
	 		
	 		return response.toString();  // 取得したJSONデータを返す
	 	} catch (Exception e) {
	 		e.printStackTrace();
	 		return null;
	 	}
	 }
}
