package org.example;

import org.example.controller.ManagerController;
import org.example.controller.MembershipController;
import org.example.entity.Membership;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class App {

    public void run() {
        System.out.println(" == Lss회원가입 == ");
        System.out.println("1. 회원가입");
        System.out.println("2. 로그인");
        System.out.println("9. 종료");
        System.out.println("0. 관리자모드");

        MembershipController membershipController = new MembershipController();

        while (true) {

            System.out.printf("명령: ");
            String command = Container.getsc().nextLine().trim();

            if (command.equals("종료")) {
                break;
            } else if (command.equals("회원가입")) {
                membershipController.join();
                Connection conn = null;
                PreparedStatement pstmt = null;

                try {

                    Membership membership = membershipController.membership;

                    Class.forName("com.mysql.cj.jdbc.Driver");

                    String url = "jdbc:mysql://127.0.0.1:3306/Membership_board?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";

                    conn = DriverManager.getConnection(url, "root", "");
                    String sql = "INSERT INTO Membership ";
                    sql += "(`name`, age, gender, birth, userID, `password`) ";
                    sql += "VALUES (?, ?, ?, ?, ?, ?)";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, membership.getName());
                    pstmt.setInt(2, membership.getAge());
                    pstmt.setString(3, membership.getGender());
                    pstmt.setInt(4, membership.getBirth());
                    pstmt.setString(5, membership.getUserID());
                    pstmt.setString(6, membership.getPassword());
                    int affectedRows = pstmt.executeUpdate();

                    System.out.println("affectedRows : " + affectedRows);
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

            } else if (command.equals("회원확인")) {
                ManagerController.list();
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
                        String userID = rs.getString("userID");
                        String password = rs.getString("password");
                        int age = rs.getInt("age");
                        int birth = rs.getInt("birth");

                        Membership membership = new Membership(id, name, age, birth, gender, userID, password);
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


            } else if (command.equals("로그인")) {
                membershipController.login();
            } else if (command.equals("도움말")) {
                System.out.println("1. 회원가입");
                System.out.println("2. 로그인");
                System.out.println("9. 종료");
                System.out.println("0. 관리자모드");

            } else {
                System.out.println("명령어를 제대로 입력해주세요.");
                System.out.println("만약 명령어에 대한 정보를 원하시면 \"도움말\"을 입력해주세요.");
            }

        }
    }
}