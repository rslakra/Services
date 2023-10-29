package com.rslakra.healthcare.routinecheckup.service.impl.mail;

import com.rslakra.healthcare.routinecheckup.entity.UserEntity;
import com.rslakra.healthcare.routinecheckup.service.mail.MailComponent;
import com.rslakra.healthcare.routinecheckup.service.mail.RegistrationNotificationService;
import com.rslakra.healthcare.routinecheckup.service.security.TokenComponent;
import com.rslakra.healthcare.routinecheckup.utils.components.holder.MailMessages;
import com.rslakra.healthcare.routinecheckup.utils.components.holder.WebConstants;
import com.rslakra.healthcare.routinecheckup.utils.constants.ViewNames;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:21 PM
 */
@Service
@RequiredArgsConstructor
public class RegistrationNotificationServiceImpl
    implements RegistrationNotificationService {

    private final TokenComponent tokenComponent;

    private final MailComponent mailComponent;

    private final WebConstants webConstants;

    private final MailMessages mailMessages;

    @Override
    public String sendRegistrationEmail(UserEntity userEntity) {
        String token
            = tokenComponent.generateRegistrationToken(userEntity);
        String baseUrlPattern = "https://%s:%d%s";
        String baseUrl = String.format(
            baseUrlPattern,
            webConstants.getDomainName(),
            webConstants.getAppPort(),
            webConstants.getBasePath()
        );
        String url = baseUrl + ViewNames.REGISTRATION_URL + "/" + token;

        String messageBodyTemp
            = mailMessages.getCompletionMessageBodyTemplate();
        String messageBody = String.format(messageBodyTemp, url);

        String mail = userEntity.getMail();
        mailComponent.sendMessage(
            mail,
            mailMessages.getCompletionMessageSubject(),
            messageBody
        );

        return token;
    }
}
