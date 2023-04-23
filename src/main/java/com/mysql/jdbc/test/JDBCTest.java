package com.mysql.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCTest {
    public static void main(String[] args) {
        Connection conn = null;

        try {
            // 드라이버 클래스를 로드합니다.
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 데이터베이스 연결 URL을 생성합니다.
            String url = "jdbc:mysql://127.0.0.1:3306/Menbership_board?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";

            // 데이터베이스 연결을 수행합니다.
            conn = DriverManager.getConnection(url, "root", "");

            System.out.println("연결 성공");
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패");
        } catch (SQLException e) {
            System.out.println("에러: " + e.getMessage());
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
