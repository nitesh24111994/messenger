package org.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.javabrains.messenger.database.DatabaseClass;
import org.javabrains.messenger.exception.DataNotFoundException;
import org.javabrains.messenger.model.Message;

public class MessageService {
	
	Map<Long,Message> messages = DatabaseClass.getMessages();
	
	public MessageService() {
		messages.put(1L,new Message(1L,"Hello World","Nitesh"));
		messages.put(2L,new Message(2L,"Welcome Jersey","Koushik"));
	}
    
	public List<Message> getAllMessages(){
		return new ArrayList<Message> (messages.values());
	}
	
	public Message getMessage(long id) {
		Message message = messages.get(id);
		if(message==null)
			throw new DataNotFoundException("Message with id "+id+" not found");
		return message;
	}
	
	public List<Message> getAllMessagesForYear(int year){
		List<Message> messagesForYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for(Message message:messages.values()) {
		    cal.setTime(message.getCreated());
		    if(cal.get(Calendar.YEAR)==year)
		    	messagesForYear.add(message);
		}
		return messagesForYear;
	}
	
	public List<Message> getAllMessagesPaginated(int start,int size){
		List<Message> list = new ArrayList<>();
		if(start+size > list.size())
			return new ArrayList<Message>();
		return list.subList(start,start+size);
	}
	
	public Message addMessage(Message message) {
		message.setId(messages.size()+1);
		messages.put(message.getId(),message);
		return message;
	}
	
	public Message updateMessage(Message message) {
		if(message.getId()<=0)
			return null;
		messages.put(message.getId(),message);
		return message;
	}
	
	public void removeMessage(long id) {
		 messages.remove(id);
	}
}
