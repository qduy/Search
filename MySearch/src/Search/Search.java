package Search;

import Login.LoginResp;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Search {
    public static void main(String[] args) throws Exception {
        case11();
    }
    public static SearchResp Search(String token, String keyword, String user_id, int index, int count) throws IOException {
        URL url = new URL(Constant.Search + "?token=" + token + "&keyword=" + keyword + "&user_id" + user_id
                + "&index" + index + "&count" + count);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        try {
            StringBuilder content;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                content = new StringBuilder();
                while ((line = in.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }
            String java_string_content = content.toString();
            System.out.println(java_string_content);
            Gson g = new Gson();

            return g.fromJson(java_string_content, SearchResp.class);
        }
        finally {
            connection.disconnect();
        }
    }

    public static LoginResp getInfoFromServer(String phonenumber, String password) throws IOException {
        URL url = new URL(Constant.LOG_IN + "?phonenumber=" + phonenumber + "&password=" + password);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        try {
            StringBuilder content;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                content = new StringBuilder();
                while ((line = in.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }
            String java_string_content = content.toString();
            System.out.println(java_string_content);
            Gson g = new Gson();

            return g.fromJson(java_string_content, LoginResp.class);
        }
        finally {
            connection.disconnect();
        }
    }

    public static void case1() throws IOException {
        System.out.println("Case 1: Token is correct");
        LoginResp loginResp = getInfoFromServer("0332416592", "123456s");
        SearchResp searchResp = Search(loginResp.data.token, "funny", "20187270", 29, 1);
        try {
            assert "1000".equals(loginResp.data.token) : "Fail";
            System.out.println("OK");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
    public static void case2() throws IOException {
        System.out.println("Case 2: Token is invalid");
        LoginResp loginResp = getInfoFromServer("0332416592", "123456s");
        SearchResp searchResp = Search(loginResp.data.token, "funny", "20187270", 29, 1);
        try {
            assert "9998".equals(loginResp.data.token) : "OK";
            System.out.println("token is invalid");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
    public static void case3() throws IOException {
        System.out.println("Case 3: Token is correct but none is returned");
        LoginResp loginResp = getInfoFromServer("0332416592", "123456s");
        SearchResp searchResp = Search(loginResp.data.token, "funny", "20187270", 29, 1);
        try {
            assert "9992".equals(loginResp.code) : "OK";
            System.out.println("Post is not existed");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
    public static void case4() throws IOException {
        System.out.println("Case 4: Token and parameters is valid but user is locked");
        LoginResp loginResp = getInfoFromServer("0332416592", "123456s");
        SearchResp searchResp = Search(loginResp.data.token, "funny", "20187270", 29, 1);
        try {
            assert "9995".equals(loginResp.code) : "OK";
            System.out.println("User is not validated");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
    public static void case5() throws IOException {
        System.out.println("Case 5: Token is valid but user_id is invalid");
        LoginResp loginResp = getInfoFromServer("0332416592", "123456s");
        SearchResp searchResp = Search(loginResp.data.token, "funny", "20187270", 29, 1);
        try {
            assert "1000".equals(loginResp.code) : "Ok";
            System.out.println("You have not enter phone number and password");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
    public static void case6() throws IOException {
        System.out.println("Case 6: Parameters are valid but keyword is null");
        LoginResp loginResp = getInfoFromServer("0332416592", "123456s");
        SearchResp searchResp = Search(loginResp.data.token, "funny", "20187270", 29, 1);
        try {
            assert "1000".equals(loginResp.code) : "OK";
            System.out.println("Parameters are valid but keyword is null");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
    public static void case7() throws IOException {
        System.out.println("Case 7: Token and parameters are valid but author's id returned is invalid");
        LoginResp loginResp = getInfoFromServer("0332416592", "123456s");
        SearchResp searchResp = Search(loginResp.data.token, "funny", "20187270", 29, 1);
        try {
            assert "1000".equals(loginResp.code) : "OK";
            System.out.println("Token and parameters are valid but author's id data which returned is failed");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }

    public static void case8() throws IOException{
        System.out.println("Case 8: Token and parameters is valid but the returned data has the described field and the media is faulty");
        LoginResp loginResp = getInfoFromServer("0332416592", "123456s");
        SearchResp searchResp = Search(loginResp.data.token, "funny", "20187270", 29, 1);
        try {
            assert " ".equals(loginResp.code) : "OK";
            System.out.println(" ");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }

    public static void case9() throws IOException {
        System.out.println("Case 9: Token and parameters is valid But the returned data has one of the described fields and the media field is missing.");
        LoginResp loginResp = getInfoFromServer("0332416592", "123456s");
        SearchResp searchResp = Search(loginResp.data.token, "funny", "20187270", 29, 1);
        try {
            assert " ".equals(loginResp.code) : "OK";
            System.out.println(" ");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }

    public static void case10() throws IOException{
        System.out.println("Case 10: Token and parameters is valid");
        LoginResp loginResp = getInfoFromServer("0332416592", "123456s");
        SearchResp searchResp = Search(loginResp.data.token, "funny", "20187270", 29, 1);
    }

    public static void case11() throws IOException{
        System.out.println("Case 11: Token and parameters is valid but disconnected internet");
        LoginResp loginResp = getInfoFromServer("0332416592", "123456s");
        SearchResp searchResp = Search(loginResp.data.token, "funny", "20187270", 29, 1);
        try {
            assert "1009".equals(loginResp.code): "OK";
            System.out.println("Not access");
        }catch (AssertionError e){
            e.printStackTrace();
        }
    }

    public static void case12() throws IOException{
        System.out.println("Case 12: Token and parameters is valid");
        LoginResp loginResp = getInfoFromServer("0332416592", "123456s");
        SearchResp searchResp = Search(loginResp.data.token, "funny", "20187270", 29, 1);
    }

    public static void case13() throws IOException{
        System.out.println("Case 10: Token and parameters is valid, new posts appear matching search results");
        LoginResp loginResp = getInfoFromServer("0332416592", "123456s");
        SearchResp searchResp = Search(loginResp.data.token, "funny", "20187270", 29, 1);
    }

    public static void case14() throws IOException{
        System.out.println("Case 14: Token and parameters is valid but idex and count are error");
        LoginResp loginResp = getInfoFromServer("0332416592","123456s");
        SearchResp searchResp = Search(loginResp.data.token,"funny","20187270",2,10000);
        try {
            assert "1001".equals(loginResp.code): "OK";
            System.out.println("Can't connect Internet");
        }catch (AssertionError e){
            e.printStackTrace();
        }
    }

}
