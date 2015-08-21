package com.hortonworks.iotas.webservice;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hortonworks.iotas.model.IotasMessage;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.utils.ZkUtils;
import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.collection.JavaConversions;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/api/v1/queue")
@Produces(MediaType.APPLICATION_JSON)
public class FeedResource {
    private static final Logger LOG = LoggerFactory.getLogger(FeedResource.class);
    private Producer producer;
    private ZkClient zkClient;

    public FeedResource(Producer producer, ZkClient zkClient){
        this.producer = producer;
        this.zkClient = zkClient;
    }


    @POST
    @Path("{feed}")
    public void publish(IotasMessage message, @PathParam("feed") String feed){
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(message);
            producer.send(new KeyedMessage<String, String>(feed, json));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @GET
    public Object listFeeds(){
        JavaConversions.JListWrapper topics =
                (JavaConversions.JListWrapper)
                        ZkUtils.getChildrenParentMayNotExist(this.zkClient, ZkUtils.BrokerTopicsPath());
        return topics.underlying();
    }
}
