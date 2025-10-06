package com.exemplo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class CalculadoraSeleniumTest {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        try {
            File file = new File("src/main/resources/webapp/index.html");
            driver.get(file.toURI().toString());

            // Testes das operações
            testarOperacao(driver, "2", "2", "soma", "Resultado: 4");
            testarOperacao(driver, "6", "3", "sub", "Resultado: 3");
            testarOperacao(driver, "5", "2", "mult", "Resultado: 10");
            testarOperacao(driver, "8", "2", "div", "Resultado: 4");
            testarOperacao(driver, "7", "0", "div", "Resultado: Erro: Divisão por zero");
            testarOperacao(driver, "a", "b", "mult", "Resultado: Erro: Entrada inválida");
            testarOperacao(driver, "", "", "soma", "Resultado: Erro: Entrada inválida");

        } finally {
            driver.quit();
        }
    }

    private static void testarOperacao(WebDriver driver, String val1, String val2, String op,
            String resultadoEsperado) {
        // Valores
        driver.findElement(By.name("val1")).clear();
        driver.findElement(By.name("val1")).sendKeys(val1);
        driver.findElement(By.name("val2")).clear();
        driver.findElement(By.name("val2")).sendKeys(val2);

        WebElement selectOp = driver.findElement(By.name("op"));
        Select select = new Select(selectOp);
        select.selectByValue(op);

        driver.findElement(By.tagName("button")).click();

        // Resultado
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement resultado = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("resultado")));

        if (resultado.getText().equals(resultadoEsperado)) {
            System.out.println("Teste " + op + " passou!, obtido: "+ resultado.getText());
        } else {
            System.out.println("Teste " + op + " falhou! Resultado esperado: " + resultadoEsperado + ", obtido: "
                    + resultado.getText());
        }
    }
}
