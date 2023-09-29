package Service;

import Model.Message;
import DAO.MessageDAO;
import java.util.List;

public class MessageService {
    
    MessageDAO messageDAO;

    public MessageService() {
        this.messageDAO = new MessageDAO();
    }

    public MessageService(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    public Message addMessage(Message message) {
        return messageDAO.addMessage(message);
    }

    public Message getMessage(int id) {
        return messageDAO.getMessage(id);
    }

    public Message deleteMessage(int id) {
        return messageDAO.deleteMessage(id);
    }

    public Message updateMessage(int id, String message) {
        return messageDAO.updateMessage(id, message);
    }

    public List<Message> getMessagesByUser(int id) {
        return messageDAO.getMessagesByUser(id);
    }
}
