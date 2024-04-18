package main.java.org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import main.java.org.example.utils.ReadUtils;
import main.java.org.example.service.HashServiceImpl;
import main.java.org.example.controller.HashController;
import main.java.org.example.controller.dto.HashDto;
import main.java.org.example.repository.HashRepositoryImpl;
import main.java.org.example.exception.EntityNotFoundException;

import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        while (true) {
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
        System.out.println("Сервис сокращения ссылок\n\nВыберите действие:\n\n1. Сократить ссылку\n2. " +
                "Получить полную ссылку по короткой\n3. Выйти\n\n");
    }
}
