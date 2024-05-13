package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import org.example.controller.dto.UserDto;
import org.example.utils.ReadUtils;
import org.example.service.HashServiceImpl;
import org.example.service.UserServiceImpl;
import org.example.controller.HashController;
import org.example.controller.UserController;
import org.example.controller.dto.HashDto;
import org.example.repository.HashRepositoryImpl;
import org.example.repository.UserRepositoryImpl;
import org.example.repository.HashRepository;
import org.example.exception.EntityNotFoundException;
import org.example.jdbc.JdbcUtils;

import java.sql.Connection;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        boolean ok = false;
        long id = -1;
        while (true) {
            boolean connection = JdbcUtils.createConnection();
            //System.out.printf(String.valueOf(connection));
            HashController hashController = new HashController(new HashServiceImpl(new HashRepositoryImpl()));
            UserController userController = new UserController(new UserServiceImpl(new UserRepositoryImpl()));
            while (!ok) {
                printMenu();
                String chosenService = ReadUtils.readLine();
                if (Objects.equals(chosenService, "3"))
                    break;
                System.out.println("Введите login:");
                String login = ReadUtils.readLine();
                System.out.println("Введите пароль:");
                String password = ReadUtils.readLine();
                if (Objects.equals(chosenService, "1")) {
                    try {
                        id = userController.signIN(new UserDto(login, password));
                        ok = true;
                    }
                    catch (EntityNotFoundException ex) {
                        System.out.printf("Такого пользователя еще не было :(\n");
                    }
                } else if (Objects.equals(chosenService, "2")) {
                    id = userController.logIN(new UserDto(login, password));
                    ok = true;
                }
                else System.out.printf("Мы вас не поняли, повторите запрос :(\n");
            }
            if (!ok) {
                break;
            }
            printMenu1();
            String chosenService = ReadUtils.readLine();
            if (Objects.equals(chosenService, "3")) {
                ok = false;
                continue;
            }
            if (Objects.equals(chosenService, "1")) {
                System.out.println("Введите длинную ссылку:");
                String longURL = ReadUtils.readLine();
                String shortURL = hashController.addHash(new HashDto(longURL), id);
                System.out.printf("Получена короткая ссылка %s%n\n", shortURL);
            }
            else if (Objects.equals(chosenService, "2")) {
                System.out.println("Введите короткую ссылку:");
                String shortURL = ReadUtils.readLine();
                try {
                    String longURL = hashController.findHash(shortURL);
                    System.out.printf("Получена длинная ссылка %s%n\n", longURL);
                } catch (EntityNotFoundException ex) {
                    System.out.printf("Такой короткой ссылки еще не было :(\n");
                }
            }
            else {
                System.out.printf("Мы вас не поняли, повторите запрос :(\n");
            }
        }
    }

    private static void printMenu() {
        System.out.println("Сервис сокращения ссылок!\n\nВыберите действие:\n\n" +
                "1. Войти\n" +
                "2. Зарегистрироваться\n" +
                "3. Выйти из приложения\n"
        );
    }

    private static void printMenu1() {
        System.out.println("\nВыберите действие:\n\n" +
                "1. Сократить длинную ссылку\n" +
                "2. Получить длинную ссылку по короткой\n" +
                "3. Выйти из аккаунта\n"
        );
    }
}