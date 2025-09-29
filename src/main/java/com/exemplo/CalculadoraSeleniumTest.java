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
        // System.setProperty("webdriver.chrome.driver",
        // "C:\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        try {
            // O Path mudda para cada um
            File file = new File("src/main/resources/webapp/index.html");
            driver.get(file.toURI().toString());

            // Testes das operações
            testarOperacao(driver, "10", "5", "soma", "Resultado: 15");
            testarOperacao(driver, "10", "5", "sub", "Resultado: 5");
            testarOperacao(driver, "10", "5", "mult", "Resultado: 20");
            testarOperacao(driver, "10", "5", "div", "Resultado: 2");
            testarOperacao(driver, "2", "2", "pot", "Resultado: 4");
            testarOperacao(driver, "0", "5", "mult", "Resultado: 0");

        } finally {
            driver.quit();
        }
    }

    private static void testarOperacao(WebDriver driver, String val1, String val2, String op,
            String resultadoEsperado) {
        // Preencher valores
        driver.findElement(By.name("val1")).clear();
        driver.findElement(By.name("val1")).sendKeys(val1);
        driver.findElement(By.name("val2")).clear();
        driver.findElement(By.name("val2")).sendKeys(val2);

        // Selecionar operação
        WebElement selectOp = driver.findElement(By.name("op"));
        Select select = new Select(selectOp);
        select.selectByValue(op);

        // Clicar no botão
        driver.findElement(By.tagName("button")).click();

        // Esperar o resultado aparecer
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement resultado = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("resultado")));

        // Validar resultado
        if (resultado.getText().equals(resultadoEsperado)) {
            System.out.println("Teste " + op + " passou!");
        } else {
            System.out.println("Teste " + op + " falhou! Resultado esperado: " + resultadoEsperado + ", obtido: "
                    + resultado.getText());
        }
    }
}
