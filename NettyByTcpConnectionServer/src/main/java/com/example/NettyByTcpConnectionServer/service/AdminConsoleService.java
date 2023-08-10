package com.example.NettyByTcpConnectionServer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
//
//import java.util.Scanner;
//
////@Service
////@RequiredArgsConstructor
//public class AdminConsoleService implements Runnable{
////    private final ChatService chatService;
//
//    public void start() {
//        new Thread(this).start();
//    }
//
//    @Override
//    public void run() {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("관리자 채팅이 시작되었습니다. 응답을 입력하세요.");
//
//        while (true) {
//            System.out.println("채팅을 시작할 클라이언트 포트를 선택하세요.");
//            chatService.listClients(); // 현재 연결된 클라이언트 목록 출력
//            int port = sc.nextInt();
//            sc.nextLine(); // 줄 바꿈 처리
//
//            System.out.println("클라이언트 " + port + "와 채팅을 시작합니다. '/exit'를 입력하면 채팅을 종료합니다.");
//            while (true) {
//                String adminMessage = sc.nextLine();
//                if ("/exit".equalsIgnoreCase(adminMessage)) {
//                    System.out.println("채팅을 종료합니다.");
//                    break;
//                }
//                chatService.sendRequestToClient(adminMessage);
//            }
//        }
//    }
//}
