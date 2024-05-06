package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import org.example.utils.ReadUtils;
import org.example.service.HashServiceImpl;
import org.example.controller.HashController;
import org.example.controller.dto.HashDto;
import org.example.repository.HashRepositoryImpl;
import org.example.repository.HashRepository;
import org.example.exception.EntityNotFoundException;
import org.example.jdbc.JdbcUtils;

import java.sql.Connection;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        while (true) {
            boolean connection = JdbcUtils.createConnection();
            HashController hashController = new HashController(new HashServiceImpl(new HashRepositoryImpl()));
            printMenu();
            String chosenService = ReadUtils.readLine();
            if (Objects.equals(chosenService, "3")) {
                break;
            }
            if (Objects.equals(chosenService, "1")) {
                System.out.println("Введите длинную ссылку:");
                String longURL = ReadUtils.readLine();
                String shortURL = hashController.addHash(new HashDto(longURL));
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
        System.out.println("Сервис сокращения ссылок!\n\nВыберите действие:\n\n1. Сократить ссылку\n2. " +
                "Получить полную ссылку по короткой\n3. Выйти\n\n");
    }
}
