package com.awstraining.backend.business.notifyme.adapter;

import com.awstraining.backend.business.notifyme.MessageSender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class MessageSnsAWSSender implements MessageSender {

    private static final Logger LOGGER = LogManager.getLogger(MessageSnsAWSSender.class);

    private AmazonSNS mAmazonSNS;

    private String topicArn;
    // TODO: lab1
    //  1. Inject AWS AmazonsSNS from configuration SNSConfig.
    //  2. Make sure that you created new value in parameter store with arn of sns topic.
    //  3. Inject parameter with @Value annotation through constructor.
    @Autowired
    public MessageSnsAWSSender(AmazonSNS amazonSNS, @Value("${notification.topicarn}") String topicArn) {
            this.mAmazonSNS = amazonSNS;
            this.topicArn = topicArn;
    }

    @Override
    public void send(String text) {
        // TODO: lab1
        //  1. Create publish request.
        //  2. Publish request with sns.
        //  3. Log information about successful sent message to topic with topic arn and message id.

        final PublishRequest publishRequest = new PublishRequest(topicArn, text);
        final PublishResult publish = mAmazonSNS.publish(publishRequest);
        LOGGER.info("Message was sent to topic '{}' with id '{}'.", topicArn, publish.getMessageId());
    }
}
