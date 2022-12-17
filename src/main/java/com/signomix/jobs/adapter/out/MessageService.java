package com.signomix.jobs.adapter.out;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.signomix.common.EventEnvelope;
import com.signomix.common.MessageEnvelope;
//import com.signomix.common.event.IotEvent;

import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class MessageService {

    private static final Logger LOG = Logger.getLogger(MessageService.class);

    @Channel("events")
    Emitter<byte[]> eventEmitter;

    @Channel("notifications")
    Emitter<byte[]> iotEventEmitter;

    @Channel("admin_email")
    Emitter<byte[]> adminEmailEmitter;

    public void onApplicationStart(@Observes StartupEvent event) {
    }

    public void sendEvent(EventEnvelope wrapper) {
        LOG.info("sending event to MQ");
        String encodedMessage;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            encodedMessage = objectMapper.writeValueAsString(wrapper);
            eventEmitter.send(encodedMessage.getBytes());
        } catch (JsonProcessingException ex) {
            LOG.error(ex.getMessage());
        }
    }

    public void sendAdminEmail(MessageEnvelope wrapper) {
        LOG.info("sending admin e-mail to MQ");
        String encodedMessage;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            encodedMessage = objectMapper.writeValueAsString(wrapper);
            eventEmitter.send(encodedMessage.getBytes());
        } catch (JsonProcessingException ex) {
            LOG.error(ex.getMessage());
        }
    }

    /*
    public void sendNotification(IotEvent event) {
        LOG.info("sending notification to MQ, origin:" + event.getOrigin());

        String[] origin = event.getOrigin().split("\t");
        User user = new User();
        user.uid = origin[0];

        MessageEnvelope wrapper = new MessageEnvelope();
        wrapper.type = event.getType();
        wrapper.eui = origin[1];
        wrapper.message = (String) event.getPayload();
        wrapper.user = user;

        String encodedMessage;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            encodedMessage = objectMapper.writeValueAsString(wrapper);
            iotEventEmitter.send(encodedMessage.getBytes());
        } catch (JsonProcessingException ex) {
            LOG.error(ex.getMessage());
        }
    }

    public void sendData(IotEvent event) {
        LOG.info("sending data to MQ");
    }

    public void sendCommand(IotEvent event) {
        LOG.info("sending command to MQ");
        String[] origin = event.getOrigin().split("\t");
        User user = new User();
        user.uid = origin[0];
        MessageEnvelope wrapper = new MessageEnvelope();
        wrapper.type = event.getType();
        wrapper.eui = origin[1];
        wrapper.message = (String) event.getPayload();
        wrapper.user = user;
        String encodedMessage;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            encodedMessage = objectMapper.writeValueAsString(event);
            eventEmitter.send(encodedMessage.getBytes());
        } catch (JsonProcessingException ex) {
            LOG.error(ex.getMessage());
        }
    }
    */
}
