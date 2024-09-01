package com.apidevelopment.demo;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;

@Controller("/accounts")
public class AccountController {
    private Map<String, Account> accounts = new HashMap<>();

    public AccountController() {
        accounts.put("gosho.pesho@abv.com", new Account("Gosho", "Pesho", "gosho.pesho@abv.com", "5555-1111", "Panagyrushte"));
        accounts.put("gosho2.pesho@abv.com", new Account("Gosho2", "Pesho", "gosho2.pesho@abv.com", "5555-1111", "Panagyrushte2"));
        accounts.put("gosho3.pesho@abv.com", new Account("Gosho3", "Pesho", "gosho3.pesho@abv.com", "5555-1111", "Panagyrushte3"));
        accounts.put("gosho4.pesho@abv.com", new Account("Gosho4", "Pesho", "gosho4.pesho@abv.com", "5555-1111", "Panagyrushte4"));
        accounts.put("gosho5.pesho@abv.com", new Account("Gosho5", "Pesho", "gosho5.pesho@abv.com", "5555-1111", "Panagyrushte5"));
    }


    @Get("/{email}")
    public HttpResponse<Map<String, Object>> getAccountByEmail(String email) {
        Account account = accounts.get(email);
        Map<String, Object> response = new HashMap<>();
        if (account != null) {
            response.put("message", "Account with email " + email + " found!");
            response.put("account", account);
            return HttpResponse.ok().body(response);
        } else {
            response.put("message", "Account with " + email + " not found");
            return HttpResponse.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @Delete("/{email}")
    public HttpResponse<Void> deleteAccountByEmail(String email) {
        Account removedAccount = accounts.remove(email);
        if (removedAccount != null) {
            return HttpResponse.noContent();
        } else {
            return HttpResponse.notFound();
        }
    }

/*    @Post("/create")
    public HttpResponse<Map<String, Object>> createAccount(@Valid Account account) {


    }*/
}
