package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;


@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet {
	private static final String API_KEY = "a43a61cfe2a33cfe595e67f280d7938d";  // ここにAPIキーを設定
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String DB_USER = "your_username";
    private static final String DB_PASSWORD = "your_password";
       
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();

        String title = request.getParameter("title"); // フォームからタイトルを取得

        if (title == null || title.trim().isEmpty()) {
            out.println("{\"error\":\"タイトルを入力してください\"}");
            return;
        }

        try {
            // APIから書籍情報を取得
            String jsonResponse = fetchBookInfo(title);
            if (jsonResponse == null) {
                out.println("{\"error\":\"APIからデータを取得できませんでした\"}");
                return;
            }

            // JSONを解析してISBNを取得
            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONArray booksArray = jsonObject.getJSONArray("books");

            if (booksArray.length() > 0) {
                JSONObject bookData = booksArray.getJSONObject(0); // 最初の書籍情報を取得
                String isbn = bookData.getString("isbn");
                String writer = bookData.optString("author", "不明"); // 著者のフィールドを「writer」に変更
                String description = bookData.optString("description", "説明なし");

                // 書籍情報をデータベースに保存
                boolean isSaved = saveBookToDB(title, writer, description, isbn);
                if (isSaved) {
                    out.println("{\"success\":\"書籍情報を保存しました\"}");
                } else {
                    out.println("{\"error\":\"データベースに保存できませんでした\"}");
                }
            } else {
                out.println("{\"error\":\"書籍が見つかりませんでした\"}");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.println("{\"error\":\"内部エラーが発生しました\"}");
        }
    }

    // **カーリルAPIから書籍情報を取得**
    private String fetchBookInfo(String title) {
        try {
            String apiUrl = "https://api.calil.jp/book?appkey=" + API_KEY + "&title=" + title + "&format=json";
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();
            conn.disconnect();

            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // **データベースに書籍情報を保存**
    private boolean saveBookToDB(String title, String writer, String description, String isbn) {
        String sql = "INSERT INTO books (title, writer, description, isbn) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, title);
            pstmt.setString(2, writer); // author → writerに変更
            pstmt.setString(3, description);
            pstmt.setString(4, isbn);

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
