package com.mysql.jdbc.test;

import org.example.entity.Membership;

import java.lang.reflect.Member;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCSelectTest {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;


        List<Membership> Membership_board = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://127.0.0.1:3306/Membership_board?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";

            conn = DriverManager.getConnection(url, "root", "");

            String sql = "SELECT id, name, age, gender, birth,userID,password";
            sql += " FROM Membership";
            sql += " ORDER BY id DESC";

            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery(sql);


            while (rs.next()) {
                int id = rs.getInt("id");

                String name = rs.getString("name");
                String gender = rs.getString("gender");
                String  userID = rs.getString("userID");
                String password = rs.getString("password");
                int age = rs.getInt("age");
                int birth =rs.getInt("birth");

                Membership membership = new Membership(id, name,age, birth,  gender,  userID, password);
                Membership_board.add(membership);
            }

            System.out.println("결과 : " + Membership_board);

            System.out.println("연결 성공");
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패");
        } catch (SQLException e) {
            System.out.println("에러: " + e);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
