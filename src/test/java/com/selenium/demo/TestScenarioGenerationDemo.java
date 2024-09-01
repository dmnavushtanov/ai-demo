package com.selenium.demo;

import java.io.IOException;

public class TestScenarioGenerationDemo {

    public static void main(String[] args) throws IOException, InterruptedException {
        String workflows =
                "Given my bank account is positive " +
                "And I made no withdrawals recently " +
                "When I attempt to withdraw an amount less than my card’s limit " +
                "Then the withdrawal should be complete without errors or warnings";

        String prompt = "I have those customer workflows " + workflows + " . " +
                "Can you generate me 2 general and 1 corner cases test scenarios. No comments and escaping , each step should be on new line , scenarios separated by new line";

        String scenarios = ChatGptApi.makeChatGptRequestAndGetAnswer(prompt);

        System.out.println(scenarios);
    }




}
