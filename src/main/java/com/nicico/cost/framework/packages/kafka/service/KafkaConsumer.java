package com.nicico.cost.framework.packages.kafka.service;

import com.nicico.cost.framework.domain.dto.BaseDTO;

import java.util.List;

/**
 * @author nima
 * @version 1.0.1
 */
public interface KafkaConsumer {

    List<Object> kafkaMessages();

    BaseDTO<Boolean> removeAll();

    Object getLastMessage();

    <R> R getLastMessage(Class<R> tClass);

    Object getFirstMessage();

    <R> R getFirstMessage(Class<R> tClass);

    Object getAndRemoveFirstMessage();

    <R> R getAndRemoveFirstMessage(Class<R> tClass);

    Object getAndRemoveLastMessage();

    <R> R getAndRemoveLastMessage(Class<R> tClass);

    int messagesList();

    Object getMessage(int index);

    <R> R getMessage(int index, Class<R> tClass);
}
