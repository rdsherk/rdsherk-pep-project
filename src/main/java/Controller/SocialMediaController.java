package Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import Model.Account;
import Model.Message;
import io.javalin.Javalin;
import io.javalin.http.Context;
import Service.*;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;
    public SocialMediaController(){
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::postAccount);
        app.post("/login", this::login);
        app.post("/messages", this::postMessage);
        app.get("/messages", this::getAllMessages);
        app.get("/messages/{message_id}", this::getMessageByID);
        app.delete("/messages/{message_id}", this::deleteMessage);
        app.patch("/messages/{message_id}", this::updateMessage);
        app.get("/accounts/{account_id}", this::getMessagesByUser);

        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void postAccount(Context context)  throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);
        Account newAccount = accountService.createAccount(account.getUsername(), account.getPassword());
        if (newAccount == null) {
            context.status(400);
        }
        context.json(mapper.writeValueAsString(newAccount));
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void login(Context context)  throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);
        Account newAccount = accountService.login(account);
        if (newAccount == null) {
            context.status(401);
        }
        context.json(mapper.writeValueAsString(newAccount));
        context.status(200);
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void postMessage(Context context)  throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(), Message.class);
        Message newMessage = messageService.addMessage(message);
        if (newMessage == null) {
            context.status(400);
        }
        context.json(mapper.writeValueAsString(newMessage));
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void getAllMessages(Context context)  throws JsonProcessingException {
        context.json(messageService.getAllMessages());
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void getMessageByID(Context context)  throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message newMessage = messageService.getMessage(Integer.parseInt(context.pathParam("message_id")));
        context.json(mapper.writeValueAsString(newMessage));
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void deleteMessage(Context context)  throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = messageService.deleteMessage(Integer.parseInt(context.pathParam("message_id")));
        context.json(mapper.writeValueAsString(message));
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void updateMessage(Context context)  throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String message_text = mapper.readValue(context.body(), String.class);
        Message message = messageService.updateMessage(Integer.parseInt(context.pathParam("message_id")), message_text);
        if (message == null) {
            context.status(400);
        }
        context.json(mapper.writeValueAsString(message));
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void getMessagesByUser(Context context)  throws JsonProcessingException {
        context.json(messageService.getMessagesByUser(Integer.parseInt(context.pathParam("account_id"))));
    }



}