package com.rslakra.healthcare.routinecheckup.utils.components.impl.holder;

import com.rslakra.healthcare.routinecheckup.utils.components.holder.MailMessages;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class MailMessagesImpl implements MailMessages {

    @Value("${registration.completion_message.body_template}")
    private String completionMessageBodyTemplate;

    @Value("${registration.completion_message.subject}")
    private String completionMessageSubject;

}
