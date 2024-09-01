package com.selenium.demo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class SeleniumDemo {

    public class GetSelector {

        public static void main(String[] args) {
            WebDriver driver = new FirefoxDriver();
            SeleniumDemo demo = new SeleniumDemo();

            try {
                GetSelector getSelector = demo.new GetSelector();
                driver.get("https://www.abv.bg");
//            String page = driver.findElement(By.id("loginForm")).getAttribute("outerHTML");

                String username = "<p><input id=\"username\" name=\"username\" type=\"text\" class=\"abv-LoginField\" maxlength=\"128\"></p>";
                String password = "<label style=\"position: relative; height: 29px; width: 100%; display: inline-block;\"><input id=\"password\" name=\"password\" data-min-chars=\"6\" type=\"password\" maxlength=\"30\"><img src=\"https://img.abv.bg/images/shown.svg?1724228509751\" style=\"position:absolute;top:1.5px;cursor:pointer;right:14.453125px;\" alt=\"Покажи паролата\" width=\"26\" height=\"26\"></label>";
                String loginBtn = "<p class=\"x\"><input id=\"loginBut\" type=\"submit\" value=\"Вход\" class=\"fl\"> <a href=\"https://passport.abv.bg/app/profiles/lostpass\" class=\"abv-ml10 fl\">Забравена парола</a></p>";

                String usernameSelector = getSelector.getElementSelectorFromChatGPTByKeyWord(username, "username");
                driver.findElement(By.xpath(usernameSelector)).sendKeys("aishleme15");

                String passwordSelector = getSelector.getElementSelectorFromChatGPTByKeyWord(password, "password");
                driver.findElement(By.xpath(passwordSelector)).sendKeys("Wimgrenade1!");

                String selector = getSelector.getElementSelectorFromChatGPT(loginBtn, "Вход");
                driver.findElement(By.xpath(selector)).click();

            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                driver.quit();
            }
        }
        private String getElementSelectorFromChatGPT(String pageSource, String text) throws IOException, InterruptedException {
            String escapedHtmlContent = escapeJsonString(pageSource);

            // Construct the prompt
            String prompt = "Given the following HTML: " + escapedHtmlContent +
                    " Find a xpath selector for the element that contains the text " + text + ". Response only with the selector without any escapes, new lines etc.";

            String elementSelector = ChatGptApi.makeChatGptRequestAndGetAnswer(prompt);

            System.out.println("This is the locator we are gone use: " + elementSelector);

            return elementSelector;
        }

        private String getElementSelectorFromChatGPTByKeyWord(String pageSource, String text) throws IOException, InterruptedException {
            String escapedHtmlContent = escapeJsonString(pageSource);

            // Construct the prompt
            String prompt = "Given the following HTML: " + escapedHtmlContent +
                    " Find a xpath selector for the element that contains keyword " + text + ". Response only with the selector without any escapes, new lines etc.";

            String elementSelector = ChatGptApi.makeChatGptRequestAndGetAnswer(prompt);

            System.out.println("This is the locator we are gone use: " + elementSelector);

            return elementSelector;
        }

        // escape special characters in the JSON string
        private static String escapeJsonString(String str) {
            StringBuilder escapedString = new StringBuilder();
            for (char c : str.toCharArray()) {
                switch (c) {
                    case '\"':
                        escapedString.append("\\\"");
                        break;
                    case '\\':
                        escapedString.append("\\\\");
                        break;
                    case '\b':
                        escapedString.append("\\b");
                        break;
                    case '\f':
                        escapedString.append("\\f");
                        break;
                    case '\n':
                        escapedString.append("\\n");
                        break;
                    case '\r':
                        escapedString.append("\\r");
                        break;
                    case '\t':
                        escapedString.append("\\t");
                        break;
                    default:
                        if (c < 0x20) {
                            escapedString.append(String.format("\\u%04x", (int) c));
                        } else {
                            escapedString.append(c);
                        }
                        break;
                }
            }
            return escapedString.toString();
        }
    }
}
