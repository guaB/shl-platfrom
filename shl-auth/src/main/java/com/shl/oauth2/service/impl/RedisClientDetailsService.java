package com.shl.oauth2.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.util.List;
import java.util.Objects;

/**
 * @description:
 *   将oauth_client_details表数据缓存到redis，这里做个缓存优化
 *   注意对oauth_client_details清空redis db部分数据的清空
 * @author: songhonglei
 * @date: 2019-12-03
 */
@Slf4j
public class RedisClientDetailsService extends JdbcClientDetailsService {

    private RedisTemplate<String, Object> redisTemplate;

    public RedisClientDetailsService(DataSource dataSource) {
        super(dataSource);
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
        //先从redis获取
        ClientDetails clientDetails = (ClientDetails)redisTemplate.opsForValue().get(clientRedisKey(clientId));
        if (Objects.isNull(clientDetails)){
            clientDetails = cacheAndGetClient(clientId);
        }
        return clientDetails;
    }

    @Override
    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
        super.updateClientDetails(clientDetails);
        cacheAndGetClient(clientDetails.getClientId());
    }

    @Override
    public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {
        super.updateClientSecret(clientId, secret);
        cacheAndGetClient(clientId);
    }
    @Override
    public void removeClientDetails(String clientId) {
        super.removeClientDetails(clientId);
        removeRedisCache(clientId);
    }

    private void removeRedisCache(String clientId) {
        redisTemplate.delete(clientId);
    }

    /**
     * 缓存client并返回client
     * @param clientId
     * @return
     */
    private ClientDetails cacheAndGetClient(String clientId) {

        ClientDetails clientDetails = null;
        try {
            clientDetails = super.loadClientByClientId(clientId);
            if (!Objects.isNull(clientDetails)){
                // 写入redis缓存
                redisTemplate.opsForValue().set(clientRedisKey(clientId), clientDetails);
                log.info("缓存clientId:{},{}", clientId, clientDetails);
            }
        }catch (NoSuchClientException e){
            log.error("clientId:{},{}", clientId, clientId);
        }catch (InvalidClientException e) {
            log.error("cacheAndGetClient-invalidClient:{}", clientId, e);
        }

        return clientDetails;
    }

    private String clientRedisKey(String clientId) {
        return SecurityConstants.CACHE_CLIENT_KEY.msg() + ":" + clientId;

    }

    /**
     * 将oauth_client_details全表刷入redis
     */
    public void loadAllClientToCache() {
        List<ClientDetails> clientDetails = super.listClientDetails();
        if (CollectionUtils.isEmpty(clientDetails)) {
            log.error("oauth_client_details表数据为空，请检查");
            return;
        }
        clientDetails.parallelStream().forEach(client -> redisTemplate.opsForValue().set(clientRedisKey(client.getClientId()), client));
    }
}
