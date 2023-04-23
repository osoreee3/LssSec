package org.example.controller;

import org.example.Container;
import org.example.entity.Membership;

import java.util.ArrayList;


public class MembershipController {

    private int count = 1;

  static ArrayList<Membership> memberships = new ArrayList<>();
    public Membership membership =new Membership(1, "John Doe", 30,1994,"남", "johndoe123", "mypassword");
                                 //`name`, age, gender, birth, userID, `password`
    public void join() {

        System.out.println("이름을 입력해주세요.");
        String name = Container.getsc().nextLine().trim();
        System.out.println("나이를 입력해주세요");
        int age = Container.getsc().nextInt();
        Container.getsc().nextLine();
        System.out.println("생년월일을 입력해주세요.");
        int birth = Container.getsc().nextInt();
        Container.getsc().nextLine();
        System.out.println("성별을 입력해주세요.");
        String gender = Container.getsc().nextLine().trim();
        System.out.println("사용할 ID를 입력해주세요.");
        String userID = Container.getsc().nextLine().trim();
        System.out.println("사용할 비밀번호를 입력해주세요.");
        String password = Container.getsc().nextLine().trim();
        System.out.println("정보를 저장하시겠습니까?, 저장하시려면 저장하기를 입력해주세요.");
        String result = Container.getsc().nextLine().trim();
        System.out.println("정보가 저장되었습니다. 회원가입을 종료합니다.");
        int id = count;

        memberships.add(new Membership(id, name, age, birth, gender, userID, password));
        membership.setInfo(id, name, age, birth, gender, userID, password);
        count++;
    }


    public void login() {
        System.out.println("ID를 입력해주세요.");
        String userID = Container.getsc().nextLine().trim();
        System.out.println("비밀번호를 입력해주세요.");
        String password = Container.getsc().nextLine().trim();
        String membershipUserID = findByID(userID);
        String membershipPassword = findByPS(password);


        if (!(userID.equals(membershipUserID)) && !(password.equals(membershipPassword))) {
            System.out.println("일치하는 회원정보가 존재하지않습니다.");
            System.out.println("아이디 또는 비밀번호를 확인해주세요.");
        } else if (userID.equals(membershipUserID) && password.equals(membershipPassword)) {
            System.out.println("로그인 되었습니다.");
        }


    }

    private String findByID(String userID) {
        for (Membership membership : memberships) {
            if (membership.getUserID().equals(userID)) {
                return membership.getUserID();
            }
        }
        return null;
    }


    private String findByPS(String password) {
        for (Membership membership : memberships) {
            if (membership.getPassword().equals(password)) {
                return membership.getPassword();
            }
        }
        return null;
    }

}