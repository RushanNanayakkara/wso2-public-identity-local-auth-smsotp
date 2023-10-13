/*
 * Copyright (c) 2023, WSO2 LLC. (http://www.wso2.com). All Rights Reserved.
 *
 * This software is the property of WSO2 LLC. and its suppliers, if any.
 * Dissemination of any information or reproduction of any material contained
 * herein in any form is strictly forbidden, unless permitted by WSO2 expressly.
 * You may not alter or remove any copyright or other notice from copies of this content.
 *
 */

package org.wso2.carbon.identity.local.auth.smsotp.provider.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.local.auth.smsotp.provider.model.SMSData;
import org.wso2.carbon.identity.local.auth.smsotp.provider.model.SMSMetadata;
import org.wso2.carbon.identity.local.auth.smsotp.provider.exception.PublisherException;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * This class will be used to publish the SMS to the custom SMS provider using the HTTP protocol.
 *
 * @version 1.0.0
 * @since 1.0.0
 */
public class HTTPPublisher {

    private static final Log log = LogFactory.getLog(HTTPPublisher.class);

    private final String publisherURL;

    public HTTPPublisher(String publisherURL) {
        this.publisherURL = publisherURL;
    }

    /**
     * This method will publish the {@link SMSData} as a JSON to the provided publisher URL.
     * @param smsData {@link SMSData} object
     */
    public void publish(SMSData smsData) throws PublisherException {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(smsData);
            HttpURLConnection connection = getHttpURLConnection(smsData, json);
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                if (log.isDebugEnabled()) {
                    log.debug("Successfully published the sms data to the: " + publisherURL);
                    log.debug("JSON data: " + json);
                }
            } else {
                log.warn("Error occurred while publishing the sms data to the: " + publisherURL);
            }
            connection.disconnect();
        } catch (JsonProcessingException e) {
            throw new PublisherException("Error while converting the SMSData object to JSON", e);
        } catch (ProtocolException e) {
            throw new PublisherException("Error while setting the request method to POST", e);
        } catch (MalformedURLException e) {
            throw new PublisherException("Error while creating the URL object", e);
        } catch (IOException e) {
            throw new PublisherException("Error while opening the connection", e);
        }
    }

    private HttpURLConnection getHttpURLConnection(SMSData smsData, String json) throws IOException {

        URL url = new URL(publisherURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        SMSMetadata smsMetadata = smsData.getSmsMetadata();
        if (smsMetadata.getKey() != null) {
            connection.setRequestProperty("key", smsMetadata.getKey());
        }
        if (smsMetadata.getSecret() != null) {
            connection.setRequestProperty("secret", smsMetadata.getSecret());
        }
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = json.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        return connection;
    }
}
