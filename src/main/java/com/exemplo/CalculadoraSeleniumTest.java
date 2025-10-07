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
            Soma(driver, "2", "2", "Resultado: 4");
            Subtracao(driver, "6", "3", "Resultado: 3");
            Multiplicacao(driver, "5", "2", "Resultado: 10");
            Divisao(driver, "8", "2", "Resultado: 4");
            Divisao(driver, "7", "0", "Resultado: Erro: Divisão por zero");
            Multiplicacao(driver, "a", "b", "Resultado: Erro: Entrada inválida");
            Soma(driver, "", "", "Resultado: Erro: Entrada inválida");
            Potencia(driver, "2", "3", "Resultado: 8");
            SubtracaoNegativa(driver, "5", "10", "Resultado: -5");

        } finally {
            driver.quit();
        }
    }

    private static void executarTeste(WebDriver driver, String val1, String val2, String op, String resultadoEsperado) {
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
            System.out.println("Teste " + op + " passou!, obtido: " + resultado.getText());
        } else {
            System.out.println("Teste " + op + " falhou! Resultado esperado: " + resultadoEsperado + ", obtido: "
                    + resultado.getText());
        }
    }

    private static void Soma(WebDriver driver, String val1, String val2, String resultadoEsperado) {
        executarTeste(driver, val1, val2, "soma", resultadoEsperado);
    }

    private static void Subtracao(WebDriver driver, String val1, String val2, String resultadoEsperado) {
        executarTeste(driver, val1, val2, "sub", resultadoEsperado);
    }

    private static void Divisao(WebDriver driver, String val1, String val2, String resultadoEsperado) {
        executarTeste(driver, val1, val2, "mult", resultadoEsperado);
    }

    private static void Multiplicacao(WebDriver driver, String val1, String val2, String resultadoEsperado) {
        executarTeste(driver, val1, val2, "div", resultadoEsperado);
    }

    private static void Potencia(WebDriver driver, String val1, String val2, String resultadoEsperado) {
        executarTeste(driver, val1, val2, "pot", resultadoEsperado);
    }

    private static void SubtracaoNegativa(WebDriver driver, String val1, String val2, String resultadoEsperado) {
        executarTeste(driver, val1, val2, "sub", resultadoEsperado);
    }
}
